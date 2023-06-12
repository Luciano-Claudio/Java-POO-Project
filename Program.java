import java.util.Scanner;
import tabuleiro.*;
import xadrez.*;
public class Program {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        try{
            PartidaDeXadrez partida= new PartidaDeXadrez();
            while(!partida.isTerminada()){
                try{
                    Tela.imprimirPartida(partida);

                    System.out.printf("\nOrigem: ");
                    Posicao origem = Tela.lerPosicaoXadrez(input).toPosicao();
                    partida.validarPosicaoOrigem(origem);

                    boolean[][] posicoesPossiveis = partida.getTab().peca(origem).movimentosPossiveis();

                    Tela.apagarTela();
                    Tela.imprimirTabuleiro(partida.getTab(), posicoesPossiveis);

                    System.out.print("\nDestino: ");
                    Posicao destino = Tela.lerPosicaoXadrez(input).toPosicao();
                    partida.validarPosicaoDestino(origem, destino);

                    partida.realizaJogada(origem, destino);
                }
                catch(TabuleiroException e){
                    System.out.println(e.getMessage());
                    System.out.println("Pressione qualquer tecla para continuar!");
                    input.nextLine();
                }
            }
            Tela.apagarTela();
            Tela.imprimirPartida(partida);
            
        }
        catch(TabuleiroException e){
            System.out.println(e.getMessage());
        }
        input.close();
    }
}
