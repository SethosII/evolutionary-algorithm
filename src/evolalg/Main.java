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
		int lowerBound = -10;
		int upperBound = 10;
		double rate = 10.0;
		double strength = 1;
		boolean isBinary = false;
		
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
			p.calculateFitness();

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
				pNew.mutate(rate, strength, i, generations);

				// 7. Bestimmung der Fitness der Individuen der neuen Population
				pNew.calculateFitness();


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
				i++;
			} while (i < generations);// p.evaluate());

			System.out.println("Run " + k + ":");
			p.getPopulation()[0].printIndividuum();
			
			String name = "d:\\test_run_" + runs + ".csv";

			GenerateCsv.generateCsvFile(name,fitness_best);
		}
		// System.out.println();
		// System.out.println("Neu:");
		// p.printPopulation();
		

		
		
		double[] plotdata = new double[fitness_best.length];
		plotdata = fitness_best;
				
		//Plotter plot = new Plotter("Test", plotdata);
		
		//Plotter plot = new Plotter(fitness_best);	
		
		//XPlotter xplot = new XPlotter(fitness_best);
	}

}