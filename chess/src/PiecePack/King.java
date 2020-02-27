package PiecePack;

/**
 * The type King.
 */
public class King extends Piece {


    /**
     * Instantiates a new King.
     *
     * @param color  the color
     * @param column the column
     * @param row    the row               \     * @param size
     * @param size   the size
     */
    public King(boolean color, int column, int row, int size) {
        super(color, column, row, size);
        this.name = color ? 'K' : 'k';
    }

    /**
     * Instantiates a new King.
     *
     * @param piece the piece
     */
    public King(Piece piece) {
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
        return Math.abs(destRow - row) <= 1 && Math.abs(destCol - column) <= 1;
    }

}
