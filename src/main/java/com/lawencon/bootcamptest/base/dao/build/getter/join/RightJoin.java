package com.lawencon.bootcamptest.base.dao.build.getter.join;

import com.lawencon.bootcamptest.base.dao.build.getter.DefinitionJoin;

public interface RightJoin{
    public DefinitionJoin rightJoin(Class<?> clazzEntity, String...fieldname);
}
