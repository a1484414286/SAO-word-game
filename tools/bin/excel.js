"use strict";
var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
var __generator = (this && this.__generator) || function (thisArg, body) {
    var _ = { label: 0, sent: function() { if (t[0] & 1) throw t[1]; return t[1]; }, trys: [], ops: [] }, f, y, t, g;
    return g = { next: verb(0), "throw": verb(1), "return": verb(2) }, typeof Symbol === "function" && (g[Symbol.iterator] = function() { return this; }), g;
    function verb(n) { return function (v) { return step([n, v]); }; }
    function step(op) {
        if (f) throw new TypeError("Generator is already executing.");
        while (_) try {
            if (f = 1, y && (t = op[0] & 2 ? y["return"] : op[0] ? y["throw"] || ((t = y["return"]) && t.call(y), 0) : y.next) && !(t = t.call(y, op[1])).done) return t;
            if (y = 0, t) op = [op[0] & 2, t.value];
            switch (op[0]) {
                case 0: case 1: t = op; break;
                case 4: _.label++; return { value: op[1], done: false };
                case 5: _.label++; y = op[1]; op = [0]; continue;
                case 7: op = _.ops.pop(); _.trys.pop(); continue;
                default:
                    if (!(t = _.trys, t = t.length > 0 && t[t.length - 1]) && (op[0] === 6 || op[0] === 2)) { _ = 0; continue; }
                    if (op[0] === 3 && (!t || (op[1] > t[0] && op[1] < t[3]))) { _.label = op[1]; break; }
                    if (op[0] === 6 && _.label < t[1]) { _.label = t[1]; t = op; break; }
                    if (t && _.label < t[2]) { _.label = t[2]; _.ops.push(op); break; }
                    if (t[2]) _.ops.pop();
                    _.trys.pop(); continue;
            }
            op = body.call(thisArg, _);
        } catch (e) { op = [6, e]; y = 0; } finally { f = t = 0; }
        if (op[0] & 5) throw op[1]; return { value: op[0] ? op[1] : void 0, done: true };
    }
};
exports.__esModule = true;
var fs = require("fs-extra");
var path = require("path");
var xlsx = require("xlsx");
//取excel路径
var excelRoot = "";
var codeRoot = "";
var jsonRoot = "";
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
var stringArray = [];
var loadAllJsonModelTpl = "package com.sao.JsonModel;\n\nimport java.io.InputStream;\npublic class LoadAllJsonModel {\n    public static void load() {\n";
function main() {
    return __awaiter(this, void 0, void 0, function () {
        var xlsxFiles, i, l_i, xlsxFile;
        return __generator(this, function (_a) {
            switch (_a.label) {
                case 0:
                    xlsxFiles = fs.readdirSync(excelRoot);
                    i = 0, l_i = xlsxFiles.length;
                    _a.label = 1;
                case 1:
                    if (!(i < l_i)) return [3 /*break*/, 4];
                    xlsxFile = xlsxFiles[i];
                    if (path.extname(xlsxFile) != ".xlsx" || xlsxFile.indexOf("~") >= 0) {
                        //不是配置表不处理
                        return [3 /*break*/, 3];
                    }
                    console.log("\u3010".concat(xlsxFile, "\u3011\u6B63\u5728\u89E3\u6790\u4E2D..."));
                    return [4 /*yield*/, parse(xlsxFile)];
                case 2:
                    _a.sent();
                    _a.label = 3;
                case 3:
                    i++;
                    return [3 /*break*/, 1];
                case 4:
                    loadAllJsonModelTpl += "\t}\n";
                    loadAllJsonModelTpl += "}\n";
                    // console.log(loadAllJsonModelTpl);
                    fs.writeFileSync(path.join(codeRoot, "LoadAllJsonModel.java"), loadAllJsonModelTpl);
                    console.log('请输入任意键退出...');
                    process.stdin.setRawMode(true);
                    process.stdin.resume();
                    process.stdin.on('data', process.exit.bind(process, 0));
                    return [2 /*return*/];
            }
        });
    });
}
function parse(xlsxFile) {
    return __awaiter(this, void 0, void 0, function () {
        var xlsxPath, workbook, sheets, sheet, ref, sheetRange, sheetHead;
        return __generator(this, function (_a) {
            xlsxPath = path.join(excelRoot, xlsxFile);
            workbook = xlsx.readFile(xlsxPath);
            sheets = workbook.Sheets;
            sheet = sheets[workbook.SheetNames[0]];
            ref = sheet["!ref"];
            sheetRange = calRange(ref, xlsxFile);
            sheetHead = calHead(sheet, sheetRange, xlsxFile);
            // console.log(sheetHead);
            parseCell(xlsxFile, sheet, sheetRange, sheetHead);
            return [2 /*return*/];
        });
    });
}
function parseCell(xlsxFile, sheet, sheetRang, sheetHead) {
    var uses = sheetHead.uses;
    var types = sheetHead.types;
    var fields = sheetHead.fields;
    var comments = sheetHead.comments;
    var jsonObj = {};
    for (var row = 4; row <= sheetRang.rowEnd; row++) {
        var id = void 0;
        for (var i = 0, l_i = uses.length; i < l_i; i++) {
            var col = uses[i];
            var curType = types[i];
            var str = numToString(col) + String(row);
            var v = sheet[str];
            var curField = fields[i];
            if (col == 1) {
                //第一个行
                if (!isValid(v)) {
                    //没有id 不取此行数据
                    break;
                }
                else {
                    id = getValue(v);
                    if (jsonObj[id]) {
                        console.log("\u3010".concat(xlsxFile, "\u3011\u3010id=").concat(id, "\u3011\u91CD\u590D"));
                        process.exit(1);
                    }
                }
                jsonObj[id] = { id: id };
                continue;
            }
            var value = void 0;
            if (!isValid(v)) {
                //无值
                // value = getTypeDefaultValue(curType);
                // jsonObj[id][curField] = value;
                continue;
            }
            else {
                value = getTypeValue(curType, v);
                jsonObj[id][curField] = value;
            }
        }
    }
    // console.log(JSON.stringify(jsonObj, null, " "));
    var modelName = getXlsxName(xlsxFile);
    var modelContent = getXlsxComment(xlsxFile);
    //生成代码文件
    var content = "";
    //1.结构类文件
    content = "";
    content += "package com.sao.JsonModel;\n\n";
    content += "/** ".concat(modelContent, " */\n");
    content += "public class ".concat(modelName, "JsonModel {\n");
    for (var i = 0, l_i = fields.length; i < l_i; i++) {
        content += "\t/** ".concat(comments[i], " */\n");
        content += "\tpublic ".concat(types[i], " ").concat(fields[i], ";\n\n");
    }
    content += "}\n";
    // console.log(content);
    fs.writeFileSync(path.join(codeRoot, "".concat(modelName, "JsonModel.java")), content);
    //2.工具类文件
    var idType = types[0];
    var idJavaType = getJavaKeyType(idType);
    content = "";
    content += "package com.sao.JsonModel;\n";
    content += "\n";
    content += "import java.util.HashMap;\n";
    content += "import java.util.Set;\n";
    content += "\n";
    content += "import com.alibaba.fastjson2.JSONObject;\n";
    content += "\n";
    content += "public class ".concat(modelName, "JsonUtil {\n");
    content += "\tprivate static HashMap<".concat(idJavaType, ", ").concat(modelName, "JsonModel> map = new HashMap<>();\n");
    content += "\n";
    content += "\tpublic static void loadJson(String text) {\n";
    content += "\t\tmap.clear();\n";
    content += "\t\tJSONObject object = JSONObject.parseObject(text);\n";
    content += "\t\tSet<String> set = object.keySet();\n";
    content += "\t\tfor (String key : set) {\n";
    content += "\t\t\tJSONObject row = object.getJSONObject(key);\n";
    if (idType == "int") {
        content += "\t\t\tmap.put(Integer.parseInt(key), row.to(".concat(modelName, "JsonModel.class));\n");
    }
    else if (idType == "long") {
        content += "\t\t\tmap.put(Long.parseLong(key), row.to(".concat(modelName, "JsonModel.class));\n");
    }
    else if (idType == "String") {
        content += "\t\t\tmap.put(key, row.to(".concat(modelName, "JsonModel.class));\n");
    }
    else {
        console.error("\u3010".concat(xlsxFile, "\u3011id\u7C7B\u578B\u672A\u652F\u6301"));
        process.exit(1);
    }
    content += "\t\t}\n";
    content += "\t}\n";
    content += "\n";
    content += "\tpublic static ".concat(modelName, "JsonModel getModel(").concat(idJavaType, " id) {\n");
    content += "\t\treturn map.get(id);\n";
    content += "\t}\n";
    content += "\tpublic static HashMap<".concat(idJavaType, ", ").concat(modelName, "JsonModel> getMap() {\n");
    content += "\t\treturn map;\n";
    content += "\t}\n";
    content += "}\n";
    fs.writeFileSync(path.join(codeRoot, "".concat(modelName, "JsonUtil.java")), content);
    //3.LoadAllJsonModel
    loadAllJsonModelTpl += "\t\tInputStream stream".concat(modelName, " = LoadAllJsonModel.class.getClassLoader().getResourceAsStream(\"json/").concat(modelName, ".json\");\n");
    loadAllJsonModelTpl += "\t\ttry {\n";
    loadAllJsonModelTpl += "\t\t\t".concat(modelName, "JsonUtil.loadJson(new String(stream").concat(modelName, ".readAllBytes(), \"UTF-8\"));\n");
    loadAllJsonModelTpl += "\t\t} catch (Exception e) {\n";
    loadAllJsonModelTpl += "\t\t\tSystem.err.println(e);\n";
    loadAllJsonModelTpl += "\t\t}\n";
    //4.生成对应的json
    fs.writeFileSync(path.join(jsonRoot, "".concat(modelName, ".json")), JSON.stringify(jsonObj));
}
function getTypeValue(curType, v) {
    var value = getValue(v);
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
            var result = [];
            var arr1 = value.split(";");
            for (var i = 0, l_i = arr1.length; i < l_i; i++) {
                result.push(arr1[i].split("|"));
            }
            return result;
        }
    }
}
function getValue(v) {
    return v.w.trim();
}
function getJavaKeyType(idType) {
    switch (idType) {
        case "String":
            return "String";
        case "int":
            return "Integer";
        case "long":
            return "Long";
    }
}
function calHead(sheet, range, xlsxFile) {
    var uses = [];
    for (var i = range.colStart; i <= range.colEnd; i++) {
        //取前三个行 必须有值
        var str1 = numToString(i) + "1";
        var str2 = numToString(i) + "2";
        var str3 = numToString(i) + "3";
        var v1 = sheet[str1];
        var v2 = sheet[str2];
        var v3 = sheet[str3];
        if (isValid(v1) && isValid(v2) && isValid(v3)) {
            uses.push(i);
        }
    }
    //取注释
    var comments = [];
    //取字段名
    var fields = [];
    //取类型
    var types = [];
    for (var i = 0, l_i = uses.length; i < l_i; i++) {
        var pos = uses[i];
        {
            //注释
            var str = numToString(pos) + "1";
            var v = sheet[str];
            comments.push(v.w.trim());
        }
        {
            //字段名
            var str = numToString(pos) + "2";
            var v = sheet[str];
            var field = v.w.trim();
            if (fields.indexOf(field) >= 0) {
                console.error("\u3010".concat(xlsxFile, "\u3011\u3010").concat(field, "\u3011\u5B57\u6BB5\u540D\u91CD\u590D!"));
                process.exit(1);
            }
            fields.push(field);
        }
        {
            //类型
            var str = numToString(pos) + "3";
            var v = sheet[str];
            types.push(v.w.trim());
        }
    }
    return {
        uses: uses,
        comments: comments,
        fields: fields,
        types: types
    };
}
function isValid(v) {
    return v && v.w && v.w.trim();
}
function calRange(ref, xlsxFile) {
    if (!ref) {
        console.error("\u3010".concat(xlsxFile, "\u3011\u53D1\u751F\u9519\u8BEF\u8BF7\u68C0\u67E5!"));
        process.exit(1);
    }
    var refArr = ref.split(":");
    var r0 = refArr[0];
    var r1 = refArr[1];
    var colStart = "";
    var colEnd = "";
    var rowStart = "";
    var rowEnd = "";
    for (var i = 0, l_i = r0.length; i < l_i; i++) {
        var code = r0.charCodeAt(i);
        if (code >= 65 && code <= 90) {
            //字母
            colStart += r0[i];
        }
        else {
            //数字
            rowStart += r0[i];
        }
    }
    for (var i = 0, l_i = r1.length; i < l_i; i++) {
        var code = r1.charCodeAt(i);
        if (code >= 65 && code <= 90) {
            //字母
            colEnd += r1[i];
        }
        else {
            //数字
            rowEnd += r1[i];
        }
    }
    return {
        colStart: stringTonum(colStart),
        colEnd: stringTonum(colEnd),
        rowStart: parseInt(rowStart),
        rowEnd: parseInt(rowEnd)
    };
}
function numToString(num) {
    stringArray.length = 0;
    numToStringAction(num);
    return stringArray.reverse().join("");
}
function numToStringAction(nnum) {
    var num = nnum - 1;
    var a = parseInt(num / 26);
    var b = num % 26;
    stringArray.push(String.fromCharCode(64 + parseInt(b + 1)));
    if (a > 0) {
        numToStringAction(a);
    }
}
function stringTonum(a) {
    var str = a.toLowerCase().split("");
    var al = str.length;
    var numout = 0;
    var charnum = 0;
    for (var i = 0; i < al; i++) {
        charnum = getCharNumber(str[i]);
        numout += charnum * Math.pow(26, al - i - 1);
    }
    ;
    return numout;
}
function getCharNumber(charx) {
    return charx.charCodeAt() - 96;
}
function getXlsxName(xlsxFile) {
    return xlsxFile.split(".")[0].split("_")[1];
}
function getXlsxComment(xlsxFile) {
    return xlsxFile.split(".")[0].split("_")[0];
}
main();
