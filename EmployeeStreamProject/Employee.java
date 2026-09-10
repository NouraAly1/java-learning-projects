// This class is just a blueprint for one employee.
// We need name, age, department, and salary because those
// are the fields the assignment asks us to work with later.
public class Employee {
    private String name;
    private int age;
    private String department;
    private double salary;

    // constructor - lets us build an Employee in one line instead of
    // setting each field separately every time
    public Employee(String name, int age, String department, double salary) {
        this.name = name;
        this.age = age;
        this.department = department;
        this.salary = salary;
    }

    // getters - streams and lambdas will need these to pull data
    // out of each Employee object
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    // just so we can print an Employee nicely later if we want to check things
    @Override
    public String toString() {
        return name + " (" + department + ", age " + age + ", $" + salary + ")";
    }
}