package xadrez;

import tabuleiro.*;

public class Peao extends Peca {

    private PartidaDeXadrez partida;

    public Peao(Tabuleiro tab, Cor cor, PartidaDeXadrez partida) {
        super(tab, cor);
        this.partida = partida;
    }

    @Override
    public String toString() {
        return "P";
    }

    // Sobreposição
    protected boolean podeMover(Posicao pos) {
        return tab.peca(pos) == null;
    }

    // Sobrecarga
    protected boolean podeMover(Posicao pos, Cor cor) {
        Peca peca = tab.peca(pos);
        return peca != null && peca.getCor() != cor;
    }

    // Sobreposição
    public boolean[][] movimentosPossiveis() {
        boolean[][] mat = new boolean[tab.getLinhas()][tab.getColunas()];
        Posicao pos = new Posicao(0, 0);

        if (cor == Cor.BRANCA) {
            pos.setValores(posicao.getLinha() - 1, posicao.getColuna());
            if (tab.posicaoValida(pos) && podeMover(pos)) {
                mat[pos.getLinha()][pos.getColuna()] = true;
            }
            pos.setValores(posicao.getLinha() - 2, posicao.getColuna());
            if (tab.posicaoValida(pos) && podeMover(pos) && getQtdMovimentos() == 0) {
                mat[pos.getLinha()][pos.getColuna()] = true;
            }
            pos.setValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
            if (tab.posicaoValida(pos) && podeMover(pos, Cor.BRANCA)) {
                mat[pos.getLinha()][pos.getColuna()] = true;
            }
            pos.setValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
            if (tab.posicaoValida(pos) && podeMover(pos, Cor.BRANCA)) {
                mat[pos.getLinha()][pos.getColuna()] = true;
            }

            // #jogadaespecial en passant
            if (posicao.getLinha() == 3) {
                Posicao esquerda = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
                if (tab.posicaoValida(esquerda) && podeMover(esquerda, Cor.BRANCA)
                        && tab.peca(esquerda) == partida.getVulneravelEnPassant()) {
                    mat[esquerda.getLinha() - 1][esquerda.getColuna()] = true;
                }
                Posicao direita = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
                if (tab.posicaoValida(direita) && podeMover(direita, Cor.BRANCA)
                        && tab.peca(direita) == partida.getVulneravelEnPassant()) {
                    mat[direita.getLinha() - 1][direita.getColuna()] = true;
                }
            }
        } else {
            pos.setValores(posicao.getLinha() + 1, posicao.getColuna());
            if (tab.posicaoValida(pos) && podeMover(pos)) {
                mat[pos.getLinha()][pos.getColuna()] = true;
            }
            pos.setValores(posicao.getLinha() + 2, posicao.getColuna());
            if (tab.posicaoValida(pos) && podeMover(pos) && getQtdMovimentos() == 0) {
                mat[pos.getLinha()][pos.getColuna()] = true;
            }
            pos.setValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
            if (tab.posicaoValida(pos) && podeMover(pos, Cor.PRETA)) {
                mat[pos.getLinha()][pos.getColuna()] = true;
            }
            pos.setValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
            if (tab.posicaoValida(pos) && podeMover(pos, Cor.PRETA)) {
                mat[pos.getLinha()][pos.getColuna()] = true;
            }

            // #jogadaespecial en passant
            if (posicao.getLinha() == 4) {
                Posicao esquerda = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
                if (tab.posicaoValida(esquerda) && podeMover(esquerda, Cor.PRETA)
                        && tab.peca(esquerda) == partida.getVulneravelEnPassant()) {
                    mat[esquerda.getLinha() + 1][esquerda.getColuna()] = true;
                }
                Posicao direita = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
                if (tab.posicaoValida(direita) && podeMover(direita, Cor.PRETA)
                        && tab.peca(direita) == partida.getVulneravelEnPassant()) {
                    mat[direita.getLinha() + 1][direita.getColuna()] = true;
                }
            }
        }

        return mat;
    }
}
