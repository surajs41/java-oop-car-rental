# 🚗 Car Rental System - Java Backend (OOP + ArrayList)

A simple **Car Rental System backend** implemented in **Java** using **Object-Oriented Programming (OOP) concepts** and **ArrayList** to manage cars, customers, and rentals.  

This project demonstrates OOP concepts like **classes, objects, constructors, encapsulation, and methods** in a real-world scenario.

---

## ✨ Features

- 🚘 Add cars to the system  
- 🧑‍💼 Add customers to the system  
- 🏎️ Rent a car (check availability before renting)  
- 🔄 Return a car (update availability and remove rental record)  
- 💰 Calculate total rental price based on days  
- 🖥️ Simple console-based menu for interaction  

---

## 📦 Classes Used

- `Car` – Represents a car, tracks availability and rental price  
- `Customer` – Represents a customer  
- `Rental` – Represents a rental record (which car is rented by which customer for how many days)  
- `CarRentalSystem` – Manages the list of cars, customers, and rentals; contains menu operations  
- `Main` – Runs the application  

---

## 🖥️ Console Output Example



===== Car Rental System =====
1. Rent a Car
2. Return a Car
3. Exit
Enter your choice: 1

Enter your name: John

Available Cars:
C001 - Toyota Camry
C002 - Honda Accord
C003 - Mahindra Thar

Enter the car ID you want to rent: C002
Enter the number of days for rental: 3

== Rental Information ==
Customer ID: CID1
Customer Name: John
Car: Honda Accord
Rental Days: 3
Total Price: $390.00

Confirm rental (Y/N): Y
Car rented successfully.



