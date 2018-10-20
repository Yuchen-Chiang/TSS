package com.silver.tss.dao;

import com.silver.tss.domain.Status;
import com.silver.tss.domain.StatusExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StatusMapper {
    long countByExample(StatusExample example);

    int deleteByExample(StatusExample example);

    int insert(Status record);

    int insertSelective(Status record);

    List<Status> selectByExample(StatusExample example);

    int updateByExampleSelective(@Param("record") Status record, @Param("example") StatusExample example);

    int updateByExample(@Param("record") Status record, @Param("example") StatusExample example);
}