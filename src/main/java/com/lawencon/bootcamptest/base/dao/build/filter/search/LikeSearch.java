package com.lawencon.bootcamptest.base.dao.build.filter.search;

import com.lawencon.bootcamptest.base.dao.build.filter.DefinitionSearch;

public interface LikeSearch {
    public DefinitionSearch likeFront(String field, String value);
    public DefinitionSearch unlikeFront(String field, String value);

    public DefinitionSearch likeBack(String field, String value);
    public DefinitionSearch unlikeBack(String field, String value);
}
