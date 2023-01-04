package com.lawencon.bootcamptest.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;

import javax.persistence.Query;

import com.lawencon.bootcamptest.constant.OptionalBuilder;

public class QueryBuilder<T> {
    
    private StringBuilder sql;
    
    public QueryBuilder<T> select(String tablename,ArrayList<String> columnnames){
		sql.append("SELECT ");

		int sizeOfColumn = columnnames.size();

		if(!columnnames.isEmpty()){
			for(int i = 0;i < sizeOfColumn;i++){
				sql.append(columnnames.get(i));
	
				if(i < sizeOfColumn - 1){
					sql.append(",");
				}
				sql.append(" ");
			}
		}else{
			sql.append("* ");
		}

		sql.append(" ")
		.append("FROM ")
		.append(tablename);

        return this;
    }

    public QueryBuilder<T> comparing(Map<String,?> columns){

        return this;
    }


    public QueryBuilder<T> join(){
        
        return this;
    }

    public QueryBuilder<T> limit(){
        
        return this;
    }


	public String getSQL(){
		return sql.toString();
	}
}
