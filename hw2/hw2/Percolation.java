package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int[][] perc; // 值为1 则表示open, 和第一行open的格子connected 则表示full
    private WeightedQuickUnionUF WQUDS;
    private int numberOpen = 0;
    private int size;

    public Percolation(int N) {
        if (N <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        size = N;
        perc = new int[N][N];
        WQUDS = new WeightedQuickUnionUF(N * N);
        // initialize a DS that size is N * N
    }
    // create N-by-N grid, with all sites initially blocked

    private void findOpen(int row, int col) {
        int upRow = row - 1;
        int downRow = row + 1;
        int leftCol = col - 1;
        int rightCol = col + 1;
        if (upRow >= 0 && perc[upRow][col] == 1) {
            WQUDS.union(row * size + col, row * size + col - size);
        }
        if (downRow < size && perc[downRow][col] == 1) {
            WQUDS.union(row * size + col, row * size + col + size);
        }
        if (leftCol >= 0 && perc[row][leftCol] == 1) {
            WQUDS.union(row * size + col, row * size + col - 1);
        }
        if (rightCol < size && perc[row][rightCol] == 1) {
            WQUDS.union(row * size + col, row * size + col + 1);
        }
    }
    // find the surrounding open sites

    public void open(int row, int col) {
        if (row < 0 || col < 0 || col >= size || row >= size) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        if (perc[row][col] != 1) {
            perc[row][col] = 1;
            numberOpen += 1;
            findOpen(row, col);
        }
    }
    // open the site (row, col) if it is not open already

    public boolean isOpen(int row, int col) {
        if (row < 0 || col < 0 || col >= size || row >= size) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        return perc[row][col] == 1;
    }
    // is the site (row, col) open?

    public boolean isFull(int row, int col) {
        if (row < 0 || col < 0 || col >= size || row >= size) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        for (int i = 0; i < size; i++) {
            if (perc[0][i] == 1) {
                if (WQUDS.connected(i, (row * size + col))) {
                    return true;
                }
            }
        }
        return false;
    }
    // is the site (row, col) full?

    public int numberOfOpenSites() {
        return numberOpen;
    }
    // number of open sites

    public boolean percolates() {
        for (int i = 0; i < size; i++) {
            if (perc[0][i] == 1) {
                for (int j = 0; j < size; j++) {
                    if (perc[size - 1][j] == 1) {
                        if (WQUDS.connected(i, (j + (size - 1) * size))) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    // does the system percolate?

    public static void main(String[] args) {
        Percolation perc = new Percolation(1);
//        perc.open(0, 3);
//        perc.open(1, 3);
//        perc.open(2, 3);
//        perc.open(3, 3);
//        perc.open(4, 3);
        System.out.println("if the system percolate: " + perc.percolates());
        System.out.println("The number of open sites: " + perc.numberOfOpenSites());
//        System.out.println("if (2, 3) is full: " + perc.isFull(2, 4));
//        System.out.println("if (1, 3) is full: " + perc.isFull(1, 3));
//        System.out.println("if (0, 3) is full: " + perc.isFull(0, 3));
//        System.out.println("if (3, 4) is open: " + perc.isOpen(3, 4));
//        perc.open(3, 4);
//        System.out.println("if (3, 4) is open: " + perc.isOpen(3, 4));
    }
    // use for unit testing (not required)
}
