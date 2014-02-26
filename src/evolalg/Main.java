package evolalg;

import java.sql.Time;
import java.util.Date;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static double fitness_best[];
	static double fitness_worst[];
	static double fitness_average[];
	static double fitness_geometric[];
	
	public static void main(String args[]) throws IOException {

		int runs = 20;
		int generations = 100;
		int amountStart = 500;
		int amountEnd = 500;
		int alleles = 20;
		int lowerBound = -10;
		int upperBound = 10;
		double rate = 10.0;
		double strength = 5;
		double selectionRate = 1d/6d;
		boolean isBinary = false;
		String fitnessType = "griewank"; // null, griewank, test
		String mutationType = "null"; // null, linear, exponential, exponentialdec, special
		String populationType = "null"; // null, linear
		String location = ".";

		fitness_best = new double[generations];
		fitness_worst = new double[generations];
		fitness_average = new double[generations];
		fitness_geometric = new double[generations];
		
		System.out.println("Evolutionäre Algorithmen");
		System.out.println("========================");

		for (int k = 0; k < runs; k++) {
			Population p = new Population();
			// 1. Bestimmung der Ausgangspopulation
			p.createPopulation(amountStart, alleles, lowerBound, upperBound,
					isBinary);

			if (isBinary) {
				p.toDecimal();
			}
			
//			// Anfangspopulation in Datei schreiben
//			String pathname = location + "\\population_" + k + ".txt";
//			GenerateFile.savePopulationToFile(pathname, p, alleles);

			// 2. Bestimmung der Fitness der Individuen der Ausgangspopulation
			p.calculateFitness(fitnessType);

			// System.out.println("Start:");
			// p.printPopulation();

			int i = 0;
			do {
				// 3. Selektion
				Population pForNew = p.selection("komma", selectionRate);

				// 4. Rekombination
				Population pNew = pForNew.recombinate(
						"arithmetic", populationType, i, amountEnd);

				// 5. Umweltselektion

				// 6. Mutation
				pNew.mutate(rate, strength, i, generations, mutationType);

				// 7. Bestimmung der Fitness der Individuen der neuen Population
				pNew.calculateFitness(fitnessType);

				p = pNew;
				// System.out.println(i + ":");
				// p.sort(0, p.getPopulation().length - 1);
				// p.printPopulation();
				// System.out.println();
				// new BufferedReader(new
				// InputStreamReader(System.in)).readLine();

				// 8. auf Abbruchkriterium prüfen, sonst 3.

				p.sort(0, p.getPopulation().length - 1);
				fitness_best[i] = p.best.getFitness();
				fitness_worst[i] = p.worst.getFitness();
				fitness_average[i] = p.mean;
				fitness_geometric[i] = p.meanSquare;
				
				//System.out.println("Generation : " + i + "\tFitness: " + fitness_best[i]);
				
				i++;
			} while (i < generations);// p.evaluate());

			System.out.println("Run " + k + ":");
			p.getPopulation()[0].printIndividuum();
			System.out.println("mean: " + p.mean + ", meansquare: "
					+ p.meanSquare);

			String name = location + "\\test_run_" + k + ".csv";

			GenerateFile.generateCsvFile(name, fitness_best, fitness_worst, fitness_average, fitness_geometric);
		}

	}

}