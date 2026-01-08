// Interface (Abstraction)
interface Vehicle {
    void start();
    void stop();
}

// Abstract class
abstract class Car implements Vehicle {

    // Encapsulation
    private String brand;
    protected int speed;

    // Constructor
    Car(String brand, int speed) {
        this.brand = brand;
        this.speed = speed;
    }

    // Getter (Encapsulation)
    public String getBrand() {
        return brand;
    }

    // Concrete method
    void showDetails() {
        System.out.println("Brand: " + brand);
        System.out.println("Speed: " + speed + " km/h");
    }

    // Abstract method
    abstract void fuelType();
}

// Child class 1 (Inheritance)
class ElectricCar extends Car {

    ElectricCar(String brand, int speed) {
        super(brand, speed);
    }

    // Method Overriding
    @Override
    public void start() {
        System.out.println("Electric car starts silently");
    }

    @Override
    public void stop() {
        System.out.println("Electric car stops using brakes");
    }

    @Override
    void fuelType() {
        System.out.println("Fuel Type: Electricity");
    }
}

// Child class 2 (Inheritance)
class PetrolCar extends Car {

    PetrolCar(String brand, int speed) {
        super(brand, speed);
    }

    @Override
    public void start() {
        System.out.println("Petrol car starts with engine sound");
    }

    @Override
    public void stop() {
        System.out.println("Petrol car stops using clutch & brakes");
    }

    @Override
    void fuelType() {
        System.out.println("Fuel Type: Petrol");
    }
}

// Main class
public class OOPDemo {

    public static void main(String[] args) {

        // Runtime Polymorphism
        Car car1 = new ElectricCar("Tesla", 150);
        Car car2 = new PetrolCar("Toyota", 180);

        System.out.println("---- Electric Car ----");
        car1.start();
        car1.fuelType();
        car1.showDetails();
        car1.stop();

        System.out.println("\n---- Petrol Car ----");
        car2.start();
        car2.fuelType();
        car2.showDetails();
        car2.stop();
    }
}
