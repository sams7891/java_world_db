package operations;

public enum Continent {

    Asia("Asia"),
    Europe("Europe"),
    North_america("North America"),
    South_america("South America"),
    Africa("Africa"),
    Oceania("Oceania");

    private final String displayName;

    Continent(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
