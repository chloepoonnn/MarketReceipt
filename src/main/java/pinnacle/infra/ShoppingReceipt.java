package pinnacle.infra;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ShoppingReceipt {

  private static Double subtotal = 0.0;
  private static Double tax = 0.0;
  private static Double total = 0.0;

  // private static final double NY_TAX_RATE = 0.08875;
  // private static final double CA_TAX_RATE = 0.0975;

  private List<ShoppingItem> shoppingList = new ArrayList<>();

  public void addShoppingList(ShoppingItem shoppingItem) {
    shoppingList.add(shoppingItem);
  }

  public static Double calculateSubtotal(List<ShoppingItem> shoppingList) {
    if (shoppingList.isEmpty()) {
      throw new IllegalArgumentException("Shopping list cannot be empty");
    }
    Double subtotal = 0.0;
    for (ShoppingItem item : shoppingList) {
      subtotal += item.getPrice() * item.getQuantity();
    }
    return subtotal;
  }

  public static Double calculateSalesTax(String location,
      List<ShoppingItem> shoppingList) {
    if (shoppingList.isEmpty()) {
      throw new IllegalArgumentException("Shopping list cannot be empty");
    }
    double taxRate;
    if (location.equalsIgnoreCase("NY")) {
      taxRate = Currency.NY.getTaxRate();
    } else if (location.equalsIgnoreCase("CA")) {
      taxRate = Currency.CA.getTaxRate();
    } else {
      throw new IllegalArgumentException("Unsupported location: " + location);
    }

    BigDecimal salesTax = BigDecimal.ZERO;
    BigDecimal scale = BigDecimal.valueOf(0.05);

    for (ShoppingItem item : shoppingList) {
      ProductCategory category =
          ProductCategory.getCategory(item.getItemName());

      if (location.equalsIgnoreCase("NY") && (category == ProductCategory.FOOD
          || category == ProductCategory.CLOTHING)) {
        continue;
      } else if (location.equalsIgnoreCase("CA")
          && category == ProductCategory.FOOD) {
        continue;
      } else {
        BigDecimal itemTax = BigDecimal.valueOf(item.getPrice())
            .multiply(BigDecimal.valueOf(item.getQuantity()))
            .multiply(BigDecimal.valueOf(taxRate))
            .setScale(2, RoundingMode.CEILING); 

        itemTax = itemTax.divide(scale, 0, RoundingMode.UP).multiply(scale);
        
        salesTax = salesTax.add(itemTax);
      }
    }
    return salesTax.doubleValue();
  }


  public void printReceipt(String location) {
    subtotal = calculateSubtotal(shoppingList);
    tax = calculateSalesTax(location, shoppingList);
    total = subtotal + tax;

    System.out.printf("%-13s %-11s %-3s%n", "Item", "Price", "Qty");
    for (ShoppingItem item : shoppingList) {
      System.out.printf("%-13s %-13s %-8s%n", item.getItemName(),
          item.getPrice(), item.getQuantity());
    }
    System.out.printf("%-22s $%-8.2f%n", "subtotal:", subtotal);
    System.out.printf("%-23s $%-8.2f%n", "tax:", tax);
    System.out.printf("%-22s $%-8.2f%n", "total:", total);

  }

}

