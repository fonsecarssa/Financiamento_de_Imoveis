package Imoveis;

import modelo.Financiamento;
import logs.AumentoMaiorDoQueJurosException;

import java.io.Serializable;
import java.util.Locale;

public class Casa extends Financiamento implements Serializable {



    private double areaConstruida;
    private double areaTerreno;

    private final double taxaFixaMensal = 80.00;

    public Casa(double valorImovel, int prazoFinanciamento, double taxaJurosAnual, double areaConstruida, double areaTerreno) {
        super(valorImovel, prazoFinanciamento, taxaJurosAnual);
        this.areaConstruida = areaConstruida;
        this.areaTerreno = areaTerreno;
    }


    // chamar os metodos abstratos com o override

    @Override
    public String textFileString() {
        return String.format(Locale.US, "CASA;%.2f;%.2f;%.2f;%d;%.2f;%.2f",
                getValorImovel(),
                getValorFinanciamentoAproximado(),
                getTaxaJurosAnual(),
                getPrazoFinanciamento(),
                areaConstruida,
                areaTerreno);
    }
    @Override
    public double totalPagamento() throws AumentoMaiorDoQueJurosException {
        int prazoMeses = this.prazoFinanciamento * 12;
        return this.getPagamentoMensal() * prazoMeses;
    }


    @Override
    public void calcularPagamentoMensal() {
        double taxaMensal = (this.taxaJurosAnual / 100.0) / 12;
        int prazoMeses = this.prazoFinanciamento * 12;

        if (taxaMensal == 0) {
            this.pagamentoMensal = this.getValorImovel() / prazoMeses;
        } else {
            double fatorPotencia = Math.pow(1 + taxaMensal, prazoMeses);
            double num = taxaMensal * fatorPotencia;
            double den = fatorPotencia - 1;
            this.pagamentoMensal = this.getValorImovel() * (num / den);
        }



    }

    @Override
    public void exibirDadosFinanciamento ()  throws AumentoMaiorDoQueJurosException{
        System.out.println("---------------------INFORMAÇÕES DA CASA-----------------------------");
        String string = toString();
        System.out.println(string);

        System.out.println("---------------------------------------------------------------------");

        System.out.println("\n--- RESULTADO DO FINANCIAMENTO (CASA) ---");
        System.out.printf("Valor total do financiamento: R$ %.2f ", this.totalPagamento());
        System.out.println();
        System.out.printf("Valor do imóvel: R$ %.2f ", this.getValorImovel());
        System.out.println();
        System.out.printf(" Pagamento Mensal: R$ %.2f\n", this.getPagamentoMensal());
        System.out.println("---------------------------------------------------------------------");
    }

    // GETTERS
    @Override
    public double getPagamentoMensal() throws AumentoMaiorDoQueJurosException {
        double pagamentoBase = super.getPagamentoMensal();

        int prazoMeses = this.prazoFinanciamento * 12;


        double jurosBase = (pagamentoBase * prazoMeses) - this.getValorImovel();
        double jurosMensaisAprox = jurosBase / prazoMeses;


        if (taxaFixaMensal > (jurosMensaisAprox / 2.0)){
            throw new AumentoMaiorDoQueJurosException(
                    "ERRO: O acréscimo de R$ " +taxaFixaMensal +
                            " é maior que a metade dos juros mensais calculados " +
                            "(R$" + String.format("%.2f", jurosMensaisAprox/2.0)+ ")."
            );
        }
        return pagamentoBase + taxaFixaMensal;
    }

    public double getAreaConstruida() {
        return areaConstruida;
    }


    public double getAreaTerreno() {
        return areaTerreno;
    }


    public double getTaxaFixaMensal() {
        return taxaFixaMensal;
    }

    private double getValorFinanciamentoAproximado() {
        try{
            return totalPagamento();
        } catch (AumentoMaiorDoQueJurosException e){
            return 0.0;
        }
    }

    @Override
    public String toString() {
        return  "| INFO CASA: " +
                "\n | Área (m²): " + getAreaConstruida() +
                "\n | Área do terreno (m²): " + getAreaTerreno()+
                "\n | Taxa fixa mensal aplicada: " + getTaxaFixaMensal() + '|';
    }
}
