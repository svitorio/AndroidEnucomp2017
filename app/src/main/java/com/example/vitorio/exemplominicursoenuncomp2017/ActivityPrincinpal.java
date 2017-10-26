package com.example.vitorio.exemplominicursoenuncomp2017;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityPrincinpal extends AppCompatActivity {


    private FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_princinpal);

        Button b;

        //b.setOnClickListener();
        findViewById(R.id.mapaButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityPrincinpal.this, Mapa.class);
                intent.putExtra("coordenadas","0");
                startActivity(intent);

            }
        });

        findViewById(R.id.listaButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityPrincinpal.this, TelaListar.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.cadastroButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityPrincinpal.this, TelaCadastro.class);
                intent.putExtra("nome","");
                startActivity(intent);

            }
        });

    }
    /*public void showFragment(Fragment frag, String name){

        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.replace(R.id.container,frag, name);

        transaction.commit();
    }*/
}
