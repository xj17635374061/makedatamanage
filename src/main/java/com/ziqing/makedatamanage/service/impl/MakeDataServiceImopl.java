package com.ziqing.makedatamanage.service.impl;


import com.ziqing.makedatamanage.dao.MakeDataMapper;
import com.ziqing.makedatamanage.pojo.makedata;
import com.ziqing.makedatamanage.service.MakeDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MakeDataServiceImopl implements MakeDataService {

    @Autowired
    private MakeDataMapper makeDataMapper;
    /**
     * 添加预约信息 (2018-10-23)
     * @param makedata  预约信息对象
     * @return
     * @throws Exception
     */
    @Override
    public Integer saveMakeInfo(makedata makedata) throws Exception {
        return makeDataMapper.saveMakeInfo(makedata);
    }

    /**
     * 根据时间区间查询预约数据集合
     * @param beginDate 起始时间
     * @param endDate 结束时间
     * @return
     */
    @Override
    public List<makedata> getMakeDataList(String beginDate,String endDate) throws Exception {
        return makeDataMapper.getAllMakeDataInfoByDate(beginDate,endDate);
    }

    /**
     * 导出excel表格
     * @return
     */
    @Override
    public List<makedata> exprotExcel() throws Exception {
        return makeDataMapper.exprotExcel();
    }
}
