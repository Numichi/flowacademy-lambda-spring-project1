package com.example.demo;

import com.example.demo.services.JwtService;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.UUID;

public class JwtTest {

    @Test
    public void jwtTest() {
        var service = new JwtService();
        var jwt1 = service.createJwt(UUID.randomUUID().toString(), Map.of());

        System.out.println(jwt1);

        var change = "eyJhbG1hZmEiOiJrb3J0ZWZhIiwic3ViIjoiZDNkN2ZiNTItNGJiYy00YmFhLWJmZDctNjhhZmY5N2EwOWJmIiwiaWF0IjoxNjMyODE3NzQ4LCJleHAiOjE2MzI4MTc3NTJ9";

//        var split = jwt1.split("\\.");
//        System.out.println(split.length);
//        var data = service.decode(split[0] + "." + change + "." + split[2]);

        var data = service.decode(jwt1);

        System.out.println(data.get("almafa"));
    }
}
