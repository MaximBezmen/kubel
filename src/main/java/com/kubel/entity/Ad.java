package com.kubel.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class Ad extends AbstractEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ad_id_seq")
    @SequenceGenerator(name = "ad_id_seq", sequenceName = "ad_id_seq", allocationSize = 1)
    private Long id;
    @Column(name = "topic")
    private String topic;
    @Column(name = "date_of_placement")
    private LocalDate dateOfPlacement;
    @Column(name = "validity")
    private LocalDate validity;
    @Column(name = "content")
    private String content;
    @Column(name = "city")
    private String city;
    @Column(name = "price")
    private Long price;
    @ManyToOne
    private Account account;
    @Column(name = "phone_number")
    private String phoneNumber;
    @OneToMany(mappedBy = "ad")
    private List<Message> messages;
    @Column(name = "active")
    private Boolean active;
    @Column(name = "photo_path")
    private String photoPath;


    @PrePersist
    private void setDateOfPlacementAndValidity(){
        dateOfPlacement = LocalDate.now();
        validity = dateOfPlacement.plusDays(31);
        active = true;
    }

}
