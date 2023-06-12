package xadrez;

import tabuleiro.*;

public class Torre extends Peca {

    public Torre(Tabuleiro tab, Cor cor) {
        super(tab, cor);
    }

    @Override
    public String toString() {
        return "T";
    }

    //Sobreposição
    public boolean[][] movimentosPossiveis() {
        boolean[][] mat = new boolean[tab.getLinhas()][tab.getColunas()];

        Posicao pos = new Posicao(0, 0);

        // acima
        pos.setValores(posicao.getLinha() - 1, posicao.getColuna());
        while (tab.posicaoValida(pos) && podeMover(pos)) {
            mat[pos.getLinha()][pos.getColuna()] = true;
            if (tab.peca(pos) != null && tab.peca(pos).getCor() != cor)
                break;
            pos.setLinha(pos.getLinha() - 1);
        }
        // abaixo
        pos.setValores(posicao.getLinha() + 1, posicao.getColuna());
        while (tab.posicaoValida(pos) && podeMover(pos)) {
            mat[pos.getLinha()][pos.getColuna()] = true;
            if (tab.peca(pos) != null && tab.peca(pos).getCor() != cor)
                break;
            pos.setLinha(pos.getLinha() + 1);
        }

        // direita
        pos.setValores(posicao.getLinha(), posicao.getColuna() + 1);
        while (tab.posicaoValida(pos) && podeMover(pos)) {
            mat[pos.getLinha()][pos.getColuna()] = true;
            if (tab.peca(pos) != null && tab.peca(pos).getCor() != cor)
                break;
            pos.setColuna(pos.getColuna() + 1);
        }

        // esquerda
        pos.setValores(posicao.getLinha(), posicao.getColuna() - 1);
        while (tab.posicaoValida(pos) && podeMover(pos)) {
            mat[pos.getLinha()][pos.getColuna()] = true;
            if (tab.peca(pos) != null && tab.peca(pos).getCor() != cor)
                break;
            pos.setColuna(pos.getColuna() - 1);
        }

        return mat;
    }
}
