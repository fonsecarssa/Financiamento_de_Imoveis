#  Sistema de Simulação de Financiamento de Imóveis (POO em Java)

##  Visão Geral

Este projeto, desenvolvido para a disciplina de **Programação Orientação a Objetos (POO)**, simula o processo de financiamento para diferentes tipos de imóveis (Casas, Apartamentos e Terrenos). O objetivo principal foi aplicar e demonstrar conceitos avançados de POO, como herança, polimorfismo, classes abstratas e tratamento de exceções, utilizando a linguagem **Java**.

---

##  Conceitos de POO Aplicados

O projeto foi estruturado para demonstrar o domínio dos seguintes pilares da Programação Orientada a Objetos:

* **Classes Abstratas e Herança:** A classe `Financiamento` é abstrata e define o contrato base para todos os tipos de imóveis. As classes `Casa`, `Apartamento` e `Terreno` herdam de `Financiamento`.
* **Polimorfismo:** Métodos como `calcularPagamentoMensal()` e `exibirDadosFinanciamento()` são sobrescritos em cada subclasse para implementar lógicas específicas, permitindo que a lista principal (`ArrayList<Financiamento>`) trate todos os objetos de forma uniforme.
* **Encapsulamento:** Utilização de modificadores de acesso (`private`, `protected`) e métodos `getter`/`setter` para proteger os atributos internos das classes.
* **Tratamento de Exceções:** Implementação de classes de exceção customizadas (ex: `AumentoMaiorDoQueJurosException`) para lidar com regras de negócio específicas durante o cálculo financeiro.

---

##  Funcionalidades Principais do Sistema

O sistema executa a simulação e persistência de dados.

### 1. Simulação de Financiamentos

O cálculo da parcela e do valor total é baseado em:

* **Valor do Imóvel**
* **Prazo do Financiamento** (em meses)
* **Taxa de Juros Anual**

**Regras Específicas:**

| Tipo de Imóvel | Diferencial no Cálculo |
| :--- | :--- |
|  **Casa** | Acrescenta uma taxa fixa mensal na parcela e aplica uma regra de exceção para limitar o aumento dos juros. |
|  **Apartamento** | Não possui regras de juros adicionais, mas armazena e processa o número do andar. |
|  **Terreno** | Calcula juros com base no tipo de zona (ex: residencial vs. comercial). |

### 2. Persistência de Dados

O programa armazena os dados dos financiamentos para comprovar a persistência entre as execuções.

* **Arquivo de Texto (`financiamentos.txt`):** Gravação e leitura de dados formatados em formato CSV customizado (separado por ponto e vírgula), demonstrando manipulação de arquivos de texto (`PrintWriter`, `BufferedReader`).
* **Serialização (`financiamentos.ser`):** Gravação e recuperação de objetos Java completos, demonstrando serialização (`ObjectOutputStream` e `ObjectInputStream`).

---

##  Tecnologias Utilizadas

* **Linguagem:** Java
* **Ferramentas:** Classes `java.io.*` e `java.util.*` (Scanner, Locale, Listas)

---

##  Como Rodar o Projeto

1.  **Clone o Repositório:**
    ```bash
    git clone [https://github.com/fonsecarssa/Financiamento_de_Imoveis.git]
    ```
2.  **Abra na IDE:** Importe o projeto para sua IDE Java favorita (IntelliJ IDEA, Eclipse ou VS Code).
3.  **Execute a Classe Principal:** Execute a classe `Main.java` para iniciar a simulação.
4.  **Interação:** O programa solicitará a entrada de dados para um exemplo de Casa, Apartamento e Terreno, e em seguida, exibirá os resultados dos cálculos e comprovará a leitura dos arquivos de persistência.

---

##  Autor

**[Raissa dos Santos Fonseca]** - *[https://www.linkedin.com/in/raissa-fonseca--/]*
