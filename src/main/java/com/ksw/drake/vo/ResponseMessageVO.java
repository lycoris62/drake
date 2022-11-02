package com.ksw.drake.vo;

import javax.xml.crypto.Data;

public class ResponseMessageVO {
    private String version;
    private TemplateVO template;
    private DataVO data;

    public DataVO getData() {
        return data;
    }

    public void setData(DataVO data) {
        this.data = data;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public TemplateVO getTemplate() {
        return template;
    }

    public void setTemplate(TemplateVO template) {
        this.template = template;
    }
}
