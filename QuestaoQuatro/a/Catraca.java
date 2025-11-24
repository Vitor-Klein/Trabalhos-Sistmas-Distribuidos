package QuestaoQuatro.a;

public class Catraca implements Runnable {

    private final int id;
    private final Contador contadorCentral;
    private final int N_PASSAGENS;
    private final boolean usarCorrecao;

    public Catraca(int id, Contador contador, int numPassagens, boolean corrigir) {
        this.id = id;
        this.contadorCentral = contador;
        this.N_PASSAGENS = numPassagens;
        this.usarCorrecao = corrigir;
    }

    @Override
    public void run() {
        for (int i = 0; i < N_PASSAGENS; i++) {
            if (usarCorrecao) {
                contadorCentral.incrementarCorretamente();
            } else {
                contadorCentral.incrementar();
            }
        }
        
        System.out.printf("... Catraca %d (Contador Local) terminou. Passagens: %,d\n", 
                          id, N_PASSAGENS);
    }
}