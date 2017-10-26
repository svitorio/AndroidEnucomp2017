package com.example.vitorio.exemplominicursoenuncomp2017;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vitorio.exemplominicursoenuncomp2017.Banco.DatabaseControl;
import com.example.vitorio.exemplominicursoenuncomp2017.modelo.Ponto;

public class TelaCadastro extends AppCompatActivity {

    private EditText nomeEditText, longitudeEditText, latitudeEditText;

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

        nomeEditText = (EditText) findViewById(R.id.cadastroNomeLocal);
        longitudeEditText = (EditText) findViewById(R.id.cadastroLongitude);
        latitudeEditText = (EditText) findViewById(R.id.cadastroLatitude);

    }

    private void cadastrar() {
        String nomeLocal = nomeEditText.getText().toString();
        String longitude = longitudeEditText.getText().toString();
        String latitude = latitudeEditText.getText().toString();

        if (nomeLocal.isEmpty()) {
            nomeLocal = "Sem nome";
        }

        float longitudeFloat = 0, latitudeFloat = 0;

        try {
            longitudeFloat = Float.parseFloat(longitude);
        } catch (Exception ignored) {
            longitudeEditText.setError("Longitude Inválida");
        }
        try {
            latitudeFloat = Float.parseFloat(latitude);
        } catch (Exception ignored) {
            latitudeEditText.setError("Latitude Inválida");
        }

        DatabaseControl databaseControl = new DatabaseControl(this);
        databaseControl.insertData(new Ponto(nomeLocal, latitudeFloat, longitudeFloat));

        Toast.makeText(this, "Cadastro realizado!", Toast.LENGTH_SHORT).show();
        finish();
    }
}
