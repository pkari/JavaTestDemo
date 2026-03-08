package demo.ui.sauceDemo.enums;

public enum ProductName {
    BACKPACK("sauce-labs-backpack"),
    FLEECE_JACKET("sauce-labs-fleece-jacket");

    private final String id;

    ProductName(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
