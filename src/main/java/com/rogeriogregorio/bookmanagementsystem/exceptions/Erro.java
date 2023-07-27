package com.rogeriogregorio.bookmanagementsystem.exceptions;

public class Erro {
    private int code;
    private String message;
    private String detail;

    public Erro() {
    }

    public Erro code(int code) {
        this.code = code;
        return this;
    }

    public Erro message(String message) {
        this.message = message;
        return this;
    }

    public Erro detail(String detail) {
        this.detail = detail;
        return this;
    }

    public int getCode() { return code; }

    public String getMessage() {
        return message;
    }

    public String getDetail() { return detail; }

    public Erro build() {
        this.code = code;
        this.message = message;
        this.detail = detail;
        return this;
    }
}
