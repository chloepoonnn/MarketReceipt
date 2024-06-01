package pinnacle.infra;

import lombok.ToString;

@ToString
public enum ProductCategory {
  FOOD("potato chips", "fries"), //
  CLOTHING("shirt", "skirt"), //
  OTHER;

  private String[] keywords;

  ProductCategory(String... keywords) {
    this.keywords = keywords;
  }

  public static ProductCategory getCategory(String productName) {
    for (ProductCategory category : ProductCategory.values()) {
      for (String keyword : category.keywords) {
        if (productName.toLowerCase().contains(keyword)) {
          return category;
        }
      }
    }
    return OTHER;
  }
}

