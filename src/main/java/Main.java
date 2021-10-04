import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        Timer timer = new Timer(counter);
        Waiter fiveSecWaiter = new Waiter(counter, 5);
        Waiter sevenSecWaiter = new Waiter(counter, 7);

        timer.start();
        fiveSecWaiter.start();
        sevenSecWaiter.start();

        // Исполнение следующего блока кода начнется, когда потоки завершат работу
        // Маркер завершения работы потока указан в методе run
        timer.join();
        fiveSecWaiter.join();
        sevenSecWaiter.join();

        System.out.println("<-- Начинается работа Producer и Consumer -->");

        File file = new File("voyna.txt");
        Parser parser = new Parser();
        ArrayList<String> list = null;
        try {
            list = parser.parse(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();
        Producer producer = new Producer(queue, list);
        Consumer consumer = new Consumer(queue);
        new Thread(producer).start();
        new Thread(consumer).start();
    }
}
