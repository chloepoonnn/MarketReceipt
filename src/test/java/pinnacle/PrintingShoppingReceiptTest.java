package pinnacle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import pinnacle.infra.ShoppingItem;
import pinnacle.infra.ShoppingReceipt;

public class PrintingShoppingReceiptTest {

    @Test
    void testCalculateSubtotal() {
        List<ShoppingItem> shoppingList = new ArrayList<>();
        shoppingList.add(new ShoppingItem("book", 1, 17.99));
        shoppingList.add(new ShoppingItem("pen", 1, 2.99));
        double expectedSubtotal = 21.98;
        double actualSubtotal = ShoppingReceipt.calculateSubtotal(shoppingList);
        assertEquals(expectedSubtotal, actualSubtotal);
    }

    @Test
    void testCalculateSalesTax_NY() {
        String location = "NY";
        List<ShoppingItem> shoppingList = new ArrayList<>();
        shoppingList.add(new ShoppingItem("book", 1, 17.99));
        shoppingList.add(new ShoppingItem("pencil", 3, 2.99));
        double expectedTax = 2.40;
        double actualSubtotal =
                ShoppingReceipt.calculateSalesTax(location, shoppingList);
        assertEquals(expectedTax, actualSubtotal);
    }

    @Test
    void testCalculateSalesTax_CA() {
        String location = "CA";
        List<ShoppingItem> shoppingList = new ArrayList<>();
        shoppingList.add(new ShoppingItem("book", 1, 17.99));
        shoppingList.add(new ShoppingItem("pencil", 1, 2.99));
        double expectedTax = 1.80;
        double actualSubtotal =
                ShoppingReceipt.calculateSalesTax(location, shoppingList);
        assertEquals(expectedTax, actualSubtotal);
    }

    @Test
    void testCalculateSalesTax_EmptyList() {
        List<ShoppingItem> shoppingList = new ArrayList<>();
        assertThrows(IllegalArgumentException.class,
                () -> ShoppingReceipt.calculateSalesTax("CA", shoppingList));
    }

    @Test
    void testPrintReceipt() {
        ShoppingReceipt shoppingReceipt = new ShoppingReceipt();
        shoppingReceipt.addShoppingList(new ShoppingItem("shirt", 1, 29.99));
        shoppingReceipt.addShoppingList(new ShoppingItem("pencil", 2, 2.99));
        shoppingReceipt.printReceipt("NY");
    }
}
