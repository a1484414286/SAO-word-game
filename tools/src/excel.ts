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
    let sheetRang = calRange(ref, xlsxFile);
    console.log(sheetRang);
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