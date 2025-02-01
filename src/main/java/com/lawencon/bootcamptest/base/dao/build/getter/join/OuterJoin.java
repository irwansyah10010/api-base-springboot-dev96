package com.lawencon.bootcamptest.base.dao.build.getter.join;

import com.lawencon.bootcamptest.base.dao.build.getter.DefinitionJoin;

public interface OuterJoin{
    public DefinitionJoin outerJoin(Class<?> clazzEntity, String...fieldname);
}
