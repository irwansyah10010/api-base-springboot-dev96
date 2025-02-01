package com.lawencon.bootcamptest.base.dao.build.getter.join;

import com.lawencon.bootcamptest.base.dao.build.getter.DefinitionJoin;

public interface InnerJoin{
    public DefinitionJoin innerJoin(Class<?> clazzEntity, String...fieldname);
}
