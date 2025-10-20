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
        double imovelVal;

        do {
            System.out.print("Digite o valor do imovel:  ");
            imovelVal = input.nextDouble();
            if (imovelVal <= 0){
                System.out.println("Valor inválido.Digite novamente.");
            }
        } while(imovelVal <= 0);

        imovel.setValorImovel(imovelVal);
    }

    // inserir o prazo de financiamento
    public void inserirPrazoFinanciamento(){
        int financiamentoPraz;

        do{
            System.out.print("Prazo de financiamento (em anos) :  ");
            financiamentoPraz = input.nextInt();

            if (financiamentoPraz <=0){
                System.out.println("Valor inválido.Digite novamente.");
            }
        } while (financiamentoPraz <=0);

        imovel.setPrazoFinanciamento(financiamentoPraz);

    }

    // inserir taxa de juros
    public void inserirTaxaAnual(){
        double taxAnual;

        do{
            System.out.print("Taxa de juros: ");
            taxAnual = input.nextDouble();
            if (taxAnual<=0){
                System.out.println("Valor inválido.Digite novamente.");
            }
        } while(taxAnual <= 0);

        imovel.setTaxaJurosAnual(taxAnual);
    }

    public static void main(String[] args) {
        Financiamento novoFinanciamento = new Financiamento();

        InterfaceUsuario interfaceApp = new InterfaceUsuario(novoFinanciamento);


        interfaceApp.inserirValorImovel();
        interfaceApp.inserirTaxaAnual();
        interfaceApp.inserirPrazoFinanciamento();

        novoFinanciamento.exibirDadosFinanciamento();
    }
}