package tabuleiro;

public class Peca {
    protected Posicao posicao;
    protected Tabuleiro tab;
    protected Cor cor;
    protected int qtdMovimentos;

    public Peca(Tabuleiro tab, Cor cor) {
        super();
        this.posicao = null;
        this.tab = tab;
        this.cor = cor;
        this.qtdMovimentos = 0;
    }

    public Posicao getPosicao() {
        return posicao;
    }

    public void setPosicao(Posicao posicao) {
        this.posicao = posicao;
    }

    public Cor getCor() {
        return cor;
    }

    public void setCor(Cor cor) {
        this.cor = cor;
    }

    public int getQtdMovimentos() {
        return qtdMovimentos;
    }

    public void setQtdMovimentos(int qtdMovimentos) {
        this.qtdMovimentos = qtdMovimentos;
    }

    public Tabuleiro getTab() {
        return tab;
    }

    public void setTab(Tabuleiro tab) {
        this.tab = tab;
    }

    public void incrementarQtdMovimentos() {
        qtdMovimentos++;
    }

    public void decrementarQtdMovimentos() {
        qtdMovimentos--;
    }

    public boolean[][] movimentosPossiveis() {
        boolean[][] mat = new boolean[tab.getLinhas()][tab.getColunas()];
        
        return mat;
    }

    protected boolean podeMover(Posicao pos) {
        Peca p = tab.peca(pos);
        return p == null || p.getCor() != cor ? true : false;
    }

    public boolean existeMovimentosPossiveis() {
        boolean[][] mat = movimentosPossiveis();
        for (int i = 0; i < tab.getLinhas(); i++) {
            for (int j = 0; j < tab.getColunas(); j++) {
                if(mat[i][j])
                    return true;
                
            }
        }
        return false;
    }

    public boolean movimentoPossivel(Posicao pos){
        return movimentosPossiveis()[pos.getLinha()][pos.getColuna()];
    }

}
