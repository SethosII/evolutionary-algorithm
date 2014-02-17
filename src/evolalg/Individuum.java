package evolalg;

public class Individuum {

	private double[] alleles;
	private int lowerBound, upperBound;
	private String[] binary_alleles;

	private double fitness;

	public Individuum(int alleles, int lowerBound, int upperBound,
			boolean binary) {
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
		if (!binary) {
			this.alleles = new double[alleles];
			for (int i = 0; i < this.alleles.length; i++) {
				this.alleles[i] = Math.random() * (upperBound - lowerBound)
						+ lowerBound;
			}
		} else {
			this.binary_alleles = new String[alleles];
			int diff = this.upperBound - this.lowerBound;
			int length = (int) (Math.log(diff) / Math.log(2) + 1);
			for (int j = 0; j < this.binary_alleles.length; j++) {
				String str = "";
				for (int i = 0; i < length; i++) {
					str += "" + (int) (Math.random() * 2);
				}
				binary_alleles[j] = str;
			}
		}
	}

	public Individuum() {
	}

	public void toDecimal() {
		this.alleles = new double[binary_alleles.length];
		for (int i = 0; i < binary_alleles.length; i++) {
			int dec = Integer.parseInt(binary_alleles[i], 2);
			this.alleles[i] = this.lowerBound
					+ ((this.upperBound - this.lowerBound) / (Math.pow(2,
							binary_alleles[i].length()) - 1)) * dec;
		}
	}

	public void printIndividuum() {
		System.out.print("|");
		for (int i = 0; i < alleles.length; i++) {
			System.out.printf("%+.4f|", alleles[i]);
		}
		System.out.println("\tF:" + fitness);
	}

	public void calculateFitness() {
		 // Nullstellenberechnung
		 double tmp = 0;
		 for (int i = 0; i < alleles.length; i++) {
		 if (i < alleles.length - 1) {
		 double sum = alleles[i];
		 for (int j = 0; j < alleles.length; j++) {
		 sum += alleles[j];
		 }
		 tmp += (sum - alleles.length - 1) * (sum - alleles.length - 1);
		 } else {
		 double prod = 1;
		 for (int j = 0; j < alleles.length; j++) {
		 prod *= alleles[j];
		 }
		 tmp += (prod - 1) * (prod - 1);
		 }
		 }
		 fitness = Math.sqrt(tmp);

//		// Griewank-Funktion
//		double sum = 0;
//		for (int i = 0; i < alleles.length; i++) {
//			sum += Math.pow(alleles[i], 2) / (400 * alleles.length);
//		}
//		double prod = 1;
//		for (int i = 0; i < alleles.length; i++) {
//			prod *= Math.cos(alleles[i] / Math.sqrt(i + 1));
//		}
//		fitness = 1 + sum - prod;

		// Erste Übung
		// fitness = Math.pow(alleles[0] + 10 * alleles[1], 2) + 5
		// * Math.pow(alleles[2] - alleles[3], 2)
		// + Math.pow(alleles[1] - 2 * alleles[2], 4) + 10
		// * Math.pow(alleles[0] - alleles[3], 4);
	}

	public Individuum recombinate(Individuum individuum, String typ,
			String[] args) {
		Individuum tmp = new Individuum();
		tmp.alleles = new double[this.alleles.length];
		switch (typ) {
		case "intermediate":
			for (int i = 0; i < this.alleles.length; i++) {
				tmp.alleles[i] = (this.alleles[i] + individuum.alleles[i]) / 2;
			}
			break;

		case "arithmetic":
			for (int i = 0; i < this.alleles.length; i++) {
				double min;
				double max;
				if (this.alleles[i] > individuum.alleles[i]) {
					max = this.alleles[i];
					min = individuum.alleles[i];
				} else {

					min = this.alleles[i];
					max = individuum.alleles[i];
				}
				tmp.alleles[i] = Math.random() * (max - min) + min;
			}
			break;

		case "ein-punkt":
			for (int i = 0; i < this.alleles.length; i++) {
				tmp.alleles[i] = (this.alleles[i] + individuum.alleles[i]) / 2;
			}
			break;

		default:
			System.out.println("Unbekannter Rekombinationstyp");
			break;
		}
		return tmp;
	}

	public double getFitness() {
		return fitness;
	}

	public void mutate() {
		for (int i = 0; i < this.alleles.length; i++) {
			this.alleles[i] += 5 * Math.pow(-1, (int) (Math.random() * 2) + 1);
		}
	}

}
