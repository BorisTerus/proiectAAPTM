package com.boris;

import java.util.stream.IntStream;

public class GradientDescent {
    static final double ALPHA = 0.05;
    static final int MAX_NUM_OF_ITERATIONS = 750;

    public Matrix gradientDescent(Matrix x, Matrix y) throws Exception {
        double[][] wArray = new double[x.getData()[0].length][1];
        IntStream.range(0, x.getData()[0].length).forEach(i -> wArray[i][0] = 1);
        Matrix w = new Matrix(wArray);
        for (int i = 0; i < MAX_NUM_OF_ITERATIONS; i++) {
            w = w.subtract(x.transpose().scalarOperation(ALPHA, Matrix.ScalarOperation
                    .MULTIPLY).multiply((logistic(x.multiply(w))).subtract(y)));
        }
        return w;
    }

    public String classify(Matrix x, Matrix w) throws Exception {
        String returnValue = null;
        double prob = logistic(x.multiply(w)).getData()[0][0];
        if (prob <= 0.5) {
            returnValue = "prob= " + prob + " Candidat Respins";
        } else {
            returnValue = "prob= " + prob + " Cadidat Acceptat";

        }
        return returnValue;
    }


    private Matrix logistic(Matrix ws) {
        return ws.exp().oneOver().scalarOperation(1, Matrix.ScalarOperation.ADD).oneOver();
    }
}
