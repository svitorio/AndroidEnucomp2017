package com.example.vitorio.exemplominicursoenuncomp2017;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
        Intent intentm = getIntent();
        String nome,latitude,longitude;


        nomeEditText = (EditText) findViewById(R.id.cadastroNomeLocal);
        longitudeEditText = (EditText) findViewById(R.id.cadastroLongitude);
        latitudeEditText = (EditText) findViewById(R.id.cadastroLatitude);

        if(intentm.getStringExtra("nome").equals("")){
            Log.i("tag","ta fazio");

            findViewById(R.id.botaoCoordenadas).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    coordenadas(nomeEditText);
                }
            });
        }else{
            nome = intentm.getStringExtra("nome");
            latitude = intentm.getStringExtra("latitude");
            longitude = intentm.getStringExtra("longitude");
            nomeEditText.setText(nome);
            latitudeEditText.setText(latitude);
            longitudeEditText.setText(longitude);
        }


    }

    private void coordenadas(EditText nomeEditText) {
        String nomeLocal = nomeEditText.getText().toString();
        if (nomeLocal.equals("")) {
            nomeLocal = "Sem Nome";
        }
        Intent intent = new Intent(TelaCadastro.this, Mapa.class);
        intent.putExtra("nome", nomeLocal);
        intent.putExtra("coordenadas","1");
        startActivity(intent);
        finish();
    }

    private void cadastrar() {
        String nomeLocal = nomeEditText.getText().toString();
        String longitude = longitudeEditText.getText().toString();
        String latitude = latitudeEditText.getText().toString();

        if (nomeLocal.equals("")) {
            nomeLocal = "Sem nome";
        }

        double longitudeFloat = 0, latitudeFloat = 0;

        try {
            longitudeFloat = Double.parseDouble(longitude);
            Log.i("i","Antes:: "+longitudeEditText.getText().toString()+"\nDepois:: "+latitudeFloat);
        } catch (Exception ignored) {
            longitudeEditText.setError("Longitude Inválida");
        }
        try {
            latitudeFloat = Double.parseDouble(latitude);
        } catch (Exception ignored) {
            latitudeEditText.setError("Latitude Inválida");
        }

        DatabaseControl databaseControl = new DatabaseControl(this);
        databaseControl.insertData(new Ponto(nomeLocal, latitudeFloat, longitudeFloat));
        Toast.makeText(this, "Cadastro realizado!", Toast.LENGTH_SHORT).show();
        finish();
    }
}
