package br.com.avaliacao.avaliacao_01;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelperSaldo extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "saldo.db";
    private static final int DATABASE_VERSION = 18;

    public static final String TABLE_SALDO = "saldo_table";
    public static final String TABLE_DEPESAS = "despesas_table";

    //table saldo
    public static final String COL_1 = "ID";
    public static final String COL_2 = "VALOR";

    //table despesa
    public static final String COL_3 = "DESCRICAO";
    public static final String COL_4 = "ID_CONTA";

    public DataBaseHelperSaldo(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_SALDO +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,VALOR REAL)");
        sqLiteDatabase.execSQL("create table " + TABLE_DEPESAS +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,VALOR REAL,DESCRICAO TEXT,ID_CONTA INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_SALDO );
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_DEPESAS );
        onCreate(sqLiteDatabase);
    }

    //table saldo
    public boolean insertDataSaldo(Float saldo){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,saldo);
        long result = sqLiteDatabase.insert(TABLE_SALDO,null,contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }

    public Float getSaldo(String id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("select VALOR from "+TABLE_SALDO + " WHERE ID = "+id,null );
        Float n=0f;
        if(res.moveToFirst()){
             n = res.getFloat(0);
        }
        res.close();
        sqLiteDatabase.close();
        return n;
    }

    public Cursor getAllDataSaldo(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("select * from "+TABLE_SALDO,null );
        return res;
    }

    public boolean updateDataSaldo(String id,Float valor,Float saldoAtual,boolean tipo){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        if(tipo){
            contentValues.put(COL_2,valor+saldoAtual);
        }else{
            contentValues.put(COL_2,saldoAtual-valor);
        }
        sqLiteDatabase.update(TABLE_SALDO, contentValues, "id = ?", new String[]{ id });
        return true;
    }

    //table despesa
    public boolean insertDataDespesa(Float valor,String descricao,int id_conta){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2 ,valor);
        contentValues.put(COL_3 ,descricao);
        contentValues.put(COL_4 ,id_conta);
        long result = sqLiteDatabase.insert(TABLE_DEPESAS,null,contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }

    public Cursor getAllDataDespesa(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("select * from "+TABLE_DEPESAS,null );
        return res;
    }

}
