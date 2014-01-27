package evolalg;

public class Individuum {

	private int length;
	// Individuum besteht aus mehreren Allelen
	private int[] dec_value;
	private String[] bin_value;
	private String[] gray_value;

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int[] getDec_value() {
		return dec_value;
	}

	public void setDec_value(int[] dec_value) {
		this.dec_value = dec_value;
		for (int i = 0; i < dec_value.length; i++) {
			this.bin_value[i] = Conversion.toBin(dec_value[i]);
			while (bin_value[i].length() < this.length) {
				bin_value[i] = "0" + bin_value[i];
			}
			this.gray_value[i] = Conversion.toGray(bin_value[i]);
		}

	}

	public String[] getBin_value() {
		return bin_value;
	}

	public void setBin_value(String[] bin_value) {
		this.bin_value = bin_value;
		for (int i = 0; i < bin_value.length; i++) {
			this.gray_value[i] = Conversion.toGray(bin_value[i]);
			this.dec_value[i] = Conversion.toDec(bin_value[i]);
		}
	}

	public String[] getGray_value() {
		return gray_value;
	}

	public void setGray_value(String[] gray_value) {
		this.gray_value = gray_value;
		for (int i = 0; i < gray_value.length; i++) {
			this.bin_value[i] = Conversion.toBin(gray_value[i]);
			this.dec_value[i] = Conversion.toDec(bin_value[i]);
		}
	}

	public String toString() {
		return dec_value + "";
	}

	public String[] toBinString() {
		return getBin_value();
	}

	public Individuum(int lowerBound, int upperBound, int allele) {
		int[] value = new int[allele];
		for (int i = 0; i < value.length; i++) {
			value[i] = (int) (Math.random() * (upperBound - lowerBound))
					+ lowerBound;
		}
		this.setLength(Conversion.toBin(upperBound).length());
		this.bin_value = new String[allele];
		this.gray_value = new String[allele]; 
		this.setDec_value(value);
	}

}
