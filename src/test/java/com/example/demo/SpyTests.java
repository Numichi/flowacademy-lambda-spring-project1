package com.example.demo;

import com.example.demo.fortest.ImpService;
import com.example.demo.fortest.TestService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;

@ExtendWith({MockitoExtension.class})
class SpyTests {

	@Spy
	ImpService s;

	@Test
	void mockTest3() {
		// given
		given(s.nagybetuvelKezdodik(any(), anyInt())).willReturn(true);

		// when
		var maradek = s.maradek(5);

		System.out.println(maradek);

		// then
		assertEquals(0, maradek);
	}
}
