import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// Immutable data carrier for an employee row
record Employee(int id, String firstName, String lastName,
                String email, LocalDate hireDate, BigDecimal salary) {}

public class JdbcDemo {

    // Database connection parameters (in practice, use a properties file)
    private static final String URL = "jdbc:mysql://localhost:3306/javacrud?useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static void main(String[] args) {
        // 1. Insert sample data
        insertEmployee(new Employee(0, "Alice", "Smith",
                "alice@example.com", LocalDate.of(2023, 6, 15),
                new BigDecimal("75000.00")));

        // 2. Retrieve all employees
        List<Employee> employees = getAllEmployees();
        employees.forEach(System.out::println);

        // 3. Update an employee's salary
        if (!employees.isEmpty()) {
            updateSalary(employees.get(0).id(), new BigDecimal("80000.00"));
        }

        // 4. Delete an employee
        if (employees.size() > 1) {
            deleteEmployee(employees.get(1).id());
        }
    }

    // ------------------- CRUD Operations -------------------

    private static void insertEmployee(Employee emp) {
        var sql = """
            INSERT INTO employees (first_name, last_name, email, hire_date, salary)
            VALUES (?, ?, ?, ?, ?)
            """;

        try (var conn = DriverManager.getConnection(URL, USER, PASSWORD);
             var pstmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, emp.firstName());
            pstmt.setString(2, emp.lastName());
            pstmt.setString(3, emp.email());
            pstmt.setDate(4, Date.valueOf(emp.hireDate()));
            pstmt.setBigDecimal(5, emp.salary());
            pstmt.executeUpdate();
            ResultSet rs=pstmt.getGeneratedKeys();
            if(rs.next()){
                int id= rs.getInt(1);
                System.out.println("Employee inserted.\nEmployee id: "+id);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static List<Employee> getAllEmployees() {
        var sql = "SELECT id, first_name, last_name, email, hire_date, salary FROM employees";
        List<Employee> list = new ArrayList<>();

        try (var conn = DriverManager.getConnection(URL, USER, PASSWORD);
             var stmt = conn.createStatement();
             var rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(mapRowToEmployee(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private static void updateSalary(int id, BigDecimal newSalary) {
        var sql = "UPDATE employees SET salary = ? WHERE id = ?";

        try (var conn = DriverManager.getConnection(URL, USER, PASSWORD);
             var pstmt = conn.prepareStatement(sql)) {

            pstmt.setBigDecimal(1, newSalary);
            pstmt.setInt(2, id);
            int rows = pstmt.executeUpdate();
            System.out.printf("Updated %d employee(s).%n", rows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void deleteEmployee(int id) {
        var sql = "DELETE FROM employees WHERE id = ?";

        try (var conn = DriverManager.getConnection(URL, USER, PASSWORD);
             var pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            int rows = pstmt.executeUpdate();
            System.out.printf("Deleted %d employee(s).%n", rows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Helper to map a ResultSet row to an Employee record
    private static Employee mapRowToEmployee(ResultSet rs) throws SQLException {
        return new Employee(
            rs.getInt("id"),
            rs.getString("first_name"),
            rs.getString("last_name"),
            rs.getString("email"),
            rs.getDate("hire_date").toLocalDate(),
            rs.getBigDecimal("salary")
        );
    }
}