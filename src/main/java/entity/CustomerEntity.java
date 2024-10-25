package entity;

import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class CustomerEntity {
    private String customerId;
    private String customerName;
    private String customerContact;
    private String customerCity;

}
