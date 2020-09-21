package org.zhire.design.factory.newstudy.simpleFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 简单工厂的实现优化
 */
public class RuleConfigSourceBetter {

    public void load(String ruleConfigFilePath) {
        String ruleConfigFileExtension = "";
        Object parser = RuleConfigParserFactory.createParser(ruleConfigFileExtension);
        if (parser == null) {
            System.out.println("null");
        }
    }

    /**
     * 简单工厂类，根据传入的拓展名生成不同的对象优化版
     * 这样不用每次调用都生成parser对象
     */
    public static class RuleConfigParserFactory {
        private static final Map map = new HashMap<>();

        static {
            map.put("json", new Object());
            map.put("xml", new Object());
            map.put("yaml", new Object());
            map.put("properties", new Object());
        }

        static Object createParser(String configFormat) {
            return map.get(configFormat);
        }
    }

}