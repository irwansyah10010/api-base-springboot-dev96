package com.lawencon.bootcamptest.base.dao.build.update;

import java.util.Map;

public interface InitialUpdate {
    public ConditionUpdate setFieldsUpdate(Object objClass);
    public ConditionUpdate setFieldsUpdate(Map<String, Object> dataObj);
}
