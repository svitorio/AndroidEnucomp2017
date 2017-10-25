package com.example.vitorio.exemplominicursoenuncomp2017;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vitorio.exemplominicursoenuncomp2017.Banco.DatabaseControl;
import com.example.vitorio.exemplominicursoenuncomp2017.modelo.Ponto;

public class TelaCadastro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);

        findViewById(R.id.botaoCadastrar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastrar();
            }
        });

    }

    private void cadastrar() {
        String nomeLocal = ((EditText) findViewById(R.id.cadastroNomeLocal)).getText().toString();
        String longitude = ((EditText) findViewById(R.id.cadastroLongitude)).getText().toString();
        String latitude = ((EditText) findViewById(R.id.cadastroLatitude)).getText().toString();

        float longitudeFloat = 0, latitudeFloat = 0;

        try {
            longitudeFloat = Float.parseFloat(longitude);
        } catch (Exception ignored) {}
        try {
            latitudeFloat = Float.parseFloat(latitude);
        } catch (Exception ignored) {}

        DatabaseControl databaseControl = new DatabaseControl(this);
        databaseControl.insertData(new Ponto(nomeLocal, latitudeFloat, longitudeFloat));

        Toast.makeText(this, "Cadastro realizado!", Toast.LENGTH_SHORT).show();
        finish();   
    }
}
