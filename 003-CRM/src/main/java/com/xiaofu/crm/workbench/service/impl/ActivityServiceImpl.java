package com.xiaofu.crm.workbench.service.impl;

import com.xiaofu.crm.util.SqlSessionUtil;
import com.xiaofu.crm.workbench.dao.ActivityDao;
import com.xiaofu.crm.workbench.domain.Activity;
import com.xiaofu.crm.workbench.service.ActivityService;

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
}
