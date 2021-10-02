public class Counter {
    private int countSec = 0;

    // notifyAll - оповещает потоки о том, что время увеличилось на 1 сек
    public synchronized void addSec() {
        countSec++;
        notifyAll();
    };

    //
    public synchronized int getCountSec() throws InterruptedException {
        wait();
        return countSec;
    };
}