// Abstract class Vehicle
abstract class Vehicle {
    abstract void startEngine();
}

// Interface Electric
interface Electric {
    void chargeBattery();
}

// Class Car inherits Vehicle and implements Electric
class Car extends Vehicle implements Electric {
    @Override
    void startEngine() {
        System.out.println("Mesin mobil dinyalakan dengan tombol atau kunci");
    }
    
    @Override
    public void chargeBattery() {
        System.out.println("Baterai mobil sedang diisi...");
    }
}

// Class Motorcycle inherits Vehicle
class Motorcycle extends Vehicle {
    @Override
    void startEngine() {
        System.out.println("Mesin motor dinyalakan dengan kick-starter atau tombol");
    }
}

// Main class for demonstration
public class MainApp {
    public static void main(String[] args) {
        // Polymorphism demonstration
        Vehicle myCar = new Car();
        Vehicle myMotorcycle = new Motorcycle();
        
        // Calling startEngine() polymorphically
        System.out.println("Menyalakan kendaraan:");
        myCar.startEngine();
        myMotorcycle.startEngine();
        
        // Interface method demonstration
        if (myCar instanceof Electric) {
            Electric electricCar = (Electric) myCar;
            System.out.println("\nMengisi daya kendaraan listrik:");
            electricCar.chargeBattery();
        }
        
        // Direct instance demonstration
        System.out.println("\nDemonstrasi instansiasi langsung:");
        Car car = new Car();
        Motorcycle bike = new Motorcycle();
        
        car.startEngine();
        bike.startEngine();
        car.chargeBattery();
    }
}