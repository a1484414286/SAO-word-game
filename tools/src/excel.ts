import * as fs from "fs-extra";
import * as path from "path";
import * as xlsx from "xlsx";
//取excel路径
let excelRoot = "";
let codeRoot = "";
let jsonRoot = "";
//测试路径
// excelRoot = "F:/workspace/my/SAO-word-game/excel";
// codeRoot = "F:/workspace/my/SAO-word-game/demo/src/main/java/com/sao/JsonModel";
// jsonRoot = "F:/workspace/my/SAO-word-game/demo/src/main/resources/json";
//正式路径
excelRoot = path.join(process.cwd(), "excel");
codeRoot = path.join(process.cwd(), "demo/src/main/java/com/sao/JsonModel");
jsonRoot = path.join(process.cwd(), "demo/src/main/resources/json");


if (!fs.existsSync(codeRoot)) {
    fs.mkdirsSync(codeRoot);
}
if (!fs.existsSync(jsonRoot)) {
    fs.mkdirsSync(jsonRoot);
}

let stringArray = [];

let loadAllJsonModelTpl = `package com.sao.JsonModel;

import java.io.InputStream;
public class LoadAllJsonModel {
    public static void load() {
`;

/** 工作表范围区间 */
interface I_SheetRange {
    colStart: number;
    colEnd: number;
    rowStart: number;
    rowEnd: number;
}

/** 工作表头部信息 */
interface I_SheetHead {
    /** 使用的col区间 */
    uses: number[];
    /** 注释数组 */
    comments: string[];
    /** 字段名数组 */
    fields: string[];
    /** 类型数组 */
    types: string[];
}

async function main() {
    let xlsxFiles = fs.readdirSync(excelRoot);
    for (let i = 0, l_i = xlsxFiles.length; i < l_i; i++) {
        let xlsxFile = xlsxFiles[i];
        if (path.extname(xlsxFile) != ".xlsx" || xlsxFile.indexOf("~") >= 0) {
            //不是配置表不处理
            continue;
        }
        console.log(`【${xlsxFile}】正在解析中...`);
        await parse(xlsxFile);
    }
    loadAllJsonModelTpl += `\t}\n`;
    loadAllJsonModelTpl += `}\n`;
    // console.log(loadAllJsonModelTpl);
    fs.writeFileSync(path.join(codeRoot, "LoadAllJsonModel.java"), loadAllJsonModelTpl);

    console.log('请输入任意键退出...');
    process.stdin.setRawMode(true);
    process.stdin.resume();
    process.stdin.on('data', process.exit.bind(process, 0));
}

async function parse(xlsxFile: string) {
    let xlsxPath = path.join(excelRoot, xlsxFile);
    let workbook = xlsx.readFile(xlsxPath);
    let sheets = workbook.Sheets;
    //这边只取第一张表
    let sheet = sheets[workbook.SheetNames[0]];
    let ref = sheet["!ref"];
    let sheetRange = calRange(ref, xlsxFile);
    // console.log(sheetRange);
    let sheetHead = calHead(sheet, sheetRange, xlsxFile);
    // console.log(sheetHead);
    parseCell(xlsxFile, sheet, sheetRange, sheetHead);
}

function parseCell(xlsxFile: string, sheet: xlsx.WorkSheet, sheetRang: I_SheetRange, sheetHead: I_SheetHead) {
    let uses = sheetHead.uses;
    let types = sheetHead.types;
    let fields = sheetHead.fields;
    let comments = sheetHead.comments;
    let jsonObj: { [key: string]: any } = {};
    for (let row = 4; row <= sheetRang.rowEnd; row++) {
        let id: string;
        for (let i = 0, l_i = uses.length; i < l_i; i++) {
            let col = uses[i];
            let curType = types[i];
            let str = numToString(col) + String(row);
            let v: xlsx.CellObject = sheet[str];
            let curField = fields[i];
            if (col == 1) {
                //第一个行
                if (!isValid(v)) {
                    //没有id 不取此行数据
                    break;
                } else {
                    id = getValue(v);
                    if (jsonObj[id]) {
                        console.log(`【${xlsxFile}】【id=${id}】重复`);
                        process.exit(1);
                    }
                }
                jsonObj[id] = { id: id };
                continue;
            }
            let value: any;
            if (!isValid(v)) {
                //无值
                // value = getTypeDefaultValue(curType);
                // jsonObj[id][curField] = value;
                continue;
            } else {
                value = getTypeValue(curType, v);
                jsonObj[id][curField] = value;
            }
        }
    }
    // console.log(JSON.stringify(jsonObj, null, " "));
    let modelName = getXlsxName(xlsxFile);
    let modelContent = getXlsxComment(xlsxFile);
    //生成代码文件
    let content = "";
    //1.结构类文件
    content = "";
    content += `package com.sao.JsonModel;\n\n`;
    content += `/** ${modelContent} */\n`;
    content += `public class ${modelName}JsonModel {\n`;
    for (let i = 0, l_i = fields.length; i < l_i; i++) {
        content += `\t/** ${comments[i]} */\n`;
        content += `\tpublic ${types[i]} ${fields[i]};\n\n`;
    }
    content += `}\n`;
    // console.log(content);
    fs.writeFileSync(path.join(codeRoot, `${modelName}JsonModel.java`), content);
    //2.工具类文件
    let idType = types[0];
    let idJavaType = getJavaKeyType(idType);
    content = "";
    content += `package com.sao.JsonModel;\n`;
    content += `\n`;
    content += `import java.util.HashMap;\n`;
    content += `import java.util.Set;\n`;
    content += `\n`;
    content += `import com.alibaba.fastjson2.JSONObject;\n`;
    content += `\n`;
    content += `public class ${modelName}JsonUtil {\n`;
    content += `\tprivate static HashMap<${idJavaType}, ${modelName}JsonModel> map = new HashMap<>();\n`;
    content += `\n`;
    content += `\tpublic static void loadJson(String text) {\n`;
    content += `\t\tmap.clear();\n`;
    content += `\t\tJSONObject object = JSONObject.parseObject(text);\n`;
    content += `\t\tSet<String> set = object.keySet();\n`;
    content += `\t\tfor (String key : set) {\n`;
    content += `\t\t\tJSONObject row = object.getJSONObject(key);\n`;
    if (idType == "int") {
        content += `\t\t\tmap.put(Integer.parseInt(key), row.to(${modelName}JsonModel.class));\n`;
    } else if (idType == "long") {
        content += `\t\t\tmap.put(Long.parseLong(key), row.to(${modelName}JsonModel.class));\n`;
    } else if (idType == "String") {
        content += `\t\t\tmap.put(key, row.to(${modelName}JsonModel.class));\n`;
    } else {
        console.error(`【${xlsxFile}】id类型未支持`);
        process.exit(1);
    }
    content += `\t\t}\n`;
    content += `\t}\n`;
    content += `\n`;
    content += `\tpublic static ${modelName}JsonModel getModel(${idJavaType} id) {\n`;
    content += `\t\treturn map.get(id);\n`;
    content += `\t}\n`;
    content += `}\n`;
    fs.writeFileSync(path.join(codeRoot, `${modelName}JsonUtil.java`), content);
    //3.LoadAllJsonModel
    loadAllJsonModelTpl += `\t\tInputStream stream${modelName} = LoadAllJsonModel.class.getClassLoader().getResourceAsStream("json/${modelName}.json");\n`;
    loadAllJsonModelTpl += `\t\ttry {\n`;
    loadAllJsonModelTpl += `\t\t\t${modelName}JsonUtil.loadJson(new String(stream${modelName}.readAllBytes()));\n`;
    loadAllJsonModelTpl += `\t\t} catch (Exception e) {\n`;
    loadAllJsonModelTpl += `\t\t\tSystem.err.println(e);\n`;
    loadAllJsonModelTpl += `\t\t}\n`;
    //4.生成对应的json
    fs.writeFileSync(path.join(jsonRoot, `${modelName}.json`), JSON.stringify(jsonObj));
}

function getTypeValue(curType: string, v: any) {
    let value = getValue(v);
    switch (curType) {
        case "String":
        case "int":
        case "long":
            return value;
        case "String[]":
        case "int[]":
        case "long[]":
            return value.split("|");
        case "String[][]":
        case "int[][]":
        case "long[][]": {
            let result: string[][] = [];
            let arr1 = value.split(";");
            for (let i = 0, l_i = arr1.length; i < l_i; i++) {
                result.push(arr1[i].split("|"));
            }
            return result;
        }
    }
}

function getValue(v: xlsx.CellObject) {
    return v.w.trim();
}

function getJavaKeyType(idType: string) {
    switch (idType) {
        case "String":
            return "String";
        case "int":
            return "Integer";
        case "long":
            return "Long";
    }
}

function calHead(sheet: xlsx.WorkSheet, range: I_SheetRange, xlsxFile: string) {
    let uses: number[] = [];
    for (let i = range.colStart; i <= range.colEnd; i++) {
        //取前三个行 必须有值
        let str1 = numToString(i) + "1";
        let str2 = numToString(i) + "2";
        let str3 = numToString(i) + "3";
        let v1 = sheet[str1] as xlsx.CellObject;
        let v2 = sheet[str2] as xlsx.CellObject;
        let v3 = sheet[str3] as xlsx.CellObject;
        if (isValid(v1) && isValid(v2) && isValid(v3)) {
            uses.push(i);
        }
    }
    //取注释
    let comments: string[] = [];
    //取字段名
    let fields: string[] = [];
    //取类型
    let types: string[] = [];
    for (let i = 0, l_i = uses.length; i < l_i; i++) {
        let pos = uses[i];
        {
            //注释
            let str = numToString(pos) + "1";
            let v = sheet[str] as xlsx.CellObject;
            comments.push(v.w.trim());
        }
        {
            //字段名
            let str = numToString(pos) + "2";
            let v = sheet[str] as xlsx.CellObject;
            let field = v.w.trim();
            if (fields.indexOf(field) >= 0) {
                console.error(`【${xlsxFile}】【${field}】字段名重复!`);
                process.exit(1);
            }
            fields.push(field);
        }
        {
            //类型
            let str = numToString(pos) + "3";
            let v = sheet[str] as xlsx.CellObject;
            types.push(v.w.trim());
        }
    }
    return {
        uses: uses,
        comments: comments,
        fields: fields,
        types: types,
    } as I_SheetHead;
}

function isValid(v: xlsx.CellObject) {
    return v && v.w && v.w.trim();
}

function calRange(ref: string, xlsxFile: string) {
    if (!ref) {
        console.error(`【${xlsxFile}】发生错误请检查!`);
        process.exit(1);
    }
    let refArr = ref.split(":");
    let r0 = refArr[0];
    let r1 = refArr[1];
    let colStart = "";
    let colEnd = "";
    let rowStart = "";
    let rowEnd = "";
    for (let i = 0, l_i = r0.length; i < l_i; i++) {
        let code = r0.charCodeAt(i);
        if (code >= 65 && code <= 90) {
            //字母
            colStart += r0[i];
        } else {
            //数字
            rowStart += r0[i];
        }
    }
    for (let i = 0, l_i = r1.length; i < l_i; i++) {
        let code = r1.charCodeAt(i);
        if (code >= 65 && code <= 90) {
            //字母
            colEnd += r1[i];
        } else {
            //数字
            rowEnd += r1[i];
        }
    }
    return {
        colStart: stringTonum(colStart),
        colEnd: stringTonum(colEnd),
        rowStart: parseInt(rowStart),
        rowEnd: parseInt(rowEnd),
    } as I_SheetRange;
}

function numToString(num: number) {
    stringArray.length = 0;
    numToStringAction(num);
    return stringArray.reverse().join("");
}

function numToStringAction(nnum: number) {
    let num = nnum - 1;
    let a = parseInt(num / 26 as any);
    let b = num % 26;
    stringArray.push(String.fromCharCode(64 + parseInt(b + 1 as any)));
    if (a > 0) {
        numToStringAction(a);
    }
}

function stringTonum(a: string) {
    let str = a.toLowerCase().split("");
    let al = str.length;
    let numout = 0;
    let charnum = 0;
    for (let i = 0; i < al; i++) {
        charnum = getCharNumber(str[i]);
        numout += charnum * Math.pow(26, al - i - 1);
    };
    return numout;
}

function getCharNumber(charx: any) {
    return charx.charCodeAt() - 96;
}

function getXlsxName(xlsxFile: string) {
    return xlsxFile.split(".")[0].split("_")[1];
}

function getXlsxComment(xlsxFile: string) {
    return xlsxFile.split(".")[0].split("_")[0];
}

main();