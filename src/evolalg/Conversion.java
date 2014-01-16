package evolalg;

public class Conversion {

	static String toBin(int dec_value) {
		return Integer.toBinaryString(dec_value);
	}
	
	static int toDec(String bin_value) {
		return Integer.parseInt(bin_value, 2);
	}
	
	static String toGray(String bin_value) {
		String gray_value = "";
		for (int i = 0; i < bin_value.length(); i++) {
			if (i == 0) {
				gray_value = bin_value.charAt(i) + "";
				//System.out.println(i + ".Durchlauf: start " + gray_value);
			}
			else if ((bin_value.charAt(i) == '0') && (bin_value.charAt(i-1) == '1') || ((bin_value.charAt(i) == '1') && (bin_value.charAt(i-1) == '0'))) {
					gray_value += "1";
					//System.out.println(i + ".Durchlauf: else if " +bin_value.charAt(i) + ";" + bin_value.charAt(i-1)+ ";" +gray_value);
			}
			else {
				gray_value += "0";
				//System.out.println(i + ".Durchlauf: else " + gray_value);
			}
		}
		return gray_value;
	}
	
	static String toBin(String gray_value) {
		String bin_value = "";
		for (int i = 0; i < gray_value.length(); i++) {
			if (i == 0) {
				bin_value = gray_value.charAt(i) + "";
				//System.out.println(i + ".Durchlauf: start " + gray_value);
			}
			else if ((gray_value.charAt(i) == '0') && (bin_value.charAt(i-1) == '1') || ((gray_value.charAt(i) == '1') && (bin_value.charAt(i-1) == '0'))) {
					bin_value += "1";
					//System.out.println(i + ".Durchlauf: else if " +bin_value.charAt(i) + ";" + bin_value.charAt(i-1)+ ";" +gray_value);
			}
			else {
				bin_value += "0";
				//System.out.println(i + ".Durchlauf: else " + gray_value);
			}
		}
		return bin_value;
	}
	
}
