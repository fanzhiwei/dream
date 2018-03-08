package net.fanzhiwei.dao.config;

/**
 * @author fanzhiwei
 */

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MBGMain {

    public static List<String> warnings = new ArrayList<String>();

    public static void main(String[] args) throws Exception {
        File configFile = new File(MBGMain.class.getResource("/dao/generatorConfig.xml").toURI());
        Configuration config = new ConfigurationParser(warnings).parseConfiguration(configFile);
        MyBatisGenerator generator = new MyBatisGenerator(config, new DefaultShellCallback(true), warnings);

        generator.generate(null);

        System.out.println(warnings);
    }

}
