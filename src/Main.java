import java.util.ArrayList; // import ArrayList class
import java.util.List;      // import List interface
import java.util.Scanner;   // import Scanner class for user input

// Class representing a Car
class Car {
    private String carId;         // unique ID for car
    private String brand;         // car brand
    private String model;         // car model
    private double basePricePerDay; // price per day
    private boolean isAvailable;  // availability status

    // Constructor to create a new car
    public Car(String carId, String brand, String model, double basePricePerDay) {
        this.carId = carId;
        this.brand = brand;
        this.model = model;
        this.basePricePerDay = basePricePerDay;
        this.isAvailable = true; // car is available by default
    }

    public String getCarId() { return carId; } // get car ID
    public String getBrand() { return brand; } // get car brand
    public String getModel() { return model; } // get car model

    // calculate total price based on rental days
    public double calculatePrice(int rentalDays) {
        return basePricePerDay * rentalDays;
    }

    public boolean isAvailable() { return isAvailable; } // check availability

    public void rent() { isAvailable = false; } // mark car as rented
    public void returnCar() { isAvailable = true; } // mark car as available
}

// Class representing a Customer
class Customer {
    private String customerId; // unique customer ID
    private String name;       // customer name

    public Customer(String customerId, String name) {
        this.customerId = customerId;
        this.name = name;
    }

    public String getCustomerId() { return customerId; } // get customer ID
    public String getName() { return name; }            // get customer name
}

// Class representing a Rental (booking)
class Rental {
    private Car car;          // car being rented
    private Customer customer; // who rented it
    private int days;         // number of rental days

    public Rental(Car car, Customer customer, int days) {
        this.car = car;
        this.customer = customer;
        this.days = days;
    }

    public Car getCar() { return car; }         // get rented car
    public Customer getCustomer() { return customer; } // get customer
    public int getDays() { return days; }       // get rental days
}

// Main Car Rental System class
class CarRentalSystem {
    private List<Car> cars;         // list of cars
    private List<Customer> customers; // list of customers
    private List<Rental> rentals;   // list of active rentals

    public CarRentalSystem() {
        cars = new ArrayList<>();       // create empty list of cars
        customers = new ArrayList<>();  // create empty list of customers
        rentals = new ArrayList<>();    // create empty list of rentals
    }

    // Add a car to the system
    public void addCar(Car car) { cars.add(car); }

    // Add a customer to the system
    public void addCustomer(Customer customer) { customers.add(customer); }

    // Rent a car
    public void rentCar(Car car, Customer customer, int days) {
        if (car.isAvailable()) {              // if car is available
            car.rent();                       // mark car as rented
            rentals.add(new Rental(car, customer, days)); // add rental record
        } else {
            System.out.println("Car is not available for rent.");
        }
    }

    // Return a car
    public void returnCar(Car car) {
        car.returnCar();                       // mark car as available
        Rental rentalToRemove = null;          // temporary variable to store rental record
        for (Rental rental : rentals) {        // go through all rentals
            if (rental.getCar() == car) {     // find rental for this car
                rentalToRemove = rental;      // store it
                break;                        // stop loop
            }
        }
        if (rentalToRemove != null) {
            rentals.remove(rentalToRemove);   // remove rental record
        } else {
            System.out.println("Car was not rented.");
        }
    }

    // User menu for interaction
    public void menu() {
        Scanner scanner = new Scanner(System.in); // create scanner object

        while (true) { // infinite loop until user exits
            System.out.println("===== Car Rental System =====");
            System.out.println("1. Rent a Car");
            System.out.println("2. Return a Car");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();  // read user choice
            scanner.nextLine();              // consume leftover newline

            if (choice == 1) { // Rent a car
                System.out.println("\n====== Rent a Car =======\n");
                System.out.print("Enter your name: ");
                String customerName = scanner.nextLine(); // read customer name

                System.out.println("\nAvailable Cars:");
                for (Car car : cars) {
                    if (car.isAvailable()) { // show only available cars
                        System.out.println(car.getCarId() + " - " + car.getBrand() + " " + car.getModel());
                    }
                }

                System.out.print("\nEnter the car ID you want to rent: ");
                String carId = scanner.nextLine();

                System.out.print("Enter the number of days for rental: ");
                int rentalDays = scanner.nextInt();
                scanner.nextLine(); // consume leftover newline

                Customer newCustomer = new Customer("CID" + (customers.size() + 1), customerName); // create new customer
                addCustomer(newCustomer); // add customer to list

                Car selectedCar = null;
                for (Car car : cars) { // find car by ID
                    if (car.getCarId().equals(carId) && car.isAvailable()) {
                        selectedCar = car; // store reference
                        break;
                    }
                }

                if (selectedCar != null) { // if car found
                    double totalPrice = selectedCar.calculatePrice(rentalDays); // calculate total price
                    System.out.println("\n== Rental Information ==\n");
                    System.out.println("Customer ID: " + newCustomer.getCustomerId());
                    System.out.println("Customer Name: " + newCustomer.getName());
                    System.out.println("Car: " + selectedCar.getBrand() + " " + selectedCar.getModel());
                    System.out.println("Rental Days: " + rentalDays);
                    System.out.printf("Total Price: $%.2f%n", totalPrice);

                    System.out.print("\nConfirm rental (Y/N): ");
                    String confirm = scanner.nextLine(); // read confirmation

                    if (confirm.equalsIgnoreCase("Y")) {
                        rentCar(selectedCar, newCustomer, rentalDays); // rent the car
                        System.out.println("\nCar rented successfully.");
                    } else {
                        System.out.println("\nRental canceled.");
                    }
                } else {
                    System.out.println("\nInvalid car selection or car not available for rent.");
                }

            } else if (choice == 2) { // Return a car
                System.out.println("\n== Return a Car ==\n");
                System.out.print("Enter the car ID you want to return: ");
                String carId = scanner.nextLine();

                Car carToReturn = null;
                for (Car car : cars) { // find car by ID
                    if (car.getCarId().equals(carId) && !car.isAvailable()) { // only rented cars
                        carToReturn = car;
                        break;
                    }
                }

                if (carToReturn != null) { // car found
                    Customer customer = null;
                    for (Rental rental : rentals) { // find customer who rented it
                        if (rental.getCar() == carToReturn) {
                            customer = rental.getCustomer();
                            break;
                        }
                    }

                    if (customer != null) { // if rental record found
                        returnCar(carToReturn); // return the car
                        System.out.println("Car returned successfully by " + customer.getName());
                    } else {
                        System.out.println("Car was not rented or rental information is missing.");
                    }
                } else {
                    System.out.println("Invalid car ID or car is not rented.");
                }

            } else if (choice == 3) { // Exit
                break;
            } else { // Invalid input
                System.out.println("Invalid choice. Please enter a valid option.");
            }
        }

        System.out.println("\nThank you for using the Car Rental System!"); // end message
    }
}

// Main class to run the program
public class Main {
    public static void main(String[] args) {
        CarRentalSystem rentalSystem = new CarRentalSystem(); // create rental system object

        // Create cars
        Car car1 = new Car("C001", "Toyota", "Camry", 100.0);
        Car car2 = new Car("C002", "Honda", "Accord", 130.0);
        Car car3 = new Car("C003", "Mahindra", "Thar", 140.0);

        // Add cars to system
        rentalSystem.addCar(car1);
        rentalSystem.addCar(car2);
        rentalSystem.addCar(car3);

        rentalSystem.menu(); // start the menu
    }
}
