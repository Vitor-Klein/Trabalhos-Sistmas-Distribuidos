package QuestaoQuatro.cMonitor;

import java.util.concurrent.TimeUnit;

public class Produtor implements Runnable {
    
    private final Buffer monitor;

    public Produtor(Buffer monitor) {
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
