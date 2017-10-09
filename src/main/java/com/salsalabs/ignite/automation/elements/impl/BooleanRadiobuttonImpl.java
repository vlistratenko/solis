package com.salsalabs.ignite.automation.elements.impl;

import com.salsalabs.ignite.automation.elements.BooleanRadiobutton;
import com.salsalabs.ignite.automation.elements.Button;


public class BooleanRadiobuttonImpl extends ElementImpl implements BooleanRadiobutton {

	private Button isYes;
	private Button isNo;
	
	public BooleanRadiobuttonImpl(String trueElementPath, String falseElementPath, String elementName) {
		super(trueElementPath, elementName);
		isYes = new ButtonImpl(trueElementPath, elementName + " Yes option ", false);
		isNo = new ButtonImpl(falseElementPath, elementName + " No option ", false);
		
		
	}

	@Override
	public void select(boolean YesOrNo) {
		if (YesOrNo) {
			isYes.click();
		}
		else{
			isNo.click();
		}
		
	}

	

}
