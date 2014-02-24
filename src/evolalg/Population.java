package evolalg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Population {

	private Individuum[] population;

	public Individuum worst;
	public Individuum best;
	public double mean;
	public double meanSquare;

	public void createPopulation(int amount, int alleles, int lowerBound,
			int upperBound, boolean binary) {
		population = new Individuum[amount];
		for (int i = 0; i < amount; i++) {
			population[i] = new Individuum(alleles, lowerBound, upperBound,
					binary);
		}
		this.setPopulation(population);
	}

	public void toDecimal() {
		for (int i = 0; i < population.length; i++) {
			population[i].toDecimal();
		}
	}

	public void printPopulation() {
		for (int i = 0; i < population.length; i++) {
			population[i].printIndividuum();
		}
	}

	public void calculateFitness(String type) throws IOException {
		double sum = 0;
		double sumLog = 1;
		for (int i = 0; i < population.length; i++) {
			population[i].calculateFitness(type);
			sum += population[i].getFitness();
			sumLog += Math.log(population[i].getFitness());
		}
		this.sort(0, this.population.length - 1);
		
		worst = population[population.length - 1];
		best = population[0];
//		System.out.println("Worst: " + worst.getFitness() + "\tBest: " + best.getFitness() + "\tResult: " + (best.getFitness() < worst.getFitness()));
//		if (best.getFitness() > worst.getFitness()) {
//			this.printPopulation();
//			(new BufferedReader(new InputStreamReader(System.in))).readLine();
//		}
		mean = sum / population.length;
		meanSquare = Math.pow(Math.E, 1d / (double) population.length * sumLog);
	}

	public Population selection(String typ) {
		Population selected = new Population();
		switch (typ) {
		case "komma":
			int top = this.population.length / 6; // amount of selected
													// individuals
			Individuum[] newElder = new Individuum[top];
			for (int i = 0; i < newElder.length; i++) {
				newElder[i] = this.population[i];
			}
			selected.setPopulation(newElder);
			break;

		// case "q_turnier":
		// int q = 5;
		// Individuum[] indi = new Individuum[q];
		// Random r = new Random();
		// int cnt = 0;
		//
		// while (cnt < q) {
		// indi[cnt] = getPopulation()[r.nextInt()];
		// cnt++;
		// }
		//
		// double cur_fitness = 0,
		// fitness = 1;
		// int index = 0;
		// for (int i = 0; i < indi.length; i++) {
		// cur_fitness = indi[i].getFitness();
		// if (cur_fitness < fitness) {
		// fitness = cur_fitness;
		// index = i;
		// }
		// }
		//
		// System.out.println("Index des Gewinners: " + index);
		// Individuum q_newElder = indi[index];
		// break;

		default:
			System.out.println("Unbekannter Selektionstyp");
			break;
		}
		return selected;

	}

	public void sort(int left, int right) {
		if (left < right) {
			int part = part(left, right);
			sort(left, part - 1);
			sort(part + 1, right);
		}
	}

	private int part(int left, int right) {
		int i = left;
		int j = right - 1;
		double pivot = population[right].getFitness();
		while (i <= j) {
			if (this.population[i].getFitness() > pivot) {
				Individuum tmp = this.population[i];
				this.population[i] = this.population[j];
				this.population[j] = tmp;
				j--;
			} else {
				i++;
			}
		}
		Individuum tmp = this.population[i];
		this.population[i] = this.population[right];
		this.population[right] = tmp;
		return i;
	}

	public Population recombinate(int amount, String typ) {
		Population childs = new Population();
		Individuum[] childs2 = new Individuum[amount];
		switch (typ) {
		case "intermediate":
			for (int i = 0; i < childs2.length; i++) {
				int e1 = (int) (Math.random() * this.population.length);
				int e2 = (int) (Math.random() * this.population.length);
				childs2[i] = this.population[e1].recombinate(
						this.population[e2], "intermediate", new String[1]);
			}
			break;

		case "arithmetic":
			for (int i = 0; i < childs2.length; i++) {
				int e1 = (int) (Math.random() * this.population.length);
				int e2 = (int) (Math.random() * this.population.length);
				childs2[i] = this.population[e1].recombinate(
						this.population[e2], "arithmetic", new String[1]);
			}
			break;

		case "ein-punkt":
			for (int i = 0; i < childs2.length; i += 2) {
				int e1 = (int) (Math.random() * this.population.length);
				int e2 = (int) (Math.random() * this.population.length);
				int z = (int) (Math.random() * this.population.length);

				String[] args = { z + "" };
				childs2[i] = this.population[e1].recombinate(
						this.population[e2], "ein-punkt", args);
				// children[0] = elder1.substring(0,z) +
				// elder2.substring(z,elder2.length());
				// children[1] = elder2.substring(0,z) +
				// elder1.substring(z,elder1.length());
			}
			break;

		/*
		 * case "zwei-punkt": Individuum[] newElder3 = new
		 * Individuum[this.population.length];
		 * selected.setPopulation(newElder3); break;
		 */

		default:
			System.out.println("Unbekannter Rekombinationstyp");
			break;
		}
		childs.setPopulation(childs2);
		return childs;
	}

	public void mutate(double d, double strength, int iteration,
			int generations, String type) {
		int count = (int) (this.population.length * d / 100);
		switch (type) {
		case "null":
			break;

		case "linear":
			strength *= (generations - iteration) / generations;
			break;

		case "exponential":
			this.population[(int) (Math.random() * this.population.length)]
					.mutate(strength);
			break;

		case "special":
			break;
			
		default:
			System.out.println("Unbekannter Mutationstyp");
			break;
		}
	}

	// Abbruchbedingung prüfen
	public boolean evaluate() {
		return false;
	}

	public void setPopulation(Individuum[] population) {
		this.population = population;
	}

	public Individuum[] getPopulation() {
		return population;
	}

}
