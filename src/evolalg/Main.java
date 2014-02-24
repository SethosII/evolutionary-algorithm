package evolalg;

import java.sql.Time;
import java.util.Date;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static double fitness_best[];
	static double fitness_worst[];

	public static void main(String args[]) throws IOException {

		int runs = 20;
		int generations = 100;
		int amount = 500;
		int alleles = 10;
		int lowerBound = -512;
		int upperBound = 512;
		double rate = 5.0;
		double strength = 1;
		boolean isBinary = false;
		String fitnessType = "null"; // null, griewank, test
		String mutationType = "exponential"; // null, linear, exponential
		String location = ".";

		fitness_best = new double[generations];
		fitness_worst = new double[generations];

		System.out.println("Evolutionäre Algorithmen");
		System.out.println("========================");

		for (int k = 0; k < runs; k++) {
			Population p = new Population();
			// 1. Bestimmung der Ausgangspopulation
			p.createPopulation(amount, alleles, lowerBound, upperBound,
					isBinary);

			if (isBinary) {
				p.toDecimal();
			}

			// 2. Bestimmung der Fitness der Individuen der Ausgangspopulation
			p.calculateFitness(fitnessType);

			// System.out.println("Start:");
			// p.printPopulation();

			int i = 0;
			do {
				// 3. Selektion
				Population pForNew = p.selection("komma");

				// 4. Rekombination
				Population pNew = pForNew.recombinate(p.getPopulation().length,
						"arithmetic");

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
				
				//System.out.println("Generation : " + i + "\tFitness: " + fitness_best[i]);
				
				i++;
			} while (i < generations);// p.evaluate());

			System.out.println("Run " + k + ":");
			p.getPopulation()[0].printIndividuum();
			System.out.println("mean: " + p.mean + ", meansquare: "
					+ p.meanSquare);

			String name = location + "\\test_run_" + runs + ".csv";

			GenerateCsv.generateCsvFile(name, fitness_best);
		}

	}

}