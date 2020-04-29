package com.example.einkaufsliste;

public class Eintrag {
    private String name;
    private String num;



    public Eintrag(String name, String num){
        this.name = name;
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public void setName(String name) {
        this.name = name;
    }
}
