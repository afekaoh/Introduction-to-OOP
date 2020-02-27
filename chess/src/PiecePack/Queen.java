package PiecePack;

/**
 * The type Queen.
 */
public class Queen extends Piece {


    /**
     * Instantiates a new Queen.
     *
     * @param color  the color
     * @param column the column
     * @param row    the row
     * @param size   the size
     */
    public Queen(boolean color, int column, int row, int size) {
        super(color, column, row, size);
        this.name = color ? 'Q' : 'q';
    }

    /**
     * Instantiates a new Queen.
     *
     * @param piece the piece
     */
    public Queen(Piece piece) {
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
        return (destCol == column || destRow == row) ||
               Math.abs(destRow - row) == Math.abs(destCol - column);
    }


}
