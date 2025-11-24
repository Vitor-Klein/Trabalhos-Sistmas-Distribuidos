package QuestaoQuatro.c.Semaforos;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainSemaforo {
    
    public static void main(String[] args) {
        BufferSemaforo buffer = new BufferSemaforo();
        ExecutorService executor = Executors.newFixedThreadPool(2);
        
        executor.submit(new ProdutorSemaforo(buffer));
        executor.submit(new ConsumidorSemaforo(buffer));
    }
}
