package bima.test;

/**
 * Created by rohan.ghosh on 27/08/17.
 */
public class ValueFetcher {

  public String getValue(String query, Configuration configuration) {
    String[] queryArray = query.split("::");
    return findRecursively(configuration, 0, queryArray);
  }

  private String findRecursively(Configuration configuration,
                                        int level,
                                        String[] queryArray) {
    if(level == queryArray.length - 1) {
      return configuration.getVariableMaps().get(queryArray[queryArray.length - 1]);
    }

    Configuration nextLevelConfig = configuration.getSubPOJO().get(queryArray[level]);

    if(nextLevelConfig == null) {
      return configuration.getVariableMaps().get(queryArray[queryArray.length-1]);
    }

    String result = findRecursively(nextLevelConfig, level + 1, queryArray);

    if(result == null) {
      return configuration.getVariableMaps().get(queryArray[queryArray.length-1]);
    }
    return result;
  }
}
