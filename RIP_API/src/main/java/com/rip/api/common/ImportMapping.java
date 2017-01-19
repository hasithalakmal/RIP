package com.rip.api.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kasun Eranga on 10/22/2016.
 */
public class ImportMapping {
    private static Map<String, String> typeMapping = new HashMap<>();
    private static Map<String, String> importMapping = new HashMap<>();
    private static Map<String, String> typeMappingDBWithORM = new HashMap<>();


    static {
        typeMapping.put("array", "List");
        typeMapping.put("map", "Map");
        typeMapping.put("List", "List");
        typeMapping.put("boolean", "Boolean");
        typeMapping.put("string", "String");
        typeMapping.put("int", "Integer");
        typeMapping.put("float", "Float");
        typeMapping.put("number", "BigDecimal");
        typeMapping.put("DateTime", "Date");
        typeMapping.put("long", "Long");
        typeMapping.put("short", "Short");
        typeMapping.put("char", "String");
        typeMapping.put("double", "Double");
        typeMapping.put("object", "Object");
        typeMapping.put("integer", "Integer");
        typeMapping.put("ByteArray", "byte[]");
        typeMapping.put("binary", "byte[]");

        importMapping.put("BigDecimal", "java.math.BigDecimal");
        importMapping.put("UUID", "java.util.UUID");
        importMapping.put("File", "java.io.File");
        importMapping.put("Date", "java.util.Date");
        importMapping.put("Timestamp", "java.sql.Timestamp");
        importMapping.put("Map", "java.util.Map");
        importMapping.put("HashMap", "java.util.HashMap");
        importMapping.put("Array", "java.util.List");
        importMapping.put("ArrayList", "java.util.ArrayList");
        importMapping.put("List", "java.util.*");
        importMapping.put("Set", "java.util.*");
        importMapping.put("DateTime", "org.joda.time.*");
        importMapping.put("LocalDateTime", "org.joda.time.*");
        importMapping.put("LocalDate", "org.joda.time.*");
        importMapping.put("LocalTime", "org.joda.time.*");
        typeMappingDBWithORM.put("short_string", "string");
        typeMappingDBWithORM.put("medium_string", "string");
        typeMappingDBWithORM.put("long_string", "string");
        typeMappingDBWithORM.put("small_integer", "integer");
        typeMappingDBWithORM.put("medium_integer", "integer");
        typeMappingDBWithORM.put("big_integer", "integer");
        typeMappingDBWithORM.put("float", "float");
        typeMappingDBWithORM.put("date_with_tz", "date");
        typeMappingDBWithORM.put("date_without_tz", "date");
        typeMappingDBWithORM.put("date", "date");
        typeMappingDBWithORM.put("time", "time");
        typeMappingDBWithORM.put("timestamp", "timestamp");
        typeMappingDBWithORM.put("small_blob", "binary");
        typeMappingDBWithORM.put("medium_blob", "binary");
        typeMappingDBWithORM.put("large_blob", "binary");
        typeMappingDBWithORM.put("xml", "string");
        typeMappingDBWithORM.put("boolean", "boolean");

    }

    public static String getTypeMapping(String type) {
        return typeMapping.get(type);
    }

    public static String getImportMapping(String type) {
        return importMapping.get(type);
    }

    public static String getTypeMappingDBWithORM(String type) {
        return typeMappingDBWithORM.get(type);
    }
}
