package Comsc075;/*
 * Duy Nguyen
 * Comsc075.TestInventory.java
 * Takes inventory of items' name, sku, price, and etc with various methods.
 */

// Creates object based off name, sku, price, and quantity
class InventoryItem {

    private String itemName;
    private int sku;
    private double price;
    private int quantity;
    private static int nItems;

    // Constructs objects' properties
    public InventoryItem(String itemName, int sku, double price, int quantity) {
        this.itemName = itemName;
        this.sku = sku;
        this.price = Math.abs(price);
        this.quantity = Math.abs(quantity);
        nItems += 1;
    }
    public InventoryItem(String itemName, int sku, double price) {
        this(itemName, sku, price, 0);
    }
    public InventoryItem() {
        this("TBD", 0, 0.0, 0);
    }

    // Gets items' properties
    public String getItemName() {
        return itemName;
    }
    public int getSku() {
        return sku;
    }
    public double getPrice() {
        return price;
    }
    public int getQuantity() {
        return quantity;
    }
    public static int getNItems() {
        return nItems;
    }
    public double getTotalValue() {
        return price * quantity;
    }

    // Sets items' properties
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    public void setSku(int sku) {
        this.sku = sku;
    }
    public void setPrice(double price) {
        this.price = Math.abs(price);
    }
    public void setQuantity(int quantity) {
        this.quantity = Math.abs(quantity);
    }

    // Displays Item name, SKU, Price, and quantity
    public void display() {
        System.out.printf("Item Name: %s\n" +
                            "SKU: %d\n" +
                            "Price: $%.2f each\n" +
                            "Quantity: %d\n",
                            itemName, sku, price, quantity);
    }

    public void displayTotalValue() {
        System.out.printf("Total value: $%.2f\n\n", getTotalValue());
    }

    // Compares 2 items' total values and returns -1, 0, and 1
    public static int compare(InventoryItem item1, InventoryItem item2) {

        double value1 = item1.getTotalValue();
        double value2 = item2.getTotalValue();
        int comparison;

        if (value1 < value2) {
            comparison = -1;
        }
        else if (value1 == value2) {
            comparison = 0;
        }
        else {
            comparison = 1;
        }

        return comparison;
    }

    // Displays output based off compare function
    public static void displayCompare(InventoryItem item1,
                                      InventoryItem item2) {

        String name1 = item1.itemName;
        String name2 = item2.itemName;

        switch (InventoryItem.compare(item1, item2)) {
            case -1:
                System.out.printf("%s has less value than %s\n",
                        name1, name2);
                break;
            case 0:
                System.out.printf("%s has the same value as %s\n",
                        name1, name2);
                break;
            case 1:
                System.out.printf("%s has the greater value than %s\n",
                        name1, name2);
                break;
        }
    }

}

public class TestInventory {

    public static void main(String[] args) {

        // Declares/Initializes objects
        InventoryItem emptyItem = new InventoryItem();
        InventoryItem staplers = new InventoryItem("Stapler, Red",
                                                91745, 7.89);
        InventoryItem pencils = new InventoryItem("Pencil, #2",
                                                73105, 0.35, 210);
        InventoryItem notebooks = new InventoryItem("Notebook, Spiral",
                                                68332, 2.57, 38);

        // Displays items' properties NItems and comparison

        System.out.printf("Number of inventory items: %d\n\n",
                            InventoryItem.getNItems());

        emptyItem.display();
        emptyItem.displayTotalValue();

        staplers.display();
        staplers.displayTotalValue();

        pencils.display();
        pencils.displayTotalValue();

        notebooks.display();
        notebooks.displayTotalValue();

        InventoryItem.displayCompare(pencils, notebooks);

    }
}
