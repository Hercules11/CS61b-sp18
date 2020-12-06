package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int[][] perc; // 值为1,表示open, 和第一行open的格子connected,表示full
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
    }

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

    public boolean isOpen(int row, int col) {
        if (row < 0 || col < 0 || col >= size || row >= size) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        return perc[row][col] == 1;
    }

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

    public int numberOfOpenSites() {
        return numberOpen;
    }

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

    public static void main(String[] args) {
        Percolation perc = new Percolation(1);
        System.out.println("if the system percolate: " + perc.percolates());
        System.out.println("The number of open sites: " + perc.numberOfOpenSites());
    }
    // use for unit testing (not required)
}
