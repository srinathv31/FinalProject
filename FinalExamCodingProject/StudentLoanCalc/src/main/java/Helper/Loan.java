package Helper;

import java.util.LinkedList;

import org.apache.poi.ss.formula.functions.Finance;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

public class Loan {
	
	double dLoanAmount;
	double dInterestRate;
	double iTerm;
	double dFutureValue;
	boolean bInterestCalc;
	double dExtraPayment;
	
	private LinkedList<Payment> loanPayments = new LinkedList<Payment>();
	
	public Loan(double LoanAmount, double FutureValue, double InterestRate, double Term, double ExtraPayment, Date firstDate, boolean bInterestCalc) {
		super();
		
	dLoanAmount = LoanAmount;
	dInterestRate = InterestRate;
	iTerm = Term;
	dFutureValue = FutureValue;
	dExtraPayment = ExtraPayment;
	
	this.bInterestCalc = bInterestCalc;
	
	int paymentNum = 0;
	
	double balanceChange = this.dLoanAmount;
		do {
			
			Payment p = new Payment(++paymentNum, firstDate, balanceChange, this);
			loanPayments.add(p);
			balanceChange = p.getBalance();
			
			System.out.println("Payment " + paymentNum + ":" + loanPayments.getLast().getBalance());
			
			if (loanPayments.getLast().getBalance() <= 0) {
				loanPayments.getLast().Zero();
				break;
			}
		} while (true);
	
	}
	
	public double getLoanAmount() {
		return dLoanAmount;
	}
	
	public double getInterestRate() {
		return dInterestRate;
	}
	
	public double getTerm() {
		return iTerm;
	}
	
	public double getExtraPayment() {
		return dExtraPayment;
	}
	
	public double getFutureValue() {
		return dFutureValue;
	}
	
	public int getLoanPaymentSize() {
		return loanPayments.size();
	}
	
	public double getIPMT() {
		double y = 0;
			for (int i = 0; i < getLoanPaymentSize(); i++) {
				y += Math.abs(Finance.ipmt(dInterestRate/12, i, (int) (iTerm*12), dLoanAmount));
				y = Math.round(y*100.00)/100.00;
			}
		return y;
	}
	
	public double getPMT() {
		double x = Math.abs(Finance.pmt(dInterestRate/12, (int) (iTerm*12), dLoanAmount));
		return Math.round(x*100.00)/100.00;
	}
	
	public static Date parseDate(String date) {
		try {
			return new SimpleDateFormat("yyyy.MM.dd").parse(date);
		} catch (ParseException e) {
			return null;
		}
	}
}
