package pages.HQ.Segments;

import objects.Button;
import objects.Table;
import pages.HQ.HomePage;



public class SegmentsPage extends HomePage{
	Button AddSegmentButton = new Button("//button[text()='+ Create New Segment']", "Add Segment");
	Table SegmentTable = new Table("//table/descendant::a[text()='Segment Name']/ancestor::table", "Table with segments");
	
	public SegmentsAddPage openAddSegmentPage() {
		AddSegmentButton.click();
		return new SegmentsAddPage();
	}

	public SegmentsPage checkSegmentsExists(String param) {
		verify(SegmentTable.isValueExists(param)>=0, true, "Segment " + param + " was not found."); 
		return this;
		
	}
}
