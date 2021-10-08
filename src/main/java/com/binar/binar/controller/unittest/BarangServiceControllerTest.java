package com.binar.binar.controller.unittest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.binar.binar.entity.Barang;
import com.binar.binar.model.ModelBarang;
import com.binar.binar.repository.BarangRepo;
import com.binar.binar.service.BarangService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;
import java.util.List;
import java.util.Map;


public class BarangServiceControllerTest extends UnitTest{

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Autowired
    public BarangService serviceBarang;

    @Autowired
    public BarangRepo repo;

//    @Autowired
//    private TestRestTemplate restTemplate;
//    @Test
//    public void restTemplateListBarang() throws Exception {
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Accept", "*/*");
//        headers.set("Content-Type", "application/json");
//        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
//
//        ResponseEntity<String> exchange = restTemplate.exchange("http://localhost:8080/api/v1/rt/list" , HttpMethod.GET, entity, String.class);
//
//        assertEquals(HttpStatus.OK, exchange.getStatusCode());
//        System.out.println("response list barang="+exchange.getBody());
//
//    }
//    @Test
    public void chekUmur(){
     int umurAdik = 10;
     int umurKakak = 15;
     int selisih = umurKakak - umurAdik;
     int expexted = 5;
        System.out.println("umur selisih="+selisih);

     assertEquals(expexted,selisih);
    }


    @Test
    public void getProductsList() throws Exception {
        //call appi localhost
        String uri = "/v1/binar/listpage";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status); // unit test

        String content = mvcResult.getResponse().getContentAsString();
        System.out.println("Response=");
        System.out.println(content);
//        ModelBarang[] productlist =   super.mapFromJson(content, ModelBarang[].class);
//        assertTrue(productlist.length > 0);
//        int a =1;
//        int b=2;
//        assert
    }

    @Test
    public void count() throws Exception {
         Long count = repo.count();
         System.out.println("total ="+count);
    }

    @Test
    public void dtoModel() throws Exception {
        List<ModelBarang> count = repo.modelDTO();
        System.out.println("total ="+count);
        for(ModelBarang a : count){
            System.out.println("nama="+a.getNama());
        }
    }

    @Test
    public void getbyNamaNative() throws Exception {
       Object[] obj = repo.getbyNamaNative("webbinar 10");
        System.out.println("getbyNamaNative ="+obj);
    }

    @Test
    public void getDataAllNative() throws Exception {
        List<Object[]> obj = repo.getDataAllNative();
        for (Object[] s_detail : obj) {
            System.out.println("id ="+s_detail[0] + " nama="+s_detail[1]);
            ModelBarang dto = new ModelBarang(Long.parseLong(s_detail[0].toString()),s_detail[1].toString());
        }
    }


}
