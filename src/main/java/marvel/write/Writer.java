package marvel.write;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.io.IOUtils;

public class Writer {
	
	/**
	 * écrit le contenu de sb  dans un fichier filePath
	 * @param sb
	 * @param filePath
	 */
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
			IOUtils.closeQuietly(bw);
		}
		
	}
}