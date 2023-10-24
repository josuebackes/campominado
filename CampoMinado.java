import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CampoMinado {
    public static void main(String[] args) {
        try {
            BufferedReader leitor = new BufferedReader(new FileReader("entrada.txt"));
            int numeroCampo = 1;
            String linha;

            while ((linha = leitor.readLine()) != null) {
                String[] dimensoes = linha.split(" ");
                int linhas = Integer.parseInt(dimensoes[0]);
                int colunas = Integer.parseInt(dimensoes[1]);

                if (linhas == 0 && colunas == 0) {
                    break;
                }

                char[][] campoMinado = lerCampoMinado(leitor, linhas, colunas);

                System.out.println("Field #" + numeroCampo + ":");
                imprimirCampoMinado(campoMinado);

                numeroCampo++;
            }

            leitor.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static char[][] lerCampoMinado(BufferedReader leitor, int linhas, int colunas) throws IOException {
        char[][] campoMinado = new char[linhas][colunas];

        for (int i = 0; i < linhas; i++) {
            String linha = leitor.readLine();
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
