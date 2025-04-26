package com.knou.calculator.token;
import com.knou.calculator.operator.OperatorRegistry;

import java.util.regex.*;
import java.util.List;
import java.util.ArrayList;

public class Tokenizer {
    private static final Pattern pattern = Pattern.compile("\\d+|[()]|[^\\d\\s()]");

    public static List<Token> tokenize(String input) {
        String expr = input.replaceAll("\\s+", "");

        List<Token> tokens = new ArrayList<>();
        Matcher matcher = pattern.matcher(expr);
        String prev = null;

        while (matcher.find()) {
            String curr = matcher.group();
            if ("-".equals(curr)){
                if (prev==null|| OperatorRegistry.contains(prev)||prev.equals("(")){
                    if (matcher.find()){
                        String next = matcher.group();
                        if (next.matches("\\d+")){
                            throw new RuntimeException("음수는 허용되지 않습니다: -" + next);
                        } else {
                            tokens.add(TokenFactory.createToken(curr));
                            tokens.add(TokenFactory.createToken(next));
                            prev= next;
                            continue;
                        }
                    } else {
                        throw new RuntimeException("수식이 잘못되었습니다.");
                    }
                }
            }
            tokens.add(TokenFactory.createToken(curr));
            prev=curr;
        }

        return tokens;
    }
}
