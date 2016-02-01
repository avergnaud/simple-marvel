package marvel.write;

import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;	

/**
 * TODO http://stackoverflow.com/questions/3051276/is-it-okay-to-use-try-catch-inside-finally
 * @author Adrien
 *
 */
public class Writer {
	public static void write(StringBuilder sb, String filePath) {		
		BufferedWriter bw = null;
		try {
			File file = new File(filePath);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			bw.write(sb.toString());
			bw.flush();
			bw.close();
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			if(bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
}