package com.github.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ServiceApplicationTests {

	@Test
	void contextLoads() {
		var truth = true;
		assertThat(truth).isTrue();
	}

}
