package QuestaoDois;

import java.util.concurrent.TimeUnit;

public class Barbeiro implements Runnable {

    private final BarbeiroDorminhoco monitor;

    public Barbeiro(BarbeiroDorminhoco monitor) {
        this.monitor = monitor;
    }

    @Override
    public void run() {
        System.out.println("Barbeiro abriu a barbearia.");
        try {
            while (true) {
                monitor.lockMonitor.lock();
                Cliente cliente;
                try {
                    while (monitor.clientesEsperando.isEmpty()) {
                        System.out.println("Barbeiro dormindo... ZzzZzZ");
                        monitor.barbeiroDormindo = true;
                        monitor.barbeiroCondition.await();
                    }
                    
                    cliente = monitor.clientesEsperando.poll();
                    System.out.printf("Barbeiro chamou Cliente %d. Cadeiras vagas: %d\n",
                            cliente.id, monitor.N_CADEIRAS - monitor.clientesEsperando.size());
                    
                    cliente.chamarParaCorte();
                    
                } finally {
                    monitor.lockMonitor.unlock();
                }

                System.out.println("Barbeiro cortando cabelo...");
                TimeUnit.SECONDS.sleep(1);
                System.out.println("Barbeiro terminou o corte.");
                
                monitor.lockMonitor.lock();
                try {
                    cliente.meuCorteComecou = false;
                    monitor.clienteCondition.signalAll();
                } finally {
                    monitor.lockMonitor.unlock();
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Barbeiro fechou a barbearia.");
            Thread.currentThread().interrupt();
        }
    }
}
