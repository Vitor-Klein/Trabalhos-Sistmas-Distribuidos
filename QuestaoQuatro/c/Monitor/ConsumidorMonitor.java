package QuestaoQuatro.c.Monitor;

import java.util.concurrent.TimeUnit;

public class ConsumidorMonitor implements Runnable {
    
    private final BufferMonitor monitor;

    public ConsumidorMonitor(BufferMonitor monitor) {
        this.monitor = monitor;
    }

    @Override
    public void run() {
        try {
            while (true) {
                monitor.consumir();
                TimeUnit.MILLISECONDS.sleep(500);
            }
        } catch (InterruptedException e) { 
            Thread.currentThread().interrupt(); 
        }
    }
}
