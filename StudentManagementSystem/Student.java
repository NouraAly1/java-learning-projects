package StudentManagementSystem;

import java.util.HashMap;

public class Student {
    private int id;
    private String name;
    private int age;
    private String email;
    private HashMap<String, String> courseGrades; // course name -> grade (or "Not graded yet")

    public Student(int id, String name, int age, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.courseGrades = new HashMap<>();
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public HashMap<String, String> getCourseGrades() { return courseGrades; }

    public void enroll(String courseName) {
        if (!courseGrades.containsKey(courseName)) {
            courseGrades.put(courseName, "Not graded yet");
        }
    }

    public void assignGrade(String courseName, String grade) {
        courseGrades.put(courseName, grade);
    }

    // this is what shows up in dropdowns and lists automatically
    public String toString() {
        return name + " (ID: " + id + ")";
    }
}