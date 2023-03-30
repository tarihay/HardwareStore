package ru.nsu.ccfit.db.hardwarestore.model.entities.productRelated;

import jakarta.persistence.*;
import ru.nsu.ccfit.db.hardwarestore.model.entities.orderRelated.OrderItemsEntity;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Product")
public class ProductEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String serialNumber;

    @Column
    private String manufacturer;

    @Column
    private Long price;

    @Column
    private Long amount;

    @ManyToOne
    @JoinColumn(name = "id")
    private ProductTypeEntity productType;

    @OneToMany(mappedBy = "product")
    private Set<ProductValuesEntity> productValues = new HashSet<>();

    @OneToMany(mappedBy = "product")
    private Set<OrderItemsEntity> orderItems = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public ProductTypeEntity getProductType() {
        return productType;
    }

    public void setProductType(ProductTypeEntity productType) {
        this.productType = productType;
    }

    public Set<ProductValuesEntity> getProductValues() {
        return productValues;
    }

    public void setProductValues(Set<ProductValuesEntity> productValues) {
        this.productValues = productValues;
    }

    public Set<OrderItemsEntity> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrderItemsEntity> orderItems) {
        this.orderItems = orderItems;
    }
}
