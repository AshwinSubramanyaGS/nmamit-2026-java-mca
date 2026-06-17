import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Demo: File Handling, BufferedReader, FileWriter & Serialization
 * Formats: CSV, JSON, XML
 *
 * Use case: Manage a list of students (id, name, grade).
 * - Write sample data to CSV, JSON, XML files using FileWriter.
 * - Read CSV using BufferedReader and convert to Student objects.
 * - Serialize the list of students to a file and deserialize it back.
 */
public class FileDemo {

    // ---------- Student model (must be Serializable) ----------
    static class Student implements Serializable {
        private static final long serialVersionUID = 1L;  // recommended for versioning
        private int id;
        private String name;
        private double grade;

        public Student(int id, String name, double grade) {
            this.id = id;
            this.name = name;
            this.grade = grade;
        }

        @Override
        public String toString() {
            return "Student{id=" + id + ", name='" + name + "', grade=" + grade + "}";
        }
    }

    // ---------- CSV operations ----------
    public static void writeCSV(String filePath, List<Student> students) throws IOException {
        try (FileWriter fw = new FileWriter(filePath)) {
            // header
            fw.write("id,name,grade\n");
            for (Student s : students) {
                fw.write(s.id + "," + s.name + "," + s.grade + "\n");
            }
        }
        System.out.println("CSV written: " + filePath);
    }

    public static List<Student> readCSV(String filePath) throws IOException {
        List<Student> students = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine(); // skip header
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0].trim());
                String name = parts[1].trim();
                double grade = Double.parseDouble(parts[2].trim());
                students.add(new Student(id, name, grade));
            }
        }
        System.out.println("CSV read: " + filePath);
        return students;
    }

    // ---------- JSON operations (manual, no library) ----------
    public static void writeJSON(String filePath, List<Student> students) throws IOException {
        try (FileWriter fw = new FileWriter(filePath)) {
            fw.write("[\n");
            for (int i = 0; i < students.size(); i++) {
                Student s = students.get(i);
                fw.write("  {\n");
                fw.write("    \"id\": " + s.id + ",\n");
                fw.write("    \"name\": \"" + s.name + "\",\n");
                fw.write("    \"grade\": " + s.grade + "\n");
                fw.write("  }");
                if (i < students.size() - 1) fw.write(",");
                fw.write("\n");
            }
            fw.write("]\n");
        }
        System.out.println("JSON written: " + filePath);
    }

    // ---------- XML operations (manual, no library) ----------
    public static void writeXML(String filePath, List<Student> students) throws IOException {
        try (FileWriter fw = new FileWriter(filePath)) {
            fw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            fw.write("<students>\n");
            for (Student s : students) {
                fw.write("  <student>\n");
                fw.write("    <id>" + s.id + "</id>\n");
                fw.write("    <name>" + s.name + "</name>\n");
                fw.write("    <grade>" + s.grade + "</grade>\n");
                fw.write("  </student>\n");
            }
            fw.write("</students>\n");
        }
        System.out.println("XML written: " + filePath);
    }

    // ---------- Serialization ----------
    public static void serializeStudents(String filePath, List<Student> students) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(students);
        }
        System.out.println("Serialized to: " + filePath);
    }

    @SuppressWarnings("unchecked")
    public static List<Student> deserializeStudents(String filePath) throws IOException, ClassNotFoundException {
        List<Student> students;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            students = (List<Student>) ois.readObject();
        }
        System.out.println("Deserialized from: " + filePath);
        return students;
    }

    // ---------- Main demo ----------
    public static void main(String[] args) {
        // 1. Prepare sample data
        List<Student> originalStudents = new ArrayList<>();
        originalStudents.add(new Student(101, "Alice", 88.5));
        originalStudents.add(new Student(102, "Bob", 72.0));
        originalStudents.add(new Student(103, "Charlie", 95.2));

        // Define file paths (adjust if needed)
        String csvFile   = "students.csv";
        String jsonFile  = "students.json";
        String xmlFile   = "students.xml";
        String serFile   = "students.ser";

        try {
            // --- Write files using FileWriter ---
            writeCSV(csvFile, originalStudents);
            writeJSON(jsonFile, originalStudents);
            writeXML(xmlFile, originalStudents);

            // --- Read CSV back using BufferedReader ---
            List<Student> fromCSV = readCSV(csvFile);
            System.out.println("Students read from CSV:");
            fromCSV.forEach(System.out::println);

            // --- Serialization ---
            serializeStudents(serFile, originalStudents);
            List<Student> fromSerialized = deserializeStudents(serFile);
            System.out.println("Students from deserialization:");
            fromSerialized.forEach(System.out::println);

            // Optional: Show contents of generated files
            printFileContents(csvFile);
            printFileContents(jsonFile);
            printFileContents(xmlFile);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Helper: display file content
    private static void printFileContents(String filePath) throws IOException {
        System.out.println("\n--- Contents of " + filePath + " ---");
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        }
    }
}