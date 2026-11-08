import java.util.*;
interface Item{
    void accept(ItemVisitor visitor);
}
class PhysicalProduct implements Item{
    String name;
    double weight;

    public PhysicalProduct(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }

    public void accept(ItemVisitor visitor) {
        visitor.visit(this);
    }
}
class DigitalProduct implements Item{
    String name;
    int downloadSizeInMB;

    public DigitalProduct(String name, int downloadSizeInMB) {
        this.name = name;
        this.downloadSizeInMB = downloadSizeInMB;
    }
    public void accept(ItemVisitor visitor) {
        visitor.visit(this);
    }
}
class GiftCard implements Item{
    String code;
    double amount;

    public GiftCard(String code, double amount) {
        this.code = code;
        this.amount = amount;
    }
    public void accept(ItemVisitor visitor) {
        visitor.visit(this);
    }
}
interface ItemVisitor{
    void visit(PhysicalProduct item);
    void visit(DigitalProduct item);
    void visit(GiftCard item);
}
class InvoiceVisitor implements ItemVisitor{
    public void visit(PhysicalProduct item){
        System.out.println("Invoice: "+ item.name + " Shipping to customer");
    }
    public void visit(DigitalProduct item){
        System.out.println("Invoice: "+ item.name + " Email with download link");
    }
    public void visit(GiftCard item){
        System.out.println("Invoice: Gift Card"+ " " + item.code );
    }
}
class ShippingCustomer implements ItemVisitor{
    public void visit(PhysicalProduct item){
        System.out.println("Shipping cost for " + item.name + ":rs" + (item.weight * 10));
    }
    public void visit(DigitalProduct item){
        System.out.println("Shipping cost for " + item.name + "Digital shipping cost");
    }
    public void visit(GiftCard item){
        System.out.println("Shipping cost for " + item.code + ":rs" + "Gift card cost");
    }
}
public class VisitorPattern {
    static void main(String[] args) {
        List<Item> items = new ArrayList<>();
        items.add(new PhysicalProduct("shoes", 100));
        items.add(new DigitalProduct("shoes", 100));
        items.add(new GiftCard("shoes", 100));
        ItemVisitor invoiceGenerator = new InvoiceVisitor();
        ItemVisitor shippingCalculator = new ShippingCustomer();
        for(Item item: items){
            item.accept(invoiceGenerator);
            item.accept(shippingCalculator);
        }
    }
}
