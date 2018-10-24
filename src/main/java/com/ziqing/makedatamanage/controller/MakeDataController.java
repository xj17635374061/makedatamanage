package com.ziqing.makedatamanage.controller;


import com.github.pagehelper.PageInfo;
import com.ziqing.makedatamanage.pojo.makedata;
import com.ziqing.makedatamanage.service.MakeDataService;
import com.ziqing.makedatamanage.utils.ReponseResult;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

import static com.ziqing.makedatamanage.utils.ExcelUtil.buildExcelDocument;
import static com.ziqing.makedatamanage.utils.ExcelUtil.buildExcelFile;
import static com.ziqing.makedatamanage.utils.ExcelUtil.createTitle;

@Controller
@RequestMapping("MakeDataController")
@CrossOrigin("*")
public class MakeDataController {
    // 日志对象
    private Logger logger = LoggerFactory.getLogger(MakeDataController.class);

    /**
     * 进入前台页面
     *
     * @return
     */
    @RequestMapping("Index.html")
    public String EnterIndex() {

        return "index";
    }

    /**
     * 进入后台页面
     *
     * @return
     */
    @RequestMapping("adminIndex.html")
    public String EnterAdminIndex() {

        return "adminIndex";
    }


    @Autowired
    private MakeDataService makeDataService;


    /**
     * 添加预约信息
     *
     * @param name
     * @param telnum
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "saveMakeInfo.do", method = RequestMethod.POST)
    @CrossOrigin(origins = "http://192.168.1.47:8080", maxAge = 3600)
    public ReponseResult saveMakeInfo(@RequestParam("name") String name, @RequestParam("telnum") String telnum) {
        ReponseResult<String> data;
        try {
            makedata makedata = new makedata();
            makedata.setName(name);
            makedata.setTelnum(telnum);
            Date date = new Date();
            makedata.setNowdate(date);
            int result = makeDataService.saveMakeInfo(makedata);
            if (result > -1) {
                data = ReponseResult.ok("1", "添加预约信息成功！");
                logger.info(" method:saveMakeInfo.do  添加预约信息成功！");

            } else {
                data = ReponseResult.ok("0", "添加预约信息失败！");
                logger.info(" method:saveMakeInfo.do  添加预约信息失败！");
            }
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(" method:saveMakeInfo.do  添加预约信息失败，系统出现异常！");
            ReponseResult<Object> err = ReponseResult.err("系统出现异常！");
            return err;
        }

    }

    /**
     * 根据时间区间查询预约数据集合
     * @param beginDate 起始时间
     * @param endDate   结束时间
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getMakeDataList.do", method = RequestMethod.GET)
    public ReponseResult getMakeDataList(@RequestParam("beginDate") String beginDate) {

        try {
            List<makedata> result = makeDataService.getMakeDataList(beginDate);
            ReponseResult<List> date;
            if (result!=null){
                date= ReponseResult.ok(result,"根据时间区间查询预约数据集合成功");
                logger.info(" method:getMakeDataList.do  根据时间区间查询预约数据集合成功！");

            }else{
                date= ReponseResult.ok("根据时间区间查询预约数据集合失败！");
                logger.info(" method:getMakeDataList.do  根据时间区间查询预约数据集合失败！");
            }
            return date;
        }catch (Exception e){
            logger.error(" method:getMakeDataList.do  根据时间区间查询预约数据集合失败，系统出现异常！");
            e.printStackTrace();
            ReponseResult<Object> err = ReponseResult.err("系统出现异常！");
            return err;
        }


    }

    @ResponseBody
    @RequestMapping("/exprotExcel")
    public void exprotExcel(String fileName, HttpServletResponse response) {
        List<makedata> makeDataInfoList = null;//得到返回的数据集合
        try {
            makeDataInfoList = makeDataService.exprotExcel();
        } catch (Exception e) {
            e.printStackTrace();
        }
        HSSFWorkbook workbook = new HSSFWorkbook();//创建一个表格
        HSSFSheet sheet = workbook.createSheet("统计表");//给这个表格创建一个单元格
        createTitle(workbook, sheet);

        // 设置内容 列样式
        HSSFCellStyle style = workbook.createCellStyle();

        // 设置字体
        HSSFFont font = workbook.createFont();
        font.setFontName("微软雅黑");
        // 相当于 12px，1 px = 20 short
        font.setFontHeight((short) 240);
        style.setFont(font);

        // 设置 水平、垂直居中
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

        for (int i = 0; i < makeDataInfoList.size(); i++) {
            makedata makeDataInfo = makeDataInfoList.get(i);
            HSSFRow row = sheet.createRow(i + 1);//创建行，除去表头，应从第二行开始
            row.setHeight((short) 600);//设置行高
            HSSFCell cell;
            cell = row.createCell(0); //第一列
            cell.setCellStyle(style);                   //设置字体样式
            cell.setCellValue(makeDataInfo.getId());    //赋值
            cell = row.createCell(1);//第二列
            cell.setCellStyle(style);
            cell.setCellValue(makeDataInfo.getId());
            cell = row.createCell(2);//第三列
            cell.setCellStyle(style);
            cell.setCellValue(makeDataInfo.getId());
            cell = row.createCell(3);//第四列
            cell.setCellStyle(style);
            cell.setCellValue(makeDataInfo.getId());
        }

        try {
            buildExcelFile(fileName, workbook);
            buildExcelDocument(fileName, workbook, response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
