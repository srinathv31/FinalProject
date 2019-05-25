package Helper;

import java.util.Date;

import org.apache.poi.ss.formula.functions.Finance;

public class Payment {
	int paymentID;
	Date due;
	double IPMT;
	double PPMT;
	double principleTotal;
	double balance;
	Loan l;
	
	public double getBalance() {
		return balance;
	}
	
	public double getPPMT () { 
		return Finance.ppmt(l.getInterestRate()/12, this.paymentID, (int) (l.getTerm()*12), l.getLoanAmount());
		
	}
	
	public void Zero() {
		balance = 0;
	}
	
	public Payment(int pmtID, Date dueDate, double balance1, Loan l) {
		paymentID = pmtID;
		due = dueDate;
		balance = balance1;
		this.l = l;
		this.IPMT = balance1 + l.getInterestRate()/12;
		this.PPMT = Math.abs(getPPMT());
		this.principleTotal = this.PPMT + l.getExtraPayment();
		this.balance = balance1 - this.principleTotal;
	}

}
