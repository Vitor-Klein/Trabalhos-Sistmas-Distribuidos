package QuestaoQuatro.b;

import java.util.concurrent.locks.ReentrantLock;

public class Conta {

    final int id;
    double saldo;
    final ReentrantLock lock = new ReentrantLock();

    public Conta(int id, double saldoInicial) {
        this.id = id;
        this.saldo = saldoInicial;
    }

    public void deposito(double valor) {
        lock.lock();
        try {
            saldo += valor;
            System.out.printf("DEPÓSITO na conta %d: +%.2f (Saldo: %.2f)\n", id, valor, saldo);
        } finally {
            lock.unlock();
        }
    }

    public void saque(double valor) {
        lock.lock();
        try {
            if (saldo >= valor) {
                saldo -= valor;
                System.out.printf("SAQUE da conta %d: -%.2f (Saldo: %.2f)\n", id, valor, saldo);
            } else {
                System.out.printf("SAQUE da conta %d: FALHOU (Saldo: %.2f, Tentativa: %.2f)\n", id, saldo, valor);
            }
        } finally {
            lock.unlock();
        }
    }
    
    public void creditoJuros(double taxa) {
        lock.lock();
        try {
            double juros = saldo * taxa;
            saldo += juros;
            System.out.printf("JUROS na conta %d: +%.2f (Saldo: %.2f)\n", id, juros, saldo);
        } finally {
            lock.unlock();
        }
    }
}
