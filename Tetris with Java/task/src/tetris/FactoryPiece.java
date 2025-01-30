package tetris;


public class FactoryPiece {
    public static Piece createPiece(String type) {
        switch (type) {
            case "I" -> {
                return new IPiece();
            }
            case "J" -> {
                return new JPiece();
            }
            case "L" -> {
                return new LPiece();
            }
            case "O" -> {
                return new OPiece();
            }
            case "S" -> {
                return new SPiece();
            }
            case "T" -> {
                return new TPiece();
            }
            case "Z" -> {
                return new ZPiece();
            }
            default -> {
                return null;
            }
        }
    }
}