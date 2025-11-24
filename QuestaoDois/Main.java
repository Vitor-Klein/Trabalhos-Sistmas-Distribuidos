package QuestaoDois;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    
    public static void main(String[] args) {
        int N_CADEIRAS = 3;
        int N_CLIENTES = 10;
        
        ExecutorService executor = Executors.newCachedThreadPool();
        BarbeiroDorminhoco barbearia = new BarbeiroDorminhoco(N_CADEIRAS);
        
        executor.submit(new Barbeiro(barbearia));
        
        for (int i = 0; i < N_CLIENTES; i++) {
            try {
                TimeUnit.MILLISECONDS.sleep((long) (Math.random() * 500));
                executor.submit(new Cliente(i, barbearia));
            } catch (InterruptedException e) {}
        }
        
        executor.shutdown();
    }
}
