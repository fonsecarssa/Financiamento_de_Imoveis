package modelo;

public class Financiamento{

    private double valorImovel;
    private int prazoFinanciamento;
    public double taxaJurosAnual;

    private double pagamentoMensal;

    public Financiamento(double valorImovel, int prazoFinanciamento, double taxaJurosAnual){
        this.valorImovel = valorImovel;
        this.prazoFinanciamento = prazoFinanciamento;
        this.taxaJurosAnual = taxaJurosAnual;
    }

    public Financiamento(){
        //none só pra rodar liso sem problema
    }

    public void calcularPagamentoMensal(){
        int prazoMeses = prazoFinanciamento * 12;
        double taxaJurosMensal = taxaJurosAnual / 12;

        double jurosTotal = valorImovel * taxaJurosAnual;
        this.pagamentoMensal = (valorImovel + jurosTotal) / prazoMeses;
    }

    public double totalPagamento(){
        return this.pagamentoMensal * ( prazoFinanciamento * 12 );
    }

    public double getPagamentoMensal(){
        return this.pagamentoMensal;
    }


}


