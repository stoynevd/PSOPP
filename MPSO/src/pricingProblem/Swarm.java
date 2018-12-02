package pricingProblem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Swarm {

	private Supermarket[] supermarkets;

	private double[] globalBest;

	private int numberOfGoods;

	public Swarm(int numberOfGoods, int numberOfsupermarkets) {

		this.numberOfGoods = numberOfGoods;

		supermarkets = new Supermarket[numberOfsupermarkets];

		globalBest = new double[1];

		for (int i = 0; i < numberOfsupermarkets; i++) {

			double[] a = { 0.0, 0.0, 0.0 };

			double[] initialPosition = randomPrices(numberOfGoods);

			supermarkets[i] = new Supermarket(initialPosition, a, initialPosition);

		}

		for (int i = 0; i < numberOfsupermarkets; i++) {

			supermarkets[i].setVelocity(generateVelocity(numberOfGoods));

		}

	}

	public int getnumberOfGoods() {
		return numberOfGoods;
	}

	public void setnumberOfGoods(int numberOfGoods) {
		this.numberOfGoods = numberOfGoods;
	}

	public double[] randomPrices(int problemSize) {

		double[] design = new double[problemSize];

		double min = 0.1;

		double max = 10.0;

		for (int i = 0; i < design.length; i++) {

			double d = min + new Random().nextDouble() * (max - min);

			design[i] = d;

		}
		return design;
	}

	public double[] generateVelocity(int numberOfGoods) {

		double[] velocities = new double[numberOfGoods];

		double[] firstDesign = supermarkets[0].getPrices();

		double[] secondDesign = randomPrices(numberOfGoods);

		for (int i = 0; i < numberOfGoods; i++) {

			velocities[i] = (secondDesign[i] - firstDesign[i]) / 2;

		}

		return velocities;
	}

	public Supermarket[] getSupermarkets() {
		return supermarkets;
	}

	public void setsupermarkets(Supermarket[] supermarkets) {
		this.supermarkets = supermarkets;
	}

	public double[] getGlobalBest() {
		return globalBest;
	}

	public void setGlobalBest(double[] globalBest) {
		this.globalBest = globalBest;
	}

	public String toString() {

		return globalBest.toString();

	}

}
