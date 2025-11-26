package Imoveis;

import logs.AumentoMaiorDoQueJurosException;
import modelo.Financiamento;

import java.io.Serializable;
import java.util.Locale;

public class Terreno extends Financiamento implements Serializable {

    private String tipoZona;


    public Terreno(double valorImovel, int prazoFinanciamento, double taxaJurosAnual, String tipoZona) {
        super(valorImovel, prazoFinanciamento, taxaJurosAnual);
        this.tipoZona = tipoZona;

    }

    // GETTERS E SETTERS
    public String getTipoZona(){
        return tipoZona;
    }

    // METODOS ABSTRATOS

    @Override
    public String textFileString(){
        return String.format(Locale.US,"TERRENO;%.2f;%.2f;%.2f;%d;%s",
                getValorImovel(),
                getValorFinanciamentoAproximado(),
                getTaxaJurosAnual(),
                getPrazoFinanciamento(),
                tipoZona);
    }
    @Override
    public double getPagamentoMensal() throws AumentoMaiorDoQueJurosException {
        double parcelaBase = super.getPagamentoMensal();
        return parcelaBase * 1.02;
    }

    // CLASSES IMPORTADAS DO METODO ABSTRATO
    @Override
    public double totalPagamento() throws AumentoMaiorDoQueJurosException {
        int prazoMeses = this.prazoFinanciamento * 12;
        return this.getPagamentoMensal() * prazoMeses;
    }

    @Override
    public void exibirDadosFinanciamento() throws AumentoMaiorDoQueJurosException {
        System.out.println("\n------------------- INFORMAÇÕES DO TERRENO ------------------------");
        String string = toString();
        System.out.println(string);
        System.out.println("---------------------------------------------------------------------");



        System.out.println("\n-------------- RESULTADO DO FINANCIAMENTO (TERRENO) ---------------");
        System.out.printf("Valor total do financiamento: R$ %.2f ", this.totalPagamento());
        System.out.println();
        System.out.printf("Valor do imóvel: R$ %.2f ", this.getValorImovel());
        System.out.println();
        System.out.printf(" Pagamento Mensal: R$ %.2f\n", this.getPagamentoMensal());
        System.out.println("---------------------------------------------------------------------");
    }


    @Override
    public String toString() {
        return  "| INFO TERRENO: " +
                "\n| Tipo de zona: " +
                getTipoZona() +
                " ";
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

    private double getValorFinanciamentoAproximado(){
        try{
            return totalPagamento();
        } catch (AumentoMaiorDoQueJurosException e){
            return 0.0;
        }











    }}




