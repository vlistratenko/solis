package objects;

import interfaces.iCheckBox;

public class CheckBox extends Element implements iCheckBox {

	public CheckBox(String elementPath, String elementName) {
		super(elementPath, elementName);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void check() {
		logger.info(elementName + " was checked.");
		super.check(path, true);

	}

	@Override
	public void unCheck() {
		logger.info(elementName + " was unchecked.");
		super.check(path, false);

	}

	@Override
	public void changeState() {
		logger.info("State for" + elementName + " was chenged.");
		if (isChecked()) {
			unCheck();
		}else{
			check();
		}
	}

	@Override
	public void highlight() {
		logger.info(elementName + " was highlighted.");
		super.highlight(path);
		
	}

	@Override
	public boolean isChecked() {
		logger.info(elementName + " was checked.");
		return findElementByXpath(path).isSelected();
	}

	@Override
	public boolean isVisible() {
		logger.info("Check is " + elementName + " visible.");
		return super.isVisible(path);
	}

	@Override
	public boolean isEnabled() {
		logger.info("Check is " + elementName + " enabled.");
		return super.isEnabled(path);
	}

	@Override
	public boolean isDisplayed() {
		logger.info("Check is " + elementName + " displayed.");
		return super.isDisplayed(path);
	}
	
	@Override
	public boolean isNotDisplayed() {
		super.isNotDisplayed(path);
		return false;
	}


}
