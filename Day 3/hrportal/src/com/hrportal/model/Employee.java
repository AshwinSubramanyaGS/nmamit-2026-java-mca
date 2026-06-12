package com.hrportal.model;

import java.time.LocalDate;

public class Employee {
    // Private instance variables for encapsulation
    private String name;
    private final int id;               // immutable after creation
    private String department;
    private double salary;
    private LocalDate joiningDate;

    // Static counter for total employees (company-wide)
    private static int employeeCount;

    // Static block to initialise the counter (as required)
    static {
        employeeCount = 0;
    }

    // Full-arg constructor – all fields validated
    public Employee(String name, int id, String department, double salary, LocalDate joiningDate) {
        // Validation
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        if (salary <= 0) {
            throw new IllegalArgumentException("Salary must be greater than 0.");
        }
        // 'this' resolves parameter-field name clash
        this.name = name.trim();
        this.id = id;
        this.department = department;
        this.salary = salary;
        this.joiningDate = joiningDate;
        employeeCount++;
    }

    // Overloaded constructor – minimal: only name and id
    // Sets sensible defaults for department, salary, joining date
    public Employee(String name, int id) {
        this(name, id, "Not Assigned", 1000.0, LocalDate.now());
    }

    // Getter methods for report generation and read-only access
    public String getName() {
        return name;
    }

    public int getId() {
        return id;   // no setter → truly read-only
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    // Name setter with validation
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        this.name = name.trim();
    }

    // Department setter (no special validation)
    public void setDepartment(String department) {
        this.department = department;
    }

    // Salary update with manager approval validation
    // Returns true if update successful, false if rejected due to rule
    public boolean setSalary(double newSalary, boolean managerApproved) {
        if (newSalary <= 0) {
            throw new IllegalArgumentException("Salary must be greater than 0.");
        }
        // If increase exceeds 20% and no manager approval, reject
        if (newSalary > this.salary * 1.2 && !managerApproved) {
            return false;   // increase not allowed
        }
        this.salary = newSalary;
        return true;
    }

    // Instance method: annual bonus (10% of salary)
    public double calculateBonus() {
        return this.salary * 0.10;
    }

    // Static utility: compare two employees by salary
    public static int compareSalary(Employee e1, Employee e2) {
        return Double.compare(e1.salary, e2.salary);
    }

    // Static accessor for the employee counter
    public static int getEmployeeCount() {
        return employeeCount;
    }

    // Nicely formatted employee string for reports
    @Override
    public String toString() {
        return String.format("ID: %d | Name: %s | Dept: %s | Salary: %.2f | Joined: %s",
                id, name, department, salary, joiningDate);
    }
}