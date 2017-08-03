package cn.ggchangan.lucene;


import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class AnalyzerUtils {
    public static void displayTokens(Analyzer analyzer, String text) throws IOException {
        displayTokens(
                analyzer.tokenStream("contents", new StringReader(text))
        );
    }

    public static void displayTokens(TokenStream stream) throws IOException {
        CharTermAttribute charTermAttribute = stream.addAttribute(CharTermAttribute.class);
        while (stream.incrementToken()) {
            System.out.println(
                    String.format("[%s]", charTermAttribute.toString())
            );
        }
    }

    public static List<String> tokenizeString(Analyzer analyzer, String string) {
        List<String> result = new ArrayList<>();
        TokenStream stream = null;
        try {
            stream  = analyzer.tokenStream(null, new StringReader(string));
            stream.reset();
            while (stream.incrementToken()) {
                result.add(stream.getAttribute(CharTermAttribute.class).toString());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (stream != null) {
                try {
                    stream.end();
                    stream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return result;
    }
}
