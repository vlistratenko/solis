package pages.HQ;

import objects.Button;
import pages.HQ.Segments.SegmentsPage;
import pages.HQ.Supporters.SupportersPage;

public class AudiencePage extends HomePage{

	Button supportersButton = new Button("//a[text()='Supporters']", "Supporters");
	Button segmentsButton = new Button("//a[text()='Segments']", "Segments");
	
	public SupportersPage openSupportersPage() {
		
		supportersButton.click();
		return new SupportersPage();
	}
	
	public SegmentsPage openSegmentsPage() {
		
		segmentsButton.click();
		return new SegmentsPage();
	}
	
	public AudiencePage verifyURL() {
			
		verify(getLocation().contains("audience"), true, "Current URL is not contains Audience");
		return this;
	}
}
