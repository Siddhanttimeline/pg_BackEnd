package com.management.pg.App.payload;

import java.time.LocalDate;

import lombok.Data;

@Data
public class PaymentDTO {

    private int id;

    private LocalDate paymentDate;

    private double amount;

    private boolean paymentStatus;

    private int month;

    private int year;	
}
