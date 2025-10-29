package util;

import modelo.Financiamento;

import java.util.Scanner;


public class InterfaceUsuario{
    private Scanner scanner;

    private Financiamento imovel;

    public InterfaceUsuario(Financiamento Financiamento, Scanner scanner ){

        this.imovel = Financiamento;
        this.scanner = scanner;
    }

    // inserir valor do imovel
    public void inserirValorImovel(){
        double imovelVal;

        do {
            System.out.print("Digite o valor do imovel:  ");

            imovelVal = this.scanner.nextDouble();

            if (imovelVal <= 0) {
                System.out.println("Valor inválido. O valor deve ser maior que zero. Digite novamente.");
            }
        } while(imovelVal <= 0);

        this.imovel.setValorImovel(imovelVal);
        this.scanner.nextLine();
        }


    // inserir o prazo de financiamento
    public void inserirPrazoFinanciamento(){
        int financiamentoPraz;

        do{
            System.out.print("Prazo de financiamento (em anos) :  ");
            financiamentoPraz = scanner.nextInt();

            if (financiamentoPraz <=0){
                System.out.println("Valor inválido.Digite novamente.");
            }
        } while (financiamentoPraz <=0);

        imovel.setPrazoFinanciamento(financiamentoPraz);
        this.scanner.nextLine();

    }

    // inserir taxa de juros
    public void inserirTaxaAnual(){
        double taxAnual;
        // verificar se a taxa é muito alta
        do{
            System.out.print("Taxa de juros MENSAL (em %): ");
            taxAnual = scanner.nextDouble();
            if (taxAnual<=0){
                System.out.println("Valor inválido.Digite novamente.");
            }
        } while(taxAnual <= 0);

        imovel.setTaxaJurosAnual(taxAnual);
        this.scanner.nextLine();
    }


    public static void main(String[] args) {
        Scanner Scanner1 = new Scanner(System.in);
        Financiamento novoFinanciamento = new Financiamento(0,0,0);

        InterfaceUsuario interfaceApp = new InterfaceUsuario(novoFinanciamento, Scanner1);


        interfaceApp.inserirValorImovel();
        interfaceApp.inserirTaxaAnual();
        interfaceApp.inserirPrazoFinanciamento();

        novoFinanciamento.exibirDadosFinanciamento();
        Scanner1.close();
    }
}