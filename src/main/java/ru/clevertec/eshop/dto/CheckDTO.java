package ru.clevertec.eshop.dto;

import ru.clevertec.eshop.model.product.Product;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class CheckDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = -3186130520949133588L;
    private Long id;
    private int shopId;
    private LocalDateTime dateTime;
    private List<Product> products;
    private BigDecimal productsSumAfterPromo;
    private BigDecimal checkSumBeforeCard;
    private double discountFromCard;
    private BigDecimal totalSum;

    public CheckDTO() {
    }

    public CheckDTO(Long id, LocalDateTime dateTime, List<Product> products, BigDecimal productsSumAfterPromo,
                    BigDecimal checkSumBeforeCard, double discountFromCard, BigDecimal totalSum, int shopId) {
        this.id = id;
        this.dateTime = dateTime;
        this.products = products;
        this.productsSumAfterPromo = productsSumAfterPromo;
        this.checkSumBeforeCard = checkSumBeforeCard;
        this.discountFromCard = discountFromCard;
        this.totalSum = totalSum;
        this.shopId = shopId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public BigDecimal getProductsSumAfterPromo() {
        return productsSumAfterPromo;
    }

    public void setProductsSumAfterPromo(BigDecimal productsSumAfterPromo) {
        this.productsSumAfterPromo = productsSumAfterPromo;
    }

    public BigDecimal getCheckSumBeforeCard() {
        return checkSumBeforeCard;
    }

    public void setCheckSumBeforeCard(BigDecimal checkSumBeforeCard) {
        this.checkSumBeforeCard = checkSumBeforeCard;
    }

    public double getDiscountFromCard() {
        return discountFromCard;
    }

    public void setDiscountFromCard(double discountFromCard) {
        this.discountFromCard = discountFromCard;
    }

    public BigDecimal getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(BigDecimal totalSum) {
        this.totalSum = totalSum;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CheckDTO checkDTO = (CheckDTO) o;
        return Double.compare(checkDTO.discountFromCard, discountFromCard) == 0 &&
                Objects.equals(id, checkDTO.id) && Objects.equals(dateTime, checkDTO.dateTime) &&
                Objects.equals(products, checkDTO.products) &&
                Objects.equals(productsSumAfterPromo, checkDTO.productsSumAfterPromo) &&
                Objects.equals(checkSumBeforeCard, checkDTO.checkSumBeforeCard) &&
                Objects.equals(totalSum, checkDTO.totalSum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateTime, products, productsSumAfterPromo, checkSumBeforeCard, discountFromCard, totalSum);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", products=" + products +
                ", productsSumAfterPromo=" + productsSumAfterPromo +
                ", checkSumBeforeCard=" + checkSumBeforeCard +
                ", discountFromCard=" + discountFromCard +
                ", totalSum=" + totalSum +
                "; ";
    }
}
