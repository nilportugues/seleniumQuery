package io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import io.github.seleniumquery.TestInfrastructure;

import org.junit.Before;
import org.junit.Test;

public class RadioPseudoClassTest {
	
	@Before
	public void setUp() {
		$.browser.setDefaultDriver(TestInfrastructure.getDriver());
		$.browser.openUrl(TestInfrastructure.getHtmlTestFileUrl(getClass()));
	}
	
	@Test
	public void radioPseudo() {
		assertThat($("[type='radio']").size(), is(3));
		assertThat($(":radio").size(), is(1));
		assertThat($("*:radio").size(), is(1));
		assertThat($("input:radio").size(), is(1));
		assertThat($("div:radio").size(), is(0));
		assertThat($("span:radio").size(), is(0));

		assertThat($("#i1").is(":radio"), is(true));
		assertThat($("#i1").is("*:radio"), is(true));
		assertThat($("#i1").is("input:radio"), is(true));

		assertThat($("#i2").is(":radio"), is(false));
		assertThat($("#i3").is(":radio"), is(false));
		assertThat($("#i4").is(":radio"), is(false));
	}
	
}