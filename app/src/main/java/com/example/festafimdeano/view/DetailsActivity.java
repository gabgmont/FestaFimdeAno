package com.example.festafimdeano.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import com.example.festafimdeano.R;
import com.example.festafimdeano.constant.FimDeAnoConstants;
import com.example.festafimdeano.data.SecurityPreferences;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener{

    private ViewHolder mViewHolder = new ViewHolder();
    private SecurityPreferences mSecurityPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        this.mSecurityPreferences = new SecurityPreferences(this);

        this.mViewHolder.checkBoxValue = findViewById(R.id.check_box);
        this.mViewHolder.checkBoxValue.setOnClickListener(this);

        this.loadDataFromActivity();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.check_box){
           if(this.mViewHolder.checkBoxValue.isChecked()){
               //Salvar Presença
              this.mSecurityPreferences.storedtring(FimDeAnoConstants.presence_key, FimDeAnoConstants.CONFIRMATION_YES);
           }else{
               //Salvar Ausência
               this.mSecurityPreferences.storedtring(FimDeAnoConstants.presence_key, FimDeAnoConstants.CONFIRMATION_NO);
           }
        }
    }

    private void loadDataFromActivity(){
       Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String presence = extras.getString(FimDeAnoConstants.presence_key);
            if (presence != null && presence.equals(FimDeAnoConstants.CONFIRMATION_YES)){
                this.mViewHolder.checkBoxValue.setChecked(true);

            } else{
                this.mViewHolder.checkBoxValue.setChecked(false);
            }
        }
    }
    private static class ViewHolder{
        CheckBox checkBoxValue;
    }
}



