package evolalg;

public class Individuum {

	private int length;
	private int dec_value;
	private String bin_value;
	private String gray_value;
	
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public int getDec_value() {
		return dec_value;
	}
	public void setDec_value(int dec_value) {
		this.dec_value = dec_value;
		this.bin_value = Conversion.toBin(dec_value);
		
		while (bin_value.length() < this.length) {
			bin_value = "0" + bin_value;
		}
		
			
		this.gray_value = Conversion.toGray(bin_value);
	}
	public String getBin_value() {
		return bin_value;
	}
	public void setBin_value(String bin_value) {
		this.bin_value = bin_value;
		
		
		this.gray_value = Conversion.toGray(bin_value);
		this.dec_value = Conversion.toDec(bin_value);
	}
	public String getGray_value() {
		return gray_value;
	}
	public void setGray_value(String gray_value) {
		this.gray_value = gray_value;
		this.bin_value = Conversion.toBin(gray_value);
		this.dec_value = Conversion.toDec(bin_value);
	}


	
	public String toString() {
		return dec_value + "";
	}
	
	public String toBinString() {
		return getBin_value();
	}
	
	public Individuum(int lowerBound, int upperBound) {
		int value = (int)(Math.random() * (upperBound-lowerBound)) + lowerBound;
		this.length = Conversion.toBin(upperBound).length();
		setDec_value(value);	
	}
	
	
	
}
