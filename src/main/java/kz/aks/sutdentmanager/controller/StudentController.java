package kz.aks.sutdentmanager.controller;

import kz.aks.sutdentmanager.model.Student;
import kz.aks.sutdentmanager.servises.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(name = "search_name", required = false) String searchName) {
        if (searchName != null && !searchName.isBlank()) {
            List<Student> filtered = studentService.getAllStudents().stream()
                    .filter(s ->
                            s.getName().toLowerCase()
                                    .contains(searchName.toLowerCase()))
                    .toList();
            model.addAttribute("students", filtered);
        } else {
            model.addAttribute("students", studentService.getAllStudents());
        }
        return "index";
    }

    @GetMapping("/add-student")
    public String addStudent() {
        return "add-student";
    }

    @PostMapping("/add-student")
    public String addStudent(@RequestParam(name = "name") String name,
                             @RequestParam(name = "surname") String surname,
                             @RequestParam(name = "exam") int exam) {

        Student student = Student.builder()
                .name(name)
                .surname(surname)
                .exam(exam)
                .mark(getMarkOfStudent(exam))
                .build();
        studentService.addStudent(student);

        return "redirect:/";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        Student s = studentService.getStudentById(id);
        model.addAttribute("student", s);
        return "details";
    }

    @PostMapping("/details/{id}")
    public String details(@PathVariable Long id,
                          @RequestParam(name = "name") String name,
                          @RequestParam(name = "surname") String surname,
                          @RequestParam(name = "exam") int exam) {
        Student s = Student.builder()
                .id(id)
                .name(name)
                .surname(surname)
                .exam(exam)
                .mark(getMarkOfStudent(exam))
                .build();
        studentService.updateStudent(s);
        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        studentService.deleteStudentById(id);
        return "redirect:/";
    }

    private String getMarkOfStudent(int exam) {
        if (exam < 25) return "F";
        else if (exam < 50) return "D";
        else if (exam < 70) return "C";
        else if (exam < 90) return "B";
        else return "A";
    }
}