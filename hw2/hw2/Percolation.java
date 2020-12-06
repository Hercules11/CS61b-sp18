package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int[][] perc;
    private WeightedQuickUnionUF WQUDS;
    private int numberOpen = 0;
    private int size;

    public Percolation(int N) {
        if (N <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        size = N;
        perc = new int[N][N];
        WQUDS = new WeightedQuickUnionUF(N * N); // initialize a DS that size is N * N
    }               // create N-by-N grid, with all sites initially blocked

    public void open(int row, int col) {
        if (row < 0 || col < 0 || col >= size || row >= size) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        perc[row][col] = 1;
        numberOpen += 1;
        if (row == 0) {
            if (isOpen(1, col)) {
                WQUDS.union(col, size + col);
            }
        } else if (row == size - 1) {
            if (isOpen(size - 2, col)) {
                WQUDS.union(size * row + col, size * row + col - size);
            }
        } else if (col == 0) {
            if (isOpen(row - 1, 0)) {
                WQUDS.union(row * size, row * (size - 1));
            }
            if (isOpen(row + 1, 0)) {
                WQUDS.union(row * size, row * (size + 1));
            }
            if (isOpen(row, 1)) {
                WQUDS.union(row * size, row * size + 1);
            }
        } else if (col == size - 1) {
            if (isOpen(row - 1, size - 1)) {
                WQUDS.union((row + 1) * size - 1, row * size - 1);
            }
            if (isOpen(row + 1, size - 1)) {
                WQUDS.union((row + 1) * size - 1, (row + 2) * size - 1);
            }
            if (isOpen(row, size - 2)) {
                WQUDS.union((row + 1) * size - 1, (row + 1) * size - 2);
            }
        } else {
            if (isOpen(row - 1, col)) {
                WQUDS.union(row * size + col, (row - 1) * size + col);
            }
            if (isOpen(row + 1, col)) {
                WQUDS.union(row * size + col, (row + 1) * size + col);
            }
            if (isOpen(row, col - 1)) {
                WQUDS.union(row * size + col, row * size + col - 1);
            }
            if (isOpen(row, col + 1)) {
                WQUDS.union(row * size + col, row * size + col + 1);
            }
        }
    }      // open the site (row, col) if it is not open already

    public boolean isOpen(int row, int col) {
        if (row < 0 || col < 0 || col >= size || row >= size) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        return perc[row][col] == 1;
    }  // is the site (row, col) open?

    public boolean isFull(int row, int col) {
        if (row < 0 || col < 0 || col >= size || row >= size) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        for (int i = 0; i < size; i++) {
            if (WQUDS.connected(i, (row * size + col)) && isOpen(0, i)) {
                return true;
            }
        }
        return false;
    }  // is the site (row, col) full?

    public int numberOfOpenSites() {
        return numberOpen;
    }         // number of open sites

    public boolean percolates() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (WQUDS.connected(i, (j + (size - 1) * size))) {
                    return true;
                }
            }
        }
        return false;
    }             // does the system percolate?

    public static void main(String[] args) {
        Percolation perc = new Percolation(5);
        perc.open(0, 3);
        perc.open(1, 3);
        perc.open(2, 3);
        perc.open(3, 3);
        perc.open(4, 3);
        System.out.println("if the system percolate: " + perc.percolates());
        System.out.println("The number of open sites: " + perc.numberOfOpenSites());
        System.out.println("if (2, 3) is full: " + perc.isFull(2, 4));
        System.out.println("if (1, 3) is full: " + perc.isFull(1, 3));
        System.out.println("if (0, 3) is full: " + perc.isFull(0, 3));
        System.out.println("if (3, 4) is open: " + perc.isOpen(3, 4));
        perc.open(3, 4);
        System.out.println("if (3, 4) is open: " + perc.isOpen(3, 4));
    }  // use for unit testing (not required)
}
