
import java.util.*;

public class Bar {
    static int rodadas;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("=== Simulação do Bar ===");
        System.out.print("Informe o número de clientes: ");
        int numClientes = input.nextInt();

        System.out.print("Informe o número de garçons: ");
        int numGarcons = input.nextInt();

        System.out.print("Informe a capacidade (quantos pedidos cada garçom coleta): ");
        int capacidade = input.nextInt();

        System.out.print("Informe o número de rodadas: ");
        rodadas = input.nextInt();

        Bartender bartender = new Bartender();
        List<Garcom> garcons = new ArrayList<>();

        for (int i = 1; i <= numGarcons; i++) {
            Garcom g = new Garcom(i, capacidade, bartender);
            garcons.add(g);
            new Thread(g, "Garcom-" + i).start();
        }

        Random r = new Random();
        for (int i = 1; i <= numClientes; i++) {
            Garcom escolhido = garcons.get(r.nextInt(numGarcons));
            new Thread(new Cliente(i, escolhido), "Cliente-" + i).start();
        }

        input.close();
    }
}