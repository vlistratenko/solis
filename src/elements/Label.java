package elements;

public interface Label extends Element {

	String getText();

	boolean waitForNotExists(Integer timeOut);
}
