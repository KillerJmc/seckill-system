package com.lingyuango.seckill.mock.utils;

import java.util.random.RandomGenerator;

/**
 * @author ChaconneLuo
 */
public class RandomGeneratorTool {

    private RandomGeneratorTool() {

    }

    static final RandomGenerator generator = RandomGenerator.getDefault();

    public static Boolean getRandomBoolean(double trueProbability) {
        if (trueProbability < 0 || trueProbability > 1) {
            throw new IllegalArgumentException("The probability should in [0,1]");
        }
        return generator.nextInt(1000000) < 1000000 * trueProbability;
    }

    public static Integer getRandomInteger(int from, int bound, double lessThanProbability, int flagValue) {
        if (lessThanProbability < 0 || lessThanProbability > 1) {
            throw new IllegalArgumentException("The probability should in [0,1]");
        }
        if (flagValue <= from || flagValue >= bound) {
            throw new IllegalArgumentException("The flagValue should not less than from and greater than bound");
        }
        if (getRandomBoolean(lessThanProbability)) {
            return generator.nextInt(from, flagValue + 1);
        } else {
            return generator.nextInt(flagValue + 1, bound);
        }
    }

    public static Long getRandomLong() {
        return generator.nextLong(Integer.MAX_VALUE, Long.MAX_VALUE);
    }

    public static Double getRandomMoney() {
        return generator.nextDouble(10000,50000);
    }

}
