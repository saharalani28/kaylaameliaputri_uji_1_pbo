// Abstract class untuk Product
abstract class Product {
    private String name;
    private double price;
    
    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
    
    public String getName() {
        return name;
    }
    
    public double getPrice() {
        return price;
    }
    
    // Method abstract untuk menghitung harga setelah diskon
    public abstract double calculateDiscount();
    
    // Method final untuk mendapatkan harga setelah diskon
    public final double getFinalPrice() {
        return price - calculateDiscount();
    }
}

// Interface Taxable
interface Taxable {
    double TAX_RATE = 0.1; // PPN 10%
    double calculateTax();
}

// Subclass Food
class Food extends Product {
    private boolean isHealthy;
    
    public Food(String name, double price, boolean isHealthy) {
        super(name, price);
        this.isHealthy = isHealthy;
    }
    
    @Override
    public double calculateDiscount() {
        // Makanan sehat dapat diskon 5%
        return isHealthy ? getPrice() * 0.05 : 0;
    }
}

// Subclass Beverage
class Beverage extends Product implements Taxable {
    private boolean isAlcoholic;
    
    public Beverage(String name, double price, boolean isAlcoholic) {
        super(name, price);
        this.isAlcoholic = isAlcoholic;
    }
    
    @Override
    public double calculateDiscount() {
        // Minuman non-alkohol diskon 2%
        return isAlcoholic ? 0 : getPrice() * 0.02;
    }
    
    @Override
    public double calculateTax() {
        // Minuman beralkohol kena pajak lebih tinggi
        return isAlcoholic ? getFinalPrice() * (TAX_RATE + 0.05) : getFinalPrice() * TAX_RATE;
    }
}

// Class untuk sistem kasir
public class SimpleCashierSystem {
    public static void main(String[] args) {
        // Membuat beberapa produk
        Product[] products = {
            new Food("Salad Buah", 25000, true),
            new Food("Burger", 35000, false),
            new Beverage("Jus Jeruk", 15000, false),
            new Beverage("Anggur Merah", 120000, true)
        };
        
        // Menghitung total belanja
        double total = 0;
        double totalTax = 0;
        
        System.out.println("Struk Belanja:");
        System.out.println("---------------------------------");
        
        for (Product product : products) {
            System.out.printf("%-15s: Rp%,.2f", product.getName(), product.getPrice());
            
            double discount = product.calculateDiscount();
            if (discount > 0) {
                System.out.printf(" (Diskon: Rp%,.2f)", discount);
            }
            
            double finalPrice = product.getFinalPrice();
            System.out.printf("\n  Harga akhir: Rp%,.2f", finalPrice);
            
            // Jika produk kena pajak
            if (product instanceof Taxable) {
                Taxable taxable = (Taxable) product;
                double tax = taxable.calculateTax();
                System.out.printf("\n  Pajak: Rp%,.2f", tax);
                totalTax += tax;
                finalPrice += tax;
            }
            
            System.out.println("\n---------------------------------");
            total += finalPrice;
        }
        
        System.out.printf("\nTotal Belanja: Rp%,.2f\n", total);
        System.out.printf("Total Pajak: Rp%,.2f\n", totalTax);
    }
}