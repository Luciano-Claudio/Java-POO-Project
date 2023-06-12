import java.util.HashSet;
import java.util.Scanner;
import tabuleiro.*;
import xadrez.*;

public class Tela {

    public static final String RESET = "\033[0m"; // Text Reset
    public static final String BLACK = "\033[1;90m"; // BLACK
    public static final String YELLOW = "\033[1;33m"; // YELLOW
    public static final String WHITE_BACKGROUND_BRIGHT = "\033[0;107m";   // WHITE
    public static final String BLACK_BACKGROUND_BRIGHT = "\033[40m";  // BLACK

    public static void imprimirPartida(PartidaDeXadrez partida){
        apagarTela();
        imprimirTabuleiro(partida.getTab());
        imprimirPecasCapturadas(partida);
        System.out.println("\nTurno: " + partida.getTurno());
        if (!partida.isTerminada()) {
            System.out.println("Aguardando jogada: " + partida.getJogadorAtual());
            if (partida.isXeque()) 
                System.out.println("XEQUE!");
        } else {
            System.out.println("XEQUEMATE!");
            System.out.println("Vencedor: " + partida.getJogadorAtual());
        }
        

    }

    public static void imprimirPecasCapturadas(PartidaDeXadrez partida) {
        System.out.println("Peças capturadas:");
        System.out.printf("Brancas: ");
        System.out.printf(YELLOW);
        imprimirConjunto(partida.pecasCapturadas(Cor.BRANCA));
        System.out.println();
        System.out.printf(RESET);
        System.out.printf("Pretas: ");
        System.out.printf(BLACK);
        imprimirConjunto(partida.pecasCapturadas(Cor.PRETA));
        System.out.printf(RESET);
        System.out.println();
    }

    public static void imprimirConjunto(HashSet<Peca> conjunto) {
        System.out.printf("[");
        for (Peca peca : conjunto) {
            System.out.printf(peca + " ");
        }
        System.out.printf("]");
    }

    public static void imprimirTabuleiro(Tabuleiro tab) {

        for (int i = 0; i < tab.getLinhas(); i++) {
            System.out.printf(8 - i + " ");
            for (int j = 0; j < tab.getColunas(); j++) {
                imprimirPeca(tab.peca(i, j));
            }
            System.out.println();
        }
        System.out.println("   a  b  c  d  e  f  g  h");
    }

    public static void imprimirTabuleiro(Tabuleiro tab, boolean[][] posicoesPossiveis) {

        for (int i = 0; i < tab.getLinhas(); i++) {
            System.out.printf(8 - i + " ");
            for (int j = 0; j < tab.getColunas(); j++) {
                if(posicoesPossiveis[i][j])
                    System.out.printf(WHITE_BACKGROUND_BRIGHT);
                else
                    System.out.printf(BLACK_BACKGROUND_BRIGHT);

                imprimirPeca(tab.peca(i, j));
            }
            System.out.println();
        }
        System.out.println("   a  b  c  d  e  f  g  h");
    }

    public static void imprimirPeca(Peca peca) {
        
        if (peca == null) 
            System.out.printf(" - ");
        else{
            System.out.printf(" ");

            if (peca.getCor() == Cor.BRANCA) 
                System.out.printf(YELLOW);
            else 
                System.out.printf(BLACK);
            
            System.out.printf(peca.toString());
            System.out.printf(" ");
        }
        
        System.out.printf(RESET);

    }

    public static PosicaoXadrez lerPosicaoXadrez(Scanner input){
        String s = input.nextLine();
        if(s.length() != 2)
            throw new TabuleiroException("Posição inválida!");
        if(!Character.isDigit(s.charAt(1)))
            throw new TabuleiroException("Posição inválida!");
        char coluna = s.charAt(0);
        int linha = Integer.parseInt(s.charAt(1) + "");
        return new PosicaoXadrez(coluna, linha);
    }

    public static void apagarTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
