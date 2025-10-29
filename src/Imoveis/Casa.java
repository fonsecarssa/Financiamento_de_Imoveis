package Imoveis;

import modelo.Financiamento;

public class Casa extends Financiamento {

    private final double taxaFixaMensal = 80.00;

    public Casa(double valorImovel, int prazoFinanciamento,double taxaJurosAnual){
        super(valorImovel, prazoFinanciamento, taxaJurosAnual);
    }

    @Override
    public double getPagamentoMensal(){
        double pagamentoBase = super.getPagamentoMensal();
        return pagamentoBase + taxaFixaMensal;
    }
}
