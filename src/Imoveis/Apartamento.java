package Imoveis;

import logs.AumentoMaiorDoQueJurosException;
import modelo.Financiamento;

import java.io.Serializable;
import java.util.Locale;

public class Apartamento extends Financiamento implements Serializable {

    private static final int numVagasGaragem = 60;
    private final int numAndarApartamento;


    public Apartamento(double valorImovel, int prazoFinanciamento, double taxaJurosAnual, int numAndarApartamento){
        super(valorImovel, prazoFinanciamento, taxaJurosAnual);
        this.numAndarApartamento = numAndarApartamento;
    }

    // OVERRIDE DOS METODOS ABSTRATOS DA CLASSE FINANCIAMENTO

    @Override
    public String textFileString(){
        return String.format(Locale.US, "APARTAMENTO;%.2f;%.2f;%.2f;%d;%d", getValorImovel(),
                getValorFinanciamentoAproximado(),
                getTaxaJurosAnual(),
                getPrazoFinanciamento(), numAndarApartamento);
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
    public double totalPagamento() throws AumentoMaiorDoQueJurosException {
        int prazoMeses = this.prazoFinanciamento * 12;
        return this.getPagamentoMensal() * prazoMeses;
    }

    @Override
    public void exibirDadosFinanciamento() throws AumentoMaiorDoQueJurosException {
        System.out.println("\n------------------INFORMAÇÕES DO APARTAMENTO-----------------------");
        String string = toString();
        System.out.println(string);

        System.out.println("---------------------------------------------------------------------");

        System.out.println("\n------------ RESULTADO DO FINANCIAMENTO (APARTAMENTO)---------------");
        System.out.printf("Valor total do financiamento: R$ %.2f ", this.totalPagamento());
        System.out.println();
        System.out.printf("Valor do imóvel: R$ %.2f ", this.getValorImovel());
        System.out.println();
        System.out.printf(" Pagamento Mensal: R$ %.2f\n", this.getPagamentoMensal());
        System.out.println("---------------------------------------------------------------------");
    }


    // GETTERS
    private double getValorFinanciamentoAproximado(){
        try{
            return totalPagamento();
        } catch (AumentoMaiorDoQueJurosException e){
            return 0.0;
        }
    }

    public int getNumAndarApartamento() {
        return numAndarApartamento;
    }

    @Override // ver se essa benção vai funcionar
    public String toString() {
        return "| INFO APARTAMENTO: " +
                "\n | Quantidade de vagas no estacionamento: " + numVagasGaragem +
                "\n | Numero do andar: " + getNumAndarApartamento() +
                " ";
    }
}
