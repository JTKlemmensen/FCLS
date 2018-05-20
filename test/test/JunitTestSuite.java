package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)

@Suite.SuiteClasses({
   TestLoanAgreementDataModel.class,
   TestLoanHandler.class,
   TestCarDataModel.class,
   TestCustomerDataModel.class,
   TestSellerDataModel.class
})

public class JunitTestSuite {   
}  