package evolalg;

import java.io.FileWriter;
import java.io.IOException;
 
public class GenerateCsv
{
   public static void generateCsvFile(String fullPath, double[] values)
   {
	try
	{
	    FileWriter writer = new FileWriter(fullPath);
	    
	    for (int i = 0; i < values.length - 1; i++) {
	    writer.append(Integer.toString(i+1));
	    writer.append(',');
	    writer.append(Double.toString(values[i]));
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