public class Timer extends Thread{
    private final Counter counter;

    public Timer(Counter counter) {
        this.counter = counter;
    };

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                Thread.sleep(1_000);
                synchronized (this.counter) {
                    counter.addSec();
                    counter.notifyAll();
                    System.out.println("Прошло " + counter.getCountSec() + " сек");

                    // Завершение работы потока после достижения 20 сек
                    if (counter.getCountSec() == 20) {interrupt();};
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
