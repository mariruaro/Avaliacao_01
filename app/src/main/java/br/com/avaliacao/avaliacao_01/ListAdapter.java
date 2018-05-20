package br.com.avaliacao.avaliacao_01;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<Despesa> {

    private LayoutInflater mInflater;
    private ArrayList<Despesa> despesas;
    private int mViewResourceId;

    public ListAdapter(Context context, int textViewResourceId, ArrayList<Despesa> despesas) {
        super(context, textViewResourceId, despesas);
        this.despesas = despesas;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewResourceId = textViewResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(mViewResourceId, null);

        Despesa despesa = despesas.get(position);

        if (despesa != null) {
            TextView valor = (TextView) convertView.findViewById(R.id.txtValorDespesa);
            TextView descircao = (TextView) convertView.findViewById(R.id.txtDespesa);
            if (valor != null) {
                valor.setText(despesa.getValor().toString());
            }
            if (descircao != null) {
                descircao.setText((despesa.getDescricao()));
            }
        }

        return convertView;
    }
}
