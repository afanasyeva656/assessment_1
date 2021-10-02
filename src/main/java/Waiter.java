public class Waiter implements Runnable{
    private final Counter counter;

    public Waiter(Counter counter) {
        this.counter = counter;
    };

    @Override
    public void run() {
        while (true) {
            try {
                if (this.counter.getCountSec() % 5 == 0) {
                    System.out.println("Ачивка 5 сек");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
