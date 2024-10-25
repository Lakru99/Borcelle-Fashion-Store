package entity;

import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ItemEntity {
    private String itemCode;
    private String itemDescription;
    private String itemSize;
    private double itemPrice;
    private Integer itemQty;
}
