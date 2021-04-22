package com.belikov.valteris.cycle.place.model;

import com.belikov.valteris.cycle.order.model.Order;
import java.util.List;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "places")
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "place", nullable = false)
    private String place;

    @Column(name = "lat", nullable = false)
    private double lat;

    @Column(name = "len", nullable = false)
    private double len;

    @OneToMany
    private List<Order> orders;
}
