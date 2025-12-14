package kz.aks.sutdentmanager.controller;

import kz.aks.sutdentmanager.db.DB;
import kz.aks.sutdentmanager.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class StudentController {

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(name = "search_name", required = false) String searchName) {
        if (searchName != null && !searchName.isBlank()) {
            List<Student> filtered = DB.getAllStudents().stream()
                    .filter(s ->
                            s.getName().toLowerCase()
                                    .contains(searchName.toLowerCase()))
                    .toList();
            model.addAttribute("students", filtered);
        } else {
            model.addAttribute("students", DB.getAllStudents());
        }
        return "index";
    }

    @GetMapping("/add-student")
    public String addStudent() {
        return "add-student";
    }

    @PostMapping("/add-student")
    public String addStudent(@RequestParam("name") String name,
                             @RequestParam("surname") String surname,
                             @RequestParam("exam") int exam) {

        Student student = Student.builder()
                .name(name)
                .surname(surname)
                .exam(exam)
                .mark(DB.getMarkOfStudent(exam))
                .build();

        DB.addStudent(student);

        return "redirect:/";
    }
}