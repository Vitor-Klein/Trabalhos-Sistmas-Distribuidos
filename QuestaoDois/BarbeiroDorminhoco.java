package QuestaoDois;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BarbeiroDorminhoco {

    final int N_CADEIRAS;
    final Queue<Cliente> clientesEsperando = new LinkedList<>();

    final ReentrantLock lockMonitor = new ReentrantLock();
    final Condition barbeiroCondition = lockMonitor.newCondition();
    final Condition clienteCondition = lockMonitor.newCondition();
    
    boolean barbeiroDormindo = true;

    public BarbeiroDorminhoco(int nCadeiras) {
        this.N_CADEIRAS = nCadeiras;
    }
}
