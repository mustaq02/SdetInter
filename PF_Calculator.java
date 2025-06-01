package com.inetbanking.testCases;

import java.util.Scanner;

public class PF_Calculator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		MonthlyIncome , AgeOfEmp, TotalPF
		Scanner X = new Scanner(System.in);
		float MonthlyIncome = 0;
		int AgeOfEmp = 0;
		float basicPay = 0;
		float employeeFund =0;
		float employerFund = 0;
		float toalPF = 0;
		System.out.println("Enter monthly pay of employee");
		MonthlyIncome  = X.nextFloat();
		System.out.println("Enter Age of employee");
		AgeOfEmp = X.nextInt();
		
		if(AgeOfEmp <= 50) {
			employeeFund = MonthlyIncome/100 * 15 ;
			employerFund = (float) (MonthlyIncome/100 * 12.5) ;
			toalPF = employeeFund + employerFund;
		} else if(AgeOfEmp > 50 && AgeOfEmp <= 60){
			employeeFund = (float) (MonthlyIncome/100 * 12.5) ;
			employerFund = MonthlyIncome/100 * 10 ;
			toalPF = employeeFund + employerFund;
		}else if(AgeOfEmp > 60) {
			employeeFund = MonthlyIncome/100 * 10 ;
			employerFund = (float) (MonthlyIncome/100 * 7.5) ;
			toalPF = employeeFund + employerFund;
		}
		
		System.out.println("Total PF value is :" +toalPF);
	}

}
