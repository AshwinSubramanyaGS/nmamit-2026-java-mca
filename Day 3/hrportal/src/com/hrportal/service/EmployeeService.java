package com.hrportal.service;

import com.hrportal.model.Employee;
import java.util.ArrayList;
import java.util.List;

public class EmployeeService {
    private List<Employee> employees;

    public EmployeeService() {
        employees = new ArrayList<>();
    }

    // Add employee with uniqueness check on ID
    public boolean addEmployee(Employee emp) {
        // Check for duplicate ID
        for (Employee e : employees) {
            if (e.getId() == emp.getId()) {
                System.out.println("Error: Employee with ID " + emp.getId() + " already exists.");
                return false;
            }
        }
        employees.add(emp);
        System.out.println("Employee added successfully.");
        return true;
    }

    // Update salary using the manager-approved flag
    public void updateSalary(int id, double newSalary, boolean managerApproved) {
        Employee emp = findEmployeeById(id);
        if (emp == null) {
            System.out.println("Employee with ID " + id + " not found.");
            return;
        }
        boolean success = emp.setSalary(newSalary, managerApproved);
        if (success) {
            System.out.println("Salary updated successfully for " + emp.getName());
        } else {
            System.out.println("Update rejected: Increase >20% requires manager approval.");
        }
    }

    // Generate full report of all employees
    public void generateAllEmployeesReport() {
        if (employees.isEmpty()) {
            System.out.println("No employees in the system.");
            return;
        }
        System.out.println("==================== ALL EMPLOYEES ====================");
        for (Employee e : employees) {
            System.out.println(e.toString());
        }
        System.out.println("=======================================================");
    }

    // Generate report of employees above a salary threshold
    public void generateHighSalaryReport(double threshold) {
        boolean found = false;
        System.out.println("===== EMPLOYEES WITH SALARY > " + threshold + " =====");
        for (Employee e : employees) {
            if (e.getSalary() > threshold) {
                System.out.println(e.toString());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No employee exceeds that salary threshold.");
        }
    }

    // Show total company-wide employee count (static counter)
    public void showTotalEmployeeCount() {
        System.out.println("Total employees in the company: " + Employee.getEmployeeCount());
    }

    // Helper: find employee by ID
    public Employee findEmployeeById(int id) {
        for (Employee e : employees) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }
}