package csv;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import logic.LoanHandler;

public class CSVWriter {
	private BufferedWriter writer;
	private String separator;

	public CSVWriter(String fileName, String separator) throws IOException {
		writer = new BufferedWriter(new FileWriter(fileName + ".csv"));
		this.separator = separator;
	}

	public void write(LoanHandler loanHandler) throws IOException {
		writer.write(loanHandler.toString());
	}

	public void writeLine(Object[] line) throws IOException {
		for (int i = 0; i < line.length; i++) {
			if (i != 0)
				writer.write(separator);
			writer.write(line[i].toString());
		}
		writer.newLine();
	}

	public void flush() throws IOException {
		writer.flush();
	}

	public void close() throws IOException {
		writer.close();
	}
}
