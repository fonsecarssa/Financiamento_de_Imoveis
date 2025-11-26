package main;

import Imoveis.Apartamento;
import Imoveis.Casa;
import Imoveis.Terreno;
import logs.AumentoMaiorDoQueJurosException;
import modelo.Financiamento;
import util.InterfaceUsuario;


import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.util.Locale;

public class Main {

    private static final String ARQUIVO_TEXTO = "financiamentos.txt";
    private static final String ARQUIVO_SERIALIZADO = "financiamentos.ser";

                        //PERSISTENCIA
    // SALVA O ARQUIVO
    private static void salvarEmArquivo(ArrayList<Financiamento> lista){
        System.out.println("SALVANDO EM ARQUIVO..: " + ARQUIVO_TEXTO);

        try(PrintWriter writer = new PrintWriter((new FileWriter(ARQUIVO_TEXTO)))){
            for (Financiamento f : lista){
                writer.println(f.textFileString());

            }
            System.out.println("DADOS SALVOS COM SUCESSO!");
        } catch (IOException e){
            System.out.println("ERRO AO SALVAR NO ARQUIVO: " + e.getMessage());
        } catch (AumentoMaiorDoQueJurosException e){
            System.err.println("ERRO DE CALCULO AO SALVAR O FINANCIAMENTO");
        }

    }

    //LEITOR DE ARQUIVO
    private static ArrayList<Financiamento> lerArquivo(){
         System.out.println("LENDO ARQUIVO...");
         ArrayList<Financiamento> listaLida = new ArrayList<>();
         File arquivo = new File(ARQUIVO_TEXTO);

         if (!arquivo.exists()){
             System.out.println("ARQUIVO NAO ENCONTRADO.");
             return listaLida;
         }


        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))){
            String linha;

            while ((linha = reader.readLine()) !=null){
                String linhaFormatada = linha.replace(",", ".");
                String[] partes = linhaFormatada.split(";");
                if (partes.length < 5) continue;

                String tipo = partes[0];

                double valorImovel = Double.parseDouble(partes[1]);
                double taxaJuros = Double.parseDouble(partes[3]);
                int prazo = Integer.parseInt(partes[4]);

                Financiamento novoFinanciamento = null;

                switch (tipo) {
                    case "CASA":
                        if (partes.length >= 7) {
                            double areaConstruida = Double.parseDouble(partes[5].replace(",", "."));
                            double areaTerreno = Double.parseDouble(partes[6].replace(",", "."));
                            novoFinanciamento = new Casa(valorImovel, prazo, taxaJuros, areaConstruida, areaTerreno);
                        }
                        break;
                    case "APARTAMENTO":
                        if (partes.length >= 6) {
                            int andar = Integer.parseInt(partes[5]);
                            novoFinanciamento = new Apartamento(valorImovel, prazo, taxaJuros, andar);
                        }
                        break;
                    case "TERRENO":
                        if (partes.length >= 6) {
                            String tipoZona = partes[5];
                            novoFinanciamento = new Terreno(valorImovel, prazo, taxaJuros, tipoZona);
                        }
                        break;
                }

                if (novoFinanciamento != null) {
                    listaLida.add(novoFinanciamento);
                }
            }
            System.out.println( listaLida.size() + " FINANCIAMENTOS LIDOS E RECRIADOS DO ARQUIVO.");

        } catch (IOException | NumberFormatException e) {
            System.err.println("ERRO AO LER OU PROCESSAR O ARQUIVO: " + e.getMessage());
        }
        return listaLida;
    }

    // SERIALIZADOR
    private static void serializar(ArrayList<Financiamento> lista) {
        System.out.println("SERIALIZANDO...");
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO_SERIALIZADO))) {
            oos.writeObject(lista);
            System.out.println("SERIALIZADO COM SUCESSO EM: " + ARQUIVO_SERIALIZADO);
        } catch (IOException e) {
            System.err.println("ERRO AO SERIALIZAR: " + e.getMessage());
        }
    }

    // DESSERIALIZADOR
    private static ArrayList<Financiamento> desserializar() {
        System.out.println("DESERIALIZANDO... ---");
        ArrayList<Financiamento> listaLida = new ArrayList<>();
        File arquivo = new File(ARQUIVO_SERIALIZADO);

        if (!arquivo.exists()) {
            System.out.println("ARQUIVO SERIALIZADO NAO ENCONTRADO.");
            return listaLida;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO_SERIALIZADO))) {

            ArrayList<Financiamento> tempLista = (ArrayList<Financiamento>) ois.readObject();
            listaLida = tempLista;
            System.out.println("DESSERIALIZADO COM SUCESSO. " + listaLida.size() + " ITENS RECUPERADOS.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("ERRO AO DESSERIALIZAR: " + e.getMessage());
        }
        return listaLida;
    }

                        // FIM DA PERSISTENCIa

    // EXIBE RESULTADO
    private static void exibirResultados(ArrayList<Financiamento> lista, String titulo){
        System.out.println("--------------- RESULTADO DA " + titulo + " ---------------------");

        double somaValorImoveis = 0;
        double somaValorFinanciamento = 0;

        if (lista.isEmpty()){
            System.out.println("LISTA ESTÁ VAZIA");
            return;
        }

        for (Financiamento f: lista){
            try{
                f.exibirDadosFinanciamento();
                System.out.println();

                somaValorImoveis += f.getValorImovel();
                somaValorFinanciamento += f.totalPagamento();
            } catch (AumentoMaiorDoQueJurosException e){
                System.err.println("----------------------------------------------------------------");
                System.err.println("ERRO (ITEM DA" + titulo + "): " + e.getMessage());
            } catch (Exception e){
                System.err.println("ERRO INESPERADO AO RECEBER FINANCIAMENTO (" + titulo + "): " + e.getMessage());
            }
        }

        System.out.println("\n------------------------------------------------------------------");
        System.out.println("---------------------------- TOTAL GERAL (" + titulo + ") -------------------------- ");
        System.out.printf("| SOMA DOS VALORES DOS IMOVEIS: R$ %.2f\n", somaValorImoveis);
        System.out.printf("| SOMA DOS VALORES DOS FINANCIAMENTOS: R$ %.2f\n", somaValorFinanciamento);
        System.out.println("---------------------------------------------------------------------");

    }

    // MAIN
    public static void main(String[] args){

        ArrayList<Financiamento> financiamentos = new ArrayList<>();
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

        // FINANCIAMENTOS

        // CASA
        System.out.println("CASA:");
        financiamentos.add(new Casa(
                InterfaceUsuario.inserirValorImovel(scanner),
                InterfaceUsuario.inserirPrazoFinanciamento(scanner),
                InterfaceUsuario.inserirTaxaAnual(scanner),
                InterfaceUsuario.inserirAreaConstruida(scanner),
                InterfaceUsuario.inserirAreaTerreno(scanner)
        ));

        // APARTAMENTO
        System.out.println("APARTAMENTO: ");
        System.out.print("Número do apartamento: ");
        int numAndaApartment = scanner.nextInt();
        scanner.nextLine();

        financiamentos.add(new Apartamento(
                InterfaceUsuario.inserirValorImovel(scanner),
                InterfaceUsuario.inserirPrazoFinanciamento(scanner),
                InterfaceUsuario.inserirTaxaAnual(scanner),
                numAndaApartment
        ));

        // TERRENO
        System.out.println("TERRENO");
        System.out.print("Tipo de zona: ");
        String tipoZona = scanner.nextLine();

        financiamentos.add(new Terreno(
                InterfaceUsuario.inserirValorImovel(scanner),
                InterfaceUsuario.inserirPrazoFinanciamento(scanner),
                InterfaceUsuario.inserirTaxaAnual(scanner),
                tipoZona
        ));

        // EXIBE OS RESULTADOS

        exibirResultados(financiamentos, "LISTA ORIGINAL");

        // ARQUIVO DE TEXTO (PERSISTENCIA)

        System.out.println();
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-= PERSISTENCIA =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.println();
        salvarEmArquivo(financiamentos);
        ArrayList<Financiamento> listaLidaTexto = lerArquivo();
        exibirResultados(listaLidaTexto, "LISTA LIDA");
        System.out.println();


        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-= SERIALIZAÇÃO =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        // SERIALIZAÇÃO

        System.out.println();
        serializar(financiamentos);
        ArrayList<Financiamento> listaDesserializada = desserializar();
        exibirResultados(listaDesserializada, "LISTA LIDA (SERIALIZADA)");
        System.out.println();



    }
}


































