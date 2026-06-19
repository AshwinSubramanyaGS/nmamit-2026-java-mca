import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Indexes;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.bson.types.Decimal128;

import static com.mongodb.client.model.Filters.eq;

// Immutable data carrier – id is the hex string representation of ObjectId
record Employee(String id, String firstName, String lastName,
                String email, LocalDate hireDate, BigDecimal salary) {}

public class MongoDemo {

    private static final String CONNECTION_STRING = "mongodb://localhost:27017";
    private static final String DATABASE = "company";
    private static final String COLLECTION = "employees";

    public static void main(String[] args) {
        // 1. Insert a new employee (MongoDB generates the _id)
        String generatedId = insertEmployee(new Employee(
                null, // id will be assigned by MongoDB
                "Alice", "Smith", "alice@example.com",
                LocalDate.of(2023, 6, 15), new BigDecimal("75000.00")
        ));
        System.out.println("Inserted employee with id: " + generatedId);

        // 2. Retrieve all employees
        List<Employee> employees = getAllEmployees();
        employees.forEach(System.out::println);

        // 3. Update an employee's salary
        if (!employees.isEmpty()) {
            String firstId = employees.get(0).id();
            updateSalary(firstId, new BigDecimal("80000.00"));
        }

        // 4. Delete an employee
        if (employees.size() > 1) {
            String secondId = employees.get(1).id();
            deleteEmployee(secondId);
        }
    }

    // ------------------- CRUD Operations -------------------

    private static String insertEmployee(Employee emp) {
        try (MongoClient mongoClient = MongoClients.create(CONNECTION_STRING)) {
            MongoCollection<Document> collection = getCollection(mongoClient);

            Document doc = new Document()
                    .append("first_name", emp.firstName())
                    .append("last_name", emp.lastName())
                    .append("email", emp.email())
                    .append("hire_date", localDateToDate(emp.hireDate()))
                    .append("salary", emp.salary());

            collection.insertOne(doc);

            // The driver populates doc with the generated _id
            ObjectId oid = doc.getObjectId("_id");
            return oid.toHexString();
        }
    }

    private static List<Employee> getAllEmployees() {
        List<Employee> list = new ArrayList<>();
        try (MongoClient mongoClient = MongoClients.create(CONNECTION_STRING)) {
            MongoCollection<Document> collection = getCollection(mongoClient);
            for (Document doc : collection.find()) {
                list.add(mapDocumentToEmployee(doc));
            }
        }
        return list;
    }

    private static void updateSalary(String hexId, BigDecimal newSalary) {
        try (MongoClient mongoClient = MongoClients.create(CONNECTION_STRING)) {
            MongoCollection<Document> collection = getCollection(mongoClient);
            Bson filter = eq("_id", new ObjectId(hexId));
            Document update = new Document("$set", new Document("salary", newSalary));
            UpdateResult result = collection.updateOne(filter, update);
            System.out.printf("Updated %d employee(s).%n", result.getModifiedCount());
        }
    }

    private static void deleteEmployee(String hexId) {
        try (MongoClient mongoClient = MongoClients.create(CONNECTION_STRING)) {
            MongoCollection<Document> collection = getCollection(mongoClient);
            Bson filter = eq("_id", new ObjectId(hexId));
            DeleteResult result = collection.deleteOne(filter);
            System.out.printf("Deleted %d employee(s).%n", result.getDeletedCount());
        }
    }

    // Ensure email uniqueness (call once at startup or setup)
    private static void createEmailIndex() {
        try (MongoClient mongoClient = MongoClients.create(CONNECTION_STRING)) {
            MongoCollection<Document> collection = getCollection(mongoClient);
            collection.createIndex(Indexes.ascending("email"), new com.mongodb.client.model.IndexOptions().unique(true));
            System.out.println("Index on email created (if not already present).");
        }
    }

    // ------------------- Helper Methods -------------------

    private static MongoCollection<Document> getCollection(MongoClient mongoClient) {
        MongoDatabase database = mongoClient.getDatabase(DATABASE);
        return database.getCollection(COLLECTION);
    }

    private static Employee mapDocumentToEmployee(Document doc) {
         String hexId = doc.getObjectId("_id").toHexString();
    String firstName = doc.getString("first_name");
    String lastName = doc.getString("last_name");
    String email = doc.getString("email");
    LocalDate hireDate = dateToLocalDate(doc.getDate("hire_date"));
    
    // Retrieve salary as Decimal128, then convert to BigDecimal
    Decimal128 decimal128 = doc.get("salary", Decimal128.class);
    BigDecimal salary = (decimal128 != null) ? decimal128.bigDecimalValue() : BigDecimal.ZERO;

    return new Employee(hexId, firstName, lastName, email, hireDate, salary);
    }

    private static Date localDateToDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    private static LocalDate dateToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}