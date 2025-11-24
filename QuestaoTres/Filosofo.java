package QuestaoTres;

import java.util.concurrent.TimeUnit;

public class Filosofo implements Runnable {

    private final int id;
    private final Object hashiMenorIndice;
    private final Object hashiMaiorIndice;

    public Filosofo(int id, Object hashiEsquerdo, Object hashiDireito) {
        this.id = id;

        if (System.identityHashCode(hashiEsquerdo) < System.identityHashCode(hashiDireito)) {
            this.hashiMenorIndice = hashiEsquerdo;
            this.hashiMaiorIndice = hashiDireito;
        } else {
            this.hashiMenorIndice = hashiDireito;
            this.hashiMaiorIndice = hashiEsquerdo;
        }
    }

    private void pensa() throws InterruptedException {
        System.out.printf("Filósofo %d está meditando.\n", id);
        TimeUnit.MILLISECONDS.sleep((long) (Math.random() * 1000 + 100));
    }

    private void come() throws InterruptedException {
        System.out.printf("Filósofo %d está comendo.\n", id);
        TimeUnit.MILLISECONDS.sleep((long) (Math.random() * 500 + 100));
    }

    @Override
    public void run() {
        try {
            while (true) {
                pensa();

                System.out.printf("Filósofo %d está com fome.\n", id);
                synchronized (hashiMenorIndice) {
                    System.out.printf("Filósofo %d pegou o primeiro hashi.\n", id);
                    synchronized (hashiMaiorIndice) {
                        System.out.printf("Filósofo %d pegou o segundo hashi.\n", id);
                        come();
                    }
                    System.out.printf("Filósofo %d largou o segundo hashi.\n", id);
                }
                System.out.printf("Filósofo %d largou o primeiro hashi.\n", id);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.printf("Filósofo %d foi embora.\n", id);
        }
    }
}
