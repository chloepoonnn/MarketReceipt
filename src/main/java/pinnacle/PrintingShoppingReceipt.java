package pinnacle;


import pinnacle.infra.ShoppingItem;
import pinnacle.infra.ShoppingReceipt;

public class PrintingShoppingReceipt {

  public static void main(String[] args) {
    ShoppingReceipt shoppingList1 = new ShoppingReceipt();

    ShoppingItem item1_1 = new ShoppingItem("book", 1, 17.99);
    ShoppingItem item1_2 = new ShoppingItem("potato chips", 1, 3.99);

    shoppingList1.addShoppingList(item1_1);
    shoppingList1.addShoppingList(item1_2);


    shoppingList1.printReceipt("CA");

    System.out.println("");

    ShoppingReceipt receipt2 = new ShoppingReceipt();

    ShoppingItem item2_1 = new ShoppingItem("book", 1, 17.99);
    ShoppingItem item2_2 = new ShoppingItem("pencil", 3, 2.99);

    receipt2.addShoppingList(item2_1);
    receipt2.addShoppingList(item2_2);

    receipt2.printReceipt("NY");

    System.out.println("");

    ShoppingReceipt receipt3 = new ShoppingReceipt();

    ShoppingItem item3_1 = new ShoppingItem("pencil", 2, 2.99);
    ShoppingItem item3_2 = new ShoppingItem("shirt", 1, 29.99);

    receipt3.addShoppingList(item3_1);
    receipt3.addShoppingList(item3_2);

    receipt3.printReceipt("NY");
  }
}
