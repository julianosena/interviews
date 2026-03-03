package com.interviews.taller.processor;

import com.interviews.taller.domain.Payment;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PaymentProcessor {

	private final List<Payment> payments = new ArrayList<>();

	public void addPayment(Payment payment){
		payments.add(payment);
		Comparator<Payment> comparator = Comparator.comparing(Payment::getAmount);
		payments.sort(comparator);
	}

	public List<Payment> payments(){
		return this.payments;
	}

	public List<Payment> payments(Payment.Status status){
		return this.payments.stream()
				.filter( (payment) -> payment.getStatus() == status)
				.toList();
	}

	public int getTotalNumberOfPayments(){
		return this.payments.size();
	}

	public int getTotalAmountOfSuccessPayments(){
		return this.payments(Payment.Status.SUCCESS).size();
	}

	public double getAverageAmountOfSuccessPayments(){
		return (double) this.payments(Payment.Status.SUCCESS).size() / this.payments.size();
	}


}
