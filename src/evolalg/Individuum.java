package evolalg;

public class Individuum {

	private double[] alleles;

	private double fitness;

	public Individuum(int alleles, int lowerBound, int upperBound) {
		this.alleles = new double[alleles];
		for (int i = 0; i < this.alleles.length; i++) {
			this.alleles[i] = Math.random() * (upperBound - lowerBound)
					+ lowerBound;
		}
	}

	public Individuum() {
	}

	public void printIndividuum() {
		System.out.print("|");
		for (int i = 0; i < alleles.length; i++) {
			System.out.printf("%+.4f|", alleles[i]);
		}
		System.out.println("\tF:" + fitness);
	}

	public void calculateFitness() {
		fitness = Math.pow(alleles[0] + 10 * alleles[1], 2) + 5
				* Math.pow(alleles[2] - alleles[3], 2)
				+ Math.pow(alleles[1] - 2 * alleles[2], 4) + 10
				* Math.pow(alleles[0] - alleles[3], 4);
	}

	public Individuum recombinate(Individuum individuum, String typ) {
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
				if(this.alleles[i]>individuum.alleles[i]){
					max = this.alleles[i];
					min = individuum.alleles[i];
				}else{

					min = this.alleles[i];
					max = individuum.alleles[i];
				}
				tmp.alleles[i] = Math.random()*(max - min) + min;
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
			this.alleles[i]+=5*Math.pow(-1, (int)(Math.random()*2)+1);
		}
	}

}
