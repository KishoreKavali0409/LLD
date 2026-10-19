/*
Composite pattern is a structured design pattern that let's us compose objects into tree-like hierarchial structural and treatt.

Individual objects(leaf)
Group of objects(composite)

Composite = Treat individual objects and collections / groups of objects in the same way.
 */

import java.util.ArrayList;
import java.util.List;

interface CartItem{
    double getPrice();
    void display(String indent);
}

//Leaf
class Product implements CartItem{
    private String name;
    private double price;
    public Product(String name, double price){
        this.name = name;
        this.price = price;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void display(String indent) {
        System.out.println(indent + "Product: " + name + "- Rs " + price);
    }
}

// Composite
class ProductBundle implements CartItem {
    private String bundleName;
    private List<CartItem> items = new ArrayList<>();

    public ProductBundle(String bundleName) {
        this.bundleName = bundleName;
    }

    public void addItems(CartItem item) {
        items.add(item);
    }

    @Override
    public double getPrice() {
        double total = 0;
        for (CartItem item : items) {
            total += item.getPrice();
        }
        return total;
    }

        @Override
        public void display (String indent){
            System.out.println(indent + "Bundle: " + bundleName);
            for(CartItem item: items){
                item.display(indent + " ");
            }
        }
    }

    // Client
    public class CompositePattern {

    static void main(String[] args) {
        CartItem book = new Product("Atomic habits", 499);
        CartItem phone = new Product("iPhone 15", 790000);
        CartItem earbuds = new Product("AirPods", 15999);
        CartItem charger = new Product("20W Charger", 1999);

        ProductBundle iphoneCombo = new ProductBundle("iphone essential combo");
        iphoneCombo.addItems(phone);
        iphoneCombo.addItems(earbuds);
        iphoneCombo.addItems(charger);

        ProductBundle amazonCart = new ProductBundle("Amazon Cart");
        amazonCart.addItems(book);
        amazonCart.addItems(iphoneCombo);
        System.out.println("Your Amazon Cart: ");
        amazonCart.display(" ");
        System.out.println("Total Price: Rs " +  amazonCart.getPrice());

    }
}
