package QuestaoQuatro.b;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        Conta conta1 = new Conta(1, 1000);
        Conta conta2 = new Conta(2, 500);

        System.out.println("--- Cenário i ---");
        executor.submit(new Transferencia(conta1, conta2, 100)); 
        executor.submit(new Deposito(conta1, 50)); 
        executor.submit(() -> conta2.creditoJuros(0.01)); 

        System.out.println("\n--- Cenário ii ---");
        executor.submit(new Saque(conta2, 200)); 
        executor.submit(new Deposito(conta1, 100)); 
        executor.submit(() -> conta1.creditoJuros(0.01)); 
        executor.submit(new Transferencia(conta2, conta1, 50)); 
        
        executor.shutdown();
    }
}
