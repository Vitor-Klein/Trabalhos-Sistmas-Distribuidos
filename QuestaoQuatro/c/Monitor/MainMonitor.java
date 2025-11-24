package QuestaoQuatro.c.Monitor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainMonitor {

    public static void main(String[] args) {
        BufferMonitor monitor = new BufferMonitor();
        ExecutorService executor = Executors.newFixedThreadPool(2);
        
        executor.submit(new ProdutorMonitor(monitor));
        executor.submit(new ConsumidorMonitor(monitor));
    }
}
