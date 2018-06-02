package com.cchl.dao;

import com.cchl.entity.OpenReport;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface OpenReportMapper {

    int insert(OpenReport record);

    List<OpenReport> selectAll();

    int isExist(Integer paperId);

    int updateFilePath(@Param(value = "paperId") Integer paperId, @Param(value = "filePath") String filePath);
}