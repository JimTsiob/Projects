package com.example.bugtracker.view.Component.AddEditComponent;

import com.example.bugtracker.Component;
import com.example.bugtracker.dao.ComponentDAO;

public class AddEditComponentPresenter {
    private AddEditComponentView view;

    private ComponentDAO comps;

    private Component attachedComp;

    /**
     * Presenter constructor used for the AddEditComponent interface.
     * @param v the AddEditComponentView that's used for the methods to handle data.
     * @param c ComponentDAO data container , used to save and manipulate components.
     */

    public AddEditComponentPresenter(AddEditComponentView v, ComponentDAO c) {
        view = v;
        comps = c;

        Integer attachedCompID = v.getAttachedComponentID();
        if (attachedCompID != null) {
            attachedComp = c.find(attachedCompID);
        }
    }

    /**
     * Method used for component creation or editing.
     * If the component doesn't exist a new one is created with the given name,saved in the DAO
     * and a success message is shown to the user.
     * If the component exists, the component that's found is edited with the new name
     * and saved back in the ComponentDAO Object.
     */

    public void onSaveComp() {
        String compName = view.getCompName();
        if (attachedComp == null) {
            Component tempComponent = new Component(compName);
            comps.save(tempComponent);
            view.goodFinish("New component with name " + tempComponent.getName());
        } else {
            attachedComp.setName(compName);
            view.goodFinish("Successfully changed component " + attachedComp.getName());
        }
    }
}
