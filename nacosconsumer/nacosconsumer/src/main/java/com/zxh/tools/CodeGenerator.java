package com.zxh.tools;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.io.File;
import java.lang.reflect.Field;
import java.util.*;

/**
 * 基于MyBatis-Plus，进行自动代码生成，会一次性生成 entity,mapper,repository/impl,service/impl 等相应代码(不生成controller层)
 * <br/>
 * <b>注：若需要对源码进行覆盖，自行设置fileOverride属性</b>
 * <br/>
 * 使用说明:
 * 1. 主键字段zid设置为bigint类型，对应java类型Long
 *
 * */
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
            if (StringUtils.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new RuntimeException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) throws Exception {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        //gc.setAuthor(System.getProperty("user.name"));
        gc.setAuthor("");
        gc.setBaseColumnList(true).setBaseResultMap(true);
        gc.setFileOverride(false); // TODO 若需要覆盖原文件，自行配置
        gc.setOpen(false);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://127.0.0.1:3306/test?serverTimezone=UTC&useUnicode=true&useSSL=false&characterEncoding=utf8");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(scanner("模块名称"));
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(new MyConstVal(){}.toFileFtl(MyConstVal.TEMPLATE_REPOSITORY_IMPL)) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return pc.getPathInfo().get(MyConstVal.REPOSITORY_IMPL_PATH) + File.separator + tableInfo.getEntityName()+"RepositoryImpl" + StringPool.DOT_JAVA;
            }
        });
        focList.add(new FileOutConfig(new MyConstVal(){}.toFileFtl(MyConstVal.TEMPLATE_REPOSITORY)) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return pc.getPathInfo().get(MyConstVal.REPOSITORY_PATH) + File.separator + tableInfo.getEntityName()+"Repository" + StringPool.DOT_JAVA;
            }
        });

        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setSuperServiceClass("com.zxh.base.IBaseRepository");
        strategy.setSuperServiceImplClass("com.zxh.base.BaseRepositoryImpl");
        strategy.setSuperEntityClass("com.zxh.base.pojo.BaseEntity");
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        // 写于父类中的公共字段(这里设置的是数据库表中的字段名称)
        strategy.setSuperEntityColumns("zid", "uid", "creator", "create_time", "operator", "update_time", "is_delete", "seller_code");
        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix("saas_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());

        // 自定义配置
        customSettingInfo(pc, mpg);

        mpg.execute();
    }

    private static void customSettingInfo(PackageConfig pc, AutoGenerator mpg) throws Exception {
        String moduleName = pc.getModuleName();
        Map<String, String> packageInfo = new HashMap<>();
        packageInfo.put(MyConstVal.ENTITY, "com.zxh."+moduleName+".entity");
        packageInfo.put(MyConstVal.MAPPER, "com.zxh."+moduleName+".mapper");
        packageInfo.put(MyConstVal.SERVICE, "com.zxh."+moduleName+".service");
        packageInfo.put(MyConstVal.SERVICE_IMPL, "com.zxh."+moduleName+".service.impl");
        packageInfo.put(MyConstVal.REPOSITORY, "com.zxh."+moduleName+".repository");
        packageInfo.put(MyConstVal.REPOSITORY_IMPL, "com.zxh."+moduleName+".repository.impl");

        String daoPath = System.getProperty("user.dir") + "/nacosconsumer";//生成dao层代码的项目名称
        String servicePath = System.getProperty("user.dir") + "/nacosconsumer";//生成service层代码的项目名称
        String srcPath = "/src/main/java/";
        Map<String, String> pathInfo = new HashMap<>();
        pathInfo.put(MyConstVal.MAPPER_PATH, daoPath + srcPath + packageInfo.get(MyConstVal.MAPPER).replaceAll("\\.", StringPool.BACK_SLASH + File.separator));
        pathInfo.put(MyConstVal.ENTITY_PATH, daoPath + srcPath + packageInfo.get(MyConstVal.ENTITY).replaceAll("\\.", StringPool.BACK_SLASH + File.separator));
        pathInfo.put(MyConstVal.SERVICE_PATH, servicePath + srcPath + packageInfo.get(MyConstVal.SERVICE).replaceAll("\\.", StringPool.BACK_SLASH + File.separator));
        pathInfo.put(MyConstVal.SERVICE_IMPL_PATH, servicePath + srcPath + packageInfo.get(MyConstVal.SERVICE_IMPL).replaceAll("\\.", StringPool.BACK_SLASH + File.separator));
        pathInfo.put(MyConstVal.REPOSITORY_PATH, servicePath + srcPath + packageInfo.get(MyConstVal.REPOSITORY).replaceAll("\\.", StringPool.BACK_SLASH + File.separator));
        pathInfo.put(MyConstVal.REPOSITORY_IMPL_PATH, servicePath + srcPath + packageInfo.get(MyConstVal.REPOSITORY_IMPL).replaceAll("\\.", StringPool.BACK_SLASH + File.separator));
        pathInfo.put(MyConstVal.XML_PATH, daoPath + "//src//main//resources//mapper//" + moduleName);
        pc.setPathInfo(pathInfo);


        ConfigBuilder configBuilder = new ConfigBuilder(mpg.getPackageInfo(), mpg.getDataSource(), mpg.getStrategy(), mpg.getTemplate(), mpg.getGlobalConfig());
        Field packageInfoField = configBuilder.getClass().getDeclaredField("packageInfo");
        packageInfoField.setAccessible(true);
        packageInfoField.set(configBuilder,packageInfo);

        mpg.setConfig(configBuilder);
    }

    public interface MyConstVal extends ConstVal {
        String REPOSITORY = "Repository";
        String REPOSITORY_IMPL = "RepositoryImpl";
        String REPOSITORY_PATH = "repository_path";
        String REPOSITORY_IMPL_PATH = "repository_impl_path";

        String TEMPLATE_REPOSITORY = "/templates/repository.java";
        String TEMPLATE_REPOSITORY_IMPL = "/templates/repositoryImpl.java";

        default String toFileFtl(String templateName) {
            return templateName + ".ftl";
        }
    }

}