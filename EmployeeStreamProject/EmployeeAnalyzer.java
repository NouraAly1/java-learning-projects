import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class EmployeeAnalyzer {

    public static void main(String[] args) {

        // ---------- build the collection ----------
        // storing employees in an ArrayList because we need an ordered
        // collection that we can easily turn into a stream later
        List<Employee> employees = new ArrayList<>();

        // just made up some sample data here - in a real project this
        // would probably come from a file or database instead
        employees.add(new Employee("Sara Khalil", 34, "Marketing", 65000));
        employees.add(new Employee("Tom Reed", 28, "IT", 72000));
        employees.add(new Employee("Lina Fahmy", 41, "Finance", 88000));
        employees.add(new Employee("Omar Nasser", 25, "IT", 60000));
        employees.add(new Employee("Mona Aziz", 37, "Marketing", 70500));
        employees.add(new Employee("Hassan Ali", 45, "Finance", 95000));

        // quick sanity check to make sure our list actually filled up right
        System.out.println("All employees:");
        for (Employee e : employees) {
            System.out.println(e);
        }

        // ---------- Function interface ----------
        // this Function takes ONE Employee in, and gives ONE String back out.
        // using a lambda here instead of a whole separate class, since
        // Function is a functional interface (only one abstract method: apply)
        Function<Employee, String> nameAndDept = emp -> emp.getName() + " - " + emp.getDepartment();

        // ---------- apply the Function to the whole list using a stream ----------
        // .stream() turns our List into a stream so we can chain operations on it
        // .map(nameAndDept) runs our Function on every employee, one by one
        // .collect(Collectors.toList()) gathers the results back into a real List
        // nothing here actually runs until .collect() is called - that's the
        // "lazy evaluation" the assignment mentions, streams don't do work
        // until something asks for the final result
        List<String> nameDeptList = employees.stream()
                .map(nameAndDept)
                .collect(Collectors.toList());

        System.out.println("\nName - Department list:");
        for (String s : nameDeptList) {
            System.out.println(s);
        }

        // ---------- average salary ----------
        // mapToDouble converts the stream of Employee objects into a stream
        // of plain doubles (just the salary numbers), which is what we need
        // before we can call .average() - average() only works on number streams
        // .orElse(0) covers the case where the list is empty, so we don't crash
        double averageSalary = employees.stream()
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0);

        System.out.println("\nAverage salary: $" + averageSalary);

        // ---------- filter by age threshold ----------
        int ageThreshold = 30; // change this number to test different thresholds

        // .filter() only lets employees through if the condition inside is true
        // we're re-using the same list, but building a totally separate
        // filtered list here - the original "employees" list never gets touched
        List<Employee> olderEmployees = employees.stream()
                .filter(emp -> emp.getAge() > ageThreshold)
                .collect(Collectors.toList());

        System.out.println("\nEmployees older than " + ageThreshold + ":");
        for (Employee e : olderEmployees) {
            System.out.println(e);
        }

        // ---------- bonus feature ----------
        // showing short-circuiting: anyMatch() stops checking the moment it
        // finds ONE match, instead of scanning the whole list like filter() does.
        // this is more efficient when we only care about a yes/no answer
        boolean anyHighEarner = employees.stream()
                .anyMatch(emp -> emp.getSalary() > 90000);

        System.out.println("\nIs there anyone earning over $90,000? " + anyHighEarner);

        // bonus feature #2: grouping employees by department, just to show
        // streams can do more than the minimum the assignment asked for
        var byDepartment = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));

        System.out.println("\nEmployees grouped by department:");
        for (String dept : byDepartment.keySet()) {
            System.out.println(dept + ": " + byDepartment.get(dept));
        }
    }
}