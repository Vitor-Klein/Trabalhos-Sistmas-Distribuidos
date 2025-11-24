package QuestaoQuatro.cMonitor;

import java.util.LinkedList;
import java.util.Queue;

public class Buffer {

    private final Queue<Integer> buffer = new LinkedList<>();
    private final int TAM_BUFFER = 5;

    public synchronized void produzir(int item) throws InterruptedException {
        while (buffer.size() == TAM_BUFFER) {
            System.out.println("Buffer CHEIO. Produtor esperando...");
            wait();
        }
        
        buffer.add(item);
        System.out.println("Produtor produziu: " + item + " (Buffer: " + buffer.size() + ")");
        
        notifyAll();
    }

    public synchronized int consumir() throws InterruptedException {
        while (buffer.isEmpty()) {
            System.out.println("Buffer VAZIO. Consumidor esperando...");
            wait();
        }
        
        int item = buffer.poll();
        System.out.println("Consumidor consumiu: " + item + " (Buffer: " + buffer.size() + ")");
        
        notifyAll();
        return item;
    }
}
