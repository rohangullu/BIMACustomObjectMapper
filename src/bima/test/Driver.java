package bima.test;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by rohan.ghosh on 27/08/17.
 */
@SuppressWarnings("InfiniteLoopStatement")
public class Driver {

  private final Configuration configuration;
  private final ValueFetcher valueFetcher;

  public Driver(String configPath) {
    this.configuration = readConfig(configPath);
    this.valueFetcher = new ValueFetcher();
  }

  private Configuration readConfig(String configPath) {
    ConfigMapper<Configuration> mapper = new ObjectMapperImpl();
    return mapper.readValue(configPath);
  }

  public static void main(String args[]) throws IOException {
    // Please provide path of file as argument
    String configPath = args[0];
    // String configPath = "/Users/rohan.ghosh/dev/test/src/bima/test/config/config.rohan";
    Driver driver = new Driver(configPath);
    driver.start();
  }

  private void start() {

    while(true) {
      // “Section-1::Sub-Section-1::Sub-Section-1_1::Var1”
      String query = getQuery();
      System.out.println(valueFetcher.getValue(query, configuration));
    }
  }


  private String getQuery() {
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter your query");
    return sc.next();
  }

}
