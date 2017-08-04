package cn.ggchangan.lucene;

import org.apache.lucene.analysis.standard.StandardAnalyzer;

/**
 * 测试StandardAnalyzer分词器的作用
 * 基于复杂语法来生成词汇单元，
 */
public class StandardAnalyzerRun {
    public static void main(String[] args) {
        String info = "测试以空格分词，小写化，移除stopwords";
        System.out.println(info);
        String text = "Set maximum the allowed token length";
        AnalyzerUtils.tokenInfos(new StandardAnalyzer(), text)
                .stream().map(TokenInfo::toString).forEach(System.out::println);

        info = "特殊字符分词，数字没有效果";
        System.out.println(info);
        text = "Set@@maximum@allowed#the#token3length";
        AnalyzerUtils.tokenInfos(new StandardAnalyzer(), text)
                .stream().map(TokenInfo::toString).forEach(System.out::println);

        info = "中文分词1";
        System.out.println(info);
        text = "Set中maximum华allowed人the民token国length";
        AnalyzerUtils.tokenInfos(new StandardAnalyzer(), text)
                .stream().map(TokenInfo::toString).forEach(System.out::println);

        info = "中文分词2";
        System.out.println(info);
        text = "中华人民共和国";
        AnalyzerUtils.tokenInfos(new StandardAnalyzer(), text)
                .stream().map(TokenInfo::toString).forEach(System.out::println);

        info = "邮箱";
        System.out.println(info);
        text = "zhangren1989@gmail.com xyz@example.com XY&Z";
        AnalyzerUtils.tokenInfos(new StandardAnalyzer(), text)
                .stream().map(TokenInfo::toString).forEach(System.out::println);
        //分成zhangren1989和gmail.com
        //说明，默认配置StandardAnalyzer并不能识别出邮箱，但是@却可以作为特殊字符分割tokens
        //数字和"."没有分词效果

        //TODO 增加更多的测试用例

    }
}
