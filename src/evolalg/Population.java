package evolalg;

import java.util.*;

public class Population {

	private Individuum[] population;

	public void createPopulation(int amount, int alleles, int lowerBound,
			int upperBound, boolean binary) {
		population = new Individuum[amount];
		for (int i = 0; i < amount; i++) {
			population[i] = new Individuum(alleles, lowerBound, upperBound, binary);
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

	public void calculateFitness() {
		for (int i = 0; i < population.length; i++) {
			population[i].calculateFitness();
		}
	}

	public Population selection(String typ) {
		Population selected = new Population();
		switch (typ) {
		case "plus":
			int top = this.population.length/6; // amount of selected individuals

			this.sort(0, this.getPopulation().length - 1);
			System.out.println();

			Individuum[] newElder = new Individuum[top];
			for (int i = 0; i < newElder.length; i++) {
				newElder[i] = this.getPopulation()[i];
			}
			selected.setPopulation(newElder);
			break;
			
		case "q_turnier":
			int q = 5;
			Individuum[] indi = new Individuum[q];
			Random r = new Random();
			int cnt = 0;
			
			while (cnt < q) {
				indi[cnt] = getPopulation()[r.nextInt()];
				cnt++;
			}
			
			double cur_fitness = 0, fitness = 1;
			int index = 0;
			for (int i = 0; i < indi.length; i++) {
				cur_fitness = indi[i].getFitness();
				if (cur_fitness < fitness) {
					fitness = cur_fitness; 
					index = i; }
			}
			
			System.out.println("Index des Gewinners: " + index);
			Individuum q_newElder = indi[index];	
			break;
			
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
		do {
			while (population[i].getFitness() <= pivot && i < right) {
				i++;
			}
			while (population[j].getFitness() >= pivot && j > left) {
				j--;
			}
			if (i < j) {
				Individuum tmp = population[i];
				population[i] = population[j];
				population[j] = tmp;
			}
		} while (i < j);
		if (population[i].getFitness() > pivot) {
			Individuum tmp = population[i];
			population[i] = population[right];
			population[right] = tmp;
		}
		return i;
	}

	public Population recombinate(int amount,String typ) {
		Population childs = new Population();
		Individuum[] childs2 = new Individuum[amount];
		switch (typ) {
		case "intermediate":
			for (int i = 0; i < childs2.length; i++) {
				int e1 = (int) (Math.random() * this.getPopulation().length);
				int e2 = (int) (Math.random() * this.getPopulation().length);
				childs2[i] = this.getPopulation()[e1].recombinate(
						this.getPopulation()[e2], "intermediate");
			}
			break;
			
		case "arithmetic":
			for (int i = 0; i < childs2.length; i++) {
				int e1 = (int) (Math.random() * this.getPopulation().length);
				int e2 = (int) (Math.random() * this.getPopulation().length);
				childs2[i] = this.getPopulation()[e1].recombinate(
						this.getPopulation()[e2], "arithmetic");
			}
			break;

		default:
			break;
		}
		childs.setPopulation(childs2);
		return childs;
	}

	public void mutate(double d) {
		int count = (int) (this.population.length * d / 100);
		for (int i = 0; i < count; i++) {
			this.population[(int)(Math.random()*this.population.length)].mutate();
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
