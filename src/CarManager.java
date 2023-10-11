import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Car {
    private int id;
    private String make;
    private String model;
    private int yearOfManufacture;
    private String color;
    private double price;
    private String registrationNumber;

    public Car(int id, String make, String model, int yearOfManufacture, String color, double price, String registrationNumber) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.yearOfManufacture = yearOfManufacture;
        this.color = color;
        this.price = price;
        this.registrationNumber = registrationNumber;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getYearOfManufacture() {
        return yearOfManufacture;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return id + " " + make + " " + model + " " + yearOfManufacture + " " + color + " " + price + " " + registrationNumber;
    }
}

public class CarManager {
    private List<Car> cars = new ArrayList<>();

    public CarManager() {
        cars.add(new Car(1, "Chevrolet", "Spark", 2017, "Blue", 13000, "01 777 UZB"));
        cars.add(new Car(2, "Chevrolet", "Malibu", 2019, "Red", 25000, "01 S 555 SS"));
    }

    public void saveCarsByBrand(String brand, String filename) throws IOException {
        try (PrintWriter writer = new PrintWriter(filename)) {
            for (Car car : cars) {
                if (car.getMake().equalsIgnoreCase(brand)) {
                    writer.println(car);
                }
            }
        }
    }

    public void saveCarsByModelAndUsage(String model, int years, String filename) throws IOException {
        int currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
        try (PrintWriter writer = new PrintWriter(filename)) {
            for (Car car : cars) {
                if (car.getModel().equalsIgnoreCase(model) && (currentYear - car.getYearOfManufacture()) > years) {
                    writer.println(car);
                }
            }
        }
    }

    public void saveCarsByYearAndPrice(int year, double minPrice, String filename) throws IOException {
        try (PrintWriter writer = new PrintWriter(filename)) {
            for (Car car : cars) {
                if (car.getYearOfManufacture() == year && car.getPrice() > minPrice) {
                    writer.println(car);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        CarManager manager = new CarManager();
        manager.saveCarsByBrand("Chevrolet", "chevrolet_cars.txt");
        manager.saveCarsByModelAndUsage("Spark", 3, "spark_over_3_years.txt");
        manager.saveCarsByYearAndPrice(2017, 12000, "cars_2017_over_12000.txt");
    }
}
