package com.lawencon.bootcamptest.base.dao.build.filter;

import com.lawencon.bootcamptest.base.dao.build.getter.InitialGetter;
import com.lawencon.bootcamptest.base.dao.build.filter.search.EqualSearch;
import com.lawencon.bootcamptest.base.dao.build.filter.search.LikeSearch;

public interface DefinitionSearch extends LikeSearch, EqualSearch{
    public DefinitionSearch and();
    public DefinitionSearch or();

    public InitialGetter closeSearch();
}
