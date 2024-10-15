package dto;

import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Item {
    private String itemCode;
    private String itemDescription;
    private String itemSize;
    private double itemPrice;
    private Integer itemQty;
}
