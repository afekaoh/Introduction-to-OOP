package PiecePack;

/**
 * The type Knight.
 */
public class Knight extends Piece {


    /**
     * Instantiates a new Knight.
     *
     * @param color  the color
     * @param column the column
     * @param row    the row
     * @param size   the size
     */
    public Knight(boolean color, int column, int row, int size) {
        super(color, column, row, size);
        this.name = color ? 'N' : 'n';

    }

    /**
     * Instantiates a new Knight.
     *
     * @param piece the piece
     */
    public Knight(Piece piece) {
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
        return ((Math.abs(destRow - row) == 2 && Math.abs(destCol - column) == 1) ||
                (Math.abs(destRow - row) == 1 && Math.abs(destCol - column) == 2));
    }

}
