package pinnacle.infra;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@AllArgsConstructor
@ToString
public class ShoppingItem {
  private String itemName;
  private int quantity;
  private double price;
}
