import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Alternative demo using popular external libraries:
 * - OpenCSV for CSV
 * - Jackson for JSON
 * - Jackson XML module for XML
 *
 * Same use case: Manage a list of students.
 */
public class FileDemoWithLibs {

    // ---------- Student POJO (Jackson & OpenCSV compatible) ----------
    // Jackson uses getters/setters or fields; OpenCSV can use @CsvBindByName
    public static class Student implements Serializable {
        private static final long serialVersionUID = 1L;

        private int id;
        private String name;
        private double grade;

        // Default constructor required for Jackson/OpenCSV
        public Student() {}

        public Student(int id, String name, double grade) {
            this.id = id;
            this.name = name;
            this.grade = grade;
        }

        // Getters and setters (Jackson uses them by default)
        public int getId() { return id; }
        public void setId(int id) { this.id = id; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public double getGrade() { return grade; }
        public void setGrade(double grade) { this.grade = grade; }

        @Override
        public String toString() {
            return "Student{id=" + id + ", name='" + name + "', grade=" + grade + "}";
        }
    }

    // ---------- OpenCSV operations ----------
    public static void writeCSV_OpenCSV(String filePath, List<Student> students) throws IOException,
            CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        try (Writer writer = new FileWriter(filePath)) {
            // Use StatefulBeanToCsv for direct bean writing
            StatefulBeanToCsv<Student> beanToCsv = new StatefulBeanToCsvBuilder<Student>(writer)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)  // optional
                    .build();
            beanToCsv.write(students);
        }
        System.out.println("CSV written (OpenCSV): " + filePath);
    }

    public static List<Student> readCSV_OpenCSV(String filePath) throws IOException {
        List<Student> students;
        try (Reader reader = new FileReader(filePath)) {
            // Use CsvToBeanBuilder for automatic mapping based on header names
            students = new CsvToBeanBuilder<Student>(reader)
                    .withType(Student.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build()
                    .parse();
        }
        System.out.println("CSV read (OpenCSV): " + filePath);
        return students;
    }

    // ---------- Jackson JSON operations ----------
    private static final ObjectMapper jsonMapper = new ObjectMapper();

    public static void writeJSON_Jackson(String filePath, List<Student> students) throws IOException {
        jsonMapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), students);
        System.out.println("JSON written (Jackson): " + filePath);
    }

    public static List<Student> readJSON_Jackson(String filePath) throws IOException {
        List<Student> students = jsonMapper.readValue(new File(filePath), new TypeReference<List<Student>>() {});
        System.out.println("JSON read (Jackson): " + filePath);
        return students;
    }

    // ---------- Jackson XML operations ----------
    private static final XmlMapper xmlMapper = new XmlMapper();

    public static void writeXML_Jackson(String filePath, List<Student> students) throws IOException {
        xmlMapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), students);
        System.out.println("XML written (Jackson): " + filePath);
    }

    public static List<Student> readXML_Jackson(String filePath) throws IOException {
        List<Student> students = xmlMapper.readValue(new File(filePath), new TypeReference<List<Student>>() {});
        System.out.println("XML read (Jackson): " + filePath);
        return students;
    }

    // ---------- Standard Java Serialization (unchanged) ----------
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
        // Sample data
        List<Student> originalStudents = new ArrayList<>(Arrays.asList(
                new Student(101, "Alice", 88.5),
                new Student(102, "Bob", 72.0),
                new Student(103, "Charlie", 95.2)
        ));

        String csvFile   = "students_open.csv";
        String jsonFile  = "students_open.json";
        String xmlFile   = "students_open.xml";
        String serFile   = "students_open.ser";

        try {
            // Write files using libraries
            writeCSV_OpenCSV(csvFile, originalStudents);
            writeJSON_Jackson(jsonFile, originalStudents);
            writeXML_Jackson(xmlFile, originalStudents);

            // Read them back
            List<Student> fromCSV = readCSV_OpenCSV(csvFile);
            System.out.println("From CSV:");
            fromCSV.forEach(System.out::println);

            List<Student> fromJSON = readJSON_Jackson(jsonFile);
            System.out.println("From JSON:");
            fromJSON.forEach(System.out::println);

            List<Student> fromXML = readXML_Jackson(xmlFile);
            System.out.println("From XML:");
            fromXML.forEach(System.out::println);

            // Serialization still works
            serializeStudents(serFile, originalStudents);
            List<Student> fromSer = deserializeStudents(serFile);
            System.out.println("From Serialization:");
            fromSer.forEach(System.out::println);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}