public enum State {
    MULTIPLY("Multiply", "times"), DIV("Division", "divided by"), PERCENT("Percent", "percent of");

    private String name, inQuestion;

    State(String name, String inQuestion) {
        this.name = name;
        this.inQuestion = inQuestion;
    }

    public String getInQuestion() {
        return inQuestion;
    }

    @Override
    public String toString() {
        return name;
    }
}
