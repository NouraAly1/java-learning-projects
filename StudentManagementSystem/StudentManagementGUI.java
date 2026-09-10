package StudentManagementSystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Map;

public class StudentManagementGUI extends JFrame {

    // acting as our "database" since we don't have a real one hooked up
    private ArrayList<Student> studentList = new ArrayList<>();
    private int nextStudentId = 1;

    private String[] availableCourses = {
        "Java Programming", "Data Structures", "Web Development",
        "Database Systems", "Computer Networks"
    };

    // Students tab
    private JTable studentTable;
    private DefaultTableModel studentTableModel;

    // Enrollment tab
    private JComboBox<String> courseDropdown;
    private DefaultListModel<Student> eligibleListModel;
    private JList<Student> eligibleStudentsList;
    private JTable enrollmentTable;
    private DefaultTableModel enrollmentTableModel;

    // Grades tab
    private JComboBox<Student> studentDropdownGrades;
    private JTable gradesTable;
    private DefaultTableModel gradesTableModel;
    private JComboBox<String> courseDropdownForGrade;
    private JComboBox<String> gradeValueDropdown;

    public StudentManagementGUI() {
        setTitle("Student Management System");
        setSize(750, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Students", buildStudentPanel());
        tabs.addTab("Course Enrollment", buildEnrollmentPanel());
        tabs.addTab("Grades", buildGradesPanel());

        setJMenuBar(buildMenuBar());
        add(tabs);
    }

    // ---------- MENU BAR ----------

    private JMenuBar buildMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu studentMenu = new JMenu("Student");

        JMenuItem addItem = new JMenuItem("Add Student");
        addItem.addActionListener(e -> openAddStudentForm());

        JMenuItem updateItem = new JMenuItem("Update Student");
        updateItem.addActionListener(e -> openUpdateStudentForm());

        JMenuItem viewItem = new JMenuItem("View Student Details");
        viewItem.addActionListener(e -> viewSelectedStudentDetails());

        studentMenu.add(addItem);
        studentMenu.add(updateItem);
        studentMenu.add(viewItem);
        menuBar.add(studentMenu);
        return menuBar;
    }

    // ---------- STUDENTS TAB ----------

    private JPanel buildStudentPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] columnNames = {"ID", "Name", "Age", "Email"};
        studentTableModel = new DefaultTableModel(columnNames, 0) {
            public boolean isCellEditable(int row, int col) {
                return false; // don't let people type directly into the table
            }
        };
        studentTable = new JTable(studentTableModel);
        panel.add(new JScrollPane(studentTable), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton addButton = new JButton("Add Student");
        JButton updateButton = new JButton("Update Student");
        JButton viewButton = new JButton("View Details");

        addButton.addActionListener(e -> openAddStudentForm());
        updateButton.addActionListener(e -> openUpdateStudentForm());
        viewButton.addActionListener(e -> viewSelectedStudentDetails());

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(viewButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private void openAddStudentForm() {
        JTextField nameField = new JTextField();
        JTextField ageField = new JTextField();
        JTextField emailField = new JTextField();

        JPanel form = new JPanel(new GridLayout(3, 2, 5, 5));
        form.add(new JLabel("Name:"));
        form.add(nameField);
        form.add(new JLabel("Age:"));
        form.add(ageField);
        form.add(new JLabel("Email:"));
        form.add(emailField);

        int result = JOptionPane.showConfirmDialog(this, form, "Add New Student",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                String name = nameField.getText().trim();
                String email = emailField.getText().trim();

                if (name.isEmpty() || email.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Name and Email can't be empty.",
                            "Missing Info", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int age = Integer.parseInt(ageField.getText().trim());

                Student newStudent = new Student(nextStudentId, name, age, email);
                nextStudentId++;
                studentList.add(newStudent);
                refreshStudentTable();

                JOptionPane.showMessageDialog(this, "Student added successfully!");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Age must be a number.",
                        "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void openUpdateStudentForm() {
        int row = studentTable.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a student from the table first.",
                    "No Student Selected", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int studentId = (int) studentTableModel.getValueAt(row, 0);
        Student student = findStudentById(studentId);
        if (student == null) return;

        JTextField nameField = new JTextField(student.getName());
        JTextField ageField = new JTextField(String.valueOf(student.getAge()));
        JTextField emailField = new JTextField(student.getEmail());

        JPanel form = new JPanel(new GridLayout(3, 2, 5, 5));
        form.add(new JLabel("Name:"));
        form.add(nameField);
        form.add(new JLabel("Age:"));
        form.add(ageField);
        form.add(new JLabel("Email:"));
        form.add(emailField);

        int result = JOptionPane.showConfirmDialog(this, form, "Update Student",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                String name = nameField.getText().trim();
                String email = emailField.getText().trim();
                if (name.isEmpty() || email.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Name and Email can't be empty.",
                            "Missing Info", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int age = Integer.parseInt(ageField.getText().trim());

                student.setName(name);
                student.setAge(age);
                student.setEmail(email);
                refreshStudentTable();

                JOptionPane.showMessageDialog(this, "Student updated!");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Age must be a number.",
                        "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void viewSelectedStudentDetails() {
        int row = studentTable.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a student first.",
                    "No Student Selected", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int studentId = (int) studentTableModel.getValueAt(row, 0);
        Student student = findStudentById(studentId);
        if (student == null) return;

        StringBuilder details = new StringBuilder();
        details.append("ID: ").append(student.getId()).append("\n");
        details.append("Name: ").append(student.getName()).append("\n");
        details.append("Age: ").append(student.getAge()).append("\n");
        details.append("Email: ").append(student.getEmail()).append("\n\n");
        details.append("Enrolled Courses:\n");

        if (student.getCourseGrades().isEmpty()) {
            details.append("  Not enrolled in any course yet.");
        } else {
            for (Map.Entry<String, String> entry : student.getCourseGrades().entrySet()) {
                details.append("  - ").append(entry.getKey()).append(" : ").append(entry.getValue()).append("\n");
            }
        }

        JOptionPane.showMessageDialog(this, details.toString(), "Student Details",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private Student findStudentById(int id) {
        for (Student s : studentList) {
            if (s.getId() == id) return s;
        }
        return null;
    }

    private void refreshStudentTable() {
        studentTableModel.setRowCount(0); // wipe the table, then rebuild it from scratch
        for (Student s : studentList) {
            studentTableModel.addRow(new Object[]{s.getId(), s.getName(), s.getAge(), s.getEmail()});
        }
        refreshStudentDropdown(); // keeps the Grades tab dropdown in sync
    }

    // ---------- COURSE ENROLLMENT TAB ----------

    private JPanel buildEnrollmentPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(new JLabel("Select Course:"));
        courseDropdown = new JComboBox<>(availableCourses);
        topPanel.add(courseDropdown);
        panel.add(topPanel, BorderLayout.NORTH);

        eligibleListModel = new DefaultListModel<>();
        eligibleStudentsList = new JList<>(eligibleListModel);
        JScrollPane listScroll = new JScrollPane(eligibleStudentsList);
        listScroll.setBorder(BorderFactory.createTitledBorder("Eligible Students"));

        String[] enrollColumns = {"Student Name", "Course"};
        enrollmentTableModel = new DefaultTableModel(enrollColumns, 0) {
            public boolean isCellEditable(int row, int col) { return false; }
        };
        enrollmentTable = new JTable(enrollmentTableModel);
        JScrollPane enrollScroll = new JScrollPane(enrollmentTable);
        enrollScroll.setBorder(BorderFactory.createTitledBorder("Current Enrollments"));

        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        centerPanel.add(listScroll);
        centerPanel.add(enrollScroll);
        panel.add(centerPanel, BorderLayout.CENTER);

        JButton enrollButton = new JButton("Enroll Selected Student");
        enrollButton.addActionListener(e -> enrollStudentInCourse());
        panel.add(enrollButton, BorderLayout.SOUTH);

        courseDropdown.addActionListener(e -> refreshEligibleList());

        return panel;
    }

    private void refreshEligibleList() {
        eligibleListModel.clear();
        String selectedCourse = (String) courseDropdown.getSelectedItem();
        if (selectedCourse == null) return;

        for (Student s : studentList) {
            if (!s.getCourseGrades().containsKey(selectedCourse)) {
                eligibleListModel.addElement(s);
            }
        }
    }

    private void enrollStudentInCourse() {
        Student selectedStudent = eligibleStudentsList.getSelectedValue();
        String selectedCourse = (String) courseDropdown.getSelectedItem();

        if (selectedStudent == null) {
            JOptionPane.showMessageDialog(this, "Please pick a student from the eligible list.",
                    "No Student Selected", JOptionPane.WARNING_MESSAGE);
            return;
        }

        selectedStudent.enroll(selectedCourse);
        refreshEligibleList();
        refreshEnrollmentTable();

        JOptionPane.showMessageDialog(this, selectedStudent.getName() + " enrolled in " + selectedCourse + "!");
    }

    private void refreshEnrollmentTable() {
        enrollmentTableModel.setRowCount(0);
        for (Student s : studentList) {
            for (String course : s.getCourseGrades().keySet()) {
                enrollmentTableModel.addRow(new Object[]{s.getName(), course});
            }
        }
    }

    // ---------- GRADES TAB ----------

    private JPanel buildGradesPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(new JLabel("Select Student:"));
        studentDropdownGrades = new JComboBox<>();
        topPanel.add(studentDropdownGrades);
        panel.add(topPanel, BorderLayout.NORTH);

        String[] gradeColumns = {"Course", "Grade"};
        gradesTableModel = new DefaultTableModel(gradeColumns, 0) {
            public boolean isCellEditable(int row, int col) { return false; }
        };
        gradesTable = new JTable(gradesTableModel);
        panel.add(new JScrollPane(gradesTable), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.add(new JLabel("Course:"));
        courseDropdownForGrade = new JComboBox<>(availableCourses);
        bottomPanel.add(courseDropdownForGrade);

        bottomPanel.add(new JLabel("Grade:"));
        gradeValueDropdown = new JComboBox<>(new String[]{"A", "B", "C", "D", "F"});
        bottomPanel.add(gradeValueDropdown);

        JButton assignButton = new JButton("Assign Grade");
        assignButton.addActionListener(e -> assignGradeToStudent());
        bottomPanel.add(assignButton);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        studentDropdownGrades.addActionListener(e -> refreshGradesTable());

        return panel;
    }

    private void assignGradeToStudent() {
        Student selectedStudent = (Student) studentDropdownGrades.getSelectedItem();
        String selectedCourse = (String) courseDropdownForGrade.getSelectedItem();
        String grade = (String) gradeValueDropdown.getSelectedItem();

        if (selectedStudent == null) {
            JOptionPane.showMessageDialog(this, "Please select a student first.",
                    "No Student Selected", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!selectedStudent.getCourseGrades().containsKey(selectedCourse)) {
            JOptionPane.showMessageDialog(this,
                    selectedStudent.getName() + " is not enrolled in " + selectedCourse + ".",
                    "Not Enrolled", JOptionPane.ERROR_MESSAGE);
            return;
        }

        selectedStudent.assignGrade(selectedCourse, grade);
        refreshGradesTable();

        JOptionPane.showMessageDialog(this, "Grade assigned!");
    }

    private void refreshGradesTable() {
        gradesTableModel.setRowCount(0);
        Student selectedStudent = (Student) studentDropdownGrades.getSelectedItem();
        if (selectedStudent == null) return;

        for (Map.Entry<String, String> entry : selectedStudent.getCourseGrades().entrySet()) {
            gradesTableModel.addRow(new Object[]{entry.getKey(), entry.getValue()});
        }
    }

    private void refreshStudentDropdown() {
        studentDropdownGrades.removeAllItems();
        for (Student s : studentList) {
            studentDropdownGrades.addItem(s);
        }
    }

    // ---------- MAIN ----------

    public static void main(String[] args) {
        StudentManagementGUI window = new StudentManagementGUI();
        window.setVisible(true);
    }
}
