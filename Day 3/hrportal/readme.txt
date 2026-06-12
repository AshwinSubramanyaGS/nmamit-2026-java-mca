## Architecture Overview (Packages)

```
hrportal/
├── src/
│   └── com/
│       └── hrportal/
│           ├── model/
│           │   └── Employee.java          # Core domain class
│           ├── service/
│           │   └── EmployeeService.java    # Business operations & validation
│           └── app/
│               └── Main.java              # Console menu & interaction
```

> **Why this split?**  
> - `model` contains the `Employee` class with all field validations, immutability constraints, and report‑ready getters.  
> - `service` manages the collection, enforces ID uniqueness, salary‑update logic, and report generation.  
> - `app` provides the console interface (Scanner‑based menu) – no business logic here.

---

## File Responsibilities & Key OOP Features

### 1. `Employee.java`
- **Encapsulation**: All fields are `private`; getters/setters control access.
- **Immutable ID**: `id` is `final`, set only via constructor – no setter.
- **Validation**:
  - `name` – rejected if null/empty (constructor/setter).
  - `salary` – rejected if ≤ 0 (constructor/setter).
- **Salary update with approval**:
  ```java
  public boolean setSalary(double newSalary, boolean managerApproved) {
      if (newSalary > this.salary * 1.2 && !managerApproved)
          return false;  // or throw exception
      this.salary = newSalary;
      return true;
  }
  ```
- **Static employee counter**: `private static int employeeCount`, incremented in all constructors. Initialised via `static { employeeCount = 0; }` (or inline).
- **`this` for name clash**: `this.name = name;` in parameterised constructors.
- **Constructor overloading**:
  - `Employee(String name, int id, String department, double salary, LocalDate joiningDate)` – full details.
  - `Employee(String name, int id)` – minimal (sets department/salary to defaults).
- **Instance method**: `public double calculateBonus()` – e.g., 10% of salary.
- **Static utility**: `public static int compareSalary(Employee e1, Employee e2)` returns -1, 0, or 1.
- **Report helpers**: Public getters for all fields, plus a `toString()` for quick display.

### 2. `EmployeeService.java`
- Holds the employee list in memory: `private List<Employee> employees = new ArrayList<>();`
- **ID uniqueness**: `addEmployee(Employee e)` checks if an employee with the same ID already exists.
- **Salary update delegation**: calls the employee’s `setSalary` with the manager‑approval flag.
- **Report generation**:
  - `generateAllEmployeesReport()` – prints a formatted table using each employee’s getters.
  - `generateHighSalaryReport(double threshold)` – filters employees above a salary threshold.
- **Total count access**: returns `Employee.getEmployeeCount()` (static).

### 3. `Main.java`
- Console menu loop with options:
  1. Add employee (collects input, constructs `Employee`, passes to service).
  2. Update salary (asks for ID, new salary, approval flag).
  3. Display all employees.
  4. Show total number of employees.
  5. Compare two employees by salary.
  6. Exit.
- Uses `Scanner` for input; simple `switch` statement.

---

## Matching Requirements to the Code (per your table)

| Requirement | Implementation in `Employee.java` |
|-------------|-----------------------------------|
| Private instance variables | `private String name; private final int id; private String department; private double salary; private LocalDate joiningDate;` |
| Validation on name/salary | Constructor & setter throw `IllegalArgumentException` for empty name or salary ≤ 0. |
| Immutable ID | `final int id` – only set in constructor, no setter. |
| Employee counter | `private static int employeeCount`; incremented in both constructors; static block `static { employeeCount = 0; }` |
| `this` keyword | `this.name = name;` etc. |
| Constructor overloading | Two constructors as described above. |
| `calculateBonus()` | Instance method returning `salary * 0.10`. |
| `compareSalary()` | `public static int compareSalary(Employee e1, Employee e2)` returns `Double.compare(e1.salary, e2.salary)`. |
| Salary increase validation | `setSalary(double newSalary, boolean managerApproved)` returns `false` if increase > 20% and no approval. |
| Report generation | `Employee` provides getters; `EmployeeService` builds the report strings. |

---

## How to Compile & Run (Console Only)

1. Navigate to the `hrportal/` root.
2. Compile:  
   `javac -d bin src/com/hrportal/model/Employee.java src/com/hrportal/service/EmployeeService.java src/com/hrportal/app/Main.java`
3. Run:  
   `java -cp bin com.hrportal.app.Main`

