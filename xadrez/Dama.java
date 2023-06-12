package xadrez;

import tabuleiro.Cor;
import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;

public class Dama extends Peca {

    public Dama(Tabuleiro tab, Cor cor) {
        super(tab, cor);
    }

    @Override
    public String toString() {
        return "D";
    }

    //Sobreposição
    public boolean[][] movimentosPossiveis() {
        boolean[][] mat = new boolean[tab.getLinhas()][tab.getColunas()];
        Posicao pos = new Posicao(0, 0);

        // esquerda
        pos.setValores(posicao.getLinha(), posicao.getColuna() - 1);
        while (tab.posicaoValida(pos) && podeMover(pos)) {
            mat[pos.getLinha()][pos.getColuna()] = true;
            if (tab.peca(pos) != null && tab.peca(pos).getCor() != getCor()) {
                break;
            }
            pos.setValores(pos.getLinha(), pos.getColuna() - 1);
        }

        // direita
        pos.setValores(posicao.getLinha(), posicao.getColuna() + 1);
        while (tab.posicaoValida(pos) && podeMover(pos)) {
            mat[pos.getLinha()][pos.getColuna()] = true;
            if (tab.peca(pos) != null && tab.peca(pos).getCor() != getCor()) {
                break;
            }
            pos.setValores(pos.getLinha(), pos.getColuna() + 1);
        }

        // acima
        pos.setValores(posicao.getLinha() - 1, posicao.getColuna());
        while (tab.posicaoValida(pos) && podeMover(pos)) {
            mat[pos.getLinha()][pos.getColuna()] = true;
            if (tab.peca(pos) != null && tab.peca(pos).getCor() != getCor()) {
                break;
            }
            pos.setValores(pos.getLinha() - 1, pos.getColuna());
        }

        // abaixo
        pos.setValores(posicao.getLinha() + 1, posicao.getColuna());
        while (tab.posicaoValida(pos) && podeMover(pos)) {
            mat[pos.getLinha()][pos.getColuna()] = true;
            if (tab.peca(pos) != null && tab.peca(pos).getCor() != getCor()) {
                break;
            }
            pos.setValores(pos.getLinha() + 1, pos.getColuna());
        }

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
