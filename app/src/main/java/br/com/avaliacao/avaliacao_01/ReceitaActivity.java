package br.com.avaliacao.avaliacao_01;

import android.database.Cursor;
import android.database.DatabaseUtils;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ReceitaActivity extends AppCompatActivity {
    Button btnSalva;
    DataBaseHelperSaldo myDb;
    EditText deposito,saldo;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receita);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        btnSalva = (Button)findViewById(R.id.btnSalvar);
        deposito = (EditText)findViewById(R.id.txtDeposito);
        saldo = (EditText)findViewById(R.id.txtSaldo);
        myDb = new DataBaseHelperSaldo(this);
        insertFirstElement();
    }

    public void insertFirstElement(){
        Cursor res = myDb.getAllData();
        if(res.getCount()==0){
            myDb.insertData(0f);
        }else{
            return;
        }
    }
}
