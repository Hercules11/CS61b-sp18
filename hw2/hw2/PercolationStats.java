package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private double[] numberPerc;
//    private double average;
//    private double stddiff;

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
        return StdStats.mean(numberPerc);
    }   // sample mean of percolation threshold

    public double stddev() {
        return StdStats.stddev(numberPerc);
    }   // sample standard deviation of percolation threshold

    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(numberPerc.length);
    }   // low endpoint of 95% confidence interval

    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(numberPerc.length);
    }   // high endpoint of 95% confidence interval

//    public static void main(String[] args) {
//        PercolationFactory pf = new PercolationFactory();
//        PercolationStats percolation = new PercolationStats(20, 10, pf);
//        System.out.println("THe stddev of system is: " + percolation.stddev());
//        System.out.println("THe high of system is: " + percolation.confidenceHigh());
//        System.out.println("THe high of system is: " + percolation.confidenceHigh());
//        System.out.println("THe stddev of system is: " + percolation.stddev());
//        System.out.println("THe low of system is: " + percolation.confidenceLow());
//        System.out.println("THe mean of system is: " + percolation.mean());
//        System.out.println("THe low of system is: " + percolation.confidenceLow());
//        System.out.println("THe high of system is: " + percolation.confidenceHigh());
//    }

}
