import java.util.concurrent.LinkedBlockingQueue;

public class Consumer implements Runnable{
    private final LinkedBlockingQueue<String> queue;
    private int wordCount = 0;

    public Consumer(LinkedBlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (queue.take().equals("стоп")) {
                    System.out.println("Consumer закончил работу");
                    break;
                } else {
                    wordCount++;
                    System.out.println("Consumer насчитал " + wordCount + " вхождений слова 'страдание'");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
