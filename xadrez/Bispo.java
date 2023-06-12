package xadrez;

import tabuleiro.*;

public class Bispo extends Peca {

    public Bispo(Tabuleiro tab, Cor cor) {
        super(tab, cor);
    }

    @Override
    public String toString() {
        return "B";
    }

    //Sobreposição
    public boolean[][] movimentosPossiveis() {
        boolean[][] mat = new boolean[tab.getLinhas()][tab.getColunas()];

        Posicao pos = new Posicao(0, 0);

        // NO
        pos.setValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
        while (tab.posicaoValida(pos) && podeMover(pos)) {
            mat[pos.getLinha()][pos.getColuna()] = true;
            if (tab.peca(pos) != null && tab.peca(pos).getCor() != getCor()) {
                break;
            }
            pos.setValores(pos.getLinha() - 1, pos.getColuna() - 1);
        }

        // NE
        pos.setValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
        while (tab.posicaoValida(pos) && podeMover(pos)) {
            mat[pos.getLinha()][pos.getColuna()] = true;
            if (tab.peca(pos) != null && tab.peca(pos).getCor() != getCor()) {
                break;
            }
            pos.setValores(pos.getLinha() - 1, pos.getColuna() + 1);
        }

        // SE
        pos.setValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
        while (tab.posicaoValida(pos) && podeMover(pos)) {
            mat[pos.getLinha()][pos.getColuna()] = true;
            if (tab.peca(pos) != null && tab.peca(pos).getCor() != getCor()) {
                break;
            }
            pos.setValores(pos.getLinha() + 1, pos.getColuna() + 1);
        }

        // SO
        pos.setValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
        while (tab.posicaoValida(pos) && podeMover(pos)) {
            mat[pos.getLinha()][pos.getColuna()] = true;
            if (tab.peca(pos) != null && tab.peca(pos).getCor() != getCor()) {
                break;
            }
            pos.setValores(pos.getLinha() + 1, pos.getColuna() - 1);
        }

        return mat;
    }
}
