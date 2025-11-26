package modelo;

import logs.AumentoMaiorDoQueJurosException;
import java.io.Serializable;

public abstract class Financiamento implements Serializable{

    // pra evitar erro de compilação
    private static final long svUID = 1L;

    private double valorImovel;
    protected int prazoFinanciamento;
    protected double taxaJurosAnual;

    protected double pagamentoMensal;

    public Financiamento(double valorImovel, int prazoFinanciamento, double taxaJurosAnual){
        this.valorImovel = valorImovel;
        this.prazoFinanciamento = prazoFinanciamento;
        this.taxaJurosAnual = taxaJurosAnual;
    }



    // METODOS ABSTRATOS
    abstract public void calcularPagamentoMensal() ;


    abstract public double totalPagamento() throws AumentoMaiorDoQueJurosException;


    abstract public void exibirDadosFinanciamento() throws AumentoMaiorDoQueJurosException;



    // METODOS CONCRETOS
    public double getPagamentoMensal() throws AumentoMaiorDoQueJurosException {
        if (this.pagamentoMensal == 0 && this.valorImovel > 0 && this.prazoFinanciamento > 0) {
            this.calcularPagamentoMensal();
        }
        return this.pagamentoMensal;

    }

    public double getTaxaJurosAnual() {return taxaJurosAnual;}

    public int getPrazoFinanciamento(){return prazoFinanciamento; }


    public void setValorImovel(double valorImovel) {
        this.valorImovel = valorImovel;
        this.pagamentoMensal = 0;
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

    public abstract String textFileString() throws AumentoMaiorDoQueJurosException;



}


