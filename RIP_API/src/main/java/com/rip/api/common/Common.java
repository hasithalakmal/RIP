package com.rip.api.common;

import com.rip.api.dto.ConfigDetails;
import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.jadira.usertype.spi.reflectionutils.ClassLoaderUtils.getClassLoader;

/**
 * Created by Kasun Eranga on 9/30/2016.
 */
public class Common {

    static ClassLoader classLoader = getClassLoader();
    private static final Logger LOGGER = Logger.getLogger(Common.class);

    public static String getTemplate(String programmingLanguage, String restFramework) {
        String template = "";
        if (programmingLanguage.equals("Java")) {
            if (restFramework.equals("RESTEasy")) {
                template = "jaxrs-resteasy";
            } else if (restFramework.equals("Jersey")) {
                template = "jaxrs";
            }
        } else if (programmingLanguage.equals("php")) {
            if (restFramework.equals("Slim")) {
                template = "slim";
            }
        }
        return template;
    }

    public static JSONObject getConfigurationJSON(String packageName, String modelPackageName, String projectName) {
        JSONObject configJSON = new JSONObject();
        configJSON.put("apiPackage", packageName);
        configJSON.put("modelPackage", modelPackageName);
        configJSON.put("sourceFolder", "src\\main\\java");
        configJSON.put("artifactId",projectName);
        configJSON.put("groupId","com.rip");
        return configJSON;
    }

    public static void generateFileFromTemplate(String templateFilePath, String filename, Object templateData) throws IOException {
        final File templateFile = new File(classLoader.getResource(templateFilePath).getFile());
        String templateString = FileUtils.readFileToString(templateFile, "UTF-8");
        Template template = Mustache.compiler().defaultValue("").escapeHTML(false).compile(templateString);
        AbstractFileWriter.writeToFile(filename, template.execute(templateData));
    }

    public static void generateFileFromTemplateWithSubTemplate(String templateFilePath, String filename, Object templateData, String subTemplateDirectoryPath) throws IOException {
        final File templateFile = new File(classLoader.getResource(templateFilePath).getFile());
        final File subTemplateDir = new File(classLoader.getResource(subTemplateDirectoryPath).getFile());
        Mustache.Compiler compiler = Mustache.compiler().withLoader(new Mustache.TemplateLoader() {
            public Reader getTemplate(String name) throws FileNotFoundException {
                return new FileReader(new File(subTemplateDir, name));
            }
        });
        String templateString = FileUtils.readFileToString(templateFile, "UTF-8");
        Template template = compiler.escapeHTML(false).compile(templateString);
        AbstractFileWriter.writeToFile(filename, template.execute(templateData));
    }

    public static List<String> prepareCleanList(ConfigDetails configDetails) {
        List<String> deleteList = new ArrayList<>();
        deleteList.add(configDetails.getOutputPathDocs() + "\\config.json");
        deleteList.add(configDetails.getOutputPath() + "\\.swagger-codegen-ignore");
        deleteList.add(configDetails.getOutputPathDocs() + "\\API-Doc\\.swagger-codegen-ignore");
        deleteList.add(configDetails.getOutputPathDocs() + "\\API-Doc\\LICENSE");
        deleteList.add(configDetails.getOutputPath() + "\\README.md");

        if (configDetails.getTechnicalSpecification().getString("orm_framework").equals("Hibernate")) {
            deleteList.add(configDetails.getOutputPath() + "\\build.xml");
            deleteList.add(configDetails.getOutputPath() + "\\hbm");
        }
        if (configDetails.getTechnicalSpecification().getString("build_tool").equals("Maven")) {
            deleteList.add(configDetails.getOutputPath() + "\\build.gradle");
            deleteList.add(configDetails.getOutputPath() + "\\settings.gradle");
        } else if (configDetails.getTechnicalSpecification().getString("build_tool").equals("Gradle")) {
            deleteList.add(configDetails.getOutputPath() + "\\pom.xml");
        }
        return deleteList;
    }

    public static void cleanProject(ConfigDetails configDetails) throws IOException {
        List<String> paths = prepareCleanList(configDetails);
        for (String path : paths) {
            LOGGER.info("deleting file " + path);
            if (new File(path).isDirectory()) {
                FileUtils.deleteDirectory(new File(path));
            } else {
                Files.deleteIfExists(Paths.get(path));
            }
        }
    }


    public static String capitalizeFirstLetter(String word) {
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }

}
