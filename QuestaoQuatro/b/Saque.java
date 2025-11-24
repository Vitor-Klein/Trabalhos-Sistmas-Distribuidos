package QuestaoQuatro.b;

public class Saque implements Runnable {
    
    private final Conta conta;
    private final double valor;
    
    public Saque(Conta c, double v) { 
        this.conta = c; 
        this.valor = v; 
    }
    
    @Override 
    public void run() { 
        conta.saque(valor); 
    }
}
