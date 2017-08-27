package bima.test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Created by rohan.ghosh on 27/08/17.
 */
public class ObjectMapperImpl implements ConfigMapper<Configuration> {

  private static final String EQUALS = "=";
  private static final int INDENTATION = 4;

  @Override
  public Configuration readValue(String configPath) {
    // Basic assumption is that indentation in the config would be increasing by 4 as it goes
    // each level deeper.
    Map<Integer, Configuration> currentIndentationMapper = new HashMap<>();
    Configuration configPOJO = new Configuration();
    currentIndentationMapper.put(0 , configPOJO);

    try (Stream<String> stream = Files.lines(Paths.get(configPath))) {
      stream.forEach(line -> {
        if(line != null) {
          int spaces = line.indexOf(line.trim());
          String field = line.trim();

          Configuration currentPOJO = currentIndentationMapper.get(spaces);

          if(field.contains(EQUALS)) {
            String[] varMap = field.split(EQUALS);
            Map<String, String> variableMap = currentPOJO.getVariableMaps();
            variableMap.put(varMap[0].trim(), varMap[1].trim());
            currentPOJO.setVariableMaps(variableMap);

          } else {
            Configuration newPOJO = new Configuration();
            currentPOJO.getSubPOJO().putIfAbsent(field, newPOJO);
            currentIndentationMapper.put(spaces + INDENTATION, newPOJO);
          }
        }
      });
    } catch (IOException e) {
      e.printStackTrace();
    }

    return configPOJO;
  }
}
