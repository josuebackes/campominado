import java.util.Scanner;

public class CampoMinado {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numeroCampo = 1;

        while (true) {
            int linhas = scanner.nextInt();
            int colunas = scanner.nextInt();

            if (linhas == 0 && colunas == 0) {
                break;
            }

            char[][] campoMinado = lerCampoMinado(scanner, linhas, colunas);

            System.out.println("Field #" + numeroCampo + ":");
            imprimirCampoMinado(campoMinado);

            numeroCampo++;
        }
    }

    private static char[][] lerCampoMinado(Scanner scanner, int linhas, int colunas) {
        char[][] campoMinado = new char[linhas][colunas];
        scanner.nextLine();

        for (int i = 0; i < linhas; i++) {
            String linha = scanner.nextLine();
            campoMinado[i] = linha.toCharArray();
        }

        return campoMinado;
    }

    private static void imprimirCampoMinado(char[][] campoMinado) {
        for (int i = 0; i < campoMinado.length; i++) {
            for (int j = 0; j < campoMinado[i].length; j++) {
                if (campoMinado[i][j] == '*') {
                    System.out.print('*');
                } else {
                    int minasAdjacentes = contarMinasAdjacentes(campoMinado, campoMinado.length, campoMinado[i].length, i, j);
                    System.out.print(minasAdjacentes);
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    private static int contarMinasAdjacentes(char[][] campoMinado, int linhas, int colunas, int x, int y) {
        int contagem = 0;
        int[] dx = { -1, -1, -1, 0, 0, 1, 1, 1 };
        int[] dy = { -1, 0, 1, -1, 1, -1, 0, 1 };

        for (int i = 0; i < 8; i++) {
            int novoX = x + dx[i];
            int novoY = y + dy[i];

            if (coordenadaValida(novoX, novoY, linhas, colunas) && campoMinado[novoX][novoY] == '*') {
                contagem++;
            }
        }

        return contagem;
    }

    private static boolean coordenadaValida(int x, int y, int linhas, int colunas) {
        return x >= 0 && x < linhas && y >= 0 && y < colunas;
    }
}
