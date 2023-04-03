package com.example.be.dto;

import java.time.LocalDateTime;


public interface OderDetailDto {
    Long getQuantity();

    LocalDateTime getCreateDate();

    boolean isStatusPay();

    Double getPrice();

    String getNameProduct();

    String getUrl();

    Long getIdProduct();

}
