package main;

import Imoveis.Apartamento;
import Imoveis.Casa;
import Imoveis.Terreno;
import modelo.Financiamento;
import util.InterfaceUsuario;
import java.util.ArrayList;
import java.util.Scanner;


public class Main{

    public static void main(String[] args){

        ArrayList<Financiamento> financiamentos  = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);


        // PRIMEIRO FINANCIAMENTO (PADRAO)

        Financiamento novoImovel = new Financiamento();
        InterfaceUsuario interfaceUsuario = new InterfaceUsuario(novoImovel, scanner);

        interfaceUsuario.inserirValorImovel();
        interfaceUsuario.inserirPrazoFinanciamento();
        interfaceUsuario.inserirTaxaAnual();




        novoImovel.exibirDadosFinanciamento();
        financiamentos.add(novoImovel);



        // DEMAIS FINANCIAMENTOS (CASA, APARTAMENTO, TERRENO)

        System.out.println("FINANCIAMENTO CASA");
        System.out.println("Valor do imóvel: ");
        double valorCasa1= scanner.nextDouble();
        System.out.println("Prazo (EM ANOS): ");
        int prazoCasa1 = scanner.nextInt();
        System.out.println("Taxa de juros anual (EX: 0.08, 8%): ");
        double taxaCasa1 = scanner.nextDouble();

        financiamentos.add(new Casa(valorCasa1,prazoCasa1,taxaCasa1));
        scanner.nextLine();
        System.out.println("---------------------------------------");

        System.out.println("FINANCIAMENTO APARTAMENTO");
        System.out.println("Valor do imóvel: ");
        double valorAp1= scanner.nextDouble();
        System.out.println("Prazo (EM ANOS): ");
        int prazoAp1 = scanner.nextInt();
        System.out.println("Taxa de juros anual (EX: 0.08, 8%): ");
        double taxaAp1 = scanner.nextDouble();

        financiamentos.add(new Apartamento(valorAp1, prazoAp1, taxaAp1));
        scanner.nextLine();
        System.out.println("---------------------------------------");

        System.out.println("FINANCIAMENTO TERRENO");
        System.out.println("Valor do imóvel: ");
        double valorTerreno1= scanner.nextDouble();
        System.out.println("Prazo (EM ANOS): ");
        int prazoTerreno1 = scanner.nextInt();
        System.out.println("Taxa de juros anual (EX: 0.08, 8%): ");
        double taxaTerreno1 = scanner.nextDouble();

        financiamentos.add(new Terreno(valorTerreno1,prazoTerreno1,taxaTerreno1));
        scanner.nextLine();
        System.out.println("---------------------------------------");

        // EXIBE OS DADOS

        double somaValorImoveis = 0;
        double somaValorFinanciamento = 0;

        System.out.println("RESUMO GERAL");

        for (Financiamento f: financiamentos){
            f.exibirDadosFinanciamento();
            System.out.println();
            somaValorImoveis += f.getValorImovel();
            somaValorFinanciamento += f.totalPagamento();
        }

        System.out.println("\n-------------------------------------");
        System.out.println("TOTAL GERAL");
        System.out.printf("Soma dos valores dos imóveis: R$ %.2f\n", somaValorImoveis);
        System.out.printf("Soma dos valores dos financiamentos: R$ %.2f\n", somaValorFinanciamento);
        System.out.println("---------------------------------------");

        scanner.close();

    }
}