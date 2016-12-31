package com.rip.tester.configuration;

import com.rip.tester.common.Constants;
import org.apache.log4j.PropertyConfigurator;

import java.io.IOException;
import java.util.Properties;

import static org.jadira.usertype.spi.reflectionutils.ClassLoaderUtils.getClassLoader;

/**
 * Created by Kasun Eranga on 9/30/2016.
 */
public class Log4jConfig {

    static ClassLoader classLoader = getClassLoader();

    Log4jConfig() throws IOException {
        Properties property = new Properties();
        property.load(classLoader.getResourceAsStream(Constants.LOG4J_PROPERTIES_FILE_PATH));
        PropertyConfigurator.configure(property);

    }
}