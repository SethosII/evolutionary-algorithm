package alt;

public class Recombination {

	static String[] children;
	
	static String[] one_Point_Recombination(String elder1, String elder2) {
		children = new String[2];
		int z = (int)(Math.random() * elder1.length());
		System.out.println("Länge: " + elder1.length() + "\tn: " +z);
		
		children[0] = elder1.substring(0,z) + elder2.substring(z,elder2.length());
		children[1] = elder2.substring(0,z) + elder1.substring(z,elder1.length());  
		
		return children;
	}
	
	static String[] two_Point_Recombination(String elder1, String elder2) {
		children = new String[2];
		int z1 = (int)(Math.random() * elder1.length());
		int z2 = (int)(Math.random() * elder1.length());
		if (z2 < z1) {
			int temp = z2;
			z2 = z1;
			z1 = temp;
		}
		System.out.println("z1: " + z1 + "\tz2: " + z2);
		
		children[0] = elder1.substring(0,z1) + elder2.substring(z1,z2) + elder1.substring(z2,elder1.length());
		children[1] = elder2.substring(0,z1) + elder1.substring(z1,z2) + elder2.substring(z2,elder2.length());
		
		return children;
	}
	
}
