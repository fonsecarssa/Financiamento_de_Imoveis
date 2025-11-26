package util;

import modelo.Financiamento;
import java.util.Scanner;



public class InterfaceUsuario{
    private Scanner scanner;

    private Financiamento imovel;



    // inserir valor do imovel
    public static double inserirValorImovel(Scanner scanner){
        double imovelVal = 0.0;
        boolean valid = false;

        do {
            System.out.print("Digite o valor do imovel:  ");

            try{
                imovelVal = scanner.nextDouble();
                if (imovelVal <= 0) {
                    System.out.println("Valor inválido. O valor deve ser maior que zero. Digite novamente.");
                } else{
                    valid = true;
                }
            } catch (java.util.InputMismatchException e){
                System.err.println("ERRO: DIGITE APENAS NUMEROS");
                scanner.nextLine();
            }

        } while(!valid);


        scanner.nextLine();
        return imovelVal;
    }


    // inserir o prazo de financiamento
    public static int  inserirPrazoFinanciamento(Scanner scanner){
        int financiamentoPraz = 0;
        boolean valid = false;

        do{
            System.out.print("Prazo de financiamento (em anos) :  ");

            try{
                financiamentoPraz = scanner.nextInt();

                if (financiamentoPraz <=0){
                    System.out.println("Valor inválido.Digite novamente.");
                } else{
                    valid = true;
                }
            } catch (java.util.InputMismatchException e){
                System.err.println("ERRO: ENTRADA INVÁLIDA, DIGITE APENAS NUMEROS INTEIROS!");
                scanner.nextLine();
            }

        } while (!valid);

        scanner.nextLine();
        return financiamentoPraz;

    }

    // inserir taxa de juros
    public static double inserirTaxaAnual(Scanner scanner){
        double taxAnual = 0.0;
        boolean valid = false;
        // verificar se a taxa é muito alta
        do{

            try{
                System.out.print("Taxa de juros MENSAL (em %): ");
                taxAnual = scanner.nextDouble();
                if (taxAnual<=0){
                    System.out.println("Valor inválido.Digite novamente.");
                } else{
                    valid = true;
                }
            } catch (java.util.InputMismatchException e){
                System.err.println("ERRO: ENTRADA INVÁLIDA, DIGITE APENAS NUMEROS!");
                scanner.nextLine();
                valid = false;
            }

        } while(taxAnual <=0 || !valid);


        scanner.nextLine();
        return taxAnual;
    }


    public static double inserirAreaConstruida(Scanner scanner){
        double areaConstruida1 = 0;
        boolean valid = false;
        do{
            System.out.println("Área construída: ");
            try{
                areaConstruida1 = scanner.nextInt();

                if (areaConstruida1 <=0){
                    System.out.println("Valor inválido. Digite novamente.");
                    valid = false;
                } else{
                    valid = true;
                }
            } catch (java.util.InputMismatchException e ){
                System.err.println("ERRO: ENTRADA INVÁLIDA, DIGITE APENAS NUMEROS.");
                valid = false;
                scanner.nextLine();
            }

        } while (!valid);

        scanner.nextLine();
        return areaConstruida1;
    }

    public static double inserirAreaTerreno(Scanner scanner){
        int areaTerreno1 = 0;
        boolean valid = false;

        do{
            System.out.println("Área do terreno: ");
            try{
                areaTerreno1 = scanner.nextInt();

                if (areaTerreno1 <=0){
                    System.out.println("Valor inválido. Digite novamente");
                    valid = false;
                } else{
                    valid = true;
                }
            } catch(java.util.InputMismatchException e ){
                System.err.println("ERRO: ENTRADA INVÁLIDA, DIGITE APENAS NUMEROS.");
                scanner.nextLine();

            }
        } while (!valid);

        scanner.nextLine();
        return areaTerreno1;
    }
}