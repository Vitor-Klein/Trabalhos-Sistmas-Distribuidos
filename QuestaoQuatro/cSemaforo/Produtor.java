package QuestaoQuatro.cSemaforo;

import java.util.concurrent.TimeUnit;

public class Produtor implements Runnable {
    
    private final Buffer bufferCompartilhado;

    public Produtor(Buffer buffer) {
        this.bufferCompartilhado = buffer;
    }

    @Override
    public void run() {
        try {
            int item = 0;
            while (true) {
                item++;
                bufferCompartilhado.empty.acquire();
                bufferCompartilhado.mutex.acquire();
                
                bufferCompartilhado.buffer.add(item);
                System.out.println("Produtor produziu: " + item + " (Buffer: " + bufferCompartilhado.buffer.size() + ")");
                
                bufferCompartilhado.mutex.release();
                bufferCompartilhado.full.release();
                
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (InterruptedException e) { 
            Thread.currentThread().interrupt(); 
        }
    }
}