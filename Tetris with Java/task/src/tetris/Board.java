package tetris;

public class Board {
    private Frame movingFrame;
    private Frame staticFrame;
    private Piece piece;
    boolean gameOver = false;

    int rows = 0, cols = 0;


    public Board(int rows, int cols) {
        movingFrame = new EmptyFrame(rows, cols);
        staticFrame = new EmptyFrame(rows, cols);
        this.rows = rows;
        this.cols = cols;
    }

    public boolean addNewPiece(String type){
        piece = FactoryPiece.createPiece(type);
        piece.row = 0;
        piece.col = (this.cols - piece.m.getColCount()) / 2;
        movingFrame = new EmptyFrame(rows, cols).addFrame(piece, piece.row, piece.col);
        if(movingFrame == null) return false;
        return true;
    }

    private boolean showPiece(int row, int col){
        Frame move = new EmptyFrame(rows, cols).addFrame(piece, row, col);
        if (move == null) return false;
        if(isCollision(move)) {
            setStaticFrame(true);
            return false;
        }
        movingFrame = move;
        return true;
    }

    private boolean isCollision(Frame move){
        Frame x = staticFrame.addFrame(move, 0, 0);
        return x.m.containsValue(2);
    }

    private void setStaticFrame(boolean force){
        if(movingFrame.m.isValueInRow(movingFrame.m.getRowCount()-1, 1) || force) {
            staticFrame = staticFrame.addFrame(movingFrame, 0, 0);
            movingFrame = new EmptyFrame(rows, cols);
            this.piece = null;
        }
    }

    public boolean movePieceDown() {
        setStaticFrame(false);
        if(piece == null) return false;
        if(showPiece(piece.row + 1, piece.col)){
            piece.row ++;
            setStaticFrame(false);
            return true;
        }
        setStaticFrame(true);
        if(staticFrame.m.isValueInRow(0, 1)) gameOver = true;

        return false;
    }

    public boolean movePieceRight() {
        if(piece == null) return false;
        if(!movingFrame.m.isValueInColumn(movingFrame.m.getColCount()-1, 1)) {
            if(showPiece(piece.row, piece.col + 1)){
                piece.col ++;
            }
        }
        return movePieceDown();
    }

    public boolean movePieceLeft() {
        if(piece == null) return false;
        if(!movingFrame.m.isValueInColumn(0, 1)) {
            if (showPiece(piece.row, piece.col - 1)) {
                piece.col--;
            }
        }
        return movePieceDown();
    }

    public boolean rotatePiece() {
        if(piece == null) return false;
        Piece n = new Piece(this.piece);
        n.rotate();
        Frame move = new EmptyFrame(rows, cols).addFrame(n, n.row, n.col);
        if(move != null) {
            if(!((move.m.isValueInColumn(move.m.getColCount()-1, 1) &&
                    move.m.isValueInColumn(0, 1)) ||
                    (move.m.isValueInRow(move.m.getRowCount()-1, 1) &&
                            move.m.isValueInRow(0, 1)))) {
                if(!isCollision(move)) {
                    this.piece = n;
                    movingFrame = move;
                }
            }
        }
        return movePieceDown();
    }

    public boolean removeFullLine(){
        for(int i = 0; i < staticFrame.m.getRowCount(); i++){
            if (!staticFrame.m.isValueInRow(i, 0)) {
                staticFrame.removeRow(i);
                staticFrame.InsertRow(0);
            }
        }
        return true;
    }

    public void print(){
        if (movingFrame == null) return;
        Frame frame = staticFrame.addFrame(movingFrame,0, 0);
        frame.print();
    }

}