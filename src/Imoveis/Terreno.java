package Imoveis;

import modelo.Financiamento;

public class Terreno extends Financiamento {


    public Terreno(double valorImovel, int prazoFinanciamento, double taxaJurosAnual) {
        super(valorImovel, prazoFinanciamento, taxaJurosAnual);
    }


    @Override

    public double getPagamentoMensal() {
        double parcelaBase = super.getPagamentoMensal();
        return parcelaBase * 1.02;
    }
}