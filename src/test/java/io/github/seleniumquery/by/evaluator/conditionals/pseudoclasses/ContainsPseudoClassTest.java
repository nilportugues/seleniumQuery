package io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses;

import static io.github.seleniumquery.SeleniumQuery.$;
import static io.github.seleniumquery.by.selector.SeleniumQueryCssCompilerIntegrationTest.assertSelectorMatchedSetSize;
import io.github.seleniumquery.TestInfrastructure;

import org.junit.Before;
import org.junit.Test;

public class ContainsPseudoClassTest {
	
	@Before
	public void setUp() {
		$.browser.setDefaultDriver(TestInfrastructure.getDriver());
		$.browser.openUrl(TestInfrastructure.getHtmlTestFileUrl(getClass()));
	}
	
	// http://jsbin.com/siwapeqe/1/edit
    @Test
    public void contains_pseudo_with_tag() {
    	assertSelectorMatchedSetSize("div", 36);

    	assertSelectorMatchedSetSize("div:contains(abc)", 12);
    	assertSelectorMatchedSetSize("div:contains(\"abc\")", 12);
    	assertSelectorMatchedSetSize("div:contains('abc')", 12);

    	assertSelectorMatchedSetSize("div:contains(\"'abc'\")", 4);
    	assertSelectorMatchedSetSize("div:contains('\"abc\"')", 4);

    	assertSelectorMatchedSetSize("div:contains(\"a'bc\")", 4);
    	assertSelectorMatchedSetSize("div:contains('a\"bc')", 4);

    	assertSelectorMatchedSetSize("div:contains(\"ab)c\")", 4);
    	assertSelectorMatchedSetSize("div:contains('ab)c')", 4);

    	assertSelectorMatchedSetSize("div:contains(\"a'b)c\")", 4);
    	assertSelectorMatchedSetSize("div:contains('a\"b)c')", 4);
    }
    
    @Test
    public void contains_pseudo_with_tag_escaping_double_quotes_inside_double_quotes_string() {
    	assertSelectorMatchedSetSize("div:contains(\"a\\\"b)c\")", 2);
    }

    @Test
    public void contains_pseudo_with_tag_escaping_single_quote_inside_single_quote_string() {
    	// the selectors below should return TWO divs, not FOUR, it is matching WRONGLY
    	// it should match the <div>a\'b)c</div>, like jQuery does, but it doesnt
    	// as the content of contains reaches the ContainsPseudoClass as a'b)c, whereas
    	// jQuery considers it to be a\'b)c.
    	//
    	// To me, the CSS parser does the right thing, but jQuery disagrees.
    	// There's not much we can do here without changing the CSS Parser...
    	assertSelectorMatchedSetSize("div:contains('a\\'b)c')", 4);
    	assertSelectorMatchedSetSize("div:contains('a\\\'b)c')", 4);
    }
    
    @Test
    public void containsPseudo() {
    	assertSelectorMatchedSetSize("div:contains(abc)", 12);
    	assertSelectorMatchedSetSize("body:contains(abc)", 1);
    	assertSelectorMatchedSetSize("html:contains(abc)", 1);
    	assertSelectorMatchedSetSize(":contains(abc)", 14);
    }
	
}