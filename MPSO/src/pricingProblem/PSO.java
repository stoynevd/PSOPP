package pricingProblem;

import java.util.Arrays;
import java.util.Random;

public class PSO {

	public void performPSO(PricingProblem pp, Swarm swarm, int numberOfIterations) {

		double lowest = 0.0;

		int iterations = 0;

		double w = 0.0;

		double c1 = 0.0;

		double c2 = 0.0;

		while (iterations < numberOfIterations) {

			if (Math.random() > 0.5) {

				// Linearly decreasing inertia
				w = (0.9 - 0.1) * ((numberOfIterations - iterations) / numberOfIterations) + 0.1;

				c1 = 2.5 - (2.5 - 0.5) * iterations / (numberOfIterations - 1);

				c2 = 0.5 - (0.5 - 2.5) * iterations / (numberOfIterations - 1);
				
			} else {

				// randomly decreasing inertia
				w = 0.5 + Math.random() / 2.0;

				c1 = 2.5 - (2.5 - 0.5) * iterations / (numberOfIterations - 1);

				c2 = 0.5 - (0.5 - 2.5) * iterations / (numberOfIterations - 1);
				
			}

			for (Supermarket supermarket : swarm.getSupermarkets()) {

				if (pp.evaluate(supermarket.getPersonalBest()) > lowest) {

					lowest = pp.evaluate(supermarket.getPersonalBest());

					swarm.setGlobalBest(
							Arrays.copyOf(supermarket.getPersonalBest(), supermarket.getPersonalBest().length));

				}

			}

			for (Supermarket supermarket : swarm.getSupermarkets()) {

				if (pp.evaluate(supermarket.getPrices()) > pp.evaluate(supermarket.getPersonalBest())) {

					supermarket.setPersonalBest(Arrays.copyOf(supermarket.getPrices(), supermarket.getPrices().length));

				}

				supermarket.updateVelocity(swarm.getnumberOfGoods(), swarm.getGlobalBest(), w, c1, c2);

			}

			iterations++;

		}

		for (Supermarket supermarket : swarm.getSupermarkets()) {

			System.out.println("\n");

			int index = Arrays.asList(swarm.getSupermarkets()).indexOf(supermarket) + 1;

			System.out.println(
					"Supermarket: " + index + " has revenue of: " + pp.evaluate(supermarket.getPersonalBest()));

			System.out.println("Prices for the 20 goods of Supermarket " + index + ": ");

			System.out.println(Arrays.toString(supermarket.getPersonalBest()) + "\n");

		}

		System.out.println("Swarms best revenue is: " + pp.evaluate(swarm.getGlobalBest()));

		System.out.println("Number of fitness function calls: " + PricingProblem.COUNT);

	}

	public void randomSolution(PricingProblem pp, int problemSize) {

		double[] design = new double[problemSize];

		Random generator = new Random();

		while (!pp.is_valid(design)) {

			double min = 0.1;

			double max = 10.0;

			for (int i = 0; i < design.length - 1; i++) {

				double d = min + generator.nextDouble() * (max - min);

				design[i] = d;

			}

		}

		System.out.println(pp.evaluate(design));
	}

}
