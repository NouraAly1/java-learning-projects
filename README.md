# Java Learning Projects

Java programs I am building while preparing for software engineering internships.

This repository is a public record of how I learn: start with the language fundamentals, then move into object-oriented design, data structures, and small applications that I can talk through in interviews.

The programs here were written over the past two months, at a pace of about one to two projects a week. I am publishing them together so the repo is a clear picture of that practice.

## Why this repo exists

I want recruiters and interviewers to see more than a list of topics. Each program here is something I wrote, ran, and can explain: inputs, logic, and output.

This README is the index: what each program does, which Java concepts it uses, and how to run it.

## How to run

**Single-file programs** (from the repo root):

```bash
javac BMI.java
java BMI
```

**Folder projects that use a package** (also from the repo root):

```bash
javac CourseSystem/*.java
java CourseSystem.Main
```

## Current projects

### Fundamentals

| Project | What it does | Java concepts |
| --- | --- | --- |
| [BMI Calculator](BMI.java) | Reads name, age, weight (kg), and height (m), then prints a summary and BMI to two decimal places | `Scanner`, primitives, arithmetic, `printf` |
| [Grading Scale](GradingScale.java) | Converts a 0–100 score into a letter grade | `if` / `else if`, input validation |
| [Ternary Operator](Ternary_Operator.java) | Maps a Celsius temperature to a weather label | ternary operator `? :` |
| [Quiz Game](QuizGame.java) | Five multiple-choice questions, then a score as a percentage | loops, conditionals, scoring |
| [Text Analysis Tool](TextAnalysisTool.java) | Analyzes a paragraph (length, words, character counts) | `String` methods, `HashMap`, validation |
| [Library System](librarySystem.java) | Menu to add books, update quantity, and search a small catalog | arrays, menus, `while` loops |
| [Stock Analyzer](StockAnalyzer.java) | Average, max, and related stats for stock prices | arrays vs `ArrayList`, method overloading |
| [Payment Example](PaymentExample.java) | Charges through `CreditCard` or `PayPal` using one `Payment` type | interfaces, polymorphism |

### Object-oriented projects

| Project | What it does | Java concepts |
| --- | --- | --- |
| [Product](Product.java) | Creates product objects and applies a discount | classes, constructors, methods |
| [Student Record System](StudentRecordSystem/) | Console menu to add, update, and view students | encapsulation, `ArrayList`, input handling |
| [Course System](CourseSystem/) | Enroll students in courses, assign grades, update/remove records | multiple classes, `static` catalog, `HashMap` |
| [Student Management System](StudentManagementSystem/) | GUI to manage students and course grades | Swing GUI, `ArrayList`, `HashMap` |
| [Car Rental System](CarRentalSystem/) | Add cars, motorcycles, and trucks to one vehicle list | interfaces |
| [Employee Stream Project](EmployeeStreamProject/) | Filter and analyze a list of employees | Java Streams, lambdas, `ArrayList` |

### Run the larger projects

```bash
# Student Record System
javac StudentRecordSystem/*.java
java StudentRecordSystem.Main

# Course System
javac CourseSystem/*.java
java CourseSystem.Main

# Student Management System (opens a window)
javac StudentManagementSystem/*.java
java StudentManagementSystem.StudentManagementGUI

# Car Rental System
cd CarRentalSystem
javac *.java
java RentalApp

# Employee Stream Project
cd EmployeeStreamProject
javac *.java
java EmployeeAnalyzer
```

## Skills this repo shows

- Console input and formatted output
- Conditionals, loops, and input validation
- Classes, constructors, and encapsulation
- Interfaces and polymorphism
- Arrays, `ArrayList`, and `HashMap`
- A small Swing GUI
- Java Streams

## How to browse this repository

1. Start with this README for the project list.
2. Open a `.java` file (or a project folder) to see the code and comments.
3. Run the program locally with `javac` and `java`.

I keep each project small enough to read in one sitting, with comments that explain the important steps the same way I would in an interview.

## About me

I am a Computer Science bachelor's student graduating in 2027, with a current GPA of 4.0. I am applying for internships and using this repository to show consistent practice in Java. If you are reviewing my application, the latest projects in this repo are the best picture of where I am right now.

Java is my main preferred language. I am using it now to learn data structures and algorithms and Database I (almost complete). I also know Python at an intermediate level; that work is in [My_Journey_Learning_Python](https://github.com/NouraAly1/My_Journey_Learning_Python). I am also studying communications and networking.
