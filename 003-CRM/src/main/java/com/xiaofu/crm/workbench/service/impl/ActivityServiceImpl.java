package com.xiaofu.crm.workbench.service.impl;

import com.xiaofu.crm.util.SqlSessionUtil;
import com.xiaofu.crm.vo.Paging;
import com.xiaofu.crm.workbench.dao.ActivityDao;
import com.xiaofu.crm.workbench.domain.Activity;
import com.xiaofu.crm.workbench.service.ActivityService;

import java.util.List;
import java.util.Map;

public class ActivityServiceImpl implements ActivityService {
    private ActivityDao activityDao= SqlSessionUtil.getSqlSession().getMapper(ActivityDao.class);
    @Override
    public boolean save(Activity activity) {
        boolean flag=true;
        int count=activityDao.save(activity);
        if(count !=1 ){
            flag=false;
            return flag;
        }else{
            return flag;

        }

    }

    @Override
    public Paging getActivityList(Map<String, Object> map) {
        // 首先得到total
        int total=activityDao.getTotalByCondition(map);
        // 得到dataList
        List<Activity> list=activityDao.getDataListByCondition(map);
        // 将total和dataList封装成一个vo对象
        Paging<Activity> paging=new Paging<>();
        paging.setDataList(list);
        paging.setTotal(total);
        // 返回给controller
        return paging;
    }
}
