package com.knou.calculator;
import java.util.regex.*;
import java.util.List;
import java.util.ArrayList;

public class Tokenizer {
    private static final Pattern pattern = Pattern.compile("\\d+|[()]|[^\\d\\s()]");

    public static List<String> tokenize(String input) {
        String expr = input.replaceAll("\\s+", "");

        List<String> tokens = new ArrayList<>();
        Matcher matcher = pattern.matcher(expr);
        String prev = null;

        while (matcher.find()) {
            String curr = matcher.group();

            if ("-".equals(curr)){
                if (prev==null|| Registry.contains(prev)||"(".equals(prev)){
                    if (matcher.find()){
                        String next = matcher.group();
                        if (next.matches("\\d+")){
                            throw new RuntimeException("음수는 허용되지 않습니다: -" + next);
                        } else {
                            tokens.add(curr);
                            tokens.add(next);
                            prev= next;
                            continue;
                        }
                    } else {
                        throw new RuntimeException("수식이 잘못되었습니다.");
                    }
                }
            }
            tokens.add(curr);
            prev=curr;
        }

        return tokens;
    }
}
