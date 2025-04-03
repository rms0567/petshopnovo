package br.com.petshop;

import java.util.Scanner;

public class PetShop {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GerenciadorAnimais gerenciador = new GerenciadorAnimais();
        int opcao;

        // Loop do menu
        do {
            System.out.println("\n===============================");
            System.out.println("== MENU DE CADASTRO DE PETSHOP ==");
            System.out.println("=================================");
            System.out.println("= 1️ Cadastrar GATO              =");
            System.out.println("= 2️ Cadastrar CACHORRO          =");
            System.out.println("= 3️ Exibir Todos os ANIMAIS     =");
            System.out.println("= 4️ Localizar ANIMAL POR NOME   =");
            System.out.println("= 0️ Sair                        =");
            System.out.print("=➡ Escolha uma opção:          =");

            // Lê a opção do menu
            while (!scanner.hasNextInt()) {  // Garante que o usuário insira um número válido
                System.out.println("⚠ Por favor, insira um número válido.");
                scanner.next(); // Limpa a entrada inválida
            }
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer

            switch (opcao) {
                case 1:
                    gerenciador.cadastrarAnimal(); // Cadastrar Gato
                    break;
                case 2:
                    gerenciador.cadastrarAnimal(); // Cadastrar Cachorro
                    break;
                case 3:
                    gerenciador.exibirAnimais(); // Exibir Todos os Animais
                    break;
                case 4:
                    gerenciador.localizarAnimal(); // Localizar Animal por Nome
                    break;
                case 0:
                    System.out.println("🔴 Saindo do sistema... Até logo!");
                    break;
                default:
                    System.out.println("⚠ Opção inválida! Tente novamente.");
            }
        } while (opcao != 0);

        scanner.close(); // Fecha o scanner ao final
    }
}
