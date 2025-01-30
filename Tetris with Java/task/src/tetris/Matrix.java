package tetris;

import java.util.Arrays;

public class Matrix {
    private int[][] data;

    public Matrix(int rows, int cols) {
        data = new int[rows][cols];
    }

    public Matrix(Matrix m){
        data = new int[m.getRowCount()][m.getColCount()];
        for (int i = 0; i < m.getRowCount(); i++) {
            for (int j = 0; j < m.getColCount(); j++) {
                data[i][j] = m.getDataAt(i, j);
            }
        }
    }

    public void setDataAt(int row, int col, int value) {
        data[row][col] = value;
    }

    public int getDataAt(int row, int col) {
        return data[row][col];
    }

    public int getRowCount() {
        return data.length;
    }

    public int getColCount() {
        return data[0].length;
    }

    public Matrix add(Matrix other) {
        Matrix result = new Matrix(data.length, data[0].length);
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                result.setDataAt(i, j, data[i][j] + other.getDataAt(i, j));
            }
        }
        return result;
    }

    public Matrix add(Matrix other, int row, int col) {
        Matrix result = new Matrix(getRowCount(), getColCount());
        for (int i = 0; i < other.getRowCount(); i++) {
            for (int j = 0; j < other.getColCount(); j++) {
                int newrow = (i + row + getRowCount()) % getRowCount();
                int newcol = (j + col + getColCount()) % getColCount();
                result.setDataAt(newrow, newcol, data[newrow][newcol] + other.getDataAt(i, j));
            }
        }
        return result;
    }

    public Matrix subtract(Matrix other) {
        Matrix result = new Matrix(data.length, data[0].length);
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                result.setDataAt(i, j, data[i][j] - other.getDataAt(i, j));
            }
        }
        return result;
    }

    public Matrix multiply(Matrix other) {
        Matrix result = new Matrix(data.length, other.data[0].length);
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < other.data[0].length; j++) {
                int sum = 0;
                for (int k = 0; k < data[0].length; k++) {
                    sum += data[i][k] * other.getDataAt(k, j);
                }
                result.setDataAt(i, j, sum);
            }
        }
        return result;
    }

    public void InsertRow(int rowIndex) {
        int rows = getRowCount() + 1;
        int cols = getColCount();
        int[][] newMatrix = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            if (i < rowIndex) {
                newMatrix[i] = Arrays.copyOf(data[i], data[i].length);
            } else if (i > rowIndex) {
                newMatrix[i] = Arrays.copyOf(data[i-1], data[i-1].length);
            }
        }
        this.data = newMatrix;
    }

    public void insertColumn(int colIndex) {
        int rows = getRowCount();
        int cols = getColCount() + 1;
        int[][] newMatrix = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (j < colIndex) {
                    newMatrix[i][j] = data[i][j];
                } else if (j > colIndex) {
                    newMatrix[i][j] = data[i][j-1];
                }
            }
        }
        this.data = newMatrix;
    }


    public void setRow(int rowIndex, int value) {
        if (rowIndex >= 0 && rowIndex < getRowCount()) {
            for (int i = 0; i < getColCount(); i++) {
                setDataAt(rowIndex, i, value);
            }
        } else {
            throw new IndexOutOfBoundsException("Row index out of bounds.");
        }
    }

    public void setColumn(int colIndex, int value) {
        if (colIndex >= 0 && colIndex < getColCount()) {
            for (int i = 0; i < getRowCount(); i++) {
                setDataAt(i, colIndex, value);
            }
        } else {
            throw new IndexOutOfBoundsException("Column index out of bounds.");
        }
    }


    public void removeRow(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < getRowCount()) {
            int[][] newData = new int[getRowCount() - 1][getColCount()];
            int p = 0;
            for (int i = 0; i < getRowCount(); i++) {
                if (i == rowIndex) continue;
                newData[p++] = data[i];
            }
            data = newData;
        } else {
            throw new IndexOutOfBoundsException("Row index out of bounds.");
        }
    }

    public void removeColumn(int colIndex) {
        if (colIndex >= 0 && colIndex < getColCount()) {
            int[][] newData = new int[getRowCount()][getColCount() - 1];
            for (int i = 0; i < getRowCount(); i++) {
                int p = 0;
                for (int j = 0; j < getColCount(); j++) {
                    if (j == colIndex) continue;
                    newData[i][p++] = data[i][j];
                }
            }
            data = newData;
        } else {
            throw new IndexOutOfBoundsException("Column index out of bounds.");
        }
    }


    public boolean isValueInColumn(int colIndex, int value) {
        if (colIndex >= 0 && colIndex < getColCount()) {
            for (int i = 0; i < getRowCount(); i++) {
                if (getDataAt(i, colIndex) == value) {
                    return true;
                }
            }
        } else {
            throw new IndexOutOfBoundsException("Column index out of bounds.");
        }
        return false;
    }

    public boolean isValueInRow(int rowIndex, int value) {
        if (rowIndex >= 0 && rowIndex < getRowCount()) {
            for (int i = 0; i < getColCount(); i++) {
                if (getDataAt(rowIndex, i) == value) {
                    return true;
                }
            }
        } else {
            throw new IndexOutOfBoundsException("Row index out of bounds.");
        }
        return false;
    }

    public boolean containsValue(int value) {
        for (int i = 0; i < getRowCount(); i++) {
            for (int j = 0; j < getColCount(); j++) {
                if (getDataAt(i, j) == value) {
                    return true;
                }
            }
        }
        return false;
    }
}