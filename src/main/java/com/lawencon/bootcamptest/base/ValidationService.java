package com.lawencon.bootcamptest.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;



public class ValidationService {
    
    public List<Boolean> validationUpdate(Map<?,?> columnValid){

        List<Boolean> acc = new ArrayList<>();

        for (Entry<?,?> columns : columnValid.entrySet()) {
            if(columns.getKey().equals(columns.getValue()) ||
            columns.getValue().equals(null)){
                acc.add(false);
            }else{
                acc.add(true);
            }
        }

        return acc;
    }


    public List<Boolean> validationNull(Map<?,?> columnValid){

        List<Boolean> acc = new ArrayList<>();

        for (Entry<?,?> columns : columnValid.entrySet()) {
            
            if(columns.getKey().equals(columns.getValue())){
                acc.add(false);
            }else{
                acc.add(true);
            }
        }

        return acc;
    }

}
