package util;

import modelo.Financiamento;

import java.util.Scanner;
public class InterfaceUsuario{
    private Scanner input = new Scanner(System.in);

    private Financiamento imovel;

    public InterfaceUsuario(Financiamento Financiamento){
        this.imovel = Financiamento;
    }

    // inserir valor do imovel
    public void inserirValorImovel(){
        System.out.print("Digite o valor do imovel:  ");
        imovel.valorImovel = input.nextDouble();
    }

    // inserir o prazo de financiamento
    public void inserirPrazoFinanciamento(){
        System.out.print("Prazo de modelo.Financiamento (em anos) :  ");
        imovel.prazoFinanciamento = input.nextInt();

    }

    // inserir taxa de juros
    public void inserirTaxaAnual(){
        System.out.print("Taxa de juros: ");
        imovel.taxaJurosAnual = input.nextDouble();
    }


}