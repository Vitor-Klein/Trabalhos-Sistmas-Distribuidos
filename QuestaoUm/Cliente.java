import java.util.*;

class Cliente implements Runnable {
    private final int id;
    private final Garcom garcom;
    private final Random random = new Random();

    public Cliente(int id, Garcom garcom) {
        this.id = id;
        this.garcom = garcom;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < Bar.rodadas; i++) {
                Thread.sleep(random.nextInt(2000) + 500); // tempo até pedir novamente
                garcom.fazerPedido(this);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public int getIdCliente() {
        return id;
    }
}