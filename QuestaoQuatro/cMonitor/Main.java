package QuestaoQuatro.cMonitor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        Buffer monitor = new Buffer();
        ExecutorService executor = Executors.newFixedThreadPool(2);
        
        executor.submit(new Produtor(monitor));
        executor.submit(new Consumidor(monitor));
    }
}
