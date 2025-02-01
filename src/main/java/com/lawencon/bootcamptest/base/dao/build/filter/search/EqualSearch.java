package com.lawencon.bootcamptest.base.dao.build.filter.search;

import com.lawencon.bootcamptest.base.dao.build.filter.DefinitionSearch;

public interface EqualSearch {
    public DefinitionSearch equal(String field,Object value);
    public DefinitionSearch notEqual(String field, Object value);
}
