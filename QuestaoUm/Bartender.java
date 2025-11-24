import java.util.*;

class Bartender {
    public synchronized void atenderPedidos(List<Cliente> pedidos, int idGarcom) throws InterruptedException {
        System.out.println("--> Bartender recebendo pedidos do Garçom " + idGarcom + ":");
        for (Cliente c : pedidos) {
            System.out.println("   --> Preparando bebida para Cliente " + c.getIdCliente());
            Thread.sleep(700);
        }
        System.out.println("--> Bartender terminou os pedidos do Garçom " + idGarcom);
    }
}