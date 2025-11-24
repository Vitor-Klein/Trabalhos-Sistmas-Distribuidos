package QuestaoQuatro.a;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        
        final int N_CATRACAS = 4;
        final int N_PASSAGENS_POR_CATRACA = 1000000;
        final int TOTAL_ESPERADO = N_CATRACAS * N_PASSAGENS_POR_CATRACA;

        System.out.printf("Simulando %d catracas, cada uma com %,d passagens.\n", 
                          N_CATRACAS, N_PASSAGENS_POR_CATRACA);
        System.out.printf("Valor total esperado (Soma dos Locais): %,d\n\n", TOTAL_ESPERADO);

        
        System.out.println("--- Iniciando Simulação 1: COM Race Condition ---");
        Contador contadorComProblema = new Contador();
        ExecutorService executor1 = Executors.newFixedThreadPool(N_CATRACAS);

        for (int i = 0; i < N_CATRACAS; i++) {
            executor1.submit(new Catraca(i, contadorComProblema, N_PASSAGENS_POR_CATRACA, false));
        }

        executor1.shutdown();
        executor1.awaitTermination(1, TimeUnit.MINUTES);

        System.out.printf("Resultado (Contador Central c/ erro):   %,d\n", 
                          contadorComProblema.getContagem());

        
        System.out.println("\n--- Iniciando Simulação 2: CORRIGIDA (com Atomic) ---");
        Contador contadorCorrigido = new Contador();
        ExecutorService executor2 = Executors.newFixedThreadPool(N_CATRACAS);

        for (int i = 0; i < N_CATRACAS; i++) {
            executor2.submit(new Catraca(i, contadorCorrigido, N_PASSAGENS_POR_CATRACA, true));
        }

        executor2.shutdown();
        executor2.awaitTermination(1, TimeUnit.MINUTES);

        System.out.printf("Resultado (Contador Central corrigido):  %,d\n", 
                          contadorCorrigido.getContagemCorreta());
    }
}
