package com.belikov.valteris.cycle.bicycle.model;

import com.belikov.valteris.cycle.detail.model.Detail;
import com.belikov.valteris.cycle.order.model.Order;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "bicycles")
public class Bicycle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "weight", nullable = false)
    private double weight;

    @Column(name = "num_of_speeds", nullable = false)
    private int numOfSpeeds;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "photo", nullable = false)
    private String photo;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToMany(mappedBy = "bicycles")
    private List<Order> orders;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "bicycle_detail",
            joinColumns = @JoinColumn(name = "bicycle_id"),
            inverseJoinColumns = @JoinColumn(name = "detail_id")
    )
    @JsonIgnoreProperties("bicycles")
    private List<Detail> details;
}
