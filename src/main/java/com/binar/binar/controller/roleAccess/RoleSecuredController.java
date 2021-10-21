package com.binar.binar.controller.roleAccess;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/role-test-secured")
public class RoleSecuredController {



    @GetMapping("/list-barang")
    @ResponseBody
    @Secured ({"ROLE_READ","ROLE_USER"})
    public ResponseEntity<Map> getList() {
        Map map = new HashMap();
        map.put("data", "ROLE_READ");
        map.put("status", "sukses");
        map.put("code =", "200");
        return new ResponseEntity<Map>(map, HttpStatus.OK);
    }

    @GetMapping("/list-karyawan")
    @ResponseBody
    @Secured ({"ROLE_READ", "ROLE_WRITE"})
    public ResponseEntity<Map> geListKar() {
        Map map = new HashMap();
        map.put("data", "ROLE_READ");
        map.put("status", "sukses");
        map.put("code =", "200");
        return new ResponseEntity<Map>(map, HttpStatus.OK);
    }


    @GetMapping("/post-user")
    @ResponseBody
    @Secured ({"ROLE_ADMIN"})
    public ResponseEntity<Map> ROLE_ADMIN() {
        Map map = new HashMap();
        map.put("data", "ROLE_ADMIN");
        map.put("status", "sukses");
        map.put("code =", "200");
        return new ResponseEntity<Map>(map, HttpStatus.OK);
    }


    @PostMapping("/post-barang")
    @ResponseBody
    @Secured ({"ROLE_WRITE"})
    public ResponseEntity<Map> ROLE_WRITE() {
        Map map = new HashMap();
        map.put("data", "ROLE_WRITE");
        map.put("status", "sukses");
        map.put("code =", "200");
        return new ResponseEntity<Map>(map, HttpStatus.OK);
    }

    @PostMapping("/post-barang-user")
    @ResponseBody
    @Secured ({"ROLE_USER"})
    public ResponseEntity<Map> ROLE_USER() {
        Map map = new HashMap();
        map.put("data", "ROLE_USER");
        map.put("status", "sukses");
        map.put("code =", "200");
        return new ResponseEntity<Map>(map, HttpStatus.OK);
    }

    @PostMapping("/post-barang-admin")
    @ResponseBody
    @Secured ({"ROLE_ADMIN"})
    public ResponseEntity<Map> roleAdminBarang() {
        Map map = new HashMap();
        map.put("data", "ROLE_ADMIN");
        map.put("status", "sukses");
        map.put("code =", "200");
        return new ResponseEntity<Map>(map, HttpStatus.OK);
    }





}
