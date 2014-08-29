package io.github.seleniumquery.selectors.pseudoclasses;

import io.github.seleniumquery.locator.ElementFilter;
import io.github.seleniumquery.selectorcss.CompiledCssSelector;
import io.github.seleniumquery.selectorcss.CssSelectorCompilerService;
import io.github.seleniumquery.selectorxpath.XPathExpression;
import io.github.seleniumquery.selectorxpath.XPathSelectorFactory;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EvenPseudoClass implements PseudoClass {

	private static final EvenPseudoClass instance = new EvenPseudoClass();
	public static EvenPseudoClass getInstance() {
		return instance;
	}
	private EvenPseudoClass() { }
	
	private static final String EVEN_PSEUDO_CLASS_NO_COLON = "even";
	
	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return EVEN_PSEUDO_CLASS_NO_COLON.equals(pseudoClassValue);
	}

	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, PseudoClassSelector pseudoClassSelector) {
		CompiledCssSelector compileSelector = CssSelectorCompilerService.compileSelector(driver, pseudoClassSelector.getStringMap(), pseudoClassSelector.getSelector());
		List<WebElement> elements = compileSelector.execute(driver);
		return elements.indexOf(element) % 2 == 0;
	}
	
	@Override
	public CompiledCssSelector compilePseudoClass(WebDriver driver, PseudoClassSelector pseudoClassSelector) {
		// we never consider :even to be supported natively
		ElementFilter evenPseudoClassFilter = new PseudoClassFilter(getInstance(), pseudoClassSelector);
		return CompiledCssSelector.createFilterOnlySelector(evenPseudoClassFilter);
	}
	
	@Override
	public XPathExpression pseudoClassToXPath(WebDriver driver, PseudoClassSelector pseudoClassSelector) {
		// notice that XPath is 1-based and :even is not.
		return XPathSelectorFactory.createNoFilterSelectorAppliedToAll("[(position() mod 2) = 1]");
	}

}