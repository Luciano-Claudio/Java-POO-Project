package xadrez;
import tabuleiro.Posicao;

public class PosicaoXadrez {
    private char coluna;
    private int linha;

    public char getColuna() {
        return coluna;
    }

    public void setColuna(char coluna) {
        this.coluna = coluna;
    }

    public int getLinha() {
        return linha;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }

    public PosicaoXadrez(char coluna, int linha) {
        this.coluna = coluna;
        this.linha = linha;
    }

    public Posicao toPosicao() {
        return new Posicao(8 - linha, coluna - 'a');
    }

    @Override
    public String toString() {
        return "" + coluna + linha;
    }
}
