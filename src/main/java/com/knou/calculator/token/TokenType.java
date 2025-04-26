package com.knou.calculator.token;

public enum TokenType {
    OPERAND {
        @Override
        public Token create(String rawToken) {
            return new OperandToken(Double.parseDouble(rawToken));
        }

        @Override
        public boolean isOperand() {
            return true;
        }
    },
    OPERATOR {
        @Override
        public Token create(String rawToken) {
            return new OperatorToken(rawToken);
        }

        @Override
        public boolean isOperator() {
            return true;
        }
    },
    PARENTHESIS {
        @Override
        public Token create(String rawToken) {
            return new ParenthesisToken(rawToken);
        }

        @Override
        public boolean isParenthesis() {
            return true;
        }
    };

    public boolean isOperator() { return false; }
    public boolean isOperand() { return false; }
    public boolean isParenthesis() { return false; }

    public abstract Token create(String rawToken);
}
