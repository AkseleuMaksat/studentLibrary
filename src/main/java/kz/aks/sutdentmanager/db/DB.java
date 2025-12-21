//package kz.aks.sutdentmanager.db;
//
//import kz.aks.sutdentmanager.model.Student;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class DB {
//    private static Long initialId = 0L;
//    private static final List<Student> students = new ArrayList<>(
//            List.of(
//                    new Student(++initialId, "Akseleu", "Maksat", 99),
//                    new Student(++initialId, "Karlygash", "Berkali", 90),
//                    new Student(++initialId, "Berik", "Aidar", 70),
//                    new Student(++initialId, "Ergali", "Sargali", 50))
//    );
//
//    public static void addStudent(Student student) {
//        student.setId(++initialId);
//        students.add(student);
//    }
//
//    public static List<Student> getAllStudents() {
//        return students;
//    }
//}