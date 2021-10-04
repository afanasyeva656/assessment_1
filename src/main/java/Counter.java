public class Counter {
    private int countSec = 0;

    public synchronized void addSec() { countSec++; };
    public synchronized int getCountSec() throws InterruptedException { return countSec; };
}