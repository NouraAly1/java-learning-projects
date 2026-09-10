package CourseSystem;

public class Course {
    private String courseCode;
    private String courseName;
    private int maxCapacity;
    private int currentEnrollment;

    // this has to be static because I need ONE running total across every
    // course, not a separate count for each course object
    private static int totalEnrolledStudents = 0;

    public Course(String courseCode, String courseName, int maxCapacity) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.maxCapacity = maxCapacity;
        this.currentEnrollment = 0;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public int getCurrentEnrollment() {
        return currentEnrollment;
    }

    // added these setters so an admin can actually update a course later
    // instead of only being able to set it once in the constructor
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    // I check this before enrolling anyone so the capacity limit actually means something
    public boolean isFull() {
        return currentEnrollment >= maxCapacity;
    }

    public void addEnrollment() {
        currentEnrollment++;
        // updating the static counter here (not in CourseManagement) so it
        // stays accurate no matter which class ends up calling this method
        totalEnrolledStudents++;
    }

    // static so I can check this number without needing a specific course object
    public static int getTotalEnrolledStudents() {
        return totalEnrolledStudents;
    }
}
