package com.example.bugtracker.addEditComponentTest;

import com.example.bugtracker.view.Component.AddEditComponent.AddEditComponentView;

public class AddEditComponentViewStub implements AddEditComponentView {
    private String compname , finish;
    private Integer id;

    @Override
    public String getCompName(){
        return compname;
    }

    @Override
    public void goodFinish(String msg){
        finish = msg;
    }

    @Override
    public Integer getAttachedComponentID(){
        return id;
    }

    public void setCompname(String n){
        compname = n;
    }

    public String getFinish(){
        return finish;
    }
}
