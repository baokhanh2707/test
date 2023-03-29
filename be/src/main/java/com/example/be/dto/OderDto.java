package com.example.be.dto;

import java.util.Date;

public interface OderDto {
    Long getIdPay();

    String getCodePay();

    String getNamePay();

    String getAddressPay();

    Double getTotalCart();

    Date getCreateDate();
}
