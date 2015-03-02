package pages.hq.segments;

import elements.Button;
import elements.Table;
import elements.impl.ButtonImpl;
import elements.impl.TableImpl;
import pages.hq.HomePage;



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
