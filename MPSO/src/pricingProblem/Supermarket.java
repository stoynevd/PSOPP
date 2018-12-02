package pricingProblem;

import java.util.Arrays;

public class Supermarket {

	private double[] prices;

	private double[] velocity;

	private double[] personalBest;

	public Supermarket(double[] prices, double[] velocity, double[] personalBest) {

		this.prices = prices;

		this.velocity = velocity;

		this.personalBest = personalBest;

	}

	public double[] getPrices() {
		return prices;
	}

	public void setprices(double[] prices) {
		this.prices = prices;
	}

	public double[] getVelocity() {
		return velocity;
	}

	public void setVelocity(double[] velocity) {
		this.velocity = velocity;
	}

	public double[] getPersonalBest() {
		return personalBest;
	}

	public void setPersonalBest(double[] personalBest) {
		this.personalBest = personalBest;
	}

	public void updateVelocity(int particleSize, double[] globalBest, double w, double c1, double c2) {

//		double w = 0.1;
//		
//		double c1 = 1.5;
//
//		double c2 = 2.0;

		double[] newVelocity = new double[particleSize];

		double[] currentPrice = Arrays.copyOf(getPrices(), getPrices().length);

		for (int i = 0; i < particleSize; i++) {

			newVelocity[i] = w * this.getVelocity()[i]
					+ c1 * Math.random() * (this.getPersonalBest()[i] - this.getPrices()[i])
					+ c2 * Math.random() * (globalBest[i] - this.getPrices()[i]);

			currentPrice[i] = currentPrice[i] + newVelocity[i];

			if (newVelocity[i] > 10) {

				newVelocity[i] = 10;

			}

			if (currentPrice[i] > 10) {

				currentPrice[i] = 10;

			} else if (currentPrice[i] < 0.01) {

				currentPrice[i] = 0.01;

			}
		}

		this.setprices(Arrays.copyOf(currentPrice, currentPrice.length));

		this.setVelocity(Arrays.copyOf(newVelocity, newVelocity.length));

	}

}
