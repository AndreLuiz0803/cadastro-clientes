package cadastros;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Cliente> clientes = new ArrayList<>();
    private static int idCounter = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\nMenu:");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Listar Clientes");
            System.out.println("3. Atualizar Cliente");
            System.out.println("4. Remover Cliente");
            System.out.println("5. Buscar Cliente");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer

            switch (opcao) {
                case 1:
                    cadastrarCliente(scanner);
                    break;
                case 2:
                    listarClientes();
                    break;
                case 3:
                    atualizarCliente(scanner);
                    break;
                case 4:
                    removerCliente(scanner);
                    break;
                case 5:
                    buscarCliente(scanner);
                    break;
                case 6:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 6);

        scanner.close();
    }

    private static void cadastrarCliente(Scanner scanner) {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("E-mail: ");
        String email = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();

        Cliente cliente = new Cliente(idCounter++, nome, email, telefone);
        clientes.add(cliente);
        System.out.println("Cliente cadastrado com sucesso!");
    }

    private static void listarClientes() {
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
        } else {
            for (Cliente cliente : clientes) {
                System.out.println(cliente);
            }
        }
    }

    private static void atualizarCliente(Scanner scanner) {
        System.out.print("Informe o ID do cliente a ser atualizado: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer

        Cliente cliente = buscarClientePorId(id);
        if (cliente != null) {
            System.out.print("Novo nome (atual: " + cliente.getNome() + "): ");
            cliente.setNome(scanner.nextLine());
            System.out.print("Novo e-mail (atual: " + cliente.getEmail() + "): ");
            cliente.setEmail(scanner.nextLine());
            System.out.print("Novo telefone (atual: " + cliente.getTelefone() + "): ");
            cliente.setTelefone(scanner.nextLine());
            System.out.println("Cliente atualizado com sucesso!");
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    private static void removerCliente(Scanner scanner) {
        System.out.print("Informe o ID do cliente a ser removido: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer

        Cliente cliente = buscarClientePorId(id);
        if (cliente != null) {
            clientes.remove(cliente);
            System.out.println("Cliente removido com sucesso!");
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    private static void buscarCliente(Scanner scanner) {
        System.out.print("Informe o ID do cliente a ser buscado: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer

        Cliente cliente = buscarClientePorId(id);
        if (cliente != null) {
            System.out.println(cliente);
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    private static Cliente buscarClientePorId(int id) {
        for (Cliente cliente : clientes) {
            if (cliente.getId() == id) {
                return cliente;
            }
        }
        return null;
    }
}