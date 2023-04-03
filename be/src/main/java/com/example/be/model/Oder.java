package com.example.be.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Oder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPay;
    private Integer codePay;
    private String namePay;
    private String addressPay;
    private String phonePay;
    private String emailPay;
    private Double totalCart;
    @OneToMany(mappedBy = "oder")
    @JsonBackReference
    public Set<OderDetail> oderDetailSet;
    @ManyToOne
    private Customer customer;
    private boolean flagDelete = false;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    private Date createDate;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_date")
    private Date modifyDate;
    @Column(columnDefinition = "text")
    private String note;
    public Oder() {
    }

    public Long getIdPay() {
        return idPay;
    }

    public void setIdPay(Long idPay) {
        this.idPay = idPay;
    }

    public Integer getCodePay() {
        return codePay;
    }

    public void setCodePay(Integer codePay) {
        this.codePay = codePay;
    }

    public String getNamePay() {
        return namePay;
    }

    public void setNamePay(String namePay) {
        this.namePay = namePay;
    }

    public String getAddressPay() {
        return addressPay;
    }

    public void setAddressPay(String addressPay) {
        this.addressPay = addressPay;
    }

    public String getPhonePay() {
        return phonePay;
    }

    public void setPhonePay(String phonePay) {
        this.phonePay = phonePay;
    }

    public String getEmailPay() {
        return emailPay;
    }

    public void setEmailPay(String emailPay) {
        this.emailPay = emailPay;
    }

    public Double getTotalCart() {
        return totalCart;
    }

    public void setTotalCart(Double totalCart) {
        this.totalCart = totalCart;
    }

    public Set<OderDetail> getOderDetailSet() {
        return oderDetailSet;
    }

    public void setOderDetailSet(Set<OderDetail> oderDetailSet) {
        this.oderDetailSet = oderDetailSet;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public boolean isFlagDelete() {
        return flagDelete;
    }

    public void setFlagDelete(boolean flagDelete) {
        this.flagDelete = flagDelete;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
