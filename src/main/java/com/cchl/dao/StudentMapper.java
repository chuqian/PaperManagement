package com.cchl.dao;

import com.cchl.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface StudentMapper {

    int insert(Student student);

    List<Student> selectAll();

    Student select();

    int loginCheck(@Param("id")Long id, @Param("password")String password);
}