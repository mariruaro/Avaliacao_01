package br.com.avaliacao.avaliacao_01;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Float saldoAtual;
    TextView txtSaldo;
    DataBaseHelperSaldo myDb;
    ArrayList<Despesa> despesaList;
    ListView listView;
    Despesa depesa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        txtSaldo = (TextView)findViewById(R.id.lblSaldo);
        myDb = new DataBaseHelperSaldo(this);
        insertFirstElement();
        saldoAtual = myDb.getSaldo("1");
        txtSaldo.setText(saldoAtual.toString());

        despesaList = new ArrayList<>();
        Cursor data = myDb.getAllDataDespesa();
        int numRows = data.getCount();
        if(numRows==0){
            return;
        }else{
            while(data.moveToNext()){
                depesa = new Despesa(data.getFloat(1),data.getString(2));
                despesaList.add(depesa);
            }
            ListAdapter adapter = new ListAdapter(this, R.layout.listview_despesas,despesaList);
            listView = (ListView) findViewById(R.id.listDespesas);
            listView.setAdapter(adapter);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.receita) {
            Intent intent = new Intent(this, ReceitaActivity.class);
            startActivity(intent);
        }

        if (id == R.id.despesa) {
            Intent intent = new Intent(this, DespesaActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    public void insertFirstElement(){
        Cursor res = myDb.getAllDataSaldo();
        if(res.getCount()==0){
            myDb.insertDataSaldo(0f);
        }else{
            return;
        }
    }
}
