package com.interviews.taller;

/**
 * Scenario:
 * You are asked to implement a small Java program that processes a list of Payment objects. Each payment has:
 * - `id` (String)
 * - `amount` (double)
 * - `currency` (String)
 * - `status` (enum: `PENDING`, `SUCCESS`, `FAILED`)
 *
 * Requirements:
 * 1. Create the `Payment` class with proper encapsulation and `toString()`.
 * 2. Implement a `PaymentProcessor` class with methods to:
 * - Add a new payment.
 * - Retrieve all payments.
 * - Retrieve payments filtered by status.
 * - Calculate statistics:
 * - Total number of payments.
 * - Total amount of successful payments.
 * - Average amount of successful payments.
 * 3. Demonstrate usage in a `main()` method:
 * - Create a few sample payments.
 * - Process them.
 * - Print statistics.
 * 4. Bonus (if time allows):
 * - Sort payments by amount (descending).
 * - Use Java Streams to implement filtering and statistics.
 * - Add a simple concurrency simulation: process payments in parallel using `CompletableFuture` or `parallelStream()`.
 */

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

	public static void main(String[] args) {
		//TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
		// to see how IntelliJ IDEA suggests fixing it.
		System.out.printf("Hello and welcome!");

		for (int i = 1; i <= 5; i++) {
			//TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
			// for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
			System.out.println("i = " + i);
		}
	}
}