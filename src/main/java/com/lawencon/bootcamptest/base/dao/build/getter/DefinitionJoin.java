package com.lawencon.bootcamptest.base.dao.build.getter;

import com.lawencon.bootcamptest.base.dao.build.getter.join.InnerJoin;
import com.lawencon.bootcamptest.base.dao.build.getter.join.LeftJoin;
import com.lawencon.bootcamptest.base.dao.build.getter.join.OuterJoin;
import com.lawencon.bootcamptest.base.dao.build.getter.join.RightJoin;

public interface DefinitionJoin extends LeftJoin, OuterJoin, RightJoin, InnerJoin{
    public InitialGetter closeJoin();
}
