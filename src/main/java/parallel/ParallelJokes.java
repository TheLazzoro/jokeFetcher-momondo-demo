package parallel;

import dtos.JokeDTO;
import sequental.SequentialPinger;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ParallelJokes {
    public List<JokeDTO> getJokes() {
        List<JokeDTO> list = null;

        String[] urls = SequentialPinger.getJokeList();
        List<String> results = new ArrayList<>();
        ExecutorService es = Executors.newCachedThreadPool();

        // We make a list of futures, since they fetch on their own thread.
        List<Future<String>> futures = new ArrayList<>();
        for (int i = 0; i < urls.length; i++) {
            Future<String> fut = es.submit(new PingURL(urls[i]));
            futures.add(fut);
        }

        // Loops through all fetched futures.
        // Each are blocking the loop until they finish fecthing,
        // so some futures later down the loop CAN finish before the others
        for(Future<String> future : futures) {
            String response = future.get();
            results.add(response);
        }

        return results;
    }
}
