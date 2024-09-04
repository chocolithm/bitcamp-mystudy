package study;

import java.util.*;

public class Solution {
  public int solution(String[][] clothes) {
    int answer = 1;
    Map<String, Integer> map = new HashMap<>();

    for (String[] cloth : clothes) {
      if (map.get(cloth[1]) == null)) {
        map.put(cloth[1], 1);
      } else {}
      map.put(cloth[1], map.get(cloth[1]) + 1);
    }
  }

  for (Integer value : map.values()) {
    answer *= value;
  }

  return answer - 1;
}
}