package com.example.bugtracker.view.Component.AddEditComponent;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bugtracker.R;
import com.example.bugtracker.memory.ComponentDAOMemory;

public class AddEditComponentActivity extends AppCompatActivity implements AddEditComponentView {

    /**
     * the required onCreate method for the Add Edit component activity interface.
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_component);

        final AddEditComponentPresenter presenter = new AddEditComponentPresenter(this,new ComponentDAOMemory());
        findViewById(R.id.btn_save_comp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onSaveComp();
            }
        });
    }

    /**
     * Getter method implemented for the AddEditComponentView.
     * Used in presenter.
     * @return
     */

    @Override
    public String getCompName(){
        return ((EditText)findViewById(R.id.edit_comp_name)).getText().toString();
    }

    /**
     * Finish method that returns a message to the user upon success.
     * @param msg the message.
     */
    @Override
    public void goodFinish(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }


    /**
     * Getter method implemented for the AddEditComponentView.
     * Used in presenter.
     * @return
     */
    @Override
        public Integer getAttachedComponentID(){
        return this.getIntent().hasExtra("EXTRA_COMPONENT_ID")? this.getIntent().getExtras().getInt("EXTRA_COMPONENT_ID"):null;
    }

}
