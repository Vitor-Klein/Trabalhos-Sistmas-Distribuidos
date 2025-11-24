package QuestaoQuatro.b;

public class Transferencia implements Runnable {
    
    private final Conta de;
    private final Conta para;
    private final double valor;

    public Transferencia(Conta de, Conta para, double valor) {
        this.de = de;
        this.para = para;
        this.valor = valor;
    }
    
    @Override
    public void run() {
        Conta lock1 = de.id < para.id ? de : para;
        Conta lock2 = de.id < para.id ? para : de;
        
        lock1.lock.lock();
        lock2.lock.lock();
        try {
            if (de.saldo >= valor) {
                de.saldo -= valor;
                para.saldo += valor;
                System.out.printf("TRANSFER: C%d -> C%d: %.2f (Saldos: C%d=%.2f, C%d=%.2f)\n",
                        de.id, para.id, valor, de.id, de.saldo, para.id, para.saldo);
            } else {
                System.out.printf("TRANSFER: C%d -> C%d: FALHOU (Saldo C%d: %.2f)\n",
                        de.id, para.id, de.id, de.saldo);
            }
        } finally {
            lock2.lock.unlock();
            lock1.lock.unlock();
        }
    }
}