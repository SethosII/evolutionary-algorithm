package evolalg;

public class Main {
	
	public static void main(String args[])
	{
		System.out.println("Evolutionäre Algorithmen");
		System.out.println("========================");
				
		Population p = new Population();
		
		//p.createPopulation(100,  10,  1000);
		//p.printPopulation("bin");
		
		/*String value = "011010011010101";
		System.out.println("Value: " + value);
		System.out.println("Gray:  " + Conversion.toGray(value));
		System.out.println("Back:  " + Conversion.toBin(Conversion.toGray(value)));
		*/
		/*
		p.createPopulation(2, 100,  1000);
		Individuum[] ind = p.getPopulation();
		p.printPopulation("bin");
		System.out.println("\n");
		String[] test = Recombination.one_Point_Recombination(ind[0].toBinString(), ind[1].toBinString());
		for (String string : test) {
			System.out.println(string);
		}*/
		
		p.createPopulation(2, 100,  1000);
		Individuum[] ind = p.getPopulation();
		p.printPopulation("bin");
		System.out.println("\n");
		String[] test = Recombination.two_Point_Recombination(ind[0].toBinString(), ind[1].toBinString());
		for (String string : test) {
			System.out.println(string);
		}
		
		
	}
	
}
