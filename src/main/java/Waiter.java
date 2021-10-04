public class Waiter extends Thread{
    private final Counter counter;
    private final int secTarget;

    public Waiter(Counter counter, int secTarget) {
        this.counter = counter;
        this.secTarget = secTarget;
    };

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                synchronized (this.counter) {
                    counter.wait();
                    if (counter.getCountSec() % secTarget == 0) {
                        System.out.println("Ачивка " + secTarget + " сек");
                    }

                    // Завершение работы потока после достижения 20 сек
                    if (counter.getCountSec() >= 20) {interrupt();};
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
