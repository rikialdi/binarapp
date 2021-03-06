package com.binar.binar.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;


@Setter
@Getter
@Entity
@Table(name = "transaksi")
public class Transaksi  extends AbstractDate  implements Serializable {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "barang_id")
    Barang barang;


    @ManyToOne
    @JoinColumn(name = "pembeli_id")
    Pembeli pembeli;
}
