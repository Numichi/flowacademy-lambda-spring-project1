package com.example.demo;

import com.example.demo.fortest.ImpService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class ArgumentCaptorTest {

    @Spy
    ImpService service;

    @Captor
    ArgumentCaptor<String> captor;

    @Captor
    ArgumentCaptor<Integer> captor2;

    @Test
    public void test() {
        service.maradek(5);

        verify(service, times(1)).nagybetuvelKezdodik(any(), any());
    }

    @Test
    public void test2() {
        given(service.nagybetuvelKezdodik(any(), anyInt())).willReturn(true);

        var result = service.maradek(5);

        verify(service).nagybetuvelKezdodik(captor.capture(), captor2.capture());

        assertEquals("6", captor.getValue());
        assertEquals(10, captor2.getValue());
        assertEquals(0, result);
    }

    @Test
    public void test3() {
        assertThrows(NullPointerException.class, () -> {
            service.context();
        });
    }
}
