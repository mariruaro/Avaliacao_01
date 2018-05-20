package br.com.avaliacao.avaliacao_01;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DespesaActivity extends AppCompatActivity {
    Float saldoAtual;
    Button btnSalva;
    DataBaseHelperSaldo myDb;
    EditText despesa,txtValor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_despesa);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        despesa = (EditText)findViewById(R.id.txtDespesa);
        txtValor = (EditText)findViewById(R.id.txtValorDespesa);
        btnSalva = (Button)findViewById(R.id.btnSalvar);
        myDb = new DataBaseHelperSaldo(this);
        saldoAtual = myDb.getSaldo("1");
        Salvar();
    }

    public void Salvar(){
        btnSalva.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String str=txtValor.getText().toString();
                        Float number = Float.parseFloat(str);
                        boolean isUpdateSaldo = myDb.updateDataSaldo("1",number,saldoAtual,false);
                        boolean isInsertDespesa = myDb.insertDataDespesa(number,despesa.getText().toString(),1);
                        if(isUpdateSaldo && isInsertDespesa){
                            Toast.makeText(DespesaActivity.this,"Despesa Realizada com Sucesso",Toast.LENGTH_LONG).show();
                            returnScreen();
                        }else{
                            Toast.makeText(DespesaActivity.this,"Erro ao Realizar Despesa",Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }

    public void returnScreen() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
