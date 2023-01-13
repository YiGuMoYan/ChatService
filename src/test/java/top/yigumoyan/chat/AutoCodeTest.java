package top.yigumoyan.chat;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.IFill;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.fill.Column;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AutoCodeTest {

    public static void main(String[] args) {
        String projectObject = System.getProperty("user.dir");
        // 构建一个 代码生成器 对象
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/chat?useSSL=false&useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8", "root", "123456")
                .globalConfig(builder -> {
                    builder.author("忆古陌烟") // 设置作者
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(projectObject + "/src/main/java") // 指定输出目录
                            .disableOpenDir();
                })
                .packageConfig(builder -> {
                    builder.parent("top.yigumoyan") // 设置父包名
                            .moduleName("chat") // 设置父包模块名
                            .entity("entity")
                            .mapper("mapper")
                            .service("service")
                            .serviceImpl("service.Impl")
                            .controller("controller")
                            .pathInfo(Collections.singletonMap(OutputFile.xml, projectObject + "/src/main/resources/mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    List<IFill> columnList = new ArrayList<>();
                    columnList.add(new Column("gmt_create", FieldFill.INSERT));
                    columnList.add(new Column("gmt_modified", FieldFill.INSERT_UPDATE));
                    builder.addInclude("GROUP");
                    builder.entityBuilder()
                            .enableLombok()     // 启用 Lombok
                            .versionColumnName("version")   // 乐观锁
                            .logicDeletePropertyName("deleted")
                            .addTableFills(columnList)
                            .idType(IdType.ASSIGN_ID);
                    builder.mapperBuilder()
                            .enableMapperAnnotation();
                })
                .execute();
    }
}
