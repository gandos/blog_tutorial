public class StringUsage {
	public void buildString() {
		String s1 = new String("counter");
		String s2 = "empat mata";

		s1 = s1.concat(" seterika");
		s2 = "bukan " +s2;

		System.out.println(s1); //hasil: "counter seterika"
		System.out.println(s2); //hasil: "bukan empat mata"
	}

	public void compareString() {
		String s1 = "test";
		String s2 = "test";
		String s3 = new String("test");

		//membandingkan nilai dari objek
		if(s1.equals(s2)) {
			System.out.println("serupa tapi tidak sama");
		}
		else {
			System.out.println("pura-pura serupa");
		}

		System.out.println(" dan ");
		
		//membandingkan referensi dari objek
		if(s1 == s2) {
			System.out.println("buruk rupa adanya");
		}
		else {
			System.out.println("bunga tujuh warna");
		}	
		
		if(s1 == s3) {
			System.out.println(" I");
		}
		else {
			System.out.println(" II");
		}
	}

	public void doMutableString() {
		StringBuilder sb = new StringBuilder("bambang pamungkas,");
		sb.append("widodo putro,");
		sb.insert(25, "cahyono ");
		sb.append("jack gandos komboy,");
		sb.delete(43,50);
		sb.append("bejo sugiantoro");

		System.out.println(sb.toString());
	}

	public static void main(String...args) {
		StringUsage str = new StringUsage();
		str.compareString();
		str.doMutableString();
	}
}