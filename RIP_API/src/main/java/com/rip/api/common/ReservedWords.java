package com.rip.api.common;

import java.util.*;

/**
 * Created by Kasun Eranga on 10/20/2016.
 */
public class ReservedWords {

    private static Set<String> variableNames = new HashSet<String>(
            Arrays.asList(
                    "abstract",
                    "continue",
                    "for",
                    "new",
                    "switch",
                    "assert",
                    "default",
                    "if",
                    "package",
                    "synchronized",
                    "boolean",
                    "do",
                    "goto",
                    "private",
                    "this",
                    "break",
                    "double",
                    "implements",
                    "protected",
                    "throw",
                    "byte",
                    "else",
                    "import",
                    "public",
                    "throws",
                    "case",
                    "enum",
                    "instanceof",
                    "return",
                    "transient",
                    "catch",
                    "extends",
                    "int",
                    "short",
                    "try",
                    "char",
                    "final",
                    "interface",
                    "static",
                    "void",
                    "class",
                    "finally",
                    "long",
                    "strictfp",
                    "volatile",
                    "const",
                    "float",
                    "native",
                    "super",
                    "while")
    );

    private static Set<String> selectOperationNames = new HashSet<String>(
            Arrays.asList(
                    "currentPage",
                    "numberOfRows",
                    "sortOrder",
                    "sortColumn",
                    "limit"
                    )
    );

    public static Set<String> getVariableNames() {
        return variableNames;
    }

    public static Set<String> getSelectOperationNames() {
        return selectOperationNames;
    }
}
