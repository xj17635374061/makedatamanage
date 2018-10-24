package com.ziqing.makedatamanage.service;

import com.ziqing.makedatamanage.pojo.makedata;

import java.util.List;


public interface MakeDataService {
    /**
     * 添加预约信息 (2018-10-23)
     * @param makedata  预约信息对象
     * @return
     * @throws Exception
     */
    Integer saveMakeInfo(makedata makedata) throws Exception;

    /**
     * 根据时间区间查询预约数据集合
     * @param beginDate 起始时间
     * @return
     */
    List<makedata> getMakeDataList(String beginDate)throws  Exception;

    /**
     * excel导出
     * @return
     */
    List<makedata> exprotExcel()throws  Exception;
}
