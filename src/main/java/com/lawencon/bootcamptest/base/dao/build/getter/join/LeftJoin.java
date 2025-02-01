package com.lawencon.bootcamptest.base.dao.build.getter.join;

import com.lawencon.bootcamptest.base.dao.build.getter.DefinitionJoin;

public interface LeftJoin{
    public DefinitionJoin leftJoin(Class<?> clazzEntity, String...fieldname);
}
