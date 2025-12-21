package kz.aks.sutdentmanager.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {
    private Long id;
    private String name;
    private String surname;
    private int exam;
    private String mark;
}
