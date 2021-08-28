package com.example.bugtracker.addEditComponentTest;


import com.example.bugtracker.dao.ComponentDAO;
import com.example.bugtracker.dao.Initializer;
import com.example.bugtracker.view.Component.AddEditComponent.AddEditComponentPresenter;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import static org.junit.Assert.assertEquals;

public class AddEditComponentPresenterTest {
    private AddEditComponentViewStub view;
    private AddEditComponentPresenter presenter;


    @Test
    public void testAddEditComp(){
        try{
            Initializer init = new Initializer();
            init.preparedata();
            view = new AddEditComponentViewStub();
            ComponentDAO comps = init.getComponentDAO();
            presenter = new AddEditComponentPresenter(view,comps);

            // component saving.

            view.setCompname("Component 5");
            presenter.onSaveComp();
            assertEquals("New component with name " + view.getCompName(),view.getFinish());

        }catch (ParseException e){
            e.printStackTrace();
        }
    }

}
