package kz.aks.sutdentmanager.model;

import kz.aks.sutdentmanager.db.DB;
import lombok.*;


@Getter
@Setter
public class Student {
    private static Long initialId = 0L;
    private Long id;
    private String name;
    private String surname;
    private int exam;
    private String mark;

    public Student(String name, String surname, int exam) {
        id = ++initialId;
        this.name = name;
        this.surname = surname;
        this.exam = exam;
        this.mark = DB.getMarkOfStudent(exam);
    }
    public Student() {
        id = ++initialId;
    }
}
