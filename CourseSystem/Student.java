package CourseSystem;

import java.util.ArrayList;
import java.util.HashMap;

public class Student {
    private String name;
    private String id;
    private ArrayList<Course> enrolledCourses;
    private HashMap<Course, Double> grades;

    public Student(String name, String id) {
        this.name = name;
        this.id = id;
        enrolledCourses = new ArrayList<Course>();
        grades = new HashMap<Course, Double>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    public HashMap<Course, Double> getGrades() {
        return grades;
    }

    // I didn't make a setter for enrolledCourses because I don't want other
    // classes replacing the whole list by accident. This is the only way in.
    public void enrollInCourse(Course course) {
        enrolledCourses.add(course);
    }

    // needed this for the update feature, so admins can take a student out
    // of a course without touching the list directly
    public void removeCourse(Course course) {
        enrolledCourses.remove(course);
    }

    // same reason as enrollInCourse, grades should only change through this method
    // so I always know exactly where a grade update could be coming from
    public void setGrade(Course course, double grade) {
        grades.put(course, grade);
    }
}