package com.hrportal.app;

import com.hrportal.model.Employee;
import com.hrportal.service.EmployeeService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static EmployeeService service = new EmployeeService();
    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static void main(String[] args) {
        while (true) {
            printMenu();
            int choice = readInt("Enter your choice: ");
            switch (choice) {
                case 1 -> addEmployee();
                case 2 -> updateSalary();
                case 3 -> service.generateAllEmployeesReport();
                case 4 -> service.showTotalEmployeeCount();
                case 5 -> compareEmployees();
                case 6 -> {
                    System.out.println("Exiting HR Portal. Goodbye!");
                    scanner.close();
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n========= HR PORTAL MENU =========");
        System.out.println("1. Add Employee");
        System.out.println("2. Update Salary");
        System.out.println("3. Display All Employees");
        System.out.println("4. Show Total Employee Count");
        System.out.println("5. Compare Two Employees by Salary");
        System.out.println("6. Exit");
        System.out.println("==================================");
    }

    private static void addEmployee() {
        System.out.println("\n--- Add New Employee ---");
        try {
            String name = readNonEmptyString("Enter name: ");
            int id = readInt("Enter unique ID: ");
            String dept = readString("Enter department: ");
            double salary = readPositiveDouble("Enter salary: ");
            LocalDate joiningDate = readDate("Enter joining date (yyyy-MM-dd): ");

            Employee emp = new Employee(name, id, dept, salary, joiningDate);
            service.addEmployee(emp);

        } catch (IllegalArgumentException e) {
            System.out.println("Validation error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Input error: " + e.getMessage());
        }
    }

    private static void updateSalary() {
        System.out.println("\n--- Update Salary ---");
        int id = readInt("Enter employee ID: ");
        Employee emp = service.findEmployeeById(id);
        if (emp == null) {
            System.out.println("Employee not found.");
            return;
        }
        System.out.println("Current salary of " + emp.getName() + ": " + emp.getSalary());
        double newSalary = readPositiveDouble("Enter new salary: ");
        boolean approved = readBoolean("Manager approval given? (true/false): ");
        service.updateSalary(id, newSalary, approved);
    }

    private static void compareEmployees() {
        System.out.println("\n--- Compare Two Employees by Salary ---");
        int id1 = readInt("Enter first employee ID: ");
        Employee e1 = service.findEmployeeById(id1);
        if (e1 == null) {
            System.out.println("First employee not found.");
            return;
        }
        int id2 = readInt("Enter second employee ID: ");
        Employee e2 = service.findEmployeeById(id2);
        if (e2 == null) {
            System.out.println("Second employee not found.");
            return;
        }

        int result = Employee.compareSalary(e1, e2);
        String comparison;
        if (result < 0) {
            comparison = e1.getName() + " earns LESS than " + e2.getName();
        } else if (result > 0) {
            comparison = e1.getName() + " earns MORE than " + e2.getName();
        } else {
            comparison = "Both earn the SAME salary.";
        }
        System.out.println("Comparison result: " + comparison);
    }

    // ---------- Input helpers ----------
    private static String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private static String readNonEmptyString(String prompt) {
        while (true) {
            String input = readString(prompt);
            if (!input.isEmpty()) return input;
            System.out.println("Input cannot be empty.");
        }
    }

    private static int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = Integer.parseInt(scanner.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid integer. Try again.");
            }
        }
    }

    private static double readPositiveDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                double value = Double.parseDouble(scanner.nextLine().trim());
                if (value > 0) return value;
                System.out.println("Value must be > 0.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Try again.");
            }
        }
    }

    private static LocalDate readDate(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return LocalDate.parse(input, dateFormatter);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use yyyy-MM-dd.");
            }
        }
    }

    private static boolean readBoolean(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("true") || input.equals("yes") || input.equals("y"))
                return true;
            if (input.equals("false") || input.equals("no") || input.equals("n"))
                return false;
            System.out.println("Please enter true/false (or yes/no).");
        }
    }
}