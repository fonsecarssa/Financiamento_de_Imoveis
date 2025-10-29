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

    public Financiamento(double valorimovel, int prazofinanciamento, double taxajurosanual, double pagamentomensal) {
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



    public double getPagamentoMensal(){
        if (this.pagamentoMensal == 0 && this.valorImovel > 0 && this.prazoFinanciamento > 0) {
            this.calcularPagamentoMensal();
        }
        return this.pagamentoMensal;
    }

    public double totalPagamento(){
        int prazoMeses = this.prazoFinanciamento * 12;
        return this.getPagamentoMensal() * prazoMeses;
    }

    public void setValorImovel(double valorImovel) {
        this.valorImovel = valorImovel;
        this.pagamentoMensal = 0;
    }

    public void setTaxaJurosAnual(double taxaJurosAnual) {
        this.taxaJurosAnual = taxaJurosAnual;
    }

    public double getTaxaJurosAnual() {
        return taxaJurosAnual;
    }

    public void setPrazoFinanciamento(int prazoFinanciamento) {
        this.prazoFinanciamento = prazoFinanciamento;
    }



    public double getValorImovel(){
        return this.valorImovel;
    }

    public void exibirDadosFinanciamento(){
        System.out.println("\n--- Resultado do Financiamento ---");
        totalPagamento();
        System.out.printf("Valor total do financiamento: R$ %.2f ", this.totalPagamento());
        System.out.println();
        System.out.printf("Valor do imóvel: R$ %.2f ", this.getValorImovel());
        System.out.println();
        getPagamentoMensal();
        System.out.printf(" Pagamento Mensal: R$ %.2f\n", this.getPagamentoMensal());
        System.out.println("----------------------------------");
    }


}


