package com.kubel.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class Message extends AbstractEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "message_id_seq")
    @SequenceGenerator(name = "message_id_seq", sequenceName = "message_id_seq", allocationSize = 1)
    private Long id;
    @Column(name = "message")
    private String message;
    @ManyToOne
    @JoinColumn(name = "ad_id", nullable = false)
    private Ad ad;
    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;
}
