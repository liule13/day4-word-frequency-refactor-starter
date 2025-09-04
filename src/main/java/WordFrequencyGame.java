import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.io.CharArrayWriter;

import java.time.LocalDateTime;

public class WordFrequencyGame {

    public static final String ANY_SPACE_SEPARATOR = "\\s+";

    public String getResult(String inputStr) {
        if (inputStr.split(ANY_SPACE_SEPARATOR).length == 1) {
            return inputStr + " 1";
        } else {
            try {
                //split the input string with 1 to n pieces of spaces
                String[] words = inputStr.split(ANY_SPACE_SEPARATOR);
                List<Input> frequencies = countFrequencies(words);

                frequencies.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());

                StringJoiner joiner = new StringJoiner("\n");
                for (Input w : frequencies) {
                    String s = w.getValue() + " " + w.getWordCount();
                    joiner.add(s);
                }
                return joiner.toString();
            } catch (Exception e) {
                return "Calculate Error";
            }
        }
    }

    private List<Input> countFrequencies(String[] words) {
        List<Input> inputList = new ArrayList<>();
        for (String s : words) {
            Input input = new Input(s, 1);
            inputList.add(input);
        }
        //get the map for the next step of sizing the same word
        Map<String, List<Input>> map = getListMap(inputList);

        List<Input> frequencies = new ArrayList<>();
        for (Map.Entry<String, List<Input>> entry : map.entrySet()) {
            Input input = new Input(entry.getKey(), entry.getValue().size());
            frequencies.add(input);
        }
        return frequencies;
    }

    private Map<String, List<Input>> getListMap(List<Input> inputList) {
        Map<String, List<Input>> map = new HashMap<>();
        for (Input input : inputList) {
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!map.containsKey(input.getValue())) {
                ArrayList words = new ArrayList<>();
                words.add(input);
                map.put(input.getValue(), words);
            } else {
                map.get(input.getValue()).add(input);
            }
        }
        return map;
    }


}
