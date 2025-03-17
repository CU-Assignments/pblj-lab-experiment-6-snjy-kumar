implement Java program that uses lambda expressions and Stream API to filter students who scored above 75%, sort them by marks, and display their names.

Step 1: Create the Student Class
- Define a Student class with attributes:
    name (String)
    marks (double)
- Implement a constructor to initialize these values.
- Add a display method to print student details.

Step 2: Create the StudentFilterSort Class
- Create a list of students with sample data.
- Use Streams Class to:
      Filter students who scored above 75%.
      Sort students by marks in descending order.
      Collect the results into a new list.
- Use forEach() with a method reference to display results.ts.
-----------------------------------------------------------------------------------------------------------------------------------
    import java.util.*;
import java.util.stream.Collectors;

class Student {
    String name;
    double marks;
    
    public Student(String name, double marks) {
        this.name = name;
        this.marks = marks;
    }
    
    public void display() {
        System.out.println(name + " (Marks: " + marks + ")");
    }
}

public class StudentFilterSort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Student> students = new ArrayList<>();
        
        System.out.print("Enter number of students: ");
        int n = scanner.nextInt();
        scanner.nextLine();
        
        for (int i = 0; i < n; i++) {
            System.out.println("Enter details for Student " + (i + 1) + " (name, marks):");
            String name = scanner.nextLine();
            double marks = scanner.nextDouble();
            scanner.nextLine();
            students.add(new Student(name, marks));
        }
        
        System.out.println("\nFiltered and Sorted Students (Marks > 75%):");
        List<Student> filteredStudents = students.stream()
            .filter(s -> s.marks > 75)
            .sorted(Comparator.comparingDouble((Student s) -> s.marks).reversed()
                .thenComparing(s -> s.name))
            .collect(Collectors.toList());
            
        if (filteredStudents.isEmpty()) {
            System.out.println("No students scored above 75%.");
        } else {
            filteredStudents.forEach(Student::display);
        }
    }
}
----------------------------------------------------------------------------------------------------------------------------------------

Test Case	                        Input Data	                                                 Expected Output
Case 1:         Normal Case	Alice (80), Bob (72), Charlie (90), David (65), Eve (85)	         Charlie, Eve, Alice (Sorted by marks)
Case 2:         All Below 75%	Bob (70), David (60), Frank (65)	                               No output (Empty List)
Case 3:         Same Marks	Alice (80), Bob (80), Charlie (85)                                 Charlie, Alice, Bob (Sorted by marks, then by name)
Case 4:         Single Student Above 75%	Alice (60), Bob (50), Charlie (90)	                 Charlie (Only one student)
