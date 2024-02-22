public enum Levels {
    Easy("Easy mode"),
    Medium("Normal mode"),
    Hard("Hard mode");

    private final String name;

    Levels(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
