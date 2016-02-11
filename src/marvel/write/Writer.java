package marvel.write;

import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;	

public class Writer {
	public static void write(StringBuilder sb, String filePath) {		
		try {
			File file = new File(filePath);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(sb.toString());
			bw.flush();
			bw.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}
}