package pricingProblem;

import java.util.Random;

public class mainActivity {

	public static void main(String[] args) {
//
		Swarm swarm = new Swarm(20, 5);
//
//		pso.performPSO(new PricingProblem(swarm.getnumberOfGoods(), new Random()), swarm, 2);
		
		PSO pso = new PSO();
//		
//		pso.randomSolution(new PricingProblem(10, new Random()), 10);
		
		pso.performPSO(PricingProblem.courseworkInstance(), swarm, 50);

	}

}
