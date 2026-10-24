package loanamortizationschedule;

/*
 * Name: Emily Ly
 * Class: CIS 35A
 * Assignment: Assignment 1 Lab -2, Project 1
 * Due Date: 10/4/26
 * Date Submitted: 10/2/26
 *
 * Description: A loan amortization schedule
 *              Reads loan amount, number of years, and annual interest rate.
 *              Calculates monthly payment, total payment, interest, principal, and balance.
 *              Prints table containing payment#, interest, principal, and balance for each month.
 *              Prints total payment and monthly payment.
 */

import java.util.Scanner;
public class LoanAmortizationSchedule {
	public static double convertAnnualRateToDecimal(double annualInterestRate) {
		return annualInterestRate = annualInterestRate / 100.0;
	}
	public static double computeMonthlyInterestRate (double annualInterestRate) {
		return annualInterestRate / 12.0;
	}
	public static double computeTotalNumberOfMonthlyPayments (double numberOfYears) {
		return numberOfYears * 12;
	}
	public static double computeMonthlyPayment(double loanAmount, double monthlyInterestRate, double n) {
		double payment = (loanAmount * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, n)) / (Math.pow(1 + monthlyInterestRate, n) - 1); 
		return Math.floor(payment*100) / 100;
	}
	public static double computeExactMonthlyPayment(double loanAmount, double monthlyInterestRate, double n) {
		return (loanAmount * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, n)) / (Math.pow(1 + monthlyInterestRate, n) - 1); 
	}
	public static double computeTotalPayment (double exactMonthlyPayment, double n) {
		return exactMonthlyPayment * n;
	}
	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
	
		//Prompt for loan amount, number of years, and annual interest rate
		System.out.printf("Enter loan amount: ");
		double loanAmount = in.nextDouble();
	
		System.out.printf("Enter number of years: ");
		double numberOfYears = in.nextDouble();
	
		System.out.printf("Enter annual interest rate: ");
		double annualInterestRate = in.nextDouble();
	
		
		//Monthly payment + total payment calculation
		annualInterestRate = convertAnnualRateToDecimal(annualInterestRate);
		double monthlyInterestRate = computeMonthlyInterestRate(annualInterestRate);
		double n = computeTotalNumberOfMonthlyPayments (numberOfYears);
		double monthlyPayment = computeMonthlyPayment(loanAmount, monthlyInterestRate, n);
		
		double exactMonthlyPayment = computeExactMonthlyPayment(loanAmount, monthlyInterestRate, n);
		double totalPayment = computeTotalPayment(exactMonthlyPayment, n);
		
		//Declares balance
		double balance = loanAmount;
		
		//Displays monthly payment and total payment
		System.out.printf("\nMonthly Payment: %.2f\n", monthlyPayment);
		System.out.printf("Total Payment: %.2f\n\n", totalPayment);
		
		//Header: Payment#, Interest, Principal, Balance
		System.out.printf("%-14s %-14s %-14s %-14s\n",
						  "Payment#", "Interest", "Principal", "Balance");
		
		//Loop for monthly table
		for (int i = 1; i <=n; i++) {
			double interest = Math.floor(monthlyInterestRate * balance * 100) / 100;
			double payment = monthlyPayment;
			double principal = monthlyPayment - interest;
			balance = balance - principal;

		//Final payment absorbs any remaining balance
		if (i == n && balance > 0) {
			payment = payment + balance;
			principal = principal + balance;
			balance = 0;
		}
		
		//Prints payments interest principal and balance values
		System.out.printf("%-15d%-15.2f%-15.2f%-15.2f%n", i, interest, principal, balance);
		
		}
	}
}
