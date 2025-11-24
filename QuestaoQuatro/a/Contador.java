package QuestaoQuatro.a;

import java.util.concurrent.atomic.AtomicInteger;

public class Contador {

    private int contagem = 0;
    private AtomicInteger contagemAtomica = new AtomicInteger(0);

    public void incrementar() {
        int valorAtual = contagem; 
        valorAtual = valorAtual + 1; 
        contagem = valorAtual; 
    }

    public void incrementarCorretamente() {
        contagemAtomica.incrementAndGet();
    }

    public int getContagem() {
        return contagem;
    }

    public int getContagemCorreta() {
        return contagemAtomica.get();
    }
}
