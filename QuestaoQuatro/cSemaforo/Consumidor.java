package QuestaoQuatro.cSemaforo;

import java.util.concurrent.TimeUnit;

public class Consumidor implements Runnable {
    
    private final Buffer bufferCompartilhado;

    public Consumidor(Buffer buffer) {
        this.bufferCompartilhado = buffer;
    }

    @Override
    public void run() {
        try {
            while (true) {
                bufferCompartilhado.full.acquire();
                bufferCompartilhado.mutex.acquire();
                
                int item = bufferCompartilhado.buffer.poll();
                System.out.println("Consumidor consumiu: " + item + " (Buffer: " + bufferCompartilhado.buffer.size() + ")");
                
                bufferCompartilhado.mutex.release();
                bufferCompartilhado.empty.release();
                
                TimeUnit.MILLISECONDS.sleep(500);
            }
        } catch (InterruptedException e) { 
            Thread.currentThread().interrupt(); 
        }
    }
}
