package com.interviews.taller;

import com.interviews.taller.domain.Payment;
import com.interviews.taller.processor.PaymentProcessor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentProcessorTest {

	@Test
	@DisplayName("It should add a payment into processor with successs")
	public void itShouldAddPaymentWithSuccess(){
		// given
		Payment payment = new Payment();
		payment.setId("123");
		payment.setAmount(13.0);
		payment.setCurrency("USD");
		PaymentProcessor paymentProcessor = new PaymentProcessor();

		// when
		paymentProcessor.addPayment(payment);


		// then
		assertEquals(1, paymentProcessor.payments().size());
		Payment addedPayment = paymentProcessor.payments().getFirst();
		assertEquals("123", addedPayment.getId());
		assertEquals(13.0, addedPayment.getAmount());
		assertEquals("USD", addedPayment.getCurrency());
		assertEquals(Payment.Status.PENDING, addedPayment.getStatus());
	}

	@Test
	@DisplayName("It should retrieve all payments with successs")
	public void itShouldAllPaymentsWithSuccess(){
		// given
		Payment payment1 = new Payment();
		payment1.setId("123");
		payment1.setAmount(13.0);
		payment1.setCurrency("USD");
		Payment payment2 = new Payment();
		payment2.setId("123");
		payment2.setAmount(13.0);
		payment2.setCurrency("USD");
		PaymentProcessor paymentProcessor = new PaymentProcessor();

		// when
		paymentProcessor.addPayment(payment1);
		paymentProcessor.addPayment(payment2);


		// then
		assertEquals(2, paymentProcessor.payments().size());
	}

	@Test
	@DisplayName("It should retrieve all failed payments with successs")
	public void itShouldAllFailedPaymentsWithSuccess(){
		// given
		Payment payment1 = new Payment();
		payment1.setId("123");
		payment1.setAmount(13.0);
		payment1.setCurrency("USD");

		Payment payment2 = new Payment();
		payment2.setId("123");
		payment2.setAmount(13.0);
		payment2.setCurrency("USD");
		payment2.setStatus(Payment.Status.FAILED);

		PaymentProcessor paymentProcessor = new PaymentProcessor();


		// when
		paymentProcessor.addPayment(payment1);
		paymentProcessor.addPayment(payment2);

		// then
		assertEquals(1, paymentProcessor.payments(Payment.Status.FAILED).size());
	}

	@Test
	@DisplayName("It should get all statistics right with successs")
	public void itShouldGetAllStatisticsRightWithSuccess(){
		// given
		Payment payment1 = new Payment();
		payment1.setId("1");
		payment1.setAmount(13.0);
		payment1.setCurrency("USD");
		payment1.setStatus(Payment.Status.SUCCESS);

		Payment payment2 = new Payment();
		payment2.setId("2");
		payment2.setAmount(13.0);
		payment2.setCurrency("USD");
		payment2.setStatus(Payment.Status.FAILED);

		Payment payment3 = new Payment();
		payment3.setId("3");
		payment3.setAmount(23.0);
		payment3.setCurrency("USD");
		payment3.setStatus(Payment.Status.FAILED);

		PaymentProcessor paymentProcessor = new PaymentProcessor();


		// when
		paymentProcessor.addPayment(payment1);
		paymentProcessor.addPayment(payment2);
		paymentProcessor.addPayment(payment3);

		// then
		assertEquals(3, paymentProcessor.getTotalNumberOfPayments());
		assertEquals(1, paymentProcessor.getTotalAmountOfSuccessPayments());
		assertEquals(0.3333333333333333, paymentProcessor.getAverageAmountOfSuccessPayments());
	}

}
