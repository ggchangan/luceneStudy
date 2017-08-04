package cn.ggchangan.lucene;

import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;


public class TokenInfoRun {
    public static void main(String[] args) {
        String text = "The quick brown fox ...";

        AnalyzerUtils.tokenInfos(new WhitespaceAnalyzer(), text)
                .stream().map(TokenInfo::toString).forEach(System.out::println);

        System.out.println(".....................");

        AnalyzerUtils.tokenInfos(new SimpleAnalyzer(), text)
                .stream().map(TokenInfo::toString).forEach(System.out::println);

        System.out.println(".....................");

        AnalyzerUtils.tokenInfos(new StandardAnalyzer(), "I'll e-mail you at xyz@example.com")
                .stream().map(TokenInfo::toString).forEach(System.out::println);

    }
}
