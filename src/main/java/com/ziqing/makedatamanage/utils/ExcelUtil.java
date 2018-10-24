package com.ziqing.makedatamanage.utils;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Font;

import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

public class ExcelUtil {

    // 创建表头
    public static void createTitle(HSSFWorkbook workbook, HSSFSheet sheet) {
        HSSFRow row = sheet.createRow(0);
        //设置为居中加粗
        HSSFCellStyle style = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontName("微软雅黑");
        // 1px = 20 short，此处相当于 14px
        font.setFontHeight((short) 280);
        font.setColor(Font.COLOR_RED);
        // 表头 水平居中
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setFont(font);

        HSSFCell cell;

        // 表头列数 0 开始 属于第一列
        cell = row.createCell(0);
        // 表头名称
        cell.setCellValue("ID");
        // 表头样式
        cell.setCellStyle(style);

        cell = row.createCell(1);
        cell.setCellValue("姓名");
        cell.setCellStyle(style);

        cell = row.createCell(2);
        cell.setCellValue("电话");
        cell.setCellStyle(style);

        cell = row.createCell(3);
        cell.setCellValue("日期");
        cell.setCellStyle(style);

    }

    /**
     * 生成 Excel 文件
     *
     * @param filename
     * @param workbook
     * @throws Exception
     */
    public static void buildExcelFile(String filename, HSSFWorkbook workbook) throws Exception {
        FileOutputStream fos = new FileOutputStream(filename);
        workbook.write(fos);
        fos.flush();
        fos.close();
    }


    /**
     * 往客户浏览器下载Excel
     *
     * @param filename
     * @param workbook
     * @param response
     * @throws Exception
     */
    public static void buildExcelDocument(String filename, HSSFWorkbook workbook, HttpServletResponse response) throws Exception {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment;filename=" +
                URLEncoder.encode(filename, "utf-8"));
        OutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }
}
