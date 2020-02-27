package PiecePack;

/**
 * The type Piece.
 */
public abstract class Piece {
    /**
     * The Color.
     */
    protected boolean color;
    /**
     * The Column.
     */
    protected int column;
    /**
     * The Row.
     */
    protected int row;
    /**
     * The Name.
     */
    protected char name;

    /**
     * The Size.
     */
    protected int size;

    /**
     * Instantiates a new Piece.
     *
     * @param color  the color
     * @param column the column
     * @param row    the row
     * @param size   the size
     */
    public Piece(boolean color, int column, int row, int size) {
        this.color = color;
        this.column = column;
        this.row = row;
        this.size = size;
    }

    /**
     * Instantiates a new Piece.
     *
     * @param piece the piece
     */
    public Piece(Piece piece) {
        this.color = piece.color;
        this.column = piece.column;
        this.row = piece.row;
        this.name = piece.name;
        this.size = piece.size;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public char getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(char name) {
        this.name = name;
    }

    /**
     * Is color boolean.
     *
     * @return the boolean
     */
    public boolean getColor() {
        return color;
    }

    /**
     * Sets color.
     *
     * @param color the color
     */
    public void setColor(boolean color) {
        this.color = color;
    }

    /**
     * Gets column.
     *
     * @return the column
     */
    public int getColumn() {
        return column;
    }

    /**
     * Sets column.
     *
     * @param column the column
     */
    public void setColumn(int column) {
        this.column = column;
    }

    /**
     * Set loc.
     *
     * @param row    the row
     * @param column the column
     */
    public void setLoc(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * Gets row.
     *
     * @return the row
     */
    public int getRow() {
        return row;
    }

    /**
     * Sets row.
     *
     * @param row the row
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * Equals boolean.
     *
     * @param o the o
     * @return the boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return color == piece.color &&
               name == piece.name;
    }

    /**
     * To string string.
     *
     * @return the string
     */
    public String toString() {
        return "" + this.name;
    }

    /**
     * Can move boolean.
     *
     * @param destRow the dest row
     * @param destCol the dest col
     * @param capture the capture
     * @return the boolean
     */
    abstract public boolean canMove(int destRow, int destCol, boolean capture);

    /**
     * Is pawn boolean.
     *
     * @return the boolean
     */
    public boolean isPawn() {
        return this.getClass() == Pawn.class;
    }

    /**
     * Is knight boolean.
     *
     * @return the boolean
     */
    public boolean isKnight() {
        return this.getClass() == Knight.class;
    }

    /**
     * Is king boolean.
     *
     * @return the boolean
     */
    public boolean isKing() {
        return this.getClass() == King.class;
    }
}
