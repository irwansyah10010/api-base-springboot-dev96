package com.lawencon.bootcamptest.base.dao.build.getter;

import com.lawencon.bootcamptest.base.dao.build.filter.DefinitionSearch;

public interface InitialGetter{
    public DefinitionSearch search();
    public DefinitionJoin join();
}
