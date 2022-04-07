package sequental;

/*
 * Code taken from 
 * http://crunchify.com/how-to-get-ping-status-of-any-http-end-point-in-java/
 */
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class SequentialPinger {

  private static String[] jokeURLs = {
    "https://api.chucknorris.io/jokes/random",
    "https://icanhazdadjoke.com/",
  };
  
  //Public so URL's can be reused in the parallel part
  public static String[] getJokeList(){
    return jokeURLs;
  }
  
  //Public so it can be reused in the  parallel part
  public static String getStatus(String url) throws IOException {

    String result = "Error";
    try {
      URL siteURL = new URL(url);
      HttpURLConnection connection = (HttpURLConnection) siteURL
              .openConnection();
      connection.setRequestMethod("GET");
      connection.connect();

      int code = connection.getResponseCode();
      if (code == 200) {
        result = "GREEN";
      }
      if (code == 301) {
        result = "REDIRECT";
      }
    } catch (Exception e) {
      result = "RED";
    }
    return result;
  }

  public static void main(String args[]) throws Exception {

    long timeStart = System.nanoTime();
    for (String url : jokeURLs) {
      String status = getStatus(url);
      System.out.println(url + "\t\tStatus:" + status);
    }
    long timeEnd = System.nanoTime();
    long total = (timeEnd - timeStart) / 1_000_000;
    System.out.println("Time to check URLS: " + total + "ms.");

  }
}
