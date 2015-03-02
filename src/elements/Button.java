package elements;

import org.openqa.selenium.WebElement;

public interface Button extends Element {

	void submit();

	String getLabel();

	void onClick();

	boolean isNotExists();

	void clickByNumber(Integer number);

	String getAttribute(String attrName);

	void moveAndClick();

	WebElement getLastElement();
}
