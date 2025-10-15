package main;

import modelo.Financiamento;
import util.InterfaceUsuario;

public class Main{

    public static void main(String[] args){

        Financiamento novoImovel = new Financiamento();
        InterfaceUsuario interfaceUsuario = new InterfaceUsuario(novoImovel);

        interfaceUsuario.inserirValorImovel();
        interfaceUsuario.inserirPrazoFinanciamento();
        interfaceUsuario.inserirTaxaAnual();

        novoImovel.calcularPagamentoMensal();


        double total =novoImovel.totalPagamento();

        System.out.println("\n--- Resultado do Financiamento ---");
        System.out.printf(" Valor do Imovel: R$ %.1f\n", novoImovel.valorImovel);
        System.out.printf(" Pagamento Mensal: R$ %.1f\n", novoImovel.getPagamentoMensal());
        System.out.printf(" Pagamento Total: R$ %.1f\n", total);
        System.out.println("----------------------------------");
    }
}