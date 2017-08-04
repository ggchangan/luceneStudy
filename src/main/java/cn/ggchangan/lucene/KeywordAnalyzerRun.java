package cn.ggchangan.lucene;

import org.apache.lucene.analysis.core.KeywordAnalyzer;

/**
 * 测试KeywordAnalyzer作用
 * 整个文本作为一个单一词汇单元处理
 */
public class KeywordAnalyzerRun {
    public static void main(String[] args) {
        String info = "空格无效果";
        System.out.println(info);
        String text = "Set maximum the allowed token length";
        AnalyzerUtils.tokenInfos(new KeywordAnalyzer(), text)
                .stream().map(TokenInfo::toString).forEach(System.out::println);

        info = "特殊字符和数字无效果";
        System.out.println(info);
        text = "Set@@maximum@allowed#the#token3length";
        AnalyzerUtils.tokenInfos(new KeywordAnalyzer(), text)
                .stream().map(TokenInfo::toString).forEach(System.out::println);

        info = "中文字符无效果";
        System.out.println(info);
        text = "Set中maximum华allowed人the民token国length";
        AnalyzerUtils.tokenInfos(new KeywordAnalyzer(), text)
                .stream().map(TokenInfo::toString).forEach(System.out::println);
    }
}
