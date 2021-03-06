package com.binar.binar.repository;

import com.binar.binar.entity.Barang;
import com.binar.binar.entity.Pembeli;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PembeliRepo extends JpaRepository<Pembeli, Long> {
    @Query("select c from Pembeli c")// nama class
    public List<Pembeli> getList();
    @Query("select c from Pembeli c WHERE c.id = :id")
    public Pembeli getbyID(@Param("id") Long id);
}
