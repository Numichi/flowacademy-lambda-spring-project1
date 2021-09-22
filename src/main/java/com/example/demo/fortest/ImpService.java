package com.example.demo.fortest;

public class ImpService implements MasAltalIrtService {

    @Override
    public int maradek(int szam) {
        // modify
        szam++;

        return nagybetuvelKezdodik("" + szam, 10) ? 0: 1;
    }

    @Override
    public boolean nagybetuvelKezdodik(String str, int a) {
        return true;
    }

    public void context() {
        // code

        throw new NullPointerException();

        // code
    }
}
