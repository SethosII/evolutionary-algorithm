﻿package evolalg;

import java.sql.Time;
import java.util.Date;
import java.util.Scanner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static double fitness_best[];
	static double fitness_worst[];
	static double fitness_average[];
	static double fitness_geometric[];
	
	private static double[] runs_best;
	private static double[] runs_worst;
	private static double[] runs_mean;
	private static double[] runs_meansquare;
	
	public static void main(String args[]) throws IOException {

		int runs = 1;
		int generations = 100;
		int amountStart = 100;
		int amountEnd = 500;
		int alleles = 10;
		int lowerBound = -512;
		int upperBound = 511;
		double rate = 30.0;
		double strength = 5;
		double selectionRate = 1d/6d;
		boolean isBinary = false;
		String fitnessType = "griewank"; // null, griewank, test
		String mutationType = "linear"; // null, linear, exponential, exponentialdec, special
		String populationType = "null"; // null, linear
		String location = ".";
		boolean getVars = false;
		
		if (getVars) {
			Scanner sc = new Scanner(System.in);
			int choice = 0;
			System.out.println("Bitte Variablen eingeben:");
			System.out.println("Funktion:\n\t0 - Test\n\t1 - Griewank\n\t2 - Nullstellenberechnung\n\t--> ");
			choice = sc.nextInt();
			if (choice == 1) fitnessType = "griewank";
			else if (choice == 2) fitnessType = "null";
			else fitnessType = "test";
			System.out.print("Generationen --> ");
			generations = sc.nextInt();
			System.out.print("Binär kodiert? (0 - nein, 1 - ja) --> ");
			if (sc.nextInt() == 1) isBinary = true;
			else isBinary = false;
			System.out.print("Startpopulation: ");
			amountStart = sc.nextInt();
	//		System.out.print("Endpopulation: ");
	//		amountEnd = sc.nextInt();
			System.out.print("Allele --> ");
			alleles = sc.nextInt();
			System.out.print("Lower Bound --> ");
			lowerBound = sc.nextInt();
			System.out.print("Upper Bound --> ");
			upperBound = sc.nextInt();
			System.out.print("Mutationstyp:\n\t0 - Constant\n\t1 - Linear\n\t2 - Exponential\n\t3 - Exponential Dec\n--> ");
			choice = sc.nextInt();
			if (choice == 1) mutationType = "linear";
			else if (choice == 2) mutationType = "exponential";
			else if (choice == 3) mutationType = "exponentialdec";
			else mutationType = "null";		
		}
		
		
		runs_best = new double[generations];
		runs_worst = new double[generations];
		runs_mean = new double[generations];
		runs_meansquare = new double[generations];
		for (int i = 0; i < runs_best.length; i++) {
			runs_best[i] = 0;
			runs_worst[i] = 0;
			runs_mean[i] = 0;
			runs_meansquare[i] = 0;
		}

		fitness_best = new double[generations];
		fitness_worst = new double[generations];
		fitness_average = new double[generations];
		fitness_geometric = new double[generations];
		
		System.out.println("Evolutionäre Algorithmen");
		System.out.println("========================");

//		for (int k = 0; k < runs; k++) {
//			Population p = new Population();
//			// 1. Bestimmung der Ausgangspopulation
//			p.createPopulation(amountStart, alleles, lowerBound, upperBound,
//					isBinary);
//
////			String name = "Startpopulation_" + fitnessType + "_Allele_" + alleles + ".txt";
////			p.loadPopulation(name, amountStart);
//					
//			if (isBinary) {
//				p.toDecimal();
//			}
//			
//			// Anfangspopulation in Datei schreiben
//			String pathname = location + "\\" + fitnessType + "_Allele_" + alleles + ".txt";
//			GenerateFile.savePopulationToFile(pathname, p, alleles);
//
//			// 2. Bestimmung der Fitness der Individuen der Ausgangspopulation
//			p.calculateFitness(fitnessType);
//
//			// System.out.println("Start:");
//			// p.printPopulation();

//			double x = 10000;
//			double abbruch = 1e-10;
//			int i = 0;
//			 while (x > abbruch) {
//				// 3. Selektion
//				Population pForNew = p.selection("komma", selectionRate);
//
//				// 4. Rekombination
//				Population pNew = pForNew.recombinate(
//						"arithmetic", populationType, i, amountEnd);
//				
//				// 5. Umweltselektion
//				Population pEnv = new Population();
//				pEnv.createPopulation(p, pForNew, fitnessType);
//				//pEnv.selection("komma", selectionRate);
//				pNew.setPopulation(pEnv.getPopulation());
//
//				// 6. Mutation
//				pNew.mutate(rate, strength, i, generations, mutationType);
//
//				// 7. Bestimmung der Fitness der Individuen der neuen Population
//				pNew.calculateFitness(fitnessType);
//
//				p = pNew;
//				// System.out.println(i + ":");
//				// p.sort(0, p.getPopulation().length - 1);
//				// p.printPopulation();
//				// System.out.println();
//				// new BufferedReader(new
//				// InputStreamReader(System.in)).readLine();
//
//				// 8. auf Abbruchkriterium prüfen, sonst 3.
//
//				p.sort(0, p.getPopulation().length - 1);
////				fitness_best[i] = p.best.getFitness();
////				fitness_worst[i] = p.worst.getFitness();
////				fitness_average[i] = p.mean;
////				fitness_geometric[i] = p.meanSquare;
////				
////				//System.out.println("Generation : " + i + "\tFitness: " + fitness_best[i]);
////				
////				runs_best[i] += fitness_best[i];
////				runs_worst[i] += fitness_worst[i];
////				runs_mean[i] += fitness_average[i];
////				runs_meansquare[i] += fitness_geometric[i];
//				x = p.best.getFitness();
//				System.out.println("Generation: " + i + "\tFitness: " + x);
//				i++;
//			}		
		
		for (int k = 0; k < runs; k++) {
			Population p = new Population();
			// 1. Bestimmung der Ausgangspopulation
			p.createPopulation(amountStart, alleles, lowerBound, upperBound,
					isBinary);

//			String name = "Startpopulation_" + fitnessType + "_Allele_" + alleles + ".txt";
//			p.loadPopulation(name, amountStart);
					
			if (isBinary) {
				p.toDecimal();
			}
			
			// Anfangspopulation in Datei schreiben
			String pathname = location + "\\" + fitnessType + "_Allele_" + alleles + ".txt";
			GenerateFile.savePopulationToFile(pathname, p, alleles);

			// 2. Bestimmung der Fitness der Individuen der Ausgangspopulation
			p.calculateFitness(fitnessType);

			// System.out.println("Start:");
			// p.printPopulation();

			int i = 0;
			 while (i < generations) {
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
				
				runs_best[i] += fitness_best[i];
				runs_worst[i] += fitness_worst[i];
				runs_mean[i] += fitness_average[i];
				runs_meansquare[i] += fitness_geometric[i];
				i++;
			}
			
			for (int x = 0; x < runs_best.length; x++) {
				runs_best[x] /= runs;
				runs_worst[x] /= runs;
				runs_mean[x] /= runs;
				runs_meansquare[x] /= runs;
				
			}

			System.out.println("Run " + k + ":");
			p.getPopulation()[0].printIndividuum();
			System.out.println("mean: " + p.mean + ", meansquare: "
					+ p.meanSquare);

			String csvname = location + "\\" + fitnessType + "_Allele_" + alleles + "_run_" + k + ".csv";
			GenerateFile.generateCsvFile(csvname, fitness_best, fitness_worst, fitness_average, fitness_geometric);
			

		}
		String csvname_mean = location + "\\" + fitnessType + "_Allele_" + alleles + "_gesamt.csv";
		
		GenerateFile.generateMeanCsvFile(csvname_mean, runs_best, runs_worst, runs_mean, runs_meansquare);

	}

}