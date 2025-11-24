package QuestaoQuatro.b;

public class Deposito implements Runnable {
    
    private final Conta conta;
    private final double valor;
    
    public Deposito(Conta c, double v) { 
        this.conta = c; 
        this.valor = v; 
    }
    
    @Override 
    public void run() { 
        conta.deposito(valor); 
    }
}