package com.example.demo;

import com.example.demo.fortest.ImpService;
import com.example.demo.fortest.TestService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith({MockitoExtension.class})
@MockitoSettings(strictness = Strictness.LENIENT)
class MockTests {

	@Mock
	ImpService i;

	@InjectMocks
	TestService testService;

	/**
	 * Az alábbiak miatt kellet a: @MockitoSettings(strictness = Strictness.LENIENT)
	 *
	 * rg.mockito.exceptions.misusing.UnnecessaryStubbingException:
	 * Unnecessary stubbings detected.
	 * Clean & maintainable test code requires zero unnecessary code.
	 * Following stubbings are unnecessary (click to navigate to relevant line of code):
	 *   1. -> at com.example.demo.MockTests.mockTest(MockTests.java:34)
	 *   2. -> at com.example.demo.MockTests.mockTest(MockTests.java:35)
	 * Please remove unnecessary stubbings or use 'lenient' strictness. More info: javadoc for UnnecessaryStubbingException class.
	 */
	@Test
	void mockTest() {
		// given
		given(i.maradek(5)).willReturn(1);
		given(i.maradek(4)).willReturn(0);
		given(i.maradek(6)).willReturn(0);

		// when
		var maradek = testService.maradek(5);

		System.out.println(maradek);

		// then
		assertEquals(1, maradek);
	}

	@Test
	void listSum2() {
		var instance = new TestService(null);

		int i = instance.sum(List.of(1, 2, 3, 4, 5, 6));

		assertEquals(21, i, "Ez az értek nem egyenlő.");
	}

	@Test
	void listSum1() {
		var instance = new TestService(null);

		int i = instance.sum(List.of(1, 2, 3, 4, 5));

		assertEquals(15, i, "Ez az értek nem egyenlő.");
	}
}
