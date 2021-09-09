package com.binar.binar.service.impl;

import com.binar.binar.entity.Barang;
import com.binar.binar.repository.BarangRepo;
import com.binar.binar.service.BarangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
1. buat pakcgae impl
2. buat class BarangImpl di imple dari intercafe class
3. implement method
 */
@Service //service wajib
@Transactional // opsional :
public class BarangImpl implements BarangService {
    // // IOC DI
    @Autowired
    public BarangRepo repo;

    @Override
    public Map  getAll() {
        List<Barang> list = new ArrayList<Barang>();
        Map map = new HashMap();
        try {

            list = repo.getList();
            map.put("data", list);//data
            map.put("statusCode", 200);
            map.put("statusMessage", "Get Sukses");
            return map;//success

        } catch (Exception e) {
            e.printStackTrace();
            map.put("statusCode", "500");
            map.put("statusMessage", e);
            return map;// eror
        }
    }

    @Override
    public Map findByNama(String nama, Integer page, Integer size) {

        Map map = new HashMap();
        try {
            Pageable show_data = PageRequest.of(page, size);
            Page<Barang> list = repo.findByNama(nama, show_data);

            map.put("data", list);//data
            map.put("statusCode", 200);
            map.put("statusMessage", "Get Sukses");
            return map;//success

        } catch (Exception e) {
            e.printStackTrace();
            map.put("statusCode", "500");
            map.put("statusMessage", e);
            return map;// eror
        }
    }

    @Override
    public Page<Barang> findByNamaLike(String nama, Pageable pageable) {
        return null;
    }


    @Override
    public Map insert(Barang barang) {
        Map map = new HashMap();
        try {
  /*
    id = id ,
    nama  = \
    localho : port /api/path
   */
            Barang obj = repo.save(barang); //JPA
            map.put("data", obj);
            map.put("statusCode", "200");
            map.put("statusMessage", "Sukses");
            return map;// respon

        } catch (Exception e) {
            e.printStackTrace();
            map.put("statusCode", "500");
            map.put("statusMessage", e);
            return map;
        }
    }

    @Override
    public Map update(Barang barang) {
        Map map = new HashMap();
        try {

            Barang obj = repo.getbyID(barang.getId());
            // validasi is null return message eror
            if(obj == null ){
                map.put("statusCode", "404");
                map.put("statusMessage", "Data id tidak ditemukan");
                // kode stop
                return map;
            }

            obj.setNama(barang.getNama());
            obj.setHarga(obj.getHarga());
            obj.setSatuan(obj.getSatuan());
            obj.setStok(obj.getStok());

            repo.save(obj);//save
//            update barang
//                    set nama = "a"  // request
//                            where id = 1 //  chek by id
//            3 : simpan data
            map.put("data", obj);
            map.put("statusCode", "200");
            map.put("statusMessage", "Update Sukses");
            return map;

        } catch (Exception e) {
            e.printStackTrace();
            map.put("statusCode", "500");
            map.put("statusMessage", e);
            return map;
        }
    }

    @Override
    public Map delete(Long idbarang) {
        Map map = new HashMap();
        try {

            Barang obj = repo.getbyID(idbarang);
            if(obj == null){
                map.put("statusCode", "404");
                map.put("statusMessage", "data id tidak ditemuakan");
                return map;
            }

            repo.deleteById(obj.getId()); //provide JPA : delete permanen
            // sof delete : field isdate_delete : jika
            map.put("statusCode", "200");
            map.put("statusMessage", "Delete Sukses");
            return map;

        } catch (Exception e) {
            e.printStackTrace();
            map.put("statusCode", "500");
            map.put("statusMessage", e);
            return map;
        }
    }




}
