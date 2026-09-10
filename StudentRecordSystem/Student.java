// Student.java
// This class represents ONE student. Every student object made from this
// class holds its own separate copy of these variables (that's why they
// are instance variables, not static).
package StudentRecordSystem;

public class Student {

    private String name;
    private int studentId;
    private int age;
    private String grade;

    // Constructor - runs automatically when we make a new Student
    public Student(String name, int studentId, int age, String grade) {
        this.name = name;
        this.studentId = studentId;
        this.age = age;
        this.grade = grade;
    }

    // ---- Getters ----
    // These let other classes read the private data without touching
    // the variables directly.
    public String getName() {
        return name;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getAge() {
        return age;
    }

    public String getGrade() {
        return grade;
    }

    // ---- Setters ----
    // Notice there is no setStudentId(). The ID is how we find a student
    // later, so it should not be changed after the student is created.
    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    // Makes it easy to print a student's details in one line
    @Override
    public String toString() {
        return "ID: " + studentId + " | Name: " + name + " | Age: " + age + " | Grade: " + grade;
    }
}