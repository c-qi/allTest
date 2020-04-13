package org.zhire.utils;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


// 演示例子，执行 main 方法控制台输入模块表名回车自动生成对应项目目录中
public class CodeGenerator {

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {
        String module = scanner("模块名");
        String projectPath = new File(CodeGenerator.class.getClassLoader().getResource("").getPath()).getParentFile().getParent();
//        String projectPath = System.getProperty("user.dir");
        while (true) {
            handle(module, projectPath);
            if (!jargeOps()) {
                break;
            }
        }

    }

    private static boolean jargeOps() {
        String opFlag = scanner("是否继续（y/n)");
        switch (opFlag) {
            case "y":
            case "Y":
                return true;
            case "n":
            case "N":
                return false;
            default:
                System.out.println("输入错误，请重新输入");
                return jargeOps();
        }
    }

    private static void handle(String module, String projectPath) {
        // 代码生成器
        String outputDir = projectPath + "/src/main/java";
        String parent = "fun.chenqi";
        // 如果模板引擎是 freemarker
//        String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        String templatePath = "/templates/mapper.xml.vm";
        new AutoGenerator()
                //全局配置
                .setGlobalConfig(new GlobalConfig()
                        .setOutputDir(outputDir)
                        .setFileOverride(true)
                        .setActiveRecord(true)
                        // XML 二级缓存
                        .setEnableCache(false)
                        // XML ResultMap
                        .setBaseResultMap(true)
                        // XML columList
                        .setBaseColumnList(true)
                        .setAuthor("chenqi")
                        .setOpen(false))
                //数据源配置
                .setDataSource(new DataSourceConfig().setUrl("jdbc:mysql://localhost:3306/blog?useUnicode=true&useSSL=false&characterEncoding=utf8")
                        // dsc.setSchemaName("public");
                        .setDriverName("com.mysql.jdbc.Driver")
                        .setUsername("root")
                        .setPassword("111"))
                // 包配置
                .setPackageInfo(new PackageConfig().setParent(parent)
                        .setModuleName(module)
                        .setEntity("model")
                        .setController("controller")
                        .setService( "service")
                        .setServiceImpl( "service.impl")
                        .setMapper("mapper"))
                .setCfg(new InjectionConfig() {
                            @Override
                            public void initMap() {
                                Map<String, Object> map = new HashMap<>();
                                map.put("BasicPackage", parent);
                                this.setMap(map);
                            }
                        }.setFileOutConfigList(Collections.<FileOutConfig>singletonList(new FileOutConfig(templatePath) {
                            @Override
                            public String outputFile(TableInfo tableInfo) {
                                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                                return projectPath + "/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper.xml";
                            }
                        })))
                // 配置模板
                .setTemplate(new TemplateConfig()
//                         配置自定义输出模板
//                        指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
//                      .setEntity("templates/entity2.java")
//                      .setService()
//                        .setController("templates/controller.java")
//                         禁用默认xml输出
                        .setXml(null))
//                全局策略配置
                .setStrategy(new StrategyConfig()
                        .setNaming(NamingStrategy.underline_to_camel)
//                              .setColumnNaming(NamingStrategy.underline_to_camel)
//                              .setSuperEntityClass("com.baomidou.ant.common.BaseEntity")
                        .setEntityLombokModel(true)
                        .setRestControllerStyle(true)
//                              .setSuperControllerClass("com.baomidou.ant.common.BaseController")
                        .setInclude(scanner("表名"))
//                              .setSuperEntityColumns("id")
//                        .setTablePrefix(module + "_")
                        .setControllerMappingHyphenStyle(true))
                .setTemplateEngine(new VelocityTemplateEngine())
                .execute();
    }

}