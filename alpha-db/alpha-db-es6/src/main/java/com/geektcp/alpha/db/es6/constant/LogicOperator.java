package com.geektcp.alpha.db.es6.constant;

import org.apache.commons.lang3.StringUtils;

/**
 * @author tanghaiyang on 2019/5/6.
 */
public enum LogicOperator {
    AND("AND","交集"),
    OR("OR","并集")
    ;

    private String value;
    private String desc;

    LogicOperator(String value, String desc){
        this.value = value;
        this.desc = desc;
    }

    public String getValue(){
        return this.value;
    }

    public String getDesc(){
        return this.desc;
    }

    public static LogicOperator byValue(String value){
        for(LogicOperator logicOperator : values()){
            if(StringUtils.equalsIgnoreCase(value, logicOperator.getValue())){
                return logicOperator;
            }
        }
        return null;

    }
}
