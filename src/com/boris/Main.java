package com.boris;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.IntStream;

public class Main {
    private static GradientDescent gradientDescent = new GradientDescent();
    private static Matrix w = null;
    private static double m, b;
    private static StringBuffer weightsSB = new StringBuffer();
    private static double[][][] date = {
            {{1.0, 1.0, 2.0}, {0.0}},
            {{1.0, 2.56, 2.54}, {0.0}},
            {{1.0, 1.42, 1.0}, {0.0}},
            {{1.0, 3.00, 2.08}, {0.0}},
            {{1.0, 3.71, 3.05}, {0.0}},
            {{1.0, 2.00, 1.50}, {0.0}},
            {{1.0, 1.0, 8.0}, {0.0}},
            {{1.0, 2.0, 4.0}, {0.0}},
            {{1.0, 3.0, 6.50}, {0.0}},
            {{1.0, 6.0, 1.0}, {0.0}},
            {{1.0, 5.0, 2.0}, {0.0}},
            {{1.0, 4.5, 5.6}, {1.0}},
            {{1.0, 6.02, 7.0}, {1.0}},
            {{1.0, 7.0, 8.0}, {1.0}},
            {{1.0, 9.06, 7.03}, {1.0}},
            {{1.0, 8.00, 8.08}, {1.0}},
            {{1.0, 9.08, 4.08}, {1.0}},
            {{1.0, 7.0, 4.0}, {1.0}},
            {{1.0, 8.0, 1.0}, {1.0}},
            {{1.0, 9.0, 9.0}, {1.0}},
    };

    public static void main(String[] args) throws Exception {
        calculAlgoritm();

    }

    private static void calculAlgoritm() throws Exception {
        double[][] xArray = new double[date.length][date[0][0].length];
        double[][] yArray = new double[date.length][1];
        IntStream.range(0, date.length).forEach(i -> {
            IntStream.range(0, date[0][0].length).forEach(j ->
                    xArray[i][j] = date[i][0][j]);
            yArray[i][0] = date[i][1][0];
        });

        Matrix x = new Matrix(xArray);
        Matrix y = new Matrix(yArray);
        w = gradientDescent.gradientDescent(x, y);
        m = -w.getData()[1][0] / w.getData()[2][0];
        b = -w.getData()[0][0] / w.getData()[2][0];

        try {
            handleComandLine();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        weightsSB.append(" wheights = (");
        IntStream.range(0, w.getData().length - 1).forEach(i -> weightsSB.append(String.format(
                "@.2f", w.getData()[i][0] + ", "
        )));
        weightsSB.append(String.format("@.2f", w.getData()[w.getData().length - 1][0]) + " )");
        System.out.println(weightsSB.toString());
    }

    public static void handleComandLine() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println(" Dati notele la probe:");
            String[] values = (bufferedReader.readLine().split(" "));

            if (values[0].equals("exit")) {
                System.exit(0);
            } else {
                try {
                    Matrix test = new Matrix(new double[][]{{1.0, Double.valueOf(values[0]), Double.valueOf(values[1])}});
                    System.out.println(gradientDescent.classify(test, w));

                } catch (Exception e) {
                    System.out.println("invalid input");
                }
            }
        }
    }
}
