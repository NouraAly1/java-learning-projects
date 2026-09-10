package CourseSystem;

import java.util.ArrayList;
import java.util.HashMap;

public class CourseManagement {
    // static because there's only one course catalog for the whole system,
    // not one per object
    private static ArrayList<Course> courseList = new ArrayList<Course>();
    private static HashMap<Student, Double> overallGrades = new HashMap<Student, Double>();

    public static void addCourse(String courseCode, String courseName, int maxCapacity) {
        Course newCourse = new Course(courseCode, courseName, maxCapacity);
        courseList.add(newCourse);
        System.out.println("Course added: " + courseName);
    }

    // needed this so admins can remove a course from the system, part 4 of the rubric asks for it
    public static void removeCourse(Course course) {
        courseList.remove(course);
        System.out.println("Course removed: " + course.getCourseName());
    }

    public static boolean enrollStudent(Student student, Course course) {
        // checking capacity here instead of inside Student, because Student
        // shouldn't need to know anything about course limits
        if (course.isFull()) {
            System.out.println("Sorry, " + course.getCourseName() + " is already full.");
            return false;
        }
        student.enrollInCourse(course);
        course.addEnrollment();
        System.out.println(student.getName() + " was enrolled in " + course.getCourseName());
        return true;
    }

    public static void assignGrade(Student student, Course course, double grade) {
        // not touching student's grades map directly, going through their
        // own method so Student stays in control of its own data
        student.setGrade(course, grade);
        System.out.println("Grade of " + grade + " assigned to " + student.getName() + " for " + course.getCourseName());
    }

    public static double calculateOverallGrade(Student student) {
        HashMap<Course, Double> grades = student.getGrades();
        if (grades.isEmpty()) {
            System.out.println(student.getName() + " has no grades yet.");
            return 0.0;
        }
        double total = 0;
        for (double g : grades.values()) {
            total += g;
        }
        double average = total / grades.size();
        // saving it here so I don't have to recalculate it every time I want to look it up
        overallGrades.put(student, average);
        return average;
    }

    public static ArrayList<Course> getCourseList() {
        return courseList;
    }
}