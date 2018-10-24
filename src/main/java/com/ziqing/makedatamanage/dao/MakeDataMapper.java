package com.ziqing.makedatamanage.dao;

import com.ziqing.makedatamanage.pojo.makedata;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Component
@Repository
public interface MakeDataMapper extends CommonMapper<makedata> {


    /**
     * 添加预约信息 (2018-10-23)
     *
     * @param makedata 预约信息对象
     * @return
     * @throws Exception
     */
    Integer saveMakeInfo(makedata makedata) throws Exception;

    // 按照时间区间 查询
    List<makedata> getAllMakeDataInfoByDate(@Param("beginDate") String beginDate) throws Exception;

    //excel导出
    List<makedata> exprotExcel() throws Exception;


}
