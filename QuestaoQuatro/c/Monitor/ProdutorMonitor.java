package QuestaoQuatro.c.Monitor;

import java.util.concurrent.TimeUnit;

public class ProdutorMonitor implements Runnable {
    
    private final BufferMonitor monitor;

    public ProdutorMonitor(BufferMonitor monitor) {
        this.monitor = monitor;
    }

    @Override
    public void run() {
        try {
            int item = 0;
            while (true) {
                monitor.produzir(++item);
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (InterruptedException e) { 
            Thread.currentThread().interrupt(); 
        }
    }
}