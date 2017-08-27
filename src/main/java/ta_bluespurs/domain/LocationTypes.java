package ta_bluespurs.domain;

public enum LocationTypes {
    WALMART("Walmart"), BESTBUY("BestBuy");

    private String name;

    LocationTypes(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
