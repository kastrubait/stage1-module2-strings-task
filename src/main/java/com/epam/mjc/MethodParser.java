package com.epam.mjc;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     *      1. access modifier - optional, followed by space: ' '
     *      2. return type - followed by space: ' '
     *      3. method name
     *      4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     *      accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     *      private void log(String value)
     *      Vector3 distort(int x, int y, int z, float magnitude)
     *      public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
//        throw new UnsupportedOperationException("You should implement this method.");

        String regex = "^(\\w+\\s+)?(\\w+\\s+)(\\w+)\\(([^)]*)\\)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(signatureString);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid method signature format.");
        }

        MethodSignature methodSignature = new MethodSignature("", new ArrayList<>());

        String accessModifier = matcher.group(1);
        if (accessModifier != null) {
            methodSignature.setAccessModifier(accessModifier.trim());
        }

        methodSignature.setReturnType(matcher.group(2).trim());

        methodSignature.setMethodName(matcher.group(3).trim());

        String argumentsString = matcher.group(4).trim();

        if (!argumentsString.isEmpty()) {
            String[] args = argumentsString.split("\\s*,\\s*");
            for (String arg : args) {
                String[] parts = arg.split("\\s+");
                if (parts.length != 2) {
                    throw new IllegalArgumentException("Invalid argument format: " + arg);
                }
                String type = parts[0];
                String name = parts[1];
                methodSignature.addArgument(new MethodSignature.Argument(type, name));
            }
        }

        return methodSignature;
    }
}
