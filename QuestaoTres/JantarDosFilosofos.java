package QuestaoTres;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JantarDosFilosofos {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite a quantidade de filósofos (mínimo 2): ");
        int N_FILOSOFOS = scanner.nextInt();
        scanner.close();

        if (N_FILOSOFOS < 2) {
            System.out.println("A simulação requer pelo menos 2 filósofos.");
            return;
        }

        System.out.printf("Iniciando jantar com %d filósofos...\n", N_FILOSOFOS);

        Object[] hashis = new Object[N_FILOSOFOS];
        for (int i = 0; i < N_FILOSOFOS; i++) {
            hashis[i] = new Object();
        }

        ExecutorService executor = Executors.newFixedThreadPool(N_FILOSOFOS);

        for (int i = 0; i < N_FILOSOFOS; i++) {
            Object hashiEsquerdo = hashis[i];
            Object hashiDireito = hashis[(i + 1) % N_FILOSOFOS];

            executor.submit(new Filosofo(i, hashiEsquerdo, hashiDireito));
        }
    }
}