package evolalg;

import java.io.FileWriter;
import java.io.IOException;
 
public class GenerateCsv
{
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