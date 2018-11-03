package com.example.shirokuma.whatsdish;

class Data {
    protected String name;
    protected boolean isSelect;

    Data(String name) {
        this.name = name;
        this.isSelect = false;
    }

    Data(String name, boolean isSelect) {
        this.name = name;
        this.isSelect = isSelect;
    }
}
