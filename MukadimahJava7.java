import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class MukadimahJava7 {
	static final String FACT_1 = "ALAY";
	static final String FACT_2 = "LEBAY";
	static final String FACT_3 = "SESUATU BANGET";

	/* pengkondisian variabel string sebelum java 7*/
	public void testSwitchInStringOnOlderJava(String str) {
		if(str.equals(FACT_1)) {
			System.out.println(FACT_1);
		}
		else if(str.equals(FACT_2)) {
			System.out.println(FACT_2);
		}
		else if(str.equals(FACT_3)) {
			System.out.println(FACT_3);
		}
	}
	
	/* pengkondisian variabel string pada java 7*/
	public void testSwitchInStringOnJava7(String str) {
		switch(str) {
			case FACT_1:
				System.out.println(FACT_1);
				break;
			case FACT_2:
				System.out.println(FACT_2);
				break;
			case FACT_3:
				System.out.println(FACT_3);
				break;
		}
	}

	public void testIntegerLiteralOnJava7() {
		int decimal = 23; // literal decimal pada umumnya
		int octal = 027; // literal octal hanya memakai digit 0-7. eg, decimal = 9, octal = 010
		int hexadecimal = 0x0017; //hexadecimal memakai digit 0-7 dan character A-F (8-15). eg,  0x1F = 0x001F = 31
		int binary = 0b10111; /* java 7 only, literal binary, sesuai dengan aturan penomoran binary, 
							     0b10111 = 1x2^4 (16) + 0x2^3 (0) + 1x2^2 (4) + 1x2^1 (2) + 1x2^0 (1) = 23 */
		
		if(decimal == 0b10111){
			System.out.println("decimal="+decimal);
		}
		
		if(octal == 0x0017){
			System.out.println("octal="+octal);
		}

		if(hexadecimal == 027){
			System.out.println("hexadecimal="+hexadecimal);
		}

		if(binary == 23){
			System.out.println("binary="+binary);
		}
	}

	public void testUnderscoreInNumericOnJava7() {
		long milyaran = 1205567100;
		long milyaran_7 = 1_205_567_100;

		if(milyaran == milyaran_7) {
			System.out.println("selamat, anda menang gebyar BC*");
		}
		else {
			System.out.println("Kesalahan terjadi pada komputer anda atau diri anda sendiri");
		}
	} 
	
	public void testDiamondSyntaxOnJava7(){
		//sebelum java 7
		Map<String, List<Integer>> map = new HashMap<String, List<Integer>>(); 
		List<String> list = new ArrayList<String>();
		Set<? extends Object> set = new TreeSet<Double>();
		
		//diamond syntax, hemat waktu dan ruang:)
		Map<String, List<Integer>> map7 = new HashMap<>(); 
		List<String> list7 = new ArrayList<>();
		Set<? extends Object> set7 = new TreeSet<>();
	}
	
	public void testMultiCatchOnJava7(String... params) {
		// sebelum java 7
		try {
			System.out.println(params[0]); 
			throw new FileNotFoundException("file ga ketemu atau tulisan anda ngawur!");
		}
		catch(FileNotFoundException fne) {
			fne.printStackTrace();
		}
		catch(ArrayIndexOutOfBoundsException ae) {
			ae.printStackTrace();
		}
		
		//java 7 multi-catch
		try {
			System.out.println(params[0]); 
			throw new FileNotFoundException("sumpah dah, file kamu bener2 ga ada!");
		}
		catch(FileNotFoundException | ArrayIndexOutOfBoundsException mex) {
			mex.printStackTrace();
		}
	}
	
	public void testTryWithResourceOnJava7() {
		Connection connection = null;
		
		//sebelum java 7
		try {
			connection = getConnection("ora_kelar_kelar_11g");
			// bla bla bla SQL
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close(); //di dalam finally masih harus pake try-catch lagi, cape deh....
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		//java 7
		try {
			connection = getConnection("manggaDB");
			// bla bla bla INSERT UPDATE DELETE
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private Connection getConnection(String url) throws SQLException{
		return null; //Koneksi abal-abal dulu
	}
	
	public void testSafeVarargsOnJava7() {
		showNumbers(new ArrayList<Integer>());
	}
	
	@SafeVarargs
	// WARNING SUPPRESSED
	public static void showNumbers(List<Integer>...nums) {
		for(List<Integer> num : nums) {
			System.out.println(num);
		}
	}

	public static void main(String...args) {
		MukadimahJava7 java7 = new MukadimahJava7();

		java7.testSwitchInStringOnOlderJava(FACT_1);
		java7.testSwitchInStringOnJava7(FACT_3);
		java7.testIntegerLiteralOnJava7();
		java7.testUnderscoreInNumericOnJava7();
		java7.testMultiCatchOnJava7("gandos");
		java7.testTryWithResourceOnJava7();
	}
}