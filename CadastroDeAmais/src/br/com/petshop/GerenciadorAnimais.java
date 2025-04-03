package br.com.petshop;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GerenciadorAnimais {
    private List<Animal> listaAnimais;
    private Scanner scanner;
    private final String ARQUIVO_CSV = "ANIMAIS_CSV.csv";

    public GerenciadorAnimais() {
        this.listaAnimais = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    // Método para cadastrar um animal (Gato ou Cachorro)
    public void cadastrarAnimal() {
        System.out.print("Digite o tipo de animal (Gato/Cachorro): ");
        String tipo = scanner.nextLine().trim();

        System.out.print("Digite o nome do " + tipo + ": ");
        String nome = scanner.nextLine().trim();

        int idade = -1;
        while (idade < 0) {
            System.out.print("Digite a idade do " + tipo + ": ");
            try {
                idade = Integer.parseInt(scanner.nextLine().trim());
                if (idade < 0) {
                    System.out.println("⚠ Idade não pode ser negativa!");
                }
            } catch (NumberFormatException e) {
                System.out.println("⚠ Idade inválida! Digite um número inteiro.");
            }
        }

        if (tipo.equalsIgnoreCase("Gato")) {
            System.out.print("Cor do Pelo do Gato: ");
            String corPelo = scanner.nextLine().trim();
            listaAnimais.add(new Gato(nome, idade, corPelo));
            System.out.println("✅ Gato cadastrado com sucesso!");
        } else if (tipo.equalsIgnoreCase("Cachorro")) {
            System.out.print("Raça do Cachorro: ");
            String raca = scanner.nextLine().trim();
            listaAnimais.add(new Cachorro(nome, idade, raca));
            System.out.println("✅ Cachorro cadastrado com sucesso!");
        } else {
            System.out.println("⚠ Tipo de animal inválido.");
        }
    }

    // Método para exibir todos os animais cadastrados
    public void exibirAnimais() {
        if (listaAnimais.isEmpty()) {
            System.out.println("⚠ Nenhum animal cadastrado!");
        } else {
            System.out.println("\n🐾 LISTA DE ANIMAIS CADASTRADOS 🐾");
            for (Animal animal : listaAnimais) {
                animal.exibirInfo();
                System.out.println(" --------------------------");
            }
        }
    }

    // Método para localizar um animal por nome
    public void localizarAnimal() {
        System.out.print("Digite o nome do animal que deseja localizar: ");
        String nome = scanner.nextLine().trim();
        boolean encontrado = false;

        System.out.println("\n🔎 RESULTADO DA BUSCA:");
        for (Animal animal : listaAnimais) {
            if (animal.getNome().equalsIgnoreCase(nome)) {
                animal.exibirInfo();
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("⚠ Nenhum animal encontrado com o nome '" + nome + "'.");
        }
    }

    // Método para salvar os animais em um arquivo CSV
    public void salvarAnimais() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_CSV))) {
            for (Animal animal : listaAnimais) {
                writer.write(animal.toCSV());
                writer.newLine();
            }
            System.out.println("✅ Animais salvos com sucesso no arquivo CSV.");
        } catch (IOException e) {
            System.out.println("⚠ Erro ao salvar no arquivo CSV: " + e.getMessage());
        }
    }

    // Método para carregar os animais de um arquivo CSV
    public void carregarAnimais() {
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_CSV))) {
            String linha;
            listaAnimais.clear(); // Limpa a lista antes de carregar do arquivo

            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");

                if (dados.length == 4) {
                    try {
                        String tipo = dados[0].trim();
                        String nome = dados[1].trim();
                        int idade = Integer.parseInt(dados[2].trim());
                        String atributo = dados[3].trim();

                        if (tipo.equalsIgnoreCase("Gato")) {
                            listaAnimais.add(new Gato(nome, idade, atributo));
                        } else if (tipo.equalsIgnoreCase("Cachorro")) {
                            listaAnimais.add(new Cachorro(nome, idade, atributo));
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("⚠ Erro ao converter idade para número: " + e.getMessage());
                    }
                }
            }
            System.out.println("✅ Animais carregados do arquivo CSV.");
        } catch (IOException e) {
            System.out.println("⚠ Erro ao carregar arquivo CSV: " + e.getMessage());
        }
    }
}

// Classe base Animal
abstract class Animal {
    private String nome;
    private int idade;

    public Animal(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public void exibirInfo() {
        System.out.println("Nome: " + nome + ", Idade: " + idade);
    }

    public abstract String toCSV(); // Método abstrato para ser implementado nas subclasses
}

// Classe Gato
class Gato extends Animal {
    private String corPelo;

    public Gato(String nome, int idade, String corPelo) {
        super(nome, idade);
        this.corPelo = corPelo;
    }

    @Override
    public void exibirInfo() {
        super.exibirInfo();
        System.out.println("Cor do Pelo: " + corPelo);
    }

    @Override
    public String toCSV() {
        return "Gato," + getNome() + "," + getIdade() + "," + corPelo;
    }
}

// Classe Cachorro
class Cachorro extends Animal {
    private String raca;

    public Cachorro(String nome, int idade, String raca) {
        super(nome, idade);
        this.raca = raca;
    }

    @Override
    public void exibirInfo() {
        super.exibirInfo();
        System.out.println("Raça: " + raca);
    }

    @Override
    public String toCSV() {
        return "Cachorro," + getNome() + "," + getIdade() + "," + raca;
    }
}
