package dto;

import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Supplier {
    private String supplierId;
    private String supplierName;
    private String supplierContact;
    private String supplierCompany;
    private String supplierItemCategory;
    private String supplierItemName;


}
