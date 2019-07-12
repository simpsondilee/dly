package com.springboot.core.constants;

import com.springboot.core.base.BaseEnum;

import java.util.HashMap;
import java.util.Map;

public enum BaseEnums implements BaseEnum<String,String> {

    SUCCESS("request.success","请求成功"),

    FAILURE("request.failure","请求失败"),

    OPERATION_SUCCESS("operation.success", "操作成功"),

    OPERATION_FAILURE("operation.failure", "操作失败"),

    ERROR("system.error", "系统异常"),

    NOT_FOUND("not_found", "请求资源不存在"),

    FORBIDDEN("forbidden", "无权限访问"),

    VERSION_NOT_MATCH("record_not_exists_or_version_not_match", "记录版本不存在或不匹配"),

    PARAMETER_NOT_NULL("parameter_not_be_null", "参数不能为空");    private String code;

    private String desc;

    private static Map<String, String> allMap = new HashMap<>();

    BaseEnums(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    static {
        for(BaseEnums enums : BaseEnums.values()){
            allMap.put(enums.code, enums.desc);
        }
    }

    public String code(){
        return code;
    }

    public String desc(){
        return desc;
    }

    public String desc(String code) {
        return allMap.get(code);
    }

}
