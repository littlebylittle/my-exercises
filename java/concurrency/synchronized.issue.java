You have approach below with concurrent code. 
What would be your advice? Are there any issues?


public class SomeClassA {
	private static final String LOCK = ""; //we don't like writing new Object();

	public doSomestuff() {
	//some logic related to A
	   synchronized(LOCK) {
		   this.work();
	   }
	}
}

public class SomeClassB {
	private static final String LOCK = ""; //we don't like writing new Object();

	public doSomestuffB() {
		//some logic related to B
	   synchronized(LOCK) {
		   this.work();
	   }
	}
}
