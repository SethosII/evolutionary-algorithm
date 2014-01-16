package evolalg;

public class Population {

	private Individuum[] population;

	public Individuum[] getPopulation() {
		return population;
	}

	public void setPopulation(Individuum[] population) {
		this.population = population;
	}

	public Population() {
	}
	
	void createPopulation(int amount, int lowerBound, int upperBound) {
		population = new Individuum[amount];
		for (int i = 0; i < amount; i++) {
			population[i] = new Individuum(lowerBound, upperBound);
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
	
}
