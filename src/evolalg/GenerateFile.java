package evolalg;

import java.io.FileWriter;
import java.io.IOException;
 
public class GenerateFile
{

	public static void savePopulationToFile(String fullPath, Population pop, int alleles) {
		try {
			FileWriter writer = new FileWriter(fullPath);
			
			String temp = "";
			
			for (int i = 0; i < pop.getPopulation().length; i++) {
				temp = "Individuum " + i + ":\t";
				writer.append(temp);
				
				for(int j = 0; j < alleles; j++) {
					writer.append(Double.toString(pop.getPopulation()[0].getAlleles()[j]));
					writer.append('\t');
				}
				
				//writer.append(pop.getPopulation().printIndividuum());
				writer.append('\n');						
			}
			
		    writer.flush();
		    writer.close();
			
		}
		catch(IOException e) 
		{
			e.printStackTrace();
		}
	}
	
   public static void generateCsvFile(String fullPath, double[] best, double[] worst, double[] mean, double[] geometric)
   {
	try
	{
	    FileWriter writer = new FileWriter(fullPath);
	    
	    for (int i = 0; i < best.length - 1; i++) {
	    writer.append(Integer.toString(i+1));
	    writer.append(',');
	    writer.append(Double.toString(best[i]));
	    writer.append(',');
	    writer.append(Double.toString(worst[i]));
	    writer.append(',');
	    writer.append(Double.toString(mean[i]));
	    writer.append(',');
	    writer.append(Double.toString(geometric[i]));
	    writer.append('\n');
	    }

	    writer.flush();
	    writer.close();
	}
	catch(IOException e)
	{
	     e.printStackTrace();
	} 
  }
}