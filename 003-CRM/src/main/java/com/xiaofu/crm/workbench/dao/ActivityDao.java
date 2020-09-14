package com.xiaofu.crm.workbench.dao;

import com.xiaofu.crm.workbench.domain.Activity;

import java.util.List;
import java.util.Map;

public interface ActivityDao {
    int save(Activity activity);

    int getTotalByCondition(Map<String, Object> map);

    List<Activity> getDataListByCondition(Map<String, Object> map);
}
