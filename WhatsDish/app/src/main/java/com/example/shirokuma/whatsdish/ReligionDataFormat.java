package com.example.shirokuma.whatsdish;

public class ReligionDataFormat {
    public String religionName;
    public boolean isSelect;

    public ReligionDataFormat(String religionName, boolean isSelect) {
        this.religionName = religionName;
        this.isSelect = isSelect;
    }

    ReligionDataFormat(String religionName) {
        this.religionName = religionName;
        this.isSelect = false;
    }
}
