package com.example.demo.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class JwtService {
    private final static String key = "jb<6R42vfe(ui>@dfxXY!gnu=aprDk;C4oa#i<8/@+<VB\"MoY3P9KHLAKFYQJJA;&{CmfwTtQ5N>\"g>;ne;Gj>(nc3z5D/zBc%iLnoKR{cgwBXQ5GvqAUe9/ijkvM'@/VtAXea)#e8z1tQCYHE%UQQHk)EKidu0gfGJk=90F\"RiuZozwovbEUA5FTtgyfhJpbeTeH(LFYS9OTjO2E8b%09i#sv6&R18Usjs1Q)HteGl46zEKI9SM@P2U(4lUZEFbS@7Zk;n7UM1r4eJ5aY7vz6;VxDup4xg{UmPHLOQ9SBcqRCldcEIT'lhH5&J8@uBfVn61iji33EXmqsMeoWHIt+Cw}VEXVeFgd)S13!X7fzhukkqswVeZ7<Sk'\\\"fWG(D4&KblCL'U{XFPrFgTMcX6b9rDcIjooDq>zK%fW>2H#<zbMo5pn2S)9)6yK'hy2iw}moX{#C7eGBN)B4qp<Wvi6''Xcjqhld5ezCb21cZJR#RP7sQvhbd=cRZwDEbNT1;#";

    public String createJwt(String userId) {
        return createJwt(userId, Map.of());
    }

    public String createJwt(String userId, Map<String, Object> data) {
        var currentTime = new Date();

        return Jwts.builder()
                .setClaims(data)
                .setSubject(userId)
                .setIssuedAt(currentTime)
                .setExpiration(new Date(currentTime.getTime() + 1000000))
                .signWith(Keys.hmacShaKeyFor(JwtService.key.getBytes()))
                .compact();
    }

    public Claims decode(String jwt) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(JwtService.key.getBytes()))
                .build().parseClaimsJws(jwt).getBody();
    }
}
