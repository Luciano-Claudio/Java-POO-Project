package xadrez;

import tabuleiro.*;

public class Rei extends Peca {

    private PartidaDeXadrez partida;
    
    public Rei(Tabuleiro tab, Cor cor, PartidaDeXadrez partida) {
        super(tab, cor);
        this.partida = partida;
    }

    @Override
    public String toString() {
        return "R";
    }

    private boolean testeTorreParaRoque(Posicao posicao) {
        Peca p = tab.peca(posicao);
        return p != null && p instanceof Torre && p.getCor() == getCor() && p.getQtdMovimentos() == 0;
    }

    //Sobreposição
    public boolean[][] movimentosPossiveis() {
        boolean[][] mat = new boolean[tab.getLinhas()][tab.getColunas()];

        Posicao pos = new Posicao(0, 0);

        // acima
        pos.setValores(posicao.getLinha() - 1, posicao.getColuna());
        if (tab.posicaoValida(pos) && podeMover(pos))
            mat[pos.getLinha()][pos.getColuna()] = true;

        // nordeste
        pos.setValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
        if (tab.posicaoValida(pos) && podeMover(pos))
            mat[pos.getLinha()][pos.getColuna()] = true;

        // direita
        pos.setValores(posicao.getLinha(), posicao.getColuna() + 1);
        if (tab.posicaoValida(pos) && podeMover(pos))
            mat[pos.getLinha()][pos.getColuna()] = true;

        // suldeste
        pos.setValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
        if (tab.posicaoValida(pos) && podeMover(pos))
            mat[pos.getLinha()][pos.getColuna()] = true;

        // abaixo
        pos.setValores(posicao.getLinha() + 1, posicao.getColuna());
        if (tab.posicaoValida(pos) && podeMover(pos))
            mat[pos.getLinha()][pos.getColuna()] = true;

        // suldoeste
        pos.setValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
        if (tab.posicaoValida(pos) && podeMover(pos))
            mat[pos.getLinha()][pos.getColuna()] = true;

        // esquerda
        pos.setValores(posicao.getLinha(), posicao.getColuna() - 1);
        if (tab.posicaoValida(pos) && podeMover(pos))
            mat[pos.getLinha()][pos.getColuna()] = true;

        // noroeste
        pos.setValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
        if (tab.posicaoValida(pos) && podeMover(pos))
            mat[pos.getLinha()][pos.getColuna()] = true;

            

        // #jogadaespecial roque
        if (getQtdMovimentos() == 0 && !partida.isXeque()) {
            // #jogadaespecial roque pequeno
            Posicao posicaoT1 = new Posicao(getPosicao().getLinha(), getPosicao().getColuna() + 3);
            if (testeTorreParaRoque(posicaoT1)) {
                Posicao p1 = new Posicao(getPosicao().getLinha(), getPosicao().getColuna() + 1);
                Posicao p2 = new Posicao(getPosicao().getLinha(), getPosicao().getColuna() + 2);
                if (tab.peca(p1) == null && tab.peca(p2) == null) {
                    mat[getPosicao().getLinha()][getPosicao().getColuna() + 2] = true;
                }
            }
            // #jogadaespecial roque grande
            Posicao posicaoT2 = new Posicao(getPosicao().getLinha(), getPosicao().getColuna() - 4);
            if (testeTorreParaRoque(posicaoT2)) {
                Posicao p1 = new Posicao(getPosicao().getLinha(), getPosicao().getColuna() - 1);
                Posicao p2 = new Posicao(getPosicao().getLinha(), getPosicao().getColuna() - 2);
                Posicao p3 = new Posicao(getPosicao().getLinha(), getPosicao().getColuna() - 3);
                if (tab.peca(p1) == null && tab.peca(p2) == null && tab.peca(p3) == null) {
                    mat[getPosicao().getLinha()][getPosicao().getColuna() - 2] = true;
                }
            }
        }
        return mat;
    }
}
