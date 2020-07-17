/*
 * Duy Nguyen
 * TestInventory.java
 * Takes inventory of items' name, sku, price, and etc.
 */

class InventoryItem {

    String itemName;
    int sku;
    double price = 0.0;
    int quantity = 0;

    static int nItems = 0;

    public InventoryItem() {
        itemName = "TBD";
        sku = 0;
        price = 0.0;
        quantity = 0;
        nItems += 1;
    }
    public InventoryItem(String itemName, int sku, double price) {
        this();
        this.itemName = itemName;
        this.sku = Math.abs(sku);
        this.price = Math.abs(price);
    }
    public InventoryItem(String itemName, int sku, double price, int quantity) {
        this(itemName, sku, price);
        this.quantity = Math.abs(quantity);
    }

    public String getItemName() {
        return(itemName);
    }
    public int getSku() {
        return(sku);
    }
    public double getPrice() {
        return(price);
    }
    public int getQuantity() {
        return(quantity);
    }
    public static int getNItems() {
        return(nItems);
    }
    public double getTotalValue() {
        return(price * quantity);
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    public void setSku(int sku) {
        this.sku = Math.abs(sku);
    }
    public void setPrice(double price) {
        this.price = Math.abs(price);
    }
    public void setQuantity(int quantity) {
        this.quantity = Math.abs(quantity);
    }

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

    public static int compare(InventoryItem item1, InventoryItem item2) {

        double value1 = item1.getTotalValue();
        double value2 = item2.getTotalValue();

        if (value1 < value2) {
            return(-1);
        }
        else if (value1 == value2) {
            return(0);
        }
        else {
            return(1);
        }
    }

}

public class TestInventory {

    public static void main(String[] args) {

        InventoryItem emptyItem = new InventoryItem();
        InventoryItem staplers = new InventoryItem("Stapler, Red",
                                                91745, 7.89);
        InventoryItem pencils = new InventoryItem("Pencil, #2",
                                                73105, 0.35, 210);
        InventoryItem notebooks = new InventoryItem("Notebook, Spiral",
                                                68332, 0.35, 38);

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


        String name1 = pencils.itemName;
        String name2 = notebooks.itemName;

        switch(InventoryItem.compare(pencils, notebooks)) {
            case -1:
                System.out.printf("%s has less value than %s", name1, name2);
                break;
            case 0:
                System.out.printf("%s has the same value as %s", name1, name2);
                break;
            case 1:
                System.out.printf("%s has the greater value than %s", name1, name2);
                break;
        }

    }
}
