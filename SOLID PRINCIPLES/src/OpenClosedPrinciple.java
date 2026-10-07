//Entities should be open for extension, but closed for modification
interface TaxCalculator{
    double amountAfterTax(double amount);
}

class IndiaTax implements TaxCalculator{
    public double amountAfterTax(double amount) {
        return (amount + 0.18 * amount);
    }
}

class USTax implements TaxCalculator{
    public double amountAfterTax(double amount) {
        return (amount + 0.18 * amount);
    }
}

class InvoiceService{
    public void calculate(){
        TaxCalculator taxCalculator = new USTax();
        System.out.println(taxCalculator.amountAfterTax(100));
    }
}

public class OpenClosedPrinciple {
    public static void main(String[] args) {
        InvoiceService invoiceService = new InvoiceService();
        invoiceService.calculate();
    }
}
