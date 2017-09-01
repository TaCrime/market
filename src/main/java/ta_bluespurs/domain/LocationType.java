package ta_bluespurs.domain;

public enum LocationType {
    WALMART("Walmart"), BESTBUY("BestBuy");

    private String name;

    LocationType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
