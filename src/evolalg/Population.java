package evolalg;

import java.io.BufferedReader;
import java.io.FileReader;
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
	
	// Population nach Selektion
	public void createPopulation(Population adults, Population children, String type) throws IOException {
		population = new Individuum[adults.getPopulation().length + children.getPopulation().length];
		for (int i = 0; i < adults.getPopulation().length; i++) {
			population[i] = adults.getPopulation()[i];
		}
		for (int i = adults.getPopulation().length; i < adults.getPopulation().length + children.getPopulation().length; i++) {
			population[i] = children.getPopulation()[i - adults.getPopulation().length];
		}
		System.out.println("Length: " + this.population.length);
		population[114].printIndividuum();
		//this.printPopulation();
		
		this.calculateFitness(type);
		
		this.sort(0, this.population.length-1);
		Individuum[] temp = new Individuum[adults.getPopulation().length];
		for (int i = 0; i < temp.length; i++) {
			temp[i] = population[i];
		}
		this.setPopulation(temp);
			
		
	}

	public void toDecimal() {
		for (int i = 0; i < population.length; i++) {
			population[i].toDecimal();
		}
	}

	public void printPopulation() {
		for (int i = 0; i < population.length; i++) {
			System.out.print(i + ": ");
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
		// System.out.println("Worst: " + worst.getFitness() + "\tBest: " +
		// best.getFitness() + "\tResult: " + (best.getFitness() <
		// worst.getFitness()));
		// if (best.getFitness() > worst.getFitness()) {
		// this.printPopulation();
		// (new BufferedReader(new InputStreamReader(System.in))).readLine();
		// }
		mean = sum / population.length;
		meanSquare = Math.pow(Math.E, 1d / (double) population.length * sumLog);
	}

	public Population selection(String typ, double selectionRate) {
		Population selected = new Population();
		switch (typ) {
		case "komma":
			int top = (int) (this.population.length * selectionRate); // amount
																		// of
																		// selected
			// individuals
			Individuum[] newElder = new Individuum[top];
			for (int i = 0; i < newElder.length; i++) {
				newElder[i] = this.population[i];
			}
			selected.setPopulation(newElder);
			break;
			
		case "plus":
			int best = (int)(this.population.length * selectionRate); // amount
			// individuals
			Individuum[] newElder2 = new Individuum[best];
			for (int i = 0; i < newElder2.length; i++) {
				newElder2[i] = this.population[i];
			}
			
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

	public Population recombinate(String typ, String type, int iteration,
			int amountEnd) {
		int amount = amountEnd;
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

	public void mutate(double rate, double strength, int iteration,
			int generations, String type) {
		int length = this.population.length;
		int count = (int) (length * rate / 100);
		switch (type) {
		case "null":
			break;

		case "linear":
			count *= (generations - iteration) / generations;
			break;

		case "exponential":
			count *= Math.pow(Math.E, -0.04 * iteration);
			break;

		case "exponentialdec":
			count *= 1d - Math.pow(Math.E, 0.04 * iteration) / 60d;
			break;

		case "special":
			break;

		default:
			System.out.println("Unbekannter Mutationstyp");
			break;
		}
		for (int i = 0; i < count; i++) {
			this.population[(int) (Math.random() * length)].mutate(strength);
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

	public void loadPopulation(String name, int amount) throws IOException {
		
		BufferedReader in = null;
		this.population = new Individuum[amount];
		int i = 0;
		
		try {
			in = new BufferedReader(new FileReader(name));
			String line = in.readLine();
			while (line != null && (i < amount)) {
				String[] temp = line.split("\\s");
				double[] double_tmp = new double[temp.length];
				for (int x = 0; x < temp.length; x++) {
					double_tmp[x] = Double.parseDouble(temp[x]);
					System.out.println(double_tmp[x]);
				}
				System.out.println(i);
				population[i]=new Individuum();
				population[i].setAlleles(double_tmp);
				i++;
			}
		}
		catch(IOException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			if (in!=null) 
				in.close();
		}
	}
}
	

