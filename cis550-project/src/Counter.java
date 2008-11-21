
import java.io.Serializable;

public class Counter implements Serializable {
	
	private int count = 0;
	
	public int getCount() {
		return this.count;
	}
	
	public void increment() {
		this.count++;
	}
}