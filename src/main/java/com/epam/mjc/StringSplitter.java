package com.epam.mjc;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        if (source == null || delimiters == null) {
            return new ArrayList<>();
        }

        StringBuilder regexBuilder = new StringBuilder();
        regexBuilder.append("(");
        for (String delimiter : delimiters) {
            if (regexBuilder.length() > 2) {
                regexBuilder.append("|");
            }
            regexBuilder.append(Pattern.quote(delimiter));
        }
        regexBuilder.append(")");
        String regex = regexBuilder.toString();

        String[] parts = source.split(regex);

        List<String> result = new ArrayList<>();
        for (String part : parts) {
            if (!part.isEmpty()) {
                result.add(part);
            }
        }
        return result;
//        throw new UnsupportedOperationException("You should implement this method.");
    }
}
