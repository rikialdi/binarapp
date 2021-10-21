package com.binar.binar.controller.roleAccess;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/role-test-global")
public class RoleGlobalController {
//                .antMatchers("/v1/role-test-global/list-barang").hasAnyAuthority("ROLE_READ")
//                .antMatchers("/v1/role-test-global/post-barang").hasAnyAuthority("ROLE_WRITE")
//                .antMatchers("/v1/role-test-global/post-barang-user").hasAnyAuthority("ROLE_USER")
//                .antMatchers("/v1/role-test-global/post-barang-admin").hasAnyAuthority("ROLE_ADMIN")

    @GetMapping("/list-barang")
    @ResponseBody
    public ResponseEntity<Map> getList() {
        Map map = new HashMap();
        map.put("data", "ROLE_READ");
        map.put("status", "sukses");
        map.put("code =", "200");
        return new ResponseEntity<Map>(map, HttpStatus.OK);
    }

    @PostMapping("/list-barang")
    @ResponseBody
    public ResponseEntity<Map> getpost() {
        Map map = new HashMap();
        map.put("data", "ROLE_READ");
        map.put("status", "sukses");
        map.put("code =", "200");
        return new ResponseEntity<Map>(map, HttpStatus.OK);
    }

    @PostMapping("/post-barang")
    @ResponseBody
    public ResponseEntity<Map> postbarang() {
        Map map = new HashMap();
        map.put("data", "ROLE_WRITE");
        map.put("status", "sukses");
        map.put("code =", "200");
        return new ResponseEntity<Map>(map, HttpStatus.OK);
    }

    @PostMapping("/post-barang-user")
    @ResponseBody
    public ResponseEntity<Map> POST1() {
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
