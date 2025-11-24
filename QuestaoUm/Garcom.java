import java.util.*;
// import java.util.concurrent.*;

class Garcom implements Runnable {
    private final int id;
    private final int capacidade;
    private final Bartender bartender;
    private final List<Cliente> pedidos = new ArrayList<>();
    private final Random random = new Random();
    private final Object lock = new Object();
    private boolean ocupado = false; // indica se o garçom está na copa

    public Garcom(int id, int capacidade, Bartender bartender) {
        this.id = id;
        this.capacidade = capacidade;
        this.bartender = bartender;
    }

    public void fazerPedido(Cliente cliente) throws InterruptedException {
        synchronized (lock) {
            // se o garçom está na copa, cliente espera um pouco
            while (ocupado) {
                lock.wait(100);
            }

            if (pedidos.size() < capacidade) {
                pedidos.add(cliente);
                System.out.println("--> Cliente " + cliente.getIdCliente() + " fez pedido ao Garçom " + id);
                if (pedidos.size() == capacidade) {
                    lock.notifyAll(); // sinaliza que está pronto para ir à copa
                }
            } else {
                // Se o garçom já está com fila cheia, cliente espera o próximo ciclo
                System.out.println("   (Garçom " + id + " está cheio, Cliente " + cliente.getIdCliente() + " aguardará próxima rodada)");
            }
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                synchronized (lock) {
                    while (pedidos.size() < capacidade) {
                        lock.wait(200); // espera até ter pedidos suficientes
                    }
                    ocupado = true;
                }

                List<Cliente> pedidosParaEntregar;
                synchronized (lock) {
                    pedidosParaEntregar = new ArrayList<>(pedidos);
                    pedidos.clear();
                }

                System.out.println("\n--> Garçom " + id + " indo à copa com " + pedidosParaEntregar.size() + " pedidos.");
                bartender.atenderPedidos(pedidosParaEntregar, id);
                System.out.println("--> Garçom " + id + " entregou os pedidos e voltou ao salão.\n");

                synchronized (lock) {
                    ocupado = false;
                    lock.notifyAll();
                }

                Thread.sleep(random.nextInt(600) + 400);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}