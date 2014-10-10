package graphs.dijikstra.helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class FileReader {

	@SuppressWarnings("resource")
	public static List<String> getLines(String path) {

		File file = new File(path);
		List<String> lineList = null;
		BufferedReader bufferedReader = null;

		try {
			bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			lineList = new ArrayList<String>();
			while (bufferedReader.ready()) {
				StringBuilder line = new StringBuilder();
				line.append(new String(bufferedReader.readLine()));
				if (!line.toString().isEmpty())
					lineList.add(line.toString());
			}
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return lineList;
	}

}
