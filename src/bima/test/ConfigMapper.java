package bima.test;

/**
 * Created by rohan.ghosh on 27/08/17.
 */
public interface ConfigMapper<T> {
  T readValue(String configPath);
}