package com.xiaofu.crm.workbench.service;

import com.xiaofu.crm.vo.Paging;
import com.xiaofu.crm.workbench.domain.Activity;

import java.util.Map;

public interface ActivityService {
    boolean save(Activity activity);

    Paging getActivityList(Map<String, Object> map);
}
