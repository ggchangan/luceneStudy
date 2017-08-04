package cn.ggchangan.lucene;

import org.apache.lucene.analysis.core.SimpleAnalyzer;

/**
 * 主要测试SimpleAnalyzer的使用
 * 根据非字母拆分文本，并将其转换为小写形式
 * LowerCaseTokenizer
 */
public class SimpleAnalyzerRun {
    public static void main(String[] args) {

        String info = "测试以空格分词";
        System.out.println(info);
        String text = "Set maximum the allowed token length";
        AnalyzerUtils.tokenInfos(new SimpleAnalyzer(), text)
                .stream().map(TokenInfo::toString).forEach(System.out::println);

        info = "测试以特殊字符和数字分词";
        System.out.println(info);
        text = "Set@@maximum@allowed#the#token3length";
        AnalyzerUtils.tokenInfos(new SimpleAnalyzer(), text)
                .stream().map(TokenInfo::toString).forEach(System.out::println);

        info = "中文字符无效果";
        System.out.println(info);
        text = "Set中maximum华allowed人the民token国length";
        AnalyzerUtils.tokenInfos(new SimpleAnalyzer(), text)
                .stream().map(TokenInfo::toString).forEach(System.out::println);
        //输出结果为
        //TokenInfo{term='set中maximum华allowed人the民token国length', position=1, startOffset=0, endOffset=36, type='word'}
        //在java中汉字也被认为是字母
    }
}
