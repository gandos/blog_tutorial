public class MukadimahJava7 {
	final String JAVA_5 = "java5";
	final String JAVA_6 = "java6";
	final String JAVA_7 = "java7";

	/* pengkondisian variabel string sebelum java 7*/
	public void testSwitchInStringInOlderJava(String str) {
		if(str.equals(JAVA_5)) {
			System.out.println(JAVA_5);
		}
		else if(str.equals(JAVA_6)) {
			System.out.println(JAVA_6);
		}
		else if(str.equals(JAVA_7)) {
			System.out.println(JAVA_7);
		}
	}
	
	/* pengkondisian variabel string pada java 7*/
	public void testSwitchInStringInJava7(String str) {
		switch(str) {
			case JAVA_5:
				System.out.println(JAVA_5);
				break;
			case JAVA_6:
				System.out.println(JAVA_6);
				break;
			case JAVA_7:
				System.out.println(JAVA_7);
				break;
		}
	}

	public static void main(String...args) {
		MukadimahJava7 java7 = new MukadimahJava7();

		java7.testSwitchInStringInOlderJava(java7.JAVA_5);
		java7.testSwitchInStringInJava7(java7.JAVA_7);
	}
}