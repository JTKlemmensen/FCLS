package database;

import java.util.List;
import logic.LoanAgreementDataModel;

public interface LoanDAO 
{
	public Boolean insertLoanAgreementDB(LoanAgreementDataModel loanAgreement);
	
	public List<LoanAgreementDataModel> getLoanList(boolean onlyNonApproved);
	
	public boolean updateLoanAgreementDb(LoanAgreementDataModel loanAgreement);
}

