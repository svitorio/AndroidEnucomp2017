package com.example.vitorio.exemplominicursoenuncomp2017;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.example.vitorio.exemplominicursoenuncomp2017.modelo.Ponto;

import java.util.ArrayList;

public class TelaCadastro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);
    }

    private class TelaCadastroAdapter extends ArrayAdapter<Ponto> {

        public TelaCadastroAdapter(ArrayList<Ponto> data, Activity activity) {
            super(activity, R.layout.item_cadastro, data);
        }
    }
}
