package com.example.be.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class OderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOderDetail;
    private Long quantity;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    @ManyToOne
    private Product product;
    @ManyToOne
    private Oder oder;
    private boolean flagDelete = false;
    private boolean statusShipping = false;
    private boolean statusPay = false;
    private Double discount;
    private Double price;

    public OderDetail() {
    }

    public Long getIdOderDetail() {
        return idOderDetail;
    }

    public void setIdOderDetail(Long idOderDetail) {
        this.idOderDetail = idOderDetail;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(LocalDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Oder getOder() {
        return oder;
    }

    public void setOder(Oder oder) {
        this.oder = oder;
    }

    public boolean isFlagDelete() {
        return flagDelete;
    }

    public void setFlagDelete(boolean flagDelete) {
        this.flagDelete = flagDelete;
    }

    public boolean isStatusShipping() {
        return statusShipping;
    }

    public void setStatusShipping(boolean statusShipping) {
        this.statusShipping = statusShipping;
    }

    public boolean isStatusPay() {
        return statusPay;
    }

    public void setStatusPay(boolean statusPay) {
        this.statusPay = statusPay;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
