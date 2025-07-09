package com.fantasmaDux.restApplication;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@SpringBootTest
class RestApplicationTests {

	@Test
	void contextLoads() {
	}
	@Test
	void conCatTest() {
		String stringOne = "hello ";
		String stringTwo = "world";

		assertEquals("hello world", stringOne+stringTwo);
	}
}
