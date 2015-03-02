package elements;

public interface Element {

	boolean isVisible();

	boolean isEnabled();

	boolean isDisplayed();

	boolean isNotDisplayed();

	void highlight();

	String getPath();

	void changePath(String old, String newPath);

	void scrollIntoView();

	void addPath(String pathToAdd);

	void setImplicity(int cTimeOut);
	
	void click();
	
	void clickJS();
	
	void clickByTABKey();

	void waitElement();
	
	String getName();
}
