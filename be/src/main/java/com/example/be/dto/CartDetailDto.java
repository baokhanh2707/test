package com.example.be.dto;

public class CartDetailDto {
    private Long quantity;
    private Long idProduct;
    private Long idCustomer;
    private Long idCartDetail;
    private Double price;

    public CartDetailDto() {
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }
    public Long getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Long idCustomer) {
        this.idCustomer = idCustomer;
    }

    public Long getIdCartDetail() {
        return idCartDetail;
    }

    public void setIdCartDetail(Long idCartDetail) {
        this.idCartDetail = idCartDetail;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
