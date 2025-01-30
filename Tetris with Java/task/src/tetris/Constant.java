package tetris;

public enum Constant {

    O_SHAPE(new Integer[][]{{4, 14, 15, 5}}),
    I_SHAPE(new Integer[][]{{4, 14, 24, 34}, {3, 4, 5, 6}}),
    S_SHAPE(new Integer[][]{{5, 4, 14, 13}, {4, 14, 15, 25}}),
    Z_SHAPE(new Integer[][]{{4, 5, 15, 16}, {5, 15, 14, 24}}),
    L_SHAPE(new Integer[][]{{4, 14, 24, 25}, {5, 15, 14, 13}, {4, 5, 15, 25}, {6, 5, 4, 14}}),
    J_SHAPE(new Integer[][]{{5, 15, 25, 24}, {15, 5, 4, 3}, {5, 4, 14, 24}, {4, 14, 15, 16}}),
    T_SHAPE(new Integer[][]{{4, 14, 24, 15}, {4, 13, 14, 15}, {5, 15, 25, 14}, {4, 5, 6, 15}});

    private final Integer[][] coordinates;

    Constant(Integer[][] coordinates) {
        this.coordinates = coordinates;
    }

    public Integer[][] getCoordinates() {
        return this.coordinates;
    }
}

class GlobalConstants {
    public static final int SIZE_OF_PIECE_COL = 10;
    public static final int SIZE_OF_PIECE_ROW = 4;
}