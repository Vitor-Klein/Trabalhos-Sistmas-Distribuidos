package QuestaoQuatro.c.Semaforos;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class BufferSemaforo {
    
    final Queue<Integer> buffer = new LinkedList<>();
    final int TAM_BUFFER = 5;

    final Semaphore full = new Semaphore(0);
    final Semaphore empty = new Semaphore(TAM_BUFFER);
    final Semaphore mutex = new Semaphore(1);
}
