import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Producer implements Runnable{
    private final LinkedBlockingQueue<String> queue;
    private final ArrayList<String> list;

    public Producer(LinkedBlockingQueue<String> queue, ArrayList<String> list) {
        this.queue = queue;
        this.list = list;
    }

    @Override
    public void run() {
        findWord("страдани[иеяй].?");
        try {
            queue.put("стоп");  // маркер для Consumer, Producer перебрал весь текст
            System.out.println("Producer закончил работу");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void findWord(String wordPattern) {
        if (list != null) {
            Pattern pattern = Pattern.compile(wordPattern, Pattern.CASE_INSENSITIVE);
            for (String s : list) {
                Matcher matcher = pattern.matcher(s);
                while (matcher.find()) {
                    String foundedWord = s.substring(matcher.start(), matcher.end());
                    putFoundedWordIntoQueue(foundedWord);
                }
            }
        }
    }

    private void putFoundedWordIntoQueue(String foundedWord) {
        try {
            queue.put(foundedWord);
            System.out.println("Producer положил в queue слово '" + foundedWord + "'");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
