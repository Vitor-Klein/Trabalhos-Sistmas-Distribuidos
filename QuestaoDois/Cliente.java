package QuestaoDois;

public class Cliente implements Runnable {
    
    final int id;
    boolean meuCorteComecou = false;
    private final BarbeiroDorminhoco monitor;

    public Cliente(int id, BarbeiroDorminhoco monitor) { 
        this.id = id; 
        this.monitor = monitor;
    }
    
    public void chamarParaCorte() {
        monitor.lockMonitor.lock();
        try {
            meuCorteComecou = true;
            monitor.clienteCondition.signalAll();
        } finally {
            monitor.lockMonitor.unlock();
        }
    }
    
    @Override
    public void run() {
        try {
            monitor.lockMonitor.lock();
            try {
                if (monitor.clientesEsperando.size() == monitor.N_CADEIRAS) {
                    System.out.printf("Cliente %d: Barbearia cheia. Indo embora.\n", id);
                    return;
                }
                
                monitor.clientesEsperando.add(this);
                System.out.printf("Cliente %d sentou. Cadeiras ocupadas: %d\n",
                        id, monitor.clientesEsperando.size());
                
                if (monitor.barbeiroDormindo) {
                    System.out.printf("Cliente %d acordando o barbeiro.\n", id);
                    monitor.barbeiroDormindo = false;
                    monitor.barbeiroCondition.signal();
                }
                
                while (!meuCorteComecou) {
                    monitor.clienteCondition.await();
                }
                
                while (meuCorteComecou) {
                    monitor.clienteCondition.await();
                }
                
            } finally {
                monitor.lockMonitor.unlock();
            }
            
            System.out.printf("Cliente %d com cabelo cortado. Indo embora.\n", id);
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
