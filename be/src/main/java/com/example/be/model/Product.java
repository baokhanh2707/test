package com.example.be.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduct;
    private String nameProduct;
    @ManyToOne
    @JoinColumn(name ="model_year_id" )
    //năm sản xuất
    private YearProduct modelYear ;
    private Long amountExist;
    //động cơ
    private String engine;
    //số dặm
    private String mileage;
//    Nội địa
    private String interior;
//    Quyền lực
    private String power;
    //Giá
    private Double price;
    //ngoại thất
    private String exterior;
    private boolean flagDelete = false;
    @ManyToOne
    @JoinColumn(name ="type_product_id" )
    private TypeProduct typeProductId;
    @ManyToOne
    @JoinColumn(name ="status_product_id" )
    private StatusProduct statusProduct;
    @OneToMany(mappedBy = "product")
    private List<ImageProduct> imageProducts;
    @OneToMany(mappedBy = "product")
    @JsonBackReference
    public Set<OderDetail> oderDetailSet;
    @OneToMany(mappedBy = "product")
    @JsonBackReference
    private List<CartDetail> cartDetailList;

    public Product() {
    }

    public boolean isFlagDelete() {
        return flagDelete;
    }

    public void setFlagDelete(boolean flagDelete) {
        this.flagDelete = flagDelete;
    }

    public StatusProduct getStatusProduct() {
        return statusProduct;
    }

    public void setStatusProduct(StatusProduct statusProduct) {
        this.statusProduct = statusProduct;
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public Long getAmountExist() {
        return amountExist;
    }

    public void setAmountExist(Long amountExist) {
        this.amountExist = amountExist;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public String getInterior() {
        return interior;
    }

    public void setInterior(String interior) {
        this.interior = interior;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getExterior() {
        return exterior;
    }

    public void setExterior(String exterior) {
        this.exterior = exterior;
    }

    public TypeProduct getTypeProductId() {
        return typeProductId;
    }

    public void setTypeProductId(TypeProduct typeProductId) {
        this.typeProductId = typeProductId;
    }

    public List<ImageProduct> getImageProducts() {
        return imageProducts;
    }

    public void setImageProducts(List<ImageProduct> imageProducts) {
        this.imageProducts = imageProducts;
    }

    public Set<OderDetail> getOderDetailSet() {
        return oderDetailSet;
    }

    public void setOderDetailSet(Set<OderDetail> oderDetailSet) {
        this.oderDetailSet = oderDetailSet;
    }

    public List<CartDetail> getCartDetailList() {
        return cartDetailList;
    }

    public YearProduct getModelYear() {
        return modelYear;
    }

    public void setModelYear(YearProduct modelYear) {
        this.modelYear = modelYear;
    }

    public void setCartDetailList(List<CartDetail> cartDetailList) {
        this.cartDetailList = cartDetailList;
    }

}
