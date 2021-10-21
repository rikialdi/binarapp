package com.binar.binar.controller.roleAccess;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/role-test-global-backup")
public class RoleTestController {

    @GetMapping("/list-barang")
    @ResponseBody
    public ResponseEntity<Map> getList() {
        Map map = new HashMap();
        map.put("data", "ROLE_READ");
        map.put("status", "sukses");
        map.put("code =", "200");
        return new ResponseEntity<Map>(map, HttpStatus.OK);
    }

    @PostMapping("/post-barang")
    @ResponseBody
    public ResponseEntity<Map> ROLE_WRITE() {
        Map map = new HashMap();
        map.put("data", "ROLE_WRITE");
        map.put("status", "sukses");
        map.put("code =", "200");
        return new ResponseEntity<Map>(map, HttpStatus.OK);
    }

    @PostMapping("/post-barang-user")
    @ResponseBody
    public ResponseEntity<Map> ROLE_USER() {
        Map map = new HashMap();
        map.put("data", "ROLE_USER");
        map.put("status", "sukses");
        map.put("code =", "200");
        return new ResponseEntity<Map>(map, HttpStatus.OK);
    }

    @PostMapping("/post-barang-admin")
    @ResponseBody
    public ResponseEntity<Map> ROLE_ADMIN() {
        Map map = new HashMap();
        map.put("data", "ROLE_ADMIN");
        map.put("status", "sukses");
        map.put("code =", "200");
        return new ResponseEntity<Map>(map, HttpStatus.OK);
    }
}
