package QuestaoQuatro.cSemaforo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    
    public static void main(String[] args) {
        Buffer buffer = new Buffer();
        ExecutorService executor = Executors.newFixedThreadPool(2);
        
        executor.submit(new Produtor(buffer));
        executor.submit(new Consumidor(buffer));
    }
}
