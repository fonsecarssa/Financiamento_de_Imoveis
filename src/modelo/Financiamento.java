package modelo;

public class Financiamento{

    private double valorImovel;
    private int prazoFinanciamento;
    private double taxaJurosAnual;

    private double pagamentoMensal;

    public Financiamento(double valorImovel, int prazoFinanciamento, double taxaJurosAnual){
        this.valorImovel = valorImovel;
        this.prazoFinanciamento = prazoFinanciamento;
        this.taxaJurosAnual = taxaJurosAnual;
    }

    public Financiamento() {
    }

    public void calcularPagamentoMensal(){
        double taxaMensal = this.taxaJurosAnual / 12.0;
        int prazoMeses = this.prazoFinanciamento * 12;

        if (taxaMensal == 0) {
            this.pagamentoMensal = this.valorImovel / prazoMeses;
        } else {
            double fatorPotencia = Math.pow(1 + taxaMensal, prazoMeses);
            double num = taxaMensal * fatorPotencia;
            double den = fatorPotencia - 1;
            this.pagamentoMensal = this.valorImovel * (num / den);
        }
    }

    public double totalPagamento(){
        return this.pagamentoMensal * ( prazoFinanciamento * 12 );
    }

    public double getPagamentoMensal(){
        return this.pagamentoMensal;
    }


    public void setValorImovel(double valorImovel) {
        this.valorImovel = valorImovel;
    }

    public void setTaxaJurosAnual(double taxaJurosAnual) {
        this.taxaJurosAnual = taxaJurosAnual;
    }

    public void setPrazoFinanciamento(int prazoFinanciamento) {
        this.prazoFinanciamento = prazoFinanciamento;
    }

    public double getValorImovel(){
        return this.valorImovel;
    }

    public void exibirDadosFinanciamento(){
        this.calcularPagamentoMensal();
        System.out.printf("Valor total do financiamento: R$ %.2f ", this.totalPagamento());
        System.out.println();
        System.out.printf("Valor do imóvel: R$ %.2f ", getValorImovel());
    }


}


