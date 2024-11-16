import java.util.Scanner;

public class JogoDaVelha {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[][] tabuleiro = new char[3][3];
        boolean continuarJogando = true;

        // Loop principal para permitir jogar várias partidas
        while (continuarJogando) {
            inicializarTabuleiro(tabuleiro); // Prepara o tabuleiro para uma nova partida
            char jogadorAtual = 'X'; // O jogador X sempre começa
            boolean jogoTerminado = false;

            // Loop de uma única partida
            while (!jogoTerminado) {
                imprimirTabuleiro(tabuleiro); // Exibe o tabuleiro atualizado
                System.out.println("Jogador " + jogadorAtual + ", escolha sua jogada (linha e coluna de 1 a 3):");
                int linha = 0, coluna = 0;

                try {
                    // Captura a jogada do jogador
                    linha = scanner.nextInt() - 1; // Convertendo de 1-3 para 0-2
                    coluna = scanner.nextInt() - 1;

                    // Verifica se a jogada é válida
                    if (linha < 0 || linha >= 3 || coluna < 0 || coluna >= 3 || tabuleiro[linha][coluna] != ' ') {
                        System.out.println("Jogada inválida. Tente novamente.");
                        continue; // Volta para o início do loop
                    }
                } catch (Exception e) {
                    System.out.println("Entrada inválida. Digite números inteiros entre 1 e 3.");
                    scanner.nextLine(); // Limpa a entrada inválida
                    continue; // Volta para o início do loop
                }

                // Atualiza o tabuleiro com a jogada do jogador
                tabuleiro[linha][coluna] = jogadorAtual;

                // Verifica se o jogador atual venceu
                if (verificarVitoria(tabuleiro, jogadorAtual)) {
                    imprimirTabuleiro(tabuleiro);
                    System.out.println("Jogador " + jogadorAtual + " venceu!");
                    jogoTerminado = true; // Termina a partida
                } 
                // Verifica se houve empate
                else if (verificarEmpate(tabuleiro)) {
                    imprimirTabuleiro(tabuleiro);
                    System.out.println("O jogo terminou em empate!");
                    jogoTerminado = true; // Termina a partida
                } 
                // Alterna para o próximo jogador
                else {
                    jogadorAtual = (jogadorAtual == 'X') ? 'O' : 'X';
                }
            }

            // Pergunta se os jogadores desejam jogar novamente
            System.out.println("Deseja jogar novamente? (s/n)");
            continuarJogando = scanner.next().equalsIgnoreCase("s");
        }

        System.out.println("Obrigado por jogar!");
        scanner.close(); // Fecha o scanner ao final do programa
    }

    // Inicializa o tabuleiro com espaços em branco
    private static void inicializarTabuleiro(char[][] tabuleiro) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tabuleiro[i][j] = ' ';
            }
        }
    }

    // Exibe o tabuleiro no console
    private static void imprimirTabuleiro(char[][] tabuleiro) {
        System.out.println("  1 2 3");
        for (int i = 0; i < 3; i++) {
            System.out.print((i + 1) + " "); // Exibe o número da linha
            for (int j = 0; j < 3; j++) {
                System.out.print(tabuleiro[i][j]); // Exibe a célula atual
                if (j < 2) System.out.print("|"); // Exibe separadores de coluna
            }
            System.out.println();
            if (i < 2) System.out.println("  -----"); // Exibe separadores de linha
        }
    }

    // Verifica se o jogador atual venceu
    private static boolean verificarVitoria(char[][] tabuleiro, char jogador) {
        // Verifica linhas e colunas
        for (int i = 0; i < 3; i++) {
            if (tabuleiro[i][0] == jogador && tabuleiro[i][1] == jogador && tabuleiro[i][2] == jogador) return true;
            if (tabuleiro[0][i] == jogador && tabuleiro[1][i] == jogador && tabuleiro[2][i] == jogador) return true;
        }
        // Verifica diagonais
        if (tabuleiro[0][0] == jogador && tabuleiro[1][1] == jogador && tabuleiro[2][2] == jogador) return true;
        if (tabuleiro[0][2] == jogador && tabuleiro[1][1] == jogador && tabuleiro[2][0] == jogador) return true;
        return false; // Não há vitória
    }

    // Verifica se o jogo terminou em empate
    private static boolean verificarEmpate(char[][] tabuleiro) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabuleiro[i][j] == ' ') {
                    return false; // Há pelo menos uma célula vazia
                }
            }
        }
        return true; // Todas as células estão preenchidas
    }
}
