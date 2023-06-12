package xadrez;

import tabuleiro.Cor;
import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;

public class Cavalo extends Peca {

    public Cavalo(Tabuleiro tab, Cor cor) {
        super(tab, cor);
    }

    @Override
    public String toString() {
        return "C";
    }

    //Sobreposição
    public boolean[][] movimentosPossiveis() {
        boolean[][] mat = new boolean[tab.getLinhas()][tab.getColunas()];
        Posicao pos = new Posicao(0, 0);

        pos.setValores(posicao.getLinha() - 1, posicao.getColuna() - 2);
        if (tab.posicaoValida(pos) && podeMover(pos)) {
            mat[pos.getLinha()][pos.getColuna()] = true;
        }

        pos.setValores(posicao.getLinha() - 2, posicao.getColuna() - 1);
        if (tab.posicaoValida(pos) && podeMover(pos)) {
            mat[pos.getLinha()][pos.getColuna()] = true;
        }

        pos.setValores(posicao.getLinha() - 2, posicao.getColuna() + 1);
        if (tab.posicaoValida(pos) && podeMover(pos)) {
            mat[pos.getLinha()][pos.getColuna()] = true;
        }

        pos.setValores(posicao.getLinha() - 1, posicao.getColuna() + 2);
        if (tab.posicaoValida(pos) && podeMover(pos)) {
            mat[pos.getLinha()][pos.getColuna()] = true;
        }

        pos.setValores(posicao.getLinha() + 1, posicao.getColuna() + 2);
        if (tab.posicaoValida(pos) && podeMover(pos)) {
            mat[pos.getLinha()][pos.getColuna()] = true;
        }

        pos.setValores(posicao.getLinha() + 2, posicao.getColuna() + 1);
        if (tab.posicaoValida(pos) && podeMover(pos)) {
            mat[pos.getLinha()][pos.getColuna()] = true;
        }

        pos.setValores(posicao.getLinha() + 2, posicao.getColuna() - 1);
        if (tab.posicaoValida(pos) && podeMover(pos)) {
            mat[pos.getLinha()][pos.getColuna()] = true;
        }

        pos.setValores(posicao.getLinha() + 1, posicao.getColuna() - 2);
        if (tab.posicaoValida(pos) && podeMover(pos)) {
            mat[pos.getLinha()][pos.getColuna()] = true;
        }

        return mat;
    }
}
