package com.example.bugtracker.addEditBugTest;

import com.example.bugtracker.Bug;
import com.example.bugtracker.Developer;
import com.example.bugtracker.dao.BugDAO;
import com.example.bugtracker.dao.DeveloperDAO;
import com.example.bugtracker.dao.Initializer;
import com.example.bugtracker.view.Bug.AddEditBug.AddEditBugPresenter;
import com.example.bugtracker.view.Bug.AddEditBug.AddEditBugView;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class addEditBugPresenterTest {
    private AddEditBugPresenter presenter;
    private addEditBugViewStub view;

    @Test
    public void testAddEditBug(){
        try{
            Initializer init = new Initializer();
            init.preparedata();
            view = new addEditBugViewStub();
            BugDAO bugs = init.getBugDAO();
            DeveloperDAO devs = init.getDevDAO();
            presenter = new AddEditBugPresenter(view,bugs,devs);

            // bug saving : bug doesn't exist
            String d = "10/08/2020";
            Date d1 = new SimpleDateFormat("dd/MM/yyyy").parse(d);
            view.setBugName("Youtube issue");
            view.setBugDate(d1);
            view.setBugDescription("Big problem.");
            view.setBugPriority(Bug.Priority.Medium);
            view.setBugStatus(Bug.bugStatus.ToDo);
            presenter.onSaveBug();
            assertEquals("new bug with name" + view.getBugName(),view.getFinish());


        }catch (ParseException e){
            e.printStackTrace();
        }
    }
}
