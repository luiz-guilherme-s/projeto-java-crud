package model;

import java.io.IOException;
import java.util.Scanner;

public class CadastroPOO {
    private static final Scanner scanner = new Scanner(System.in);
    private static final PessoaFisicaRepo repoFisica = new PessoaFisicaRepo();
    private static final PessoaJuridicaRepo repoJuridica = new PessoaJuridicaRepo();

    public static void main(String[] args) {
        boolean executando = true;
        while (executando) {
            exibirMenuPrincipal();
            int opcao = lerInteiro("Opção: ");
            
            switch (opcao) {
                case 1 -> incluirPessoa();
                case 2 -> alterarPessoa();
                case 3 -> excluirPessoa();
                case 4 -> exibirPorId();
                case 5 -> exibirTodos();
                case 6 -> salvarDados();
                case 7 -> recuperarDados();
                case 8 -> debugListarIds();          // Nova opção
                case 9 -> verificarPersistencia();   // Nova opção
                case 0 -> {
                    executando = false;
                    System.out.println("Programa encerrado.");
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }
    

    private static void exibirMenuPrincipal() {
        System.out.println("\n=== MENU PRINCIPAL ===");
        System.out.println("1. Incluir");
        System.out.println("2. Alterar");
        System.out.println("3. Excluir");
        System.out.println("4. Exibir pelo ID");
        System.out.println("5. Exibir todos");
        System.out.println("6. Salvar dados");
        System.out.println("7. Recuperar dados");      
        System.out.println("0. Sair");
    }

    private static int lerInteiro(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Valor inválido! Digite um número.");
                scanner.nextLine();
            }
        }
    }

    private static void incluirPessoa() {
        System.out.println("\n=== INCLUIR PESSOA ===");
        System.out.println("1. Pessoa Física");
        System.out.println("2. Pessoa Jurídica");
        int tipo = lerInteiro("Tipo: ");
        scanner.nextLine();
        
        int id = lerInteiro("ID: ");
        scanner.nextLine();
        
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        
        switch (tipo) {
            case 1 -> {
                System.out.print("CPF: ");
                String cpf = scanner.nextLine();
                int idade = lerInteiro("Idade: ");
                scanner.nextLine();
                
                PessoaFisica pf = new PessoaFisica(id, nome, cpf, idade);
                repoFisica.inserir(pf);
                System.out.println("Pessoa física cadastrada com sucesso!");
            }
            case 2 -> {
                System.out.print("CNPJ: ");
                String cnpj = scanner.nextLine();
                
                PessoaJuridica pj = new PessoaJuridica(id, nome, cnpj);
                repoJuridica.inserir(pj);
                System.out.println("Pessoa jurídica cadastrada com sucesso!");
            }
            default -> System.out.println("Tipo inválido!");
        }
    }
    
    private static void debugListarIds() {
    System.out.println("\n[DEBUG] IDs de Pessoas Físicas:");
    repoFisica.obterTodos().forEach(pf -> 
        System.out.println("ID: " + pf.getId() + " - " + pf.getNome()));
    
    System.out.println("\n[DEBUG] IDs de Pessoas Jurídicas:");
    repoJuridica.obterTodos().forEach(pj -> 
        System.out.println("ID: " + pj.getId() + " - " + pj.getNome()));
    }

    private static void alterarPessoa() {
        System.out.println("\n=== ALTERAR PESSOA ===");
        System.out.println("1. Pessoa Física");
        System.out.println("2. Pessoa Jurídica");
        int tipo = lerInteiro("Tipo: ");
        scanner.nextLine();
        
        int id = lerInteiro("ID da pessoa a alterar: ");
        scanner.nextLine();
        
        switch (tipo) {
            case 1 -> {
                PessoaFisica pf = repoFisica.obter(id);
                if (pf != null) {
                    System.out.println("Dados atuais:");
                    pf.exibir();
                    
                    System.out.print("Novo nome: ");
                    pf.setNome(scanner.nextLine());
                    System.out.print("Novo CPF: ");
                    pf.setCpf(scanner.nextLine());
                    pf.setIdade(lerInteiro("Nova idade: "));
                    scanner.nextLine();
                    
                    repoFisica.alterar(pf);
                    System.out.println("Pessoa física alterada com sucesso!");
                } else {
                    System.out.println("Pessoa física não encontrada!");
                }
            }
            case 2 -> {
                PessoaJuridica pj = repoJuridica.obter(id);
                if (pj != null) {
                    System.out.println("Dados atuais:");
                    pj.exibir();
                    
                    System.out.print("Novo nome: ");
                    pj.setNome(scanner.nextLine());
                    System.out.print("Novo CNPJ: ");
                    pj.setCnpj(scanner.nextLine());
                    
                    repoJuridica.alterar(pj);
                    System.out.println("Pessoa jurídica alterada com sucesso!");
                } else {
                    System.out.println("Pessoa jurídica não encontrada!");
                }
            }
            default -> System.out.println("Tipo inválido!");
        }
    }

    private static void excluirPessoa() {
        System.out.println("\n=== EXCLUIR PESSOA ===");
        System.out.println("1. Pessoa Física");
        System.out.println("2. Pessoa Jurídica");
        System.out.print("Tipo: ");
            int tipo = lerInteiro("");
                scanner.nextLine();
    
        System.out.print("ID da pessoa a excluir: ");
            int id = lerInteiro("");
                scanner.nextLine();
    
    switch (tipo) {
        case 1 -> {
            if (repoFisica.excluir(id)) {
                System.out.println("Pessoa física excluída com sucesso!");
            } else {
                System.out.println("Pessoa física não encontrada!");
            }
            }
            
        case 2 -> {
            if (repoJuridica.excluir(id)) {
                System.out.println("Pessoa jurídica excluída com sucesso!");
            } else {
                System.out.println("Pessoa jurídica não encontrada!");
            }
            }
            
        default -> System.out.println("Tipo inválido!");
    }
}

    private static void exibirPorId() {
        System.out.println("\n=== EXIBIR POR ID ===");
        System.out.println("1. Pessoa Física");
        System.out.println("2. Pessoa Jurídica");
        int tipo = lerInteiro("Tipo: ");
        scanner.nextLine();
        
        int id = lerInteiro("ID da pessoa: ");
        scanner.nextLine();
        
        switch (tipo) {
            case 1 -> {
                System.out.println("[DEBUG] Tentando buscar ID: " + id);
                System.out.println("[DEBUG] IDs atualmente cadastrados:");
                repoFisica.obterTodos().forEach(pf -> 
                System.out.println("-> " + pf.getId() + " | " + pf.getNome()));
                PessoaFisica pf = repoFisica.obter(id);
                if (pf != null) {
                    pf.exibir();
                } else {
                    System.out.println("Pessoa física não encontrada!");
                }
            }
            case 2 -> {
                PessoaJuridica pj = repoJuridica.obter(id);
                if (pj != null) {
                    pj.exibir();
                } else {
                    System.out.println("Pessoa jurídica não encontrada!");
                }
            }
            default -> System.out.println("Tipo inválido!");
        }
    }

    private static void exibirTodos() {
        System.out.println("\n=== EXIBIR TODOS ===");
        System.out.println("1. Pessoa Física");
        System.out.println("2. Pessoa Jurídica");
        int tipo = lerInteiro("Tipo: ");
        scanner.nextLine();
        
        switch (tipo) {
            case 1 -> {
                System.out.println("\nPessoas Físicas:");
                repoFisica.obterTodos().forEach(PessoaFisica::exibir);
            }
            case 2 -> {
                System.out.println("\nPessoas Jurídicas:");
                repoJuridica.obterTodos().forEach(PessoaJuridica::exibir);
            }
            default -> System.out.println("Tipo inválido!");
        }
    }

    private static void salvarDados() {
        System.out.println("\n=== SALVAR DADOS ===");
        System.out.print("Prefixo para os arquivos: ");
        String prefixo = scanner.nextLine();
        
        try {
            repoFisica.persistir(prefixo + ".fisica.bin");
            repoJuridica.persistir(prefixo + ".juridica.bin");
            System.out.println("Dados salvos com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar dados: " + e.getMessage());
        }
    }

    private static void recuperarDados() {
        System.out.println("\n=== RECUPERAR DADOS ===");
        System.out.print("Prefixo dos arquivos: ");
        String prefixo = scanner.nextLine();
        
        try {
            repoFisica.recuperar(prefixo + ".fisica.bin");
            repoJuridica.recuperar(prefixo + ".juridica.bin");
            System.out.println("Dados recuperados com sucesso!");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao recuperar dados: " + e.getMessage());
        }
    }

    private static void verificarPersistencia() {
    System.out.println("\n=== VERIFICAÇÃO DE PERSISTÊNCIA ===");
    System.out.print("Digite o prefixo dos arquivos para verificar: ");
    String prefixo = scanner.nextLine();
    
    try {
        // Cria repositórios temporários para não afetar os dados atuais
        PessoaFisicaRepo tempFisica = new PessoaFisicaRepo();
        PessoaJuridicaRepo tempJuridica = new PessoaJuridicaRepo();
        
        // Carrega os dados dos arquivos
        tempFisica.recuperar(prefixo + ".fisica.bin");
        tempJuridica.recuperar(prefixo + ".juridica.bin");
        
        // Exibe os dados recuperados
        System.out.println("\nPessoas Físicas no arquivo:");
        if (tempFisica.obterTodos().isEmpty()) {
            System.out.println("Nenhuma pessoa física encontrada.");
        } else {
            tempFisica.obterTodos().forEach(pf -> 
                System.out.println("ID: " + pf.getId() + " - " + pf.getNome()));
        }
        
        System.out.println("\nPessoas Jurídicas no arquivo:");
        if (tempJuridica.obterTodos().isEmpty()) {
            System.out.println("Nenhuma pessoa jurídica encontrada.");
        } else {
            tempJuridica.obterTodos().forEach(pj -> 
                System.out.println("ID: " + pj.getId() + " - " + pj.getNome()));
        }
        
    } catch (IOException | ClassNotFoundException e) {
        System.out.println("\nErro ao verificar persistência: " + e.getMessage());
        System.out.println("Verifique se:");
        System.out.println("1. O prefixo está correto");
        System.out.println("2. Os arquivos existem no diretório do projeto");
        System.out.println("3. Você já salvou dados com esse prefixo antes");
    }
}
}    
