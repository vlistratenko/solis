package interfaces;

public interface iTextBox extends iElement {
	
	void click();
	String getText();
	void type(String text);
	void clear();
	void highlight();
}
