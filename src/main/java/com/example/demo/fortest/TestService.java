package com.example.demo.fortest;

import java.util.List;

public class TestService {

    private MasAltalIrtService intf;

    public TestService(MasAltalIrtService intf) {
        this.intf = intf;
    }

    public int sum(List<Integer> integers) {
        return integers.stream().mapToInt(i -> i).sum();
    }

    public int maradek(int szam) {
        System.out.println(intf.getClass().getCanonicalName());
        return intf.maradek(szam);
    }
}
