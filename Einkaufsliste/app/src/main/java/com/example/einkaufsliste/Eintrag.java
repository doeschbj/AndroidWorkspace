package com.example.einkaufsliste;

public class Eintrag {
    private String name;
    private String num;
    private boolean selected;


    public Eintrag(String name, String num){
        this.name = name;
        this.num = num;
        selected = false;
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

    public void setSelected(){
        if(selected){
            selected = false;
        }else {
            selected = true;
        }
    }

    public void setSelected(boolean sel){
        selected = sel;
    }

    public boolean getSelected(){
        return selected;
    }
}
