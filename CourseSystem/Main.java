package CourseSystem;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<Student>();
        int choice = 0;

        System.out.println("Welcome to the Course Enrollment and Grade Management System");

        while (choice != 10) {
            System.out.println("\n----- MENU -----");
            System.out.println("1. Add a new course");
            System.out.println("2. Add a new student");
            System.out.println("3. Enroll a student in a course");
            System.out.println("4. Assign a grade");
            System.out.println("5. Calculate overall grade");
            System.out.println("6. View total enrolled students (all courses)");
            System.out.println("7. Update student info");
            System.out.println("8. Update course info");
            System.out.println("9. Remove a course");
            System.out.println("10. Exit");
            System.out.print("Enter your choice: ");

            // checking this so typing a letter here doesn't crash the whole program
            while (!scan.hasNextInt()) {
                System.out.println("That's not a number, try again.");
                scan.next();
            }
            choice = scan.nextInt();
            scan.nextLine();

            if (choice == 1) {
                System.out.print("Enter course code: ");
                String code = scan.nextLine();
                System.out.print("Enter course name: ");
                String cname = scan.nextLine();
                System.out.print("Enter max capacity: ");
                int cap = scan.nextInt();
                scan.nextLine();
                CourseManagement.addCourse(code, cname, cap);

            } else if (choice == 2) {
                System.out.print("Enter student name: ");
                String sname = scan.nextLine();
                System.out.print("Enter student ID: ");
                String sid = scan.nextLine();
                students.add(new Student(sname, sid));
                System.out.println("Student added.");

            } else if (choice == 3) {
                if (students.isEmpty() || CourseManagement.getCourseList().isEmpty()) {
                    System.out.println("Need at least one student and one course first.");
                    continue;
                }
                // using the helper methods here instead of writing the same
                // "print list, get number, check it" code again
                int sIndex = pickStudent(students, scan);
                if (sIndex == -1) continue;
                int cIndex = pickCourse(CourseManagement.getCourseList(), scan);
                if (cIndex == -1) continue;

                CourseManagement.enrollStudent(students.get(sIndex), CourseManagement.getCourseList().get(cIndex));

            } else if (choice == 4) {
                if (students.isEmpty() || CourseManagement.getCourseList().isEmpty()) {
                    System.out.println("Need at least one student and one course first.");
                    continue;
                }
                int sIndex = pickStudent(students, scan);
                if (sIndex == -1) continue;
                int cIndex = pickCourse(CourseManagement.getCourseList(), scan);
                if (cIndex == -1) continue;

                System.out.print("Enter grade (0-100): ");
                while (!scan.hasNextDouble()) {
                    System.out.println("Please enter a number.");
                    scan.next();
                }
                double grade = scan.nextDouble();
                scan.nextLine();

                CourseManagement.assignGrade(students.get(sIndex), CourseManagement.getCourseList().get(cIndex), grade);

            } else if (choice == 5) {
                if (students.isEmpty()) {
                    System.out.println("No students yet.");
                    continue;
                }
                int sIndex = pickStudent(students, scan);
                if (sIndex == -1) continue;

                double overall = CourseManagement.calculateOverallGrade(students.get(sIndex));
                System.out.println(students.get(sIndex).getName() + "'s overall grade: " + overall);

            } else if (choice == 6) {
                // pulling from the static counter, this works across every
                // course without needing to loop through them all here
                System.out.println("Total students enrolled across all courses: " + Course.getTotalEnrolledStudents());

            } else if (choice == 7) {
                if (students.isEmpty()) {
                    System.out.println("No students yet.");
                    continue;
                }
                int sIndex = pickStudent(students, scan);
                if (sIndex == -1) continue;
                Student s = students.get(sIndex);

                System.out.println("What do you want to update?");
                System.out.println("1. Name");
                System.out.println("2. ID");
                System.out.println("3. Remove a course from their enrolled list");
                int updateChoice = scan.nextInt();
                scan.nextLine();

                if (updateChoice == 1) {
                    System.out.print("Enter new name: ");
                    String newName = scan.nextLine();
                    s.setName(newName);
                    System.out.println("Name updated.");
                } else if (updateChoice == 2) {
                    System.out.print("Enter new ID: ");
                    String newId = scan.nextLine();
                    s.setId(newId);
                    System.out.println("ID updated.");
                } else if (updateChoice == 3) {
                    if (s.getEnrolledCourses().isEmpty()) {
                        System.out.println(s.getName() + " isn't enrolled in any courses.");
                        continue;
                    }
                    // can't use pickCourse() here since this list is the
                    // student's own courses, not the full course catalog
                    System.out.println("Choose a course to remove:");
                    for (int i = 0; i < s.getEnrolledCourses().size(); i++) {
                        System.out.println(i + " - " + s.getEnrolledCourses().get(i).getCourseName());
                    }
                    int removeIndex = scan.nextInt();
                    scan.nextLine();
                    if (removeIndex < 0 || removeIndex >= s.getEnrolledCourses().size()) {
                        System.out.println("That wasn't a valid selection.");
                        continue;
                    }
                    s.removeCourse(s.getEnrolledCourses().get(removeIndex));
                    System.out.println("Course removed from student.");
                } else {
                    System.out.println("Not a valid option.");
                }

            } else if (choice == 8) {
                if (CourseManagement.getCourseList().isEmpty()) {
                    System.out.println("No courses yet.");
                    continue;
                }
                int cIndex = pickCourse(CourseManagement.getCourseList(), scan);
                if (cIndex == -1) continue;
                Course c = CourseManagement.getCourseList().get(cIndex);

                System.out.println("What do you want to update?");
                System.out.println("1. Course code");
                System.out.println("2. Course name");
                System.out.println("3. Max capacity");
                int updateChoice = scan.nextInt();
                scan.nextLine();

                if (updateChoice == 1) {
                    System.out.print("Enter new course code: ");
                    String newCode = scan.nextLine();
                    c.setCourseCode(newCode);
                    System.out.println("Course code updated.");
                } else if (updateChoice == 2) {
                    System.out.print("Enter new course name: ");
                    String newName = scan.nextLine();
                    c.setCourseName(newName);
                    System.out.println("Course name updated.");
                } else if (updateChoice == 3) {
                    System.out.print("Enter new max capacity: ");
                    int newCap = scan.nextInt();
                    scan.nextLine();
                    c.setMaxCapacity(newCap);
                    System.out.println("Max capacity updated.");
                } else {
                    System.out.println("Not a valid option.");
                }

            } else if (choice == 9) {
                if (CourseManagement.getCourseList().isEmpty()) {
                    System.out.println("No courses yet.");
                    continue;
                }
                int cIndex = pickCourse(CourseManagement.getCourseList(), scan);
                if (cIndex == -1) continue;

                CourseManagement.removeCourse(CourseManagement.getCourseList().get(cIndex));

            } else if (choice == 10) {
                System.out.println("Goodbye!");

            } else {
                System.out.println("Not a valid option, try again.");
            }
        }

        scan.close();
    }

    // this prints the student list, grabs the number the admin types, and
    // makes sure it's actually a real option before handing it back.
    // I made this its own method because I was writing this same block
    // inside almost every menu option, so pulling it out means I only
    // have to fix it in one place if something about it needs to change
    private static int pickStudent(ArrayList<Student> students, Scanner scan) {
        System.out.println("Choose a student:");
        for (int i = 0; i < students.size(); i++) {
            System.out.println(i + " - " + students.get(i).getName());
        }
        int index = scan.nextInt();
        scan.nextLine();
        // returning -1 as a signal that the pick was bad, so whatever
        // called this method knows to stop instead of using a broken index
        if (index < 0 || index >= students.size()) {
            System.out.println("Invalid selection.");
            return -1;
        }
        return index;
    }

    // same idea as pickStudent, just for courses instead. Same return -1
    // trick so the calling code can check it the same way both times
    private static int pickCourse(ArrayList<Course> courses, Scanner scan) {
        System.out.println("Choose a course:");
        for (int i = 0; i < courses.size(); i++) {
            System.out.println(i + " - " + courses.get(i).getCourseName());
        }
        int index = scan.nextInt();
        scan.nextLine();
        if (index < 0 || index >= courses.size()) {
            System.out.println("Invalid selection.");
            return -1;
        }
        return index;
    }
}
