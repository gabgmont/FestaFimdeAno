package com.example.festafimdeano.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.festafimdeano.R;
import com.example.festafimdeano.constant.FimDeAnoConstants;
import com.example.festafimdeano.data.SecurityPreferences;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private SecurityPreferences mSecurityPreferences;

    //Formatação da data
    private static SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mSecurityPreferences = new SecurityPreferences(this);

        //Definindo textos da aplicação
        this.mViewHolder.textToday = findViewById(R.id.txt_today);
        this.mViewHolder.daysLeft = findViewById(R.id.days_left);
        this.mViewHolder.buttonConfirm = findViewById(R.id.button_confirm);

        //Verificação do botão
        this.mViewHolder.buttonConfirm.setOnClickListener(this);

        //Buscando a data formatada e setando como texto da aplicação
        this.mViewHolder.textToday.setText(SIMPLE_DATE_FORMAT.format(Calendar.getInstance().getTime()));
        String daysLeft= String.format("%s %s", String.valueOf(this.getDaysLeft()), getString(R.string.days));
        this.mViewHolder.daysLeft.setText(daysLeft);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.verifyPresence();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    //Ação desencadeada do clique do botão
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_confirm) {

            String presence = this.mSecurityPreferences.getStoredString(FimDeAnoConstants.presence_key);

            Intent intent = new Intent(this, DetailsActivity.class);
            intent.putExtra(FimDeAnoConstants.presence_key, presence);
            startActivity(intent);
        }
    }

    private void verifyPresence(){
        //Não confirmado, sim, não
        String presence = this.mSecurityPreferences.getStoredString(FimDeAnoConstants.presence_key);

        if(presence.equals("")){
            this.mViewHolder.buttonConfirm.setText(getString(R.string.not_confirmed));
        } else if(presence.equals(FimDeAnoConstants.CONFIRMATION_YES)){
            this.mViewHolder.buttonConfirm.setText(getString(R.string.yes));
        } else {
            this.mViewHolder.buttonConfirm.setText(getString(R.string.no));
        }
    }

    //Método para buscar o dia atual/ultimo dia do ano e retornar a diferença
    private int getDaysLeft(){ //Dias do ano
        Calendar calendarToday = Calendar.getInstance();
        int today = calendarToday.get(Calendar.DAY_OF_YEAR);

        Calendar calendarLastDay = Calendar.getInstance();
        int lastDay = calendarLastDay.getActualMaximum(Calendar.DAY_OF_YEAR);

        return lastDay - today;
    }

    //Declaração das variáveis de texto da aplicação
    private static class ViewHolder {
        TextView textToday;
        TextView daysLeft;
        Button buttonConfirm;
    }
}