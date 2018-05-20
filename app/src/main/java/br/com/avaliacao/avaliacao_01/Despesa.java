package br.com.avaliacao.avaliacao_01;

public class Despesa
{
    private String descricao;
    private Float valor;

    public Despesa( Float valor,String descricao) {
        this.descricao = descricao;
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public Float getValor() {
        return valor;
    }
}
