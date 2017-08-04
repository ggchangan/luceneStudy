package cn.ggchangan.lucene;

import org.apache.lucene.analysis.core.StopAnalyzer;

import java.io.IOException;
import java.io.StringReader;

/**
 * 测试StopAnalyzer的使用
 * 根据非字母拆分文本，然后小写化，再移除停用词
 * LowerCaseTokenizer + StopFilter
 */
public class StopAnalyzerRun {
    public static void main(String[] args) {
        String info = "测试以空格分词";
        System.out.println(info);
        String text = "Set maximum the allowed token length";
        AnalyzerUtils.tokenInfos(new StopAnalyzer(), text)
                .stream().map(TokenInfo::toString).forEach(System.out::println);

        info = "测试以特殊字符和数字分词";
        System.out.println(info);
        text = "Set@@maximum@allowed#the#token3length";
        AnalyzerUtils.tokenInfos(new StopAnalyzer(), text)
                .stream().map(TokenInfo::toString).forEach(System.out::println);

        info = "中文字符无效果";
        System.out.println(info);
        text = "Set中maximum华allowed人the民token国length";
        AnalyzerUtils.tokenInfos(new StopAnalyzer(), text)
                .stream().map(TokenInfo::toString).forEach(System.out::println);
        //输出结果为
        //TokenInfo{term='set中maximum华allowed人the民token国length', position=1, startOffset=0, endOffset=36, type='word'}
        //在java中汉字也被认为是字母

        info = "测试把set当作停用词";
        System.out.println(info);
        text = "Set maximum the allowed token length";
        try {
            AnalyzerUtils.tokenInfos(new StopAnalyzer(new StringReader("set")), text)
                    .stream().map(TokenInfo::toString).forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
