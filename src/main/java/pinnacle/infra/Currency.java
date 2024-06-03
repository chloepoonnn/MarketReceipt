package pinnacle.infra;

import lombok.Getter;

@Getter
public enum Currency {
  NY(0.08875), //
  CA(0.0975);

  private Double taxRate;

  Currency(double taxRate) {
    this.taxRate = taxRate;
  }
}
