package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private double[] numberPerc;
    private double average;
    private double stddiff;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        numberPerc = new double[T];
        for (int i = 0; i < T; i++) {
            double count = 0;
            Percolation percolation = pf.make(N);
            while (!percolation.percolates()) {
                int position = StdRandom.uniform(N * N);
                if (percolation.isOpen(position / N, position % N)) {
                    continue;
                }
                percolation.open(position / N, position % N);
                count += 1;
            }
            double frac = count / (N * N);
            numberPerc[i] = frac;
        }
    }   // perform T independent experiments on an N-by-N grid

    public double mean() {
        average = StdStats.mean(numberPerc);
        return average;
    }   // sample mean of percolation threshold

    public double stddev() {
        stddiff = StdStats.stddev(numberPerc);
        return stddiff;
    }   // sample standard deviation of percolation threshold

    public double confidenceLow() {
        return average - 1.96 * stddiff / Math.pow(numberPerc.length, 0.5);
    }   // low endpoint of 95% confidence interval

    public double confidenceHigh() {
        return average + 1.96 * stddiff / Math.pow(numberPerc.length, 0.5);
    }   // high endpoint of 95% confidence interval

//    public static void main(String[] args) {
//        PercolationFactory pf = new PercolationFactory();
//        PercolationStats percolation = new PercolationStats(10, 30, pf);
//        System.out.println("The average number of test: " + percolation.mean());
//        System.out.println("The stddiff number of test: " + percolation.stddev());
//        System.out.println("The low number of test: " + percolation.confidenceLow());
//        System.out.println("The high number of test: " + percolation.confidenceHigh());
//
//    }

}
