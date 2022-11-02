import * as fs from "fs-extra";
import * as path from "path";
import * as xlsx from "xlsx";
//取excel路径
let argv = process.argv;
let excelRoot = argv[2];
//测试路径
excelRoot = "F:/workspace/my/SAO-word-game/excel";

let stringArray = [];

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
        await parse(xlsxFile);
    }
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
                jsonObj[id] = {};
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
    console.log(JSON.stringify(jsonObj));
}

function getTypeDefaultValue(curType: string) {
    switch (curType) {
        case "string":
            return "";
        case "int":
        case "long":
            return 0;
        case "string[]":
        case "int[]":
        case "long[]":
        case "string[][]":
        case "int[][]":
        case "long[][]":
            return [];
    }
}

function getTypeValue(curType: string, v: any) {
    let value = getValue(v);
    switch (curType) {
        case "string":
        case "int":
        case "long":
            return value;
        case "string[]":
        case "int[]":
        case "long[]":
            return value.split("|");
        case "string[][]":
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

/**
 * 获取xlsx文件的名称
 * @param xlsxFile 
 * @returns 
 */
function getXlsxName(xlsxFile: string) {
    return xlsxFile.split(".")[0].split("_")[1];
}

main();