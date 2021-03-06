package com.binar.binar.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;//wajib untuk mysql
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "barang")
@Where(clause = "deleted_date is null")
public class Barang extends AbstractDate implements Serializable  {

    @Id  // menyatakan primary key
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) // automatis  ID ++,auto:  klmhahan seq make bersama.
    private Long id;

    @NotBlank(message = "Nama is mandatory")
    @Column(name = "nama", nullable = false, length = 45)
    private String nama;

//    @NotBlank(message = "Stok is mandatory") : tidak bisa validasi integer: hanya bisa pada string
    @Column(name = "stok", nullable = false, length = 11)
    private int stok;

    @NotBlank(message = "Satuan is mandatory")
    @Column(name = "satuan", nullable = false, length = 45)
    private String satuan;

//    @NotBlank(message = "Harga is mandatory")
    @Column(name = "harga", nullable = true, length = 11)
    private int harga;

    // langkah 1: bisa save atau update : buat nampung direktori yang diupload
    @Column(name = "filenama", nullable = true, columnDefinition="TEXT")
    private String filenama;
//    step 1 : panggil rest api buat upload
//            ambil response namafile  filenama.png
//    {
//        "nama":"ini nama",
//        "satuan":"pcs",
//        "filename":"filenama.png" : update, insert
//    }


    @JsonIgnore
    @ManyToOne(targetEntity = Supplier.class, cascade = CascadeType.ALL)
    private Supplier supplier;//ok

    @JsonIgnore
    @OneToMany(mappedBy = "barang")
    private List<Transaksi> transaksi;

    //
//    @Column
//    @Temporal(TemporalType.DATE)
//    @JsonFormat(pattern="dd-MM-yyyy") //1997-10-10
//    private Date date;
//
//    @Column
//    @Temporal(TemporalType.TIMESTAMP)
//    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
//    private Date datetime;
//
//    @DateTimeFormat(pattern = "dd-mm-yyyy")
////    @Column(columnDefinition = "date")
//    public Date tanggalLahir;


    @OneToOne(mappedBy = "detailbarang")
    private BarangDetail detailbrg;

    @JsonIgnore
    @OneToMany(mappedBy = "barang")
    private List<JenisBarang> jenisbarang;

    }


