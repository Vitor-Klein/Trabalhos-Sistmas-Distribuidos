package QuestaoQuatro.cMonitor;

import java.util.concurrent.TimeUnit;

public class Consumidor implements Runnable {
    
    private final Buffer monitor;

    public Consumidor(Buffer monitor) {
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