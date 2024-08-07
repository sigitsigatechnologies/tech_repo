package com.bjj.rabbitmq_scurity.config;

public enum Utils {
    ALREADY_EXSIST("already Exist"),
    SUCESS("success"),
    FAILED("fail");

    private String value;

    Utils(String enums) {
        this.value = enums;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
