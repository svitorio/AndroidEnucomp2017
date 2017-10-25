package com.example.vitorio.exemplominicursoenuncomp2017.Banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.vitorio.exemplominicursoenuncomp2017.modelo.Ponto;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by desenvolverdor on 03/05/17.
 */

public class DatabaseControl{

    private SQLiteDatabase db;
    private CreateDatabase database;

    public DatabaseControl(Context context) {
            database = new CreateDatabase(context);
    }
    public String insertData(Ponto ponto){
        ///int birthday
        ContentValues valores;
        long resultado;

        db = database.getWritableDatabase();
        valores = new ContentValues();
        valores.put(CreateDatabase.NAME_LOCAL,ponto.getNome());
        valores.put(CreateDatabase.LATITUDE, ponto.getLatitude());
        valores.put(CreateDatabase.LONGITUDE, ponto.getLongitude());

      //  valores.put(CreateDatabase.DATE_BIRTHDAY, birthday);

        resultado = db.insert(CreateDatabase.TABLE, null, valores);
        db.close();

        if (resultado ==-1)
            return "Erro ao inserir registro";
        else
            return "Registro Inserido com sucesso";

    }
    public ArrayList<Ponto> carregaDados(){
        Cursor cursor;
        //String[] campos =  {database.ID,database.NAME_LOCAL,database.LATITUDE,database.LONGITUDE};
        //db = database.getReadableDatabase();
        //cursor = db.query(database.TABLE, campos, null, null, null, null, null, null);

        //if(cursor!=null){
         //   cursor.moveToFirst();
        //}

        ArrayList<Ponto> list = new ArrayList<Ponto>();
        String[] campos =  {database.ID,database.NAME_LOCAL,database.LATITUDE,database.LONGITUDE};
        db = database.getReadableDatabase();
        cursor = db.query(database.TABLE, campos, null, null, null, null, null, null);
        //Cursor cursor = bd.query("usuario", colunas, null, null, null, null, "nome ASC");

        if(cursor.getCount() > 0){
            cursor.moveToFirst();

            do{

                Ponto p = new Ponto();
                p.setNome(cursor.getString(1));
                p.setLatitude(cursor.getLong(2));
                p.setLongitude(cursor.getLong(3));
                list.add(p);

            }while(cursor.moveToNext());
        }

        db.close();
        return list;
    }
    // Metodo Exemplo
    /*
    public List<Usuario> buscar(){
        List<Usuario> list = new ArrayList<Usuario>();
        String[] colunas = new String[]{"_id", "nome", "email"};

        Cursor cursor = bd.query("usuario", colunas, null, null, null, null, "nome ASC");

        if(cursor.getCount() > 0){
            cursor.moveToFirst();

            do{

                Usuario u = new Usuario();
                u.setId(cursor.getLong(0));
                u.setNome(cursor.getString(1));
                u.setEmail(cursor.getString(2));
                list.add(u);

            }while(cursor.moveToNext());
        }

        return(list);
    }
    */

    public Cursor carregaDadoById(int id){
        Cursor cursor;
        String[] campos =  {database.ID,database.NAME_LOCAL,database.LATITUDE,database.LONGITUDE};
        String where = CreateDatabase.ID + "=" + id;
        db = database.getReadableDatabase();
        cursor = db.query(CreateDatabase.TABLE,campos,where, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }
    public void alteraRegistro(int id, String name,float lat, float lon){
        ContentValues valores;
        String where;

        db = database.getWritableDatabase();

        where = CreateDatabase.ID + "=" + id;

        valores = new ContentValues();
        valores.put(CreateDatabase.NAME_LOCAL, name);
        valores.put(CreateDatabase.LATITUDE, lat);
        valores.put(CreateDatabase.LONGITUDE, lon);

        db.update(CreateDatabase.TABLE,valores,where,null);
        db.close();
    }
    public void deletaRegistro(int id){
        String where = CreateDatabase.ID + "=" + id;
        db = database.getReadableDatabase();
        db.delete(CreateDatabase.TABLE,where,null);
        db.close();
    }
}
