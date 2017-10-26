package com.example.vitorio.exemplominicursoenuncomp2017;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vitorio.exemplominicursoenuncomp2017.Banco.DatabaseControl;
import com.example.vitorio.exemplominicursoenuncomp2017.modelo.Ponto;

import java.util.ArrayList;

public class TelaListar extends AppCompatActivity {

    private ListView listaDeCadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_listar);

        listaDeCadastro = (ListView) findViewById(R.id.list_view);

        DatabaseControl dc = new DatabaseControl(this);
        final ArrayList<Ponto> listaDePontosCadastrados = dc.carregaDados();

        TelaCadastroAdapter adapter = new TelaCadastroAdapter(listaDePontosCadastrados, this);

        listaDeCadastro.setAdapter(adapter);
        listaDeCadastro.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                irponto(listaDePontosCadastrados.get(position).getLatitude(), listaDePontosCadastrados.get(position).getLongitude(),listaDePontosCadastrados.get(position).getNome());
            }

        });


    }
    private void irponto(final double lat, final double lon,final String nome){

            final Intent intentcad = new Intent(TelaListar.this,Mapa.class);
            intentcad.putExtra("latitude",""+lat);
            intentcad.putExtra("longitude",""+lon);
            intentcad.putExtra("nome", nome);
            intentcad.putExtra("coordenadas","2");
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Ponto");
            //alertDialog.setIcon();
            alertDialog.setMessage("Ir para Ponto selecionado?");
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Sim",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                            startActivity(intentcad);
                            dialog.dismiss();
                            onBackPressed();
                            finish();
                        }
                    });
            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Não",
                    new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int which){
                            dialog.dismiss();
                            onBackPressed();
                        }
                    });
            alertDialog.show();
    }
    private class TelaCadastroAdapter extends ArrayAdapter<Ponto> {

        public TelaCadastroAdapter(ArrayList<Ponto> data, Activity activity) {
            super(activity, R.layout.item_lista, data);
        }

        private class ViewHolder {
            private TextView nomeDoLocal;
            private TextView latitude;
            private TextView longitude;
        }

        private int lastPosition = -1;

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            final ViewHolder viewHolder;

            //final View result;

            String nomeDoLocal = getItem(position).getNome();
            String latitude = Double.toString( getItem(position).getLatitude() );
            String longitude = Double.toString( getItem(position).getLongitude() );

            if (convertView == null) {

                viewHolder = new ViewHolder();
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(R.layout.item_lista, parent, false);

                viewHolder.nomeDoLocal = (TextView) convertView.findViewById(R.id.nomeDoLocalTextView);
                viewHolder.latitude = (TextView) convertView.findViewById(R.id.latitudeTextView);
                viewHolder.longitude = (TextView) convertView.findViewById(R.id.longitudeTextView);

                //result = convertView;
                convertView.setTag(viewHolder);
            }
            else {
                viewHolder = (ViewHolder) convertView.getTag();
                //result = convertView;
            }

            lastPosition = position;

            if (nomeDoLocal != null) {
                viewHolder.nomeDoLocal.setText(nomeDoLocal);
            }
            if (latitude != null) {
                viewHolder.latitude.setText(latitude);
            }
            if (longitude != null) {
                viewHolder.longitude.setText(longitude);
            }

            return convertView;
        }

    }
}
