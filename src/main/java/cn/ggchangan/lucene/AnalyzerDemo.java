package cn.ggchangan.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.core.StopAnalyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

public class AnalyzerDemo {
    private static final String[] examples = {
            "The quick brown fox jumped over the lazy dog",
            "XY&Z Corporation - xyz@example.com"
    };

    private static final Analyzer[] analyzers = {
            new WhitespaceAnalyzer(),
            new SimpleAnalyzer(),
            new StopAnalyzer(),
            new StandardAnalyzer()
    };

    public static void main(String[] args) throws IOException {
        String[] strings = examples;

        if (args.length > 0) {
            strings = args;
        }

        for (String text: strings) {
            analyze(text);
        }
    }

    private static void analyze(String text) throws IOException {
        System.out.println(String.format("Analyzing \\%s \\", text));
        Arrays.stream(analyzers).map(
          analyzer ->{
              String prefix = analyzer.getClass().getSimpleName() + ":[";
              String suffix = "]";
              return AnalyzerUtils.tokenizeString(analyzer, text)
                      .stream().collect(
                      Collectors.joining(",", prefix, suffix)
              );
          }
        ).forEach(System.out::println);
    }
}
