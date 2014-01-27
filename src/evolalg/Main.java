package evolalg;

public class Main {

	public static void main(String args[]) {

		int amount = 500;
		int alleles = 4;
		int lowerBound = -100;
		int upperBound = 100;

		System.out.println("Evolutionäre Algorithmen");
		System.out.println("========================");

		Population p = new Population();
		// 1. Bestimmung der Ausgangspopulation
		p.createPopulation(amount, alleles, lowerBound, upperBound);

		// 2. Bestimmung der Fitness der Individuen der Ausgangspopulation
		p.calculateFitness();

		int i = 0;
		do {
			// 3. Selektion
			Population pForNew = p.selection("plus");

			// 4. Rekombination
			Population pNew = pForNew.recombinate(p.getPopulation().length,"intermediate");

			// 5. Umweltselektion

			// 6. Mutation
			pNew.mutate(10.0);

			// 7. Bestimmung der Fitness der Individuen der neuen Population
			pNew.calculateFitness();

			p = pNew;

			// 8. auf Abbruchkriterium prüfen, sonst 3.

			i++;
		} while (i < 8);// p.evaluate());

		System.out.println("Neu:");
		p.printPopulation();
		
	}

}
