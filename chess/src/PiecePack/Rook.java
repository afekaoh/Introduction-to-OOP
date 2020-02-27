package PiecePack;

/**
 * The type Rook.
 */
public class Rook extends Piece {


    /**
     * Instantiates a new Rook.
     *
     * @param color  the color
     * @param column the column
     * @param row    the row
     * @param size   the size
     */
    public Rook(boolean color, int column, int row, int size) {
        super(color, column, row, size);
        this.name = color ? 'R' : 'r';
    }

    /**
     * Instantiates a new Rook.
     *
     * @param piece the piece
     */
    public Rook(Piece piece) {
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
        return destCol == column || destRow == row;
    }
}
