package csv;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import logic.LoanHandler;

public class LoanToCSV {
	private BufferedWriter writer;

	public LoanToCSV(String fileName) throws IOException {
		writer = new BufferedWriter(new FileWriter(fileName + ".csv"));
	}

	/**
	 * Prints all info connected to the loan in this order:
	 * LoanHandler, LoanAgreementDataModel, Customer, Seller, Car and Payments
	 * @param loanHandler
	 * @throws IOException
	 */
	public void write(LoanHandler loanHandler) throws IOException {
		Object[] list = new Object[5];
		Object[] payments = loanHandler.getPayments().toArray();
		
		list[0] = loanHandler;
		list[1] = loanHandler.getLoanAgreementDataModel();
		list[2] = loanHandler.getLoanAgreementDataModel().getCustomer();
		list[3] = loanHandler.getLoanAgreementDataModel().getSeller();
		list[4] = loanHandler.getLoanAgreementDataModel().getCar();
		
		writeLine(list);
		writeLine(payments);
	}

	public void writeLine(Object[] line) throws IOException {
		for (int i = 0; i < line.length; i++) {
			writer.write(line[i].toString());
			writer.newLine();
		}
	}

	public void flush() throws IOException {
		writer.flush();
	}

	public void close() throws IOException {
		writer.close();
	}
}
