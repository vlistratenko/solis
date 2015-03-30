package com.salsalabs.ignite.automation.pages.hq.segments;

import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.Table;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.TableImpl;
import com.salsalabs.ignite.automation.pages.hq.HomePage;



public class SegmentsPage extends HomePage{
	Button AddSegmentButton = new ButtonImpl("//button[text()='Make a New Custom Segment']", "Add Segment");
	Table SegmentTable = new TableImpl("//table/descendant::a[text()='Segment Name']/ancestor::table", "Table with segments");
	
	public SegmentsAddPage openAddSegmentPage() {
		AddSegmentButton.click();
		return new SegmentsAddPage();
	}

	public SegmentsPage checkSegmentsExists(String param) {
		verifier.verifyEquals(SegmentTable.isValueExists(param)>0, true, "Segment " + param + " was not found."); 
		return this;
		
	}
}
