package com.xiaofu;

import com.xiaofu.bean.Student;
import com.xiaofu.dao.StudentDao;
import com.xiaofu.util.SqlSessionUtil;
import com.xiaofu.vo.StudentAndClassRoom;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Text03 {
    @Test
    public void text01(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
        Student student = studentDao.select1("jack");
        System.out.println(student);


    }
    @Test
    public void text02(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
        Student student = studentDao.select2("jack");
        System.out.println(student);


    }
    @Test
    public void text03(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
        Student student = studentDao.select3("jack");
        System.out.println(student);


    }
    @Test
    public void text04(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
        Student student = studentDao.select4(3);
        System.out.println(student);


    }
    @Test
    public void text05(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
        int i = studentDao.insert5(new Student(15,"xiaofu",8));
        sqlSession.commit();
        System.out.println(i);


    }
    @Test
    public void text06(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
        Map<String,Object> map=new HashMap<>();
        map.put("name","xiao");
        map.put("age",19);
        int i = studentDao.update6(map);
        sqlSession.commit();
        System.out.println(i);


    }

    @Test
    public void text07(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
        int i = studentDao.select7();
        System.out.println(i);


    }
    @Test
    public void text08(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
        Student student = studentDao.select8(1);
        System.out.println(student);


    }
    @Test
    public void text09(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
        List<Map<String, Object>> list = studentDao.select9();
        for (Map<String, Object> map : list) {
            Set<String> keySet = map.keySet();
            for (String key : keySet) {
                Object value = map.get(key);
                System.out.println(key+"--->:"+value);
            }
            System.out.println("===========");

        }


    }
    @Test
    public void text19(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
        List<Student> list = studentDao.select10();
        for (Student student : list) {
            System.out.println(student);
        }


    }
    @Test
    public void text11(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
        List<Student> list = studentDao.select11();
        for (Student student : list) {
            System.out.println(student);
        }


    }
 /*   @Test
    public void text12(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
        Map<String, Map> map = studentDao.select12();
        System.out.println(map);

    }*/
    @Test
    public void text12(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
        List<Student> list= studentDao.select12("a");
        System.out.println(list);

    }

    @Test
    public void text13(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
        Map<String,Object> map=new HashMap<>();
        /*map.put("name","a");*/
        map.put("address","a");
        List<Student> list = studentDao.select13(map);
        for (Student student : list) {
            System.out.println(student);
        }

    }
    @Test
    public void text14(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
        List<Student> list = studentDao.select14(new Integer[]{1, 3, 6});
        for (Student student : list) {
            System.out.println(student);
        }
    }
    @Test
    public void text15(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
        List<Map<String,Object>> list=studentDao.select15();
        for (Map<String, Object> map : list) {
            Set<String> set = map.keySet();
            for (String key : set) {
                System.out.println("key="+key);
                System.out.println(map.get(key));
            }
            System.out.println("==================");
        }

    }
    @Test
    public void text16(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
        List<StudentAndClassRoom> stu=studentDao.select16();
        System.out.println(stu);


    }
    @Test
    public void text17(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
        Student student=studentDao.select17();
        System.out.println(student);


    }


}

