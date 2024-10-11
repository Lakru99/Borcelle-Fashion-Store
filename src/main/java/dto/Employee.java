package dto;

import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Employee {
    private String empId;
    private String empName;
    private String empCompany;
    private Double empSalary;
    private String empEmail;
    private String empPassword;
}
