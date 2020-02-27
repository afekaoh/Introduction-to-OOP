package PiecePack;

/**
 * The type Bishop.
 */
public class Bishop extends Piece {


    /**
     * Instantiates a new Bishop.
     *
     * @param color  the color of the piece
     * @param column the column of the piece
     * @param row    the row of the piece
     * @param size   the size of the board
     */
    public Bishop(boolean color, int column, int row, int size) {
        super(color, column, row, size);
        this.name = color ? 'B' : 'b';

    }

    /**
     * Instantiates a new Bishop.
     *
     * @param piece the piece
     */
    public Bishop(Piece piece) {
        super(piece);
    }

    /**
     * Can move boolean.
     *
     * @param destRow the dest row
     * @param destCol the dest col
     * @param capture the capture
     * @return the boolean
     */
    @Override
    public boolean canMove(int destRow, int destCol, boolean capture) {
        return Math.abs(destRow - row) == Math.abs(destCol - column);
    }

}
