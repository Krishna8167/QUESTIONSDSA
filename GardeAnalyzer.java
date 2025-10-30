import java.util.*;
import java.util.stream.Collectors;

class Student {
    private String name;
    private List<Integer> marks;

    public Student(String name, List<Integer> marks) {
        this.name = name;
        this.marks = marks;
    }

    public String getName() {
        return name;
    }

    public double getAverage() {
        return marks.stream().mapToInt(Integer::intValue).average().orElse(0.0);
    }

    public String getGrade() {
        double avg = getAverage();
        if (avg >= 90) return "A+";
        else if (avg >= 80) return "A";
        else if (avg >= 70) return "B";
        else if (avg >= 60) return "C";
        else return "F";
    }

    @Override
    public String toString() {
        return name + " | Avg: " + String.format("%.2f", getAverage()) + " | Grade: " + getGrade();
    }
}

class GradeAnalyzer {
    private List<Student> students = new ArrayList<>();

    public void addStudent(Student s) {
        students.add(s);
        System.out.println(" Added: " + s.getName());
    }

    public void showAllStudents() {
        System.out.println("\n🎓 Student Grades:");
        students.forEach(System.out::println);
    }

    public void showTopPerformers(int limit) {
        System.out.println("\n Top Performers:");
        students.stream()
                .sorted(Comparator.comparingDouble(Student::getAverage).reversed())
                .limit(limit)
                .forEach(System.out::println);
    }

    public void showFailedStudents() {
        System.out.println("\n Failed Students:");
        List<Student> failed = students.stream()
                .filter(s -> s.getGrade().equals("F"))
                .collect(Collectors.toList());

        if (failed.isEmpty()) System.out.println("All students passed!");
        else failed.forEach(System.out::println);
    }
}

public class StudentGradeAnalyzer {
    public static void main(String[] args) {
        GradeAnalyzer analyzer = new GradeAnalyzer();

        analyzer.addStudent(new Student("Alice", Arrays.asList(95, 90, 92)));
        analyzer.addStudent(new Student("Bob", Arrays.asList(72, 68, 75)));
        analyzer.addStudent(new Student("Charlie", Arrays.asList(55, 60, 58)));
        analyzer.addStudent(new Student("Diana", Arrays.asList(88, 84, 91)));
        analyzer.addStudent(new Student("Ethan", Arrays.asList(45, 50, 42)));

        analyzer.showAllStudents();
        analyzer.showTopPerformers(3);
        analyzer.showFailedStudents();
    }
}
