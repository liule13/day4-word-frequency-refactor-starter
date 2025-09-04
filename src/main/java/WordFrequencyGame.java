import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    public static final String ANY_SPACE_SEPARATOR = "\\s+";

    public String calculateWordFrequencies(String inputStr) {
        String[] words = inputStr.split(ANY_SPACE_SEPARATOR);
        if (words.length == 1) {
            return inputStr + " 1";
        }
        List<WordFrequency> frequencies = countFrequencies(words);
        return formatResult(frequencies);
    }

    private static String formatResult(List<WordFrequency> frequencies) {
        return frequencies.stream()
                .sorted(Comparator.comparingInt(WordFrequency::getFrequency).reversed())
                .map(f -> f.getWord() + " " + f.getFrequency())
                .collect(Collectors.joining("\n"));
    }

    private List<WordFrequency> countFrequencies(String[] words) {
        return Arrays.stream(words)
                .collect(Collectors.groupingBy(w -> w, Collectors.counting()))
                .entrySet().stream()
                .map(e -> new WordFrequency(e.getKey(), e.getValue().intValue()))
                .collect(Collectors.toList());
    }
}
