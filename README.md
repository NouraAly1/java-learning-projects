# Java Learning Projects

Java programs I am building while preparing for software engineering internships.

This repository is a public record of how I learn: start with the language fundamentals, then move into object-oriented design, data structures, and small applications that I can talk through in interviews.

## Why this repo exists

I want recruiters and interviewers to see more than a list of topics. Each program here is something I wrote, ran, and can explain: inputs, logic, and output.

As I add projects, this README will stay the index: what the program does, which Java concepts it uses, and how to run it.

## Current projects

| Project | What it does | Java concepts |
| --- | --- | --- |
| [BMI Calculator](BMI.java) | Console program that reads name, age, weight (kg), and height (m), then prints a summary and BMI to two decimal places | `Scanner` input, primitive types, arithmetic, `printf` formatting |

### BMI Calculator

A first console program that takes user input and applies the standard BMI formula:

`BMI = weight (kg) / height (m)²`

**Run it**

```bash
javac BMI.java
java BMI
```

You will be prompted for name, age, weight, and height. The program then prints the entered values and the calculated BMI.

## Skills I am building toward

What is already in the repo:

- Writing a complete Java class with a `main` method
- Reading keyboard input
- Using `int`, `double`, and `String`
- Formatting numeric output

What I plan to add next (and will update this table as projects land):

| Focus | Example directions |
| --- | --- |
| Object-oriented Java | Classes, constructors, encapsulation, inheritance |
| Control flow and validation | Conditionals, loops, checking bad input |
| Collections and data structures | Arrays, `ArrayList`, maps, simple algorithms |
| Problem-solving | Small programs I can walk through on a whiteboard or in a coding interview |
| Larger apps | Multi-file programs, file I/O, and eventually a small backend or CLI tool |

## How to browse this repository

1. Start with this README for the project list.
2. Open a `.java` file to see the code and comments.
3. Run the program locally with `javac` and `java`.

I will keep each project small enough to read in one sitting, with comments that explain the important steps, the same way I would explain it in an interview.

## About me

I am applying for internships and using this repository to show consistent practice in Java. If you are reviewing my application, the latest projects in this repo are the best picture of where I am right now.

*This README will grow as I upload more advanced work.*
