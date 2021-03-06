package com.binar.binar.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "supplier")
public class Supplier extends AbstractDate implements Serializable {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nama", nullable = false, length = 45)
    private String nama;

    @Pattern(regexp ="^\\+?[0-9. ()-]{7,25}$", message = "Phone number")
    @Column(name = "hp", nullable = false, length = 15)
    private String hp;

    @Column(name = "alamat", columnDefinition="TEXT")
    private String alamat;


    @OneToMany(mappedBy = "supplier", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Barang> barang;



}