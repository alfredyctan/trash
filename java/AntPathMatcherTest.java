package com.afc.gateway.zuul.external.path;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;
import org.springframework.util.AntPathMatcher;


public class AntPathMatcherTest {

	private AntPathMatcher rpm = new AntPathMatcher();
	
	@Test
	public void testSame() {
		assertThat("match", rpm.match("/user", "/user"), is(true));
		assertThat("not match", rpm.match("/user2", "/user"), is(false));
	}

	@Test
	public void testWildCardAtEnd() {
		assertThat("match", rpm.match("/user*", "/user"), is(true));
		assertThat("match", rpm.match("/*", "/user"), is(true));

		assertThat("match", rpm.match("/user/*", "/user"), is(false));
	}

	@Test
	public void testWildCardAtMiddle() {
		assertThat("match", rpm.match("/*/list", "/user/list"), is(true));
		assertThat("match", rpm.match("/*", "/user"), is(true));

		assertThat("match", rpm.match("/*/list", "/user/list2"), is(false));
		assertThat("match", rpm.match("/user/*", "/user"), is(false));
		assertThat("match", rpm.match("/*/*", "/user"), is(false));
	}
	
	@Test
	public void testDoubleWildCard() {
		assertThat("match", rpm.match("/user/**", "/user/list"), is(true));
		assertThat("match", rpm.match("/user**", "/user"), is(true));
		assertThat("match", rpm.match("/user**", "/user/"), is(false));
	}
}
