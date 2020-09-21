package org.zhire.design.factory.newstudy.simpleFactory;

/**
 * 简单工厂的实现
 */
public class RuleConfigSource {

    public void load(String ruleConfigFilePath) {
        String ruleConfigFileExtension = "";
        Object parser = RuleConfigParserFactory.createParser(ruleConfigFileExtension);
        if (parser == null) {
            System.out.println("null");
        }
    }

    /**
     * 简单工厂类，根据传入的拓展名生成不同的对象
     */
    public static class RuleConfigParserFactory {
        static Object createParser(String configFormat) {
            Object parser = null;
            if ("json".equalsIgnoreCase(configFormat)) {
                parser = new Object(); // new JsonRuleConfigParser();
            } else if ("xml".equalsIgnoreCase(configFormat)) {
                parser = new Object(); // new XmlRuleConfigParser();
            } else if ("yaml".equalsIgnoreCase(configFormat)) {
                parser = new Object(); // YamlRuleConfigParser();
            } else if ("properties".equalsIgnoreCase(configFormat)) {
                parser = new Object(); // PropertiesRuleConfigParser();
            }
            return parser;
        }
    }

}