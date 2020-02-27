package PiecePack;

import static java.lang.Math.abs;

/**
 * The type Pawn.
 */
public class Pawn extends Piece {
    /**
     * Instantiates a new Pawn.
     *
     * @param color  the color
     * @param column the column
     * @param row    the row
     * @param size   the size
     */
    public Pawn(boolean color, int column, int row, int size) {
        super(color, column, row, size);
        this.name = color ? 'P' : 'p';
    }

    /**
     * Instantiates a new Pawn.
     *
     * @param piece the piece
     */
    public Pawn(Piece piece) {
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
        return false;
    }

    /**
     * Can move boolean.
     *
     * @param destRow   the dest row
     * @param destCol   the dest col
     * @param capture   the capture
     * @param isFree    the is free
     * @param isSecFree the is sec free
     * @return the boolean
     */
    public boolean canMove(int destRow, int destCol, boolean capture, boolean isFree, boolean isSecFree) {
        int deltaRow = destRow - row;
        int deltaCol = destCol - column;
        if (capture) {
            if (abs(deltaCol) == 1) {
                if (color)
                    return deltaRow == -1;
                else
                    return deltaRow == 1;
            }
        } else if (deltaCol == 0 && Math.abs(deltaRow) < 3) {
            if (color && deltaRow == -1 || !color && deltaRow == 1)
                return isFree;

            if (color && row == size - 2 || !color && row == 1)
                return isFree && isSecFree;
        }
        return false;
    }
}
