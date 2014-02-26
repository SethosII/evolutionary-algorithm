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

	public static void generateMeanCsvFile(String fullPath, String partname,
			int runs, int generations) {
		try {

			FileWriter writer = new FileWriter(fullPath);
			BufferedReader[] in = new BufferedReader[runs];
			for (int i = 0; i < runs; i++) {

				String name = "" + partname + "" + i + ".csv";
				in[i] = new BufferedReader(new FileReader(name));
			}
			System.out.println("test " + in[1].readLine());
			for (int j = 0; j < generations; j++) {
				String[][] str = new String[runs][5];
				for (int i = 0; i < runs; i++) {
					String tmp = in[i].readLine();
					System.out.println(i + " temp: " +tmp);
					str[i]=tmp.split(",");
				}
				double[] temp = new double[5];
				for (int k = 0; k < runs; k++) {
				for (int i = 0; i < str[0].length; i++) {
					temp[i] += Double.parseDouble(str[k][i]);
				}}
				for (int i = 0; i < str[0].length; i++) {
					temp[i] = temp[i]/runs;
					System.out.println(temp[i]);
				}
				for (int i = 0; i < 5; i++) {
					writer.append(Double.toString(temp[i]));
					writer.append(',');
				}
				writer.append('\n');
			}



			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}