package xadrez;

import java.util.HashSet;
import tabuleiro.*;

public class PartidaDeXadrez {
    private Tabuleiro tab;
    private int turno;
    private Cor jogadorAtual;
    private boolean terminada;
    private HashSet<Peca> pecas;
    private HashSet<Peca> capturadas;
    private boolean xeque;
    private Peca vulneravelEnPassant;

    public PartidaDeXadrez() {
        tab = new Tabuleiro(8, 8);
        turno = 1;
        jogadorAtual = Cor.BRANCA;
        terminada = false;
        xeque = false;
        pecas = new HashSet<Peca>();
        capturadas = new HashSet<Peca>();
        vulneravelEnPassant = null;
        colocarPecas();
    }

    public Tabuleiro getTab() {
        return tab;
    }

    public int getTurno() {
        return turno;
    }

    public Cor getJogadorAtual() {
        return jogadorAtual;
    }

    public boolean isTerminada() {
        return terminada;
    }

    public boolean isXeque() {
        return xeque;
    }

    public Peca getVulneravelEnPassant() {
        return vulneravelEnPassant;
    }

    public Peca executaMovimento(Posicao origem, Posicao destino) {
        Peca p = tab.retirarPeca(origem);
        p.incrementarQtdMovimentos();
        Peca pecaCapturada = tab.retirarPeca(destino);
        tab.colocarPeca(p, destino);
        if (pecaCapturada != null)
            capturadas.add(pecaCapturada);
            
        // jogada especial roque pequeno
        if (p instanceof Rei && destino.getColuna() == origem.getColuna() + 2) {
            Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna() + 3);
            Posicao destinoT = new Posicao(origem.getLinha(), origem.getColuna() + 1);
            Peca T = tab.retirarPeca(origemT);
            T.incrementarQtdMovimentos();
            tab.colocarPeca(T, destinoT);
        }
        // jogada especial roque grande
        if (p instanceof Rei && destino.getColuna() == origem.getColuna() - 2) {
            Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna() - 4);
            Posicao destinoT = new Posicao(origem.getLinha(), origem.getColuna() - 1);
            Peca T = tab.retirarPeca(origemT);
            T.incrementarQtdMovimentos();
            tab.colocarPeca(T, destinoT);
        }

        // #jogadaespecial en passant
        if (p instanceof Peao) {
            if (origem.getColuna() != destino.getColuna() && pecaCapturada == null) {
                Posicao posP;
                if (p.getCor() == Cor.BRANCA) {
                    posP = new Posicao(destino.getLinha() + 1, destino.getColuna());
                } else {
                    posP = new Posicao(destino.getLinha() - 1, destino.getColuna());
                }
                pecaCapturada = tab.retirarPeca(posP);
                capturadas.add(pecaCapturada);
            }
        }

        return pecaCapturada;
    }

    public void desfazMovimento(Posicao origem, Posicao destino, Peca pecaCapturada) {
        Peca p = tab.retirarPeca(destino);
        p.decrementarQtdMovimentos();
        if (pecaCapturada != null) {
            tab.colocarPeca(pecaCapturada, destino);
            capturadas.remove(pecaCapturada);
        }
        tab.colocarPeca(p, origem);

        // jogada especial roque pequeno
        if (p instanceof Rei && destino.getColuna() == origem.getColuna() + 2) {
            Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna() + 3);
            Posicao destinoT = new Posicao(origem.getLinha(), origem.getColuna() + 1);
            Peca T = tab.retirarPeca(destinoT);
            T.decrementarQtdMovimentos();
            tab.colocarPeca(T, origemT);
        }
        // jogada especial roque grande
        if (p instanceof Rei && destino.getColuna() == origem.getColuna() - 2) {
            Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna() - 4);
            Posicao destinoT = new Posicao(origem.getLinha(), origem.getColuna() - 1);
            Peca T = tab.retirarPeca(destinoT);
            T.decrementarQtdMovimentos();
            tab.colocarPeca(T, origemT);
        }

        // #jogadaespecial en passant
        if (p instanceof Peao) {
            if (origem.getColuna() != destino.getColuna() && pecaCapturada == vulneravelEnPassant) {
                Peca peao = tab.retirarPeca(destino);
                Posicao posP;
                if (p.getCor() == Cor.BRANCA) {
                    posP = new Posicao(3, destino.getColuna());
                } else {
                    posP = new Posicao(4, destino.getColuna());
                }
                tab.colocarPeca(peao, posP);
            }
        }
    }

    public void realizaJogada(Posicao origem, Posicao destino) {
        Peca pecaCapturada = executaMovimento(origem, destino);

        if (estaEmXeque(jogadorAtual)) {
            desfazMovimento(origem, destino, pecaCapturada);
            throw new TabuleiroException("Você não pode se colocar em xeque!");
        }

        xeque = estaEmXeque(adversario(jogadorAtual));

        Peca p = tab.peca(destino);

        // #jogadaespecial promoção
        if (p instanceof Peao) {
            if ((p.getCor() == Cor.BRANCA && destino.getLinha() == 0) || (p.getCor() == Cor.PRETA && destino.getLinha() == 7)) {
                p = tab.retirarPeca(destino);
                pecas.remove(p);
                Peca dama = new Dama(tab, p.getCor());
                tab.colocarPeca(dama, destino);
                pecas.add(dama);
            }
        }


        if (testeXequeMate(adversario(jogadorAtual))) {
            terminada = true;
        } else {
            turno++;
            mudarJogador();
        }

        // #jogadaespecial en passant
        if (p instanceof Peao && (destino.getLinha() == origem.getLinha() - 2 || destino.getLinha() == origem.getLinha() + 2)) {
            vulneravelEnPassant = p;
        } else {
            vulneravelEnPassant = null;
        }
    }

    public void validarPosicaoOrigem(Posicao pos) {
        if (!tab.existePeca(pos)) {
            throw new TabuleiroException("Não existe peça na posição de origem!");
        }
        if (jogadorAtual != tab.peca(pos).getCor()) {
            throw new TabuleiroException("A peça escolhida não é sua!");
        }
        if (!tab.peca(pos).existeMovimentosPossiveis()) {
            throw new TabuleiroException("Não há movimentos possíveis para a peça de origem escolhida!");
        }
    }

    public void validarPosicaoDestino(Posicao origem, Posicao destino) {
        if (!tab.peca(origem).movimentoPossivel(destino)) {
            throw new TabuleiroException("Posição de destino inválida!");
        }
    }

    private void mudarJogador() {
        jogadorAtual = (jogadorAtual == Cor.BRANCA) ? Cor.PRETA : Cor.BRANCA;
    }

    public HashSet<Peca> pecasCapturadas(Cor cor) {
        HashSet<Peca> aux = new HashSet<Peca>();
        for (Peca x : capturadas) {
            if (x.getCor() == cor) {
                aux.add(x);
            }
        }
        return aux;
    }

    public HashSet<Peca> pecasEmJogo(Cor cor) {
        HashSet<Peca> aux = new HashSet<Peca>();
        for (Peca x : pecas) {
            if (x.getCor() == cor) {
                aux.add(x);
            }
        }
        aux.removeAll(pecasCapturadas(cor));
        return aux;
    }

    private Cor adversario(Cor cor) {
        return (cor == Cor.BRANCA) ? Cor.PRETA : Cor.BRANCA;
    }

    private Peca rei(Cor cor) {
        for (Peca p : pecasEmJogo(cor)) {
            if (p instanceof Rei) 
                return p;
        }
        return null;
    }

    private boolean estaEmXeque(Cor cor) {
        Peca R = rei(cor);
        if(R == null)
            throw new IllegalStateException("Não existe o rei " + cor + " no tabuleiro!");

        for (Peca p : pecasEmJogo(adversario(cor))) {
            boolean[][] mat = p.movimentosPossiveis();
            if (mat[R.getPosicao().getLinha()][R.getPosicao().getColuna()]) 
                return true;
        }
        return false;
    }

    private boolean testeXequeMate(Cor cor) {
        if (!estaEmXeque(cor)) {
            return false;
        }
        for (Peca p : pecasEmJogo(cor)) {
            boolean[][] mat = p.movimentosPossiveis();
            for (int i = 0; i < tab.getLinhas(); i++) {
                for (int j = 0; j < tab.getColunas(); j++) {
                    if (mat[i][j]) {
                        Posicao origem = p.getPosicao();
                        Posicao destino = new Posicao(i, j);
                        Peca pecaCapturada = executaMovimento(origem, destino);
                        boolean testeXeque = estaEmXeque(cor);
                        desfazMovimento(origem, destino, pecaCapturada);
                        if (!testeXeque) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public void colocarNovaPeca(char coluna, int linha, Peca peca) {
        tab.colocarPeca(peca, new PosicaoXadrez(coluna, linha).toPosicao());
        pecas.add(peca);
    }

    private void colocarPecas() {
        colocarNovaPeca('a', 1, new Torre(tab, Cor.BRANCA));
        colocarNovaPeca('b', 1, new Cavalo(tab, Cor.BRANCA));
        colocarNovaPeca('c', 1, new Bispo(tab, Cor.BRANCA));
        colocarNovaPeca('d', 1, new Dama(tab, Cor.BRANCA));
        colocarNovaPeca('e', 1, new Rei(tab, Cor.BRANCA, this));
        colocarNovaPeca('f', 1, new Bispo(tab, Cor.BRANCA));
        colocarNovaPeca('g', 1, new Cavalo(tab, Cor.BRANCA));
        colocarNovaPeca('h', 1, new Torre(tab, Cor.BRANCA));
        colocarNovaPeca('a', 2, new Peao(tab, Cor.BRANCA, this));
        colocarNovaPeca('b', 2, new Peao(tab, Cor.BRANCA, this));
        colocarNovaPeca('c', 2, new Peao(tab, Cor.BRANCA, this));
        colocarNovaPeca('d', 2, new Peao(tab, Cor.BRANCA, this));
        colocarNovaPeca('e', 2, new Peao(tab, Cor.BRANCA, this));
        colocarNovaPeca('f', 2, new Peao(tab, Cor.BRANCA, this));
        colocarNovaPeca('g', 2, new Peao(tab, Cor.BRANCA, this));
        colocarNovaPeca('h', 2, new Peao(tab, Cor.BRANCA, this));

        colocarNovaPeca('a', 8, new Torre(tab, Cor.PRETA));
        colocarNovaPeca('b', 8, new Cavalo(tab, Cor.PRETA));
        colocarNovaPeca('c', 8, new Bispo(tab, Cor.PRETA));
        colocarNovaPeca('d', 8, new Dama(tab, Cor.PRETA));
        colocarNovaPeca('e', 8, new Rei(tab, Cor.PRETA, this));
        colocarNovaPeca('f', 8, new Bispo(tab, Cor.PRETA));
        colocarNovaPeca('g', 8, new Cavalo(tab, Cor.PRETA));
        colocarNovaPeca('h', 8, new Torre(tab, Cor.PRETA));
        colocarNovaPeca('a', 7, new Peao(tab, Cor.PRETA, this));
        colocarNovaPeca('b', 7, new Peao(tab, Cor.PRETA, this));
        colocarNovaPeca('c', 7, new Peao(tab, Cor.PRETA, this));
        colocarNovaPeca('d', 7, new Peao(tab, Cor.PRETA, this));
        colocarNovaPeca('e', 7, new Peao(tab, Cor.PRETA, this));
        colocarNovaPeca('f', 7, new Peao(tab, Cor.PRETA, this));
        colocarNovaPeca('g', 7, new Peao(tab, Cor.PRETA, this));
        colocarNovaPeca('h', 7, new Peao(tab, Cor.PRETA, this));
    }
}
