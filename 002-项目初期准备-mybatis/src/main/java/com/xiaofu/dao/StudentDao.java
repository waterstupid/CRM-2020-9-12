package com.xiaofu.dao;

import com.xiaofu.bean.Student;
import com.xiaofu.vo.StudentAndClassRoom;

import java.util.List;
import java.util.Map;

public interface StudentDao {
    List<Student> selectAll();
    Student select1(String name);
    Student select2(String name);
    Student select3(String name);
    Student select4(int id);
    int insert5(Student student);
    int update6(Map<String,Object> map);
    int select7();
    Student select8(int id);
    List<Map<String,Object>> select9();
    List<Student> select10();
    List<Student> select11();
    /*Map<String,Map> select12();*/
    List<Student> select12(String name);
    List<Student> select13(Map<String,Object> map);
    List<Student> select14(Integer array[]);

    List<Map<String, Object>> select15();

    List<StudentAndClassRoom> select16();
}
