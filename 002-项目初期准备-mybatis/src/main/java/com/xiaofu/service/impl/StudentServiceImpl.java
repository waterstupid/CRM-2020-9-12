package com.xiaofu.service.impl;

import com.xiaofu.dao.StudentDao;
import com.xiaofu.bean.Student;
import com.xiaofu.service.StudentService;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    private StudentDao studentDao;

    @Override
    public List<Student> selectAll() {
        List<Student> list = studentDao.selectAll();
        return list;
    }
}
