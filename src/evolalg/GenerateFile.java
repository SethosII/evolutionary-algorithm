package evolalg;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GenerateFile {

	public static void savePopulationToFile(String fullPath, Population pop,
			int alleles) {
		try {
			FileWriter writer = new FileWriter(fullPath);

			String temp = "";

			for (int i = 0; i < pop.getPopulation().length; i++) {

				for (int j = 0; j < alleles; j++) {
					writer.append(Double.toString(pop.getPopulation()[i]
							.getAlleles()[j]));
					writer.append('\t');
				}

				writer.append('\n');
			}

			writer.flush();
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void generateCsvFile(String fullPath, double[] best,
			double[] worst, double[] mean, double[] geometric) {
		try {
			FileWriter writer = new FileWriter(fullPath);

			for (int i = 0; i < best.length; i++) {
				writer.append(Integer.toString(i + 1));
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
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public static void generateMeanCsvFile(String fullPath, double[] runs_best, double[] runs_worst, double[] runs_mean, double[] runs_meansquare) {
		
		try {

			FileWriter writer = new FileWriter(fullPath);


			for (int i = 0; i < runs_best.length; i++) {
			
				writer.append(Integer.toString(i+1));
				writer.append(',');
				writer.append(Double.toString(runs_best[i]));
				writer.append(',');
				writer.append(Double.toString(runs_worst[i]));
				writer.append(',');
				writer.append(Double.toString(runs_mean[i]));
				writer.append(',');
				writer.append(Double.toString(runs_meansquare[i]));
				writer.append('\n');
			}
		
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}