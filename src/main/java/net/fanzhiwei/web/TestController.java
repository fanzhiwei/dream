package net.fanzhiwei.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.fanzhiwei.core.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fanzhiwei
 */
@RestController
public class TestController {
    private static final Logger LOG = LoggerFactory.getLogger(TestController.class);

    @PostMapping("/login")
    public Object login(HttpServletResponse response, @RequestBody final Account account) throws IOException {
        if(isValidPassword(account)) {
            String jwt = JwtUtil.generateToken(account.username);
            return new HashMap<String,String>(){{
                put("token", jwt);
            }};
        }else {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
    }

    private boolean isValidPassword(Account credentials) {
        if ("admin".equals(credentials.username)
            && "admin".equals(credentials.password)) {
            return true;//psudo company id;
        }
        return false;
    }

    public static class Account {
        public String username;
        public String password;
    }

    @RequestMapping("/api/test/jwt/test")
    public List<String> test() {
        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("41");
        list.add("51");
        return list;
    }

    @RequestMapping("/api/test/a")
    public String tes() {
        return "a";
    }

    @Cacheable(value = "usercache")
    @RequestMapping("/api/test/test1")
    public List<String> test1() {
        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("41");
        list.add("51");
        return list;
    }

    @GetMapping("/api/jwt/protected1")
    public @ResponseBody
    Object hellWorld1() {
        return "Your user id is";
    }
    @GetMapping("/api/aa/bb/jwt/protected2")
    public @ResponseBody
    Object hellWorld2() {
        return "Your user id is";
    }
    @GetMapping("/api/protected3")
    public @ResponseBody
    Object hellWorld3() {
        return "Your user id is";
    }




}
