package com.company.constant;

public enum AppEnum {

    WeChat("WeChat","微信"), Alipay("Alipay","支付宝"), QQ("QQ","QQ");

    private String appName;
    private String appDesc;

    AppEnum(String appName, String appDesc) {
        this.appName = appName;
        this.appDesc = appDesc;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppDesc() {
        return appDesc;
    }

    public void setAppDesc(String appDesc) {
        this.appDesc = appDesc;
    }
}
