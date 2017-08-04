package cn.ggchangan.lucene;


import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;

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

    public static List<TokenInfo> tokenInfos(Analyzer analyzer, String text) {
        List<TokenInfo> tokenInfos = new ArrayList<>();

        TokenStream stream = null;
        stream = analyzer.tokenStream(null, text);

        try {
            stream.reset();

            int position = 0;
            while (stream.incrementToken()) {
                CharTermAttribute termAttribute = stream.getAttribute(CharTermAttribute.class);
                PositionIncrementAttribute positionIncrementAttribute = stream.getAttribute(PositionIncrementAttribute.class);
                OffsetAttribute offsetAttribute = stream.getAttribute(OffsetAttribute.class);
                TypeAttribute typeAttribute = stream.getAttribute(TypeAttribute.class);

                int increment = positionIncrementAttribute.getPositionIncrement();
                if (increment > 0) {
                    position += increment;
                }

                TokenInfo tokenInfo = new TokenInfo();
                tokenInfo.setTerm(termAttribute.toString());
                tokenInfo.setPosition(position);
                tokenInfo.setStartOffset(offsetAttribute.startOffset());
                tokenInfo.setEndOffset(offsetAttribute.endOffset());
                tokenInfo.setType(typeAttribute.type());

                tokenInfos.add(tokenInfo);
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

        return tokenInfos;
    }
}
