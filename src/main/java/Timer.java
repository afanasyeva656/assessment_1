public class Timer implements Runnable{
    private final Counter counter;

    public Timer(Counter counter) {
        this.counter = counter;
    };

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1_000);
                this.counter.addSec();
                System.out.println("Прошло " + this.counter.getCountSec() + " секунд");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
