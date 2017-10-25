package com.example.vitorio.exemplominicursoenuncomp2017.Banco;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by desenvolverdor on 03/05/17.
 */

public class CreateDatabase extends SQLiteOpenHelper {

    private static final String NAME_DATABASE = "evento.db";
    static final String TABLE = "local";
    static final String NAME_LOCAL = "name_local";
    static final String LATITUDE = "latitude";
    static final String LONGITUDE = "longitude";
    static final String ID = "_id";
    private static final int VERSAO = 7;

    public CreateDatabase(Context context) {
        super(context, NAME_DATABASE, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       /* String sql = "CREATE TABLE"+TABLE+"("
                + ID + "integer primary key autoincrement,"
                + PHONE + "integer,"
                + NAME_USER + "text not null,"
                + EMAIL + "text not null);";
        //+ DATE_BIRTHDAY + "integer"*/
        db.execSQL("Create table "+TABLE+"("+ID+" integer primary key autoincrement,"+NAME_LOCAL+" text not null,"+LATITUDE+" real not null,"+LONGITUDE+" real not null);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }
}
