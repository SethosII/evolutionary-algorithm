package evolalg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String args[]) {

		int amount = 100;
		int alleles = 2;
		int lowerBound = -10;
		int upperBound = 10;
		boolean isBinary = false;

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
			pNew.mutate(30.0);

			// 7. Bestimmung der Fitness der Individuen der neuen Population
			pNew.calculateFitness();

			p = pNew;
//			System.out.println(i + ":");
//			p.sort(0, p.getPopulation().length - 1);
//			p.printPopulation();
//			System.out.println();
//			try {
//				new BufferedReader(new InputStreamReader(System.in)).readLine();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}

			// 8. auf Abbruchkriterium prüfen, sonst 3.

			i++;
		} while (i < 20);// p.evaluate());

		System.out.println();
		System.out.println("Neu:");
		p.printPopulation();

	}

}
