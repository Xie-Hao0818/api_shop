package com.hao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AutoCodeGenerator {
    private static String author = "hao";
    private static String first_level_package_name = "com";
    private static String second_level_package_name = "hao";
    private static String table_to_entity = "all";

    private static String url = "jdbc:mysql://localhost:3306/test?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true&rewriteBatchedStatements=true";
    private static String username = "root";
    private static String password = "08240030xzh.";

    public static void main(String[] args) throws IOException {
        // 数据库配置
        DataSourceConfig.Builder dataSourceConfigBuilder = new DataSourceConfig
                .Builder(url, username, password);
        FastAutoGenerator.create(dataSourceConfigBuilder)
                // 全局配置
                .globalConfig((scanner, builder) -> {
                    builder.author(author)
                            // 覆盖已生成文件
                            .fileOverride()
                            // 指定输出目录
                            .outputDir(System.getProperty("user.dir") + "/src/main/java/")
                            // 禁止打开输出目录
                            .disableOpenDir()
                            // 时间策略
                            .dateType(DateType.TIME_PACK)
                            // 类注释日期的格式
                            .commentDate("yyyy-MM-dd")
                            .build();
                })
                // 包配置
                .packageConfig((scanner, builder) -> {
                    // 父包名
                    builder.parent(first_level_package_name)
                            // 模块名
                            .moduleName(second_level_package_name)
                            // Entity 包名
                            .entity("entity")
                            // Service 包名
                            .service("service")
                            // Controller 包名
                            .controller("controller")
                            // Mapper 包名
                            .mapper("mapper")
                            // MapperXML 包名
                            .xml("mapper")
                            // 路径配置信息
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, System.getProperty("user.dir") + "/src/main/resources/mapper"));
                })
                //策略配置
                .strategyConfig((scanner, builder) -> {
                    // 增加表匹配(内存过滤)，	include 与 exclude 只能配置一项
                    builder.addInclude(getTables(table_to_entity))
                            // 	增加表排除匹配(内存过滤)，	include 与 exclude 只能配置一项
                            // .addExclude(scanner.apply("请输入要忽略的表名，多个英文逗号分隔？"))
                            // 	增加过滤表后缀
                            .addTableSuffix("")
                            // 	增加过滤表前缀
                            .addTablePrefix("")
                            // service 策略配置
                            .serviceBuilder()
                            // 	格式化文件名称
                            .formatServiceFileName("%sService")
                            .formatServiceImplFileName("%sServiceImpl")
                            // 实体策略配置
                            .entityBuilder()
                            //AR模式
                            .enableActiveRecord()
                            // 开启生成实体时生成字段注解
                            .enableTableFieldAnnotation()
                            // controller 策略配置
                            .controllerBuilder()
                            .formatFileName("%sController")
                            // 开启生成@RestController 控制器
//                            .enableRestStyle()
                            // 	mapper 策略配置
                            .mapperBuilder()
                            // 设置父类
                            .superClass(BaseMapper.class)
                            .formatMapperFileName("%sMapper")
                            .enableMapperAnnotation()
                            .formatXmlFileName("%sMapper");
                })
                .execute();
    }

    /**
     * 处理 all 情况
     * 要生成的表名，以英文逗号分隔多个表格名(所有表填all)
     *
     * @param tables
     * @return
     */
    protected static List<String> getTables(String tables) {
        return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
    }
}
