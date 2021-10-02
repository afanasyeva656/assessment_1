public class Main {
    public static void main(String[] args) {
        Counter counter = new Counter();
        Timer timer = new Timer(counter);
        Waiter fiveSecWaiter = new Waiter(counter);

        new Thread(timer).start();
        new Thread(fiveSecWaiter).start();

        System.out.println("Поехали!");
    }
}
