package kz.aks.sutdentmanager.db;

import kz.aks.sutdentmanager.model.Student;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class DB {
    private static List<Student> students = new ArrayList<>(List.of(
            new Student("Akseleu", "Maksat", 99),
            new Student("Karlygash", "Berkali", 90),
            new Student("Berik", "Aidar", 70),
            new Student("Ergali", "Sargali", 50)));

    public static void addStudent(Student student) {
        students.add(student);
    }

    public static List<Student> getAllStudents() {
        return students;
    }

    public static String getMarkOfStudent(int exam) {
        if(exam<25)return "F";
        else if(exam<50 && exam>=25) return "D";
        else if (exam<70 && exam>=50) return "C";
        else if(exam<90 && exam>=70) return "B";
        else return "A";
    }
}
