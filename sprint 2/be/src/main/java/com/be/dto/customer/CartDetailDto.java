package com.be.dto.customer;

public class CartDetailDto {
    private Long quantity;
    private Long idLaptop;
    private Long idCustomer;
    private Long idCartDetail;

    public CartDetailDto() {
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getIdLaptop() {
        return idLaptop;
    }

    public void setIdLaptop(Long idLaptop) {
        this.idLaptop = idLaptop;
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
}
