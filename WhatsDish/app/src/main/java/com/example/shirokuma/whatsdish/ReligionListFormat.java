package com.example.shirokuma.whatsdish;

public class ReligionListFormat {
    public String relifionName;
    public boolean isSelect;

    ReligionListFormat(String relifionName, boolean isSelect) {
        this.relifionName = relifionName;
        this.isSelect = isSelect;
    }

    ReligionListFormat(String relifionName) {
        this.relifionName = relifionName;
        this.isSelect = false;
    }

}
