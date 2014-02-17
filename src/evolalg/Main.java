package evolalg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static double fitness_best[];
	static double fitness_worst[];
	
	public static void main(String args[]) throws IOException {

		int generations = 100;
		int amount = 500;
		int alleles = 20;
		int lowerBound = -512;
		int upperBound = 511;
		double mutation = 10.0;
		boolean isBinary = false;
		
		fitness_best = new double[generations];
		fitness_worst = new double[generations];

		System.out.println("Evolutionäre Algorithmen");
		System.out.println("========================");

		Population p = new Population();
		// 1. Bestimmung der Ausgangspopulation
		p.createPopulation(amount, alleles, lowerBound, upperBound, isBinary);

		if (isBinary) {
			p.toDecimal();
		}

		// 2. Bestimmung der Fitness der Individuen der Ausgangspopulation
		p.calculateFitness();

		System.out.println("Start:");
		p.printPopulation();

		int i = 0;
		do {
			// 3. Selektion
			Population pForNew = p.selection("komma");

			// 4. Rekombination
			Population pNew = pForNew.recombinate(p.getPopulation().length,
					"arithmetic");

			// 5. Umweltselektion

			// 6. Mutation
			pNew.mutate(mutation);

			// 7. Bestimmung der Fitness der Individuen der neuen Population
			pNew.calculateFitness();

			p = pNew;
//			 System.out.println(i + ":");
//			 p.sort(0, p.getPopulation().length - 1);
//			 p.printPopulation();
//			 System.out.println();
//			 new BufferedReader(new InputStreamReader(System.in)).readLine();

			// 8. auf Abbruchkriterium prüfen, sonst 3.

			fitness_best[i] = p.best.getFitness();
			
			i++;				
		} while (i < generations);// p.evaluate());

		System.out.println();
		System.out.println("Neu:");
		p.sort(0, p.getPopulation().length - 1);
		p.printPopulation();

		GenerateCsv.generateCsvFile("d:\\test.csv",fitness_best);
		
		//Plotter plot = new Plotter(fitness_best);	
		
		//XPlotter xplot = new XPlotter(fitness_best);
	}

}


