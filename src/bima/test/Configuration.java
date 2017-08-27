package bima.test;

import java.util.HashMap;
import java.util.Map;


class Configuration {
  private Map<String, String> variableMaps = new HashMap<>();
  private Map<String, Configuration> subPOJO = new HashMap<>();

  Map<String, String> getVariableMaps() {
    return variableMaps;
  }

  void setVariableMaps(Map<String, String> variableMaps) {
    this.variableMaps = variableMaps;
  }

  Map<String, Configuration> getSubPOJO() {
    return subPOJO;
  }

}
