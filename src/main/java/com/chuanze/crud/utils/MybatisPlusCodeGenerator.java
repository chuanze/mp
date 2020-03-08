package com.chuanze.crud.utils;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * @Description: MybatisPlus 代码生成器
 * @Author chuanze
 * @Date 2020/3/4 11:10
 * @Version V1.0
 **/
public class MybatisPlusCodeGenerator {
    //项目所在地址，如果项目不是这个地址，需要修改，生成的文件将会放到这个目录下
    private static final String PROJECT_DIR = "E://project//BackClient//crud//src//main//java";
    //包名
    private static final String PACKAGE_NAME = "com.chuanze";
    //项目名
    private static final String PROJECT_NAME = "crud";


    /**
     * 原则上本地库建好表进行代码生成
     * 线上测试环境和正式环境不可能运行本代码
     */
    private static final DbType DATA_TYPE = DbType.MYSQL;
    //数据库驱动名称
    private static final String DATABASE_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
    //数据库用户名
    private static final String DATABASE_USERNAME = "mp";
    //数据库密码
    private static final String DATABASE_PASSWORD = "123456";
    //数据库地址
    private static final String DATABASE_URL = "jdbc:mysql://192.168.1.9:3306/mp?useSSL=false&serverTimezone=UTC";

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        System.out.println(projectPath);
        gc.setOutputDir(PROJECT_DIR); // 生成文件输出目录
        gc.setFileOverride(true); // 是否覆盖已有文件
        gc.setOpen(false);// 是否打开输出目录
        gc.setEnableCache(false);// 是否在 xml 中添加二级缓存配置
        gc.setAuthor("chuanze"); // 开发人员
        gc.setKotlin(false); // 开启 Kotlin 模式
        gc.setSwagger2(false); // 开启 swagger2 模式
        gc.setActiveRecord(true); // 是否开启 ActiveRecord 模式
        gc.setBaseResultMap(true);// 开启 BaseResultMap
        gc.setBaseColumnList(true);// 开启 baseColumList
        gc.setDateType(DateType.TIME_PACK); // 时间类型对应策略 ONLY_DATE=默认 SQL_PACK=java.sql TIME_PACK=java8
        gc.setEntityName("%sEntity");// 实体命名方式
        gc.setMapperName("%sMapper");// mapper 命名方式
        gc.setXmlName("%sMapper");// Mapper.xml 命名方式
        gc.setServiceName("I%sService");// service 命名方式
        gc.setServiceImplName("%sServiceImpl");// service impl 命名方式
        gc.setControllerName("%sController");// controller 命名方式
        gc.setIdType(IdType.AUTO);// 指定生产的主键的 ID 类型
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        //dsc.setDbQuery(null); // 数据库信息查询类
        dsc.setDbType(DATA_TYPE); // 数据库类型
        dsc.setUrl(DATABASE_URL);// url
        dsc.setDriverName(DATABASE_CLASS_NAME);
        dsc.setUsername(DATABASE_USERNAME);
        dsc.setPassword(DATABASE_PASSWORD);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(PROJECT_NAME);
        pc.setParent(PACKAGE_NAME);
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        // 公共父类
        //strategy.setSuperControllerClass("你自己的父类控制器,没有就不用设置!");
        // 写于父类中的公共字段
        //strategy.setSuperEntityColumns("id");
        strategy.setInclude("user".split(","));
        strategy.setControllerMappingHyphenStyle(true);
        //strategy.setTablePrefix("tb_wind_");// 表名前缀

        mpg.setStrategy(strategy);
        //mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }
}
