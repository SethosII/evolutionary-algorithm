package alt;

public class Population {

	// Population besteht aus Individuen
	private Individuum[] population;

	public Individuum[] getPopulation() {
		return population;
	}

	public void setPopulation(Individuum[] population) {
		this.population = population;
	}

	public Population() {
	}
	
	void createPopulation(int amount, int lowerBound, int upperBound, int allele) {
		population = new Individuum[amount];
		for (int i = 0; i < amount; i++) {
			population[i] = new Individuum(lowerBound, upperBound, allele);
		}
		this.setPopulation(population);
	}
	
	void printPopulation(String value) {
		int x = 0;
		switch (value) {
		case "dec": 
			for (int i = 0; i < population.length; i++) {
				System.out.print(population[i] + "; ");
				x++;
				if (x == 10) { 
					System.out.println(); 
					x = 0;
				}
			}
			break;
		case "bin":
			for (int i = 0; i < population.length; i++) {
				System.out.print(population[i].toBinString() + "; ");
				x++;
				if (x == 10) { 
					System.out.println(); 
					x = 0;
				}
			}
			break;
		}
	}

	public void calculateFitness() {
		
	}

	public void selection(String string) {
		
	}

	public void recombinate(String string) {
		
	}

	public boolean evaluate() {
		return false;
	}
	
}
