import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    public static final String ANY_SPACE_SEPARATOR = "\\s+";

    public String getResult(String inputStr) {
        if (inputStr.split(ANY_SPACE_SEPARATOR).length == 1) {
            return inputStr + " 1";
        } else {
            try {
                //split the input string with 1 to n pieces of spaces
                String[] words = inputStr.split(ANY_SPACE_SEPARATOR);
                List<WordFrequency> frequencies = countFrequencies(words);

                frequencies.sort((w1, w2) -> w2.getFrequency() - w1.getFrequency());

                StringJoiner joiner = new StringJoiner("\n");
                for (WordFrequency w : frequencies) {
                    String s = w.getWord() + " " + w.getFrequency();
                    joiner.add(s);
                }
                return joiner.toString();
            } catch (Exception e) {
                return "Calculate Error";
            }
        }
    }

    private List<WordFrequency> countFrequencies(String[] words) {
        return Arrays.stream(words)
                .collect(Collectors.groupingBy(w -> w, Collectors.counting()))
                .entrySet().stream()
                .map(e -> new WordFrequency(e.getKey(), e.getValue().intValue()))
                .collect(Collectors.toList());
    }

    private static Map<String, List<String>> groupSameWords(String[] words) {
        List<String> inputList = new ArrayList<>();
        for (String s : words) {
            inputList.add(s);
        }
        //get the map for the next step of sizing the same word
        Map<String, List<String>> map = new HashMap<>();
        for (String input : inputList) {
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!map.containsKey(input)) {
                ArrayList words1 = new ArrayList<>();
                words1.add(input);
                map.put(input, words1);
            } else {
                map.get(input).add(input);
            }
        }

        return map;
    }


}
