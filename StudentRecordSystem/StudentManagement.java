// StudentManagement.java
// This class manages ALL the students in the system. Unlike Student.java,
// the important variables here are static, because there is only one
// student list and one total count for the whole program, not one per
// object. That is the static vs non-static idea in action.

package StudentRecordSystem;

import java.util.ArrayList;

public class StudentManagement {

    // static = belongs to the class itself, shared everywhere,
    // there is only ever one copy of these two things in the whole program.
    private static ArrayList<Student> studentList = new ArrayList<>();
    private static int totalStudents = 0;

    // Adds a new student after checking the inputs make sense
    public static void addStudent(String name, int studentId, int age, String grade) {

        if (name == null || name.trim().isEmpty()) {
            System.out.println("Error: name can't be empty.");
            return;
        }

        if (age <= 0 || age > 100) {
            System.out.println("Error: that age doesn't look right, try again.");
            return;
        }

        if (findStudentById(studentId) != null) {
            System.out.println("Error: a student with ID " + studentId + " already exists.");
            return;
        }

        Student newStudent = new Student(name, studentId, age, grade);
        studentList.add(newStudent);
        totalStudents++;

        System.out.println("Student added. Total students so far: " + totalStudents);
    }

    // Updates a student's info if that ID actually exists
    public static void updateStudent(int studentId, String newName, int newAge, String newGrade) {

        Student student = findStudentById(studentId);

        if (student == null) {
            System.out.println("Error: no student found with ID " + studentId + ".");
            return;
        }

        student.setName(newName);
        student.setAge(newAge);
        student.setGrade(newGrade);

        System.out.println("Student " + studentId + " updated.");
    }

    // Shows one student's details
    public static void viewStudent(int studentId) {

        Student student = findStudentById(studentId);

        if (student == null) {
            System.out.println("Error: no student found with ID " + studentId + ".");
            return;
        }

        System.out.println(student.toString());
    }

    // Shows every student currently saved in the system
    public static void viewAllStudents() {

        if (studentList.isEmpty()) {
            System.out.println("No students added yet.");
            return;
        }

        System.out.println("---- Student List (" + totalStudents + " total) ----");
        for (int i = 0; i < studentList.size(); i++) {
            System.out.println(studentList.get(i).toString());
        }
    }

    // Small helper method used by the methods above so the same search
    // logic isn't copy-pasted three times. Kept private since the menu
    // never needs to call this directly.
    private static Student findStudentById(int studentId) {
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getStudentId() == studentId) {
                return studentList.get(i);
            }
        }
        return null;
    }

    public static int getTotalStudents() {
        return totalStudents;
    }
}
