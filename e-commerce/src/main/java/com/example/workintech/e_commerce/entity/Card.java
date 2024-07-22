package com.example.workintech.e_commerce.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="card",schema = "workintech")
public class Card {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="id")
    private long id;
    @OneToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},mappedBy = "card")
    private Order order;
    @Column(name="card_no")
    private String cardNo;

    @Column(name="expire_month")
    private int expireMonth;

    @Column(name="expire_year")
    private int expireYear;

    @Column(name="name_on_card")
    private String nameOnCard;


}
