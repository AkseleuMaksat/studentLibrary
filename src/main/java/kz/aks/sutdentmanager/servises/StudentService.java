package kz.aks.sutdentmanager.servises;

import kz.aks.sutdentmanager.model.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class StudentService {

    private final Connection connection;

    public List<Student> getAllStudents() {
        String sql = "SELECT * FROM t_students";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet res = statement.executeQuery()) {
            List<Student> students = new ArrayList<>();
            while (res.next()) {
                Student a = new Student();
                a.setId(res.getLong("id"));
                a.setName(res.getString("name"));
                a.setSurname(res.getString("surname"));
                a.setExam(res.getInt("exam"));
                a.setMark(res.getString("mark"));
                students.add(a);
            }

            return students;
        } catch (SQLException w) {
            throw new RuntimeException(w.getMessage());
        }
    }

    public boolean addStudent(Student student) {
        String sql = """
                INSERT INTO t_students (name, surname, exam, mark) VALUES (?, ?, ? ,? )
                """;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, student.getName());
            statement.setString(2, student.getSurname());
            statement.setInt(3, student.getExam());
            statement.setString(4, student.getMark());
            return statement.executeUpdate() == 1;
        } catch (SQLException w) {
            throw new RuntimeException(w.getMessage());
        }
    }

    public Student getStudentById(Long id) {
        String sql = "SELECT * FROM t_students WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            Student a = new Student();
            try (ResultSet res = statement.executeQuery()) {
                if (res.next()) {
                    a.setId(res.getLong("id"));
                    a.setName(res.getString("name"));
                    a.setSurname(res.getString("surname"));
                    a.setExam(res.getInt("exam"));
                    a.setMark(res.getString("mark"));
                }
            }
            return a;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateStudent(Student student) {
        String sql = """
                UPDATE t_students
                SET name = ?, surname = ?, exam = ?, mark = ?
                WHERE id = ?
                """;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, student.getName());
            statement.setString(2, student.getSurname());
            statement.setInt(3, student.getExam());
            statement.setString(4, student.getMark());
            statement.setLong(5, student.getId());

            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteStudentById(Long id) {
        String sql = "DELETE FROM t_students WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            return statement.executeUpdate() == 1;
        } catch (SQLException w) {
            throw new RuntimeException(w.getMessage());
        }
    }

}