package entities;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@EqualsAndHashCode
public class Product {
    private Integer productId;
    private String href;
}
