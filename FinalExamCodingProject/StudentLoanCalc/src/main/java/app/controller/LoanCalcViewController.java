package app.controller;

import app.StudentCalc;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;

import Helper.Loan;

import Helper.Payment;

public class LoanCalcViewController implements Initializable   {


	private StudentCalc SC = null;
	
	@FXML
	private TextField LoanAmount;
	
	@FXML
	private TextField InterestRate;
	
	@FXML
	private TextField NbrOfYears;
	
	@FXML
	private TextField AdditionalPayments;
	
	@FXML
	private Label lblTotalPayemnts;
	
	@FXML
	private Label lblTotalInterest;
	
	@FXML
	private Label numPaymentlbl;
	
	@FXML
	private DatePicker PaymentStartDate;
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

	public void setMainApp(StudentCalc sc) {
		this.SC = sc;
	}
	
	/**
	 * btnCalcLoan - Fire this event when the button clicks
	 * 
	 * @version 1.0
	 * @param event
	 */
	@FXML
	private void btnCalcLoan(ActionEvent event) {

		System.out.println("Amount: " + LoanAmount.getText());
		double dLoanAmount = Double.parseDouble(LoanAmount.getText());
		System.out.println("Amount: " + dLoanAmount);
		double dInterestRate = Double.parseDouble(InterestRate.getText());
		double iTerm = Double.parseDouble(NbrOfYears.getText());
		int intTerm = (int) iTerm;
		double dExtraPayment = Double.parseDouble(AdditionalPayments.getText());
		
		LocalDate localDate = PaymentStartDate.getValue();
	
		java.util.Date updatedDate = java.sql.Date.valueOf(localDate);
		
		System.out.println("Rate: " + dInterestRate);
		System.out.println("Term: " + iTerm);
		System.out.println("Start Date: " + localDate);
		System.out.println("Extra: " + dExtraPayment);
		
		Loan l = new Loan(dLoanAmount, dInterestRate, intTerm, 0.0, dExtraPayment, updatedDate, false);
		
		String labelPayments = String.valueOf(l.getLoanPaymentSize());
		String labelPMT = String.valueOf(l.getPMT());
		String labelIPMT = String.valueOf(l.getIPMT());
		numPaymentlbl.setText(labelPayments);
		
		lblTotalPayemnts.setText(labelPMT);
		lblTotalInterest.setText(labelIPMT);
		
	 
		System.out.println(localDate);
	}
}
