import PiecePack.Bishop;
import PiecePack.King;
import PiecePack.Knight;
import PiecePack.Pawn;
import PiecePack.Piece;
import PiecePack.Queen;
import PiecePack.Rook;


/**
 * The type Move.
 */
public class Move {
    /**
     * The Board size.
     */
    private int boardSize;
    /**
     * The Src piece.
     */
    private Piece srcPiece;
    /**
     * The Dest piece.
     */
    private Piece destPiece;
    /**
     * The Promotion piece.
     */
    private Piece promotionPiece;
    /**
     * The Src row.
     */
    private int srcRow = -1;
    /**
     * The Src column.
     */
    private int srcColumn = -1;
    /**
     * The Dest row.
     */
    private int destRow;
    /**
     * The Dest column.
     */
    private int destColumn;
    /**
     * The Turn.
     */
    private boolean turn;
    /**
     * The Is capture.
     */
    private boolean isCapture;
    /**
     * The Is promotion.
     */
    private boolean isPromotion;
    /**
     * The Is check.
     */
    private boolean isCheck;
    /**
     * The Is legal.
     */
    private boolean isLegal = true;

    /**
     * Instantiates a new Move.
     *
     * @param pgn       the pgn
     * @param turn      the turn
     * @param boardSize the board size
     */
    public Move(String pgn, boolean turn, int boardSize) {
        this.boardSize = boardSize;
        this.turn = turn;
        parsePgn(pgn);
    }

    /**
     * Instantiates a new Move.
     *
     * @param move the move
     */
    public Move(Move move) {
        this.boardSize = move.boardSize;
        this.srcPiece = createPiece(move.srcPiece);
        this.destPiece = createPiece(move.destPiece);
        this.promotionPiece = createPiece(move.promotionPiece);
        this.srcRow = move.srcRow;
        this.srcColumn = move.srcColumn;
        this.destRow = move.destRow;
        this.destColumn = move.destColumn;
        this.turn = move.turn;
        this.isCapture = move.isCapture;
        this.isPromotion = move.isPromotion;
        this.isCheck = move.isCheck;
        this.isLegal = move.isLegal;
    }

    /**
     * Create piece piece.
     *
     * @param toCopy the to copy
     * @return the piece
     */
    private Piece createPiece(Piece toCopy) {
        Piece piece = null;
        if (toCopy != null) {
            if (Pawn.class.equals(toCopy.getClass())) {
                piece = new Pawn(toCopy);
            } else if (Rook.class.equals(toCopy.getClass())) {
                piece = new Rook(toCopy);
            } else if (Knight.class.equals(toCopy.getClass())) {
                piece = new Knight(toCopy);
            } else if (Bishop.class.equals(toCopy.getClass())) {
                piece = new Bishop(toCopy);
            } else if (Queen.class.equals(toCopy.getClass())) {
                piece = new Queen(toCopy);
            } else if (King.class.equals(toCopy.getClass())) {
                piece = new King(toCopy);
            }
        }
        return piece;
    }


    /**
     * Gets src piece.
     *
     * @return the src piece
     */
    public Piece getSrcPiece() {
        return srcPiece;
    }

    /**
     * Sets src piece.
     *
     * @param piece the src piece
     */
    public void setSrcPiece(Piece piece) {
        this.srcPiece = piece;
    }

    /**
     * Gets dest piece.
     *
     * @return the dest piece
     */
    public Piece getDestPiece() {
        return destPiece;
    }

    /**
     * Sets dest piece.
     *
     * @param piece the dest piece
     */
    public void setDestPiece(Piece piece) {
        this.destPiece = piece;
    }

    /**
     * Gets promotion piece.
     *
     * @return the promotion piece
     */
    public Piece getPromotionPiece() {
        return promotionPiece;
    }

    /**
     * Sets promotion piece.
     *
     * @param piece the promotion piece
     */
    public void setPromotionPiece(Piece piece) {
        this.promotionPiece = piece;
    }

    /**
     * Sets promotion piece.
     *
     * @param row the row
     * @param col the col
     */
    public void setPromotionPiece(int row, int col) {
        this.promotionPiece.setLoc(row, col);
    }

    /**
     * Gets src row.
     *
     * @return the src row
     */
    public int getSrcRow() {
        return srcRow;
    }

    /**
     * Sets src row.
     *
     * @param srcR the src row
     */
    public void setSrcRow(int srcR) {
        this.srcRow = srcR;
    }

    /**
     * Gets src column.
     *
     * @return the src column
     */
    public int getSrcColumn() {
        return srcColumn;
    }

    /**
     * Sets src column.
     *
     * @param srcCol the src column
     */
    public void setSrcColumn(int srcCol) {
        this.srcColumn = srcCol;
    }

    /**
     * Gets dest row.
     *
     * @return the dest row
     */
    public int getDestRow() {
        return destRow;
    }

    /**
     * Sets dest row.
     *
     * @param destR the dest row
     */
    public void setDestRow(int destR) {
        this.destRow = destR;
    }

    /**
     * Gets dest column.
     *
     * @return the dest column
     */
    public int getDestColumn() {
        return destColumn;
    }


    /**
     * Sets dest column.
     *
     * @param destCol the dest column
     */
    public void setDestColumn(int destCol) {
        this.destColumn = destCol;
    }

    /**
     * Gets turn.
     *
     * @return the turn
     */
    public boolean getTurn() {
        return turn;
    }

    /**
     * Sets newTurn.
     *
     * @param newTurn the newTurn
     */
    public void setTurn(boolean newTurn) {
        this.turn = newTurn;
    }

    /**
     * Is capture boolean.
     *
     * @return the boolean
     */
    public boolean isCapture() {
        return isCapture;
    }

    /**
     * Sets capture.
     *
     * @param capture the capture
     */
    public void setCapture(boolean capture) {
        isCapture = capture;
    }

    /**
     * Is promotion boolean.
     *
     * @return the boolean
     */
    public boolean isPromotion() {
        return isPromotion;
    }

    /**
     * Sets promotion.
     *
     * @param promotion the promotion
     */
    public void setPromotion(boolean promotion) {
        isPromotion = promotion;
    }

    /**
     * Is check boolean.
     *
     * @return the boolean
     */
    public boolean isCheck() {
        return isCheck;
    }

    /**
     * Sets check.
     *
     * @param check the check
     */
    public void setCheck(boolean check) {
        isCheck = check;
    }

    /**
     * Is legal boolean.
     *
     * @return the boolean
     */
    public boolean isLegal() {
        return isLegal;
    }

    /**
     * Sets legal.
     *
     * @param legal the legal
     */
    public void setLegal(boolean legal) {
        isLegal = legal;
    }


    /**
     * To string string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return "\nMove{"
               + "srcPiece=" + srcPiece
               + ", destPiece=" + destPiece
               + ", promotionPiece=" + promotionPiece
               + ", srcRow=" + srcRow
               + ", srcColumn=" + srcColumn
               + ", destRow=" + destRow
               + ", destColumn=" + destColumn
               + ", isCapture=" + isCapture
               + ", isPromotion=" + isPromotion
               + ", isCheck=" + isCheck
               + ", isLegal=" + isLegal
               + ", turn=" + (this.turn ? "White" : "Black")
               + "}";
    }

    /**
     * Parse pgn.
     *
     * @param pgn the pgn
     */
    private void parsePgn(String pgn) {
        checkFlags(pgn);
        int i = pgn.length() - 1;
        while (!Character.isDigit(pgn.charAt(i))) {
            i--;
        }
        this.destRow = this.boardSize - Character.getNumericValue(pgn.charAt(i--));
        this.destColumn = pgn.charAt(i--) - 'a';
        if (Character.isUpperCase(pgn.charAt(0))) {
            srcPiece = createPiece(pgn.charAt(0));
            if (this.isCapture) {
                i--;
            }
            if (Character.isLowerCase(pgn.charAt(i))) {
                this.srcColumn = pgn.charAt(i) - 'a';
            } else if (Character.isDigit(pgn.charAt(i))) {
                this.srcRow = Character.getNumericValue(pgn.charAt(i--));
                if (Character.isLowerCase(pgn.charAt(i))) {
                    this.srcColumn = pgn.charAt(i) - 'a';
                }
            }
        } else {
            srcPiece = createPiece('P');
            if (this.isCapture) {
                this.srcColumn = pgn.charAt(0) - 'a';
            }
        }
    }

    /**
     * Check flags.
     *
     * @param pgn the pgn
     */
    private void checkFlags(String pgn) {
        final char CAPTURE = 'x';
        final char PROMOTION = '=';
        final char CHECK = '+';
        final char MATE = '#';

        for (int i = 0; i < pgn.length(); i++) {
            switch (pgn.charAt(i)) {
                case CAPTURE:
                    this.isCapture = true;
                    break;
                case MATE:
                case CHECK:
                    this.isCheck = true;
                    break;
                case PROMOTION:
                    this.isPromotion = true;
                    this.promotionPiece = createPiece(pgn.charAt(i + 1));
                    break;
                default:
                    //do noting
            }
        }
    }

    /**
     * Create piece piece.
     *
     * @param ch the ch
     * @return the piece
     */
    private Piece createPiece(char ch) {
        Piece p;
        int col = -1;
        int row = -1;
        if (!turn) {
            ch = Character.toLowerCase(ch);
        }
        switch (ch) {
            case 'P':
            case 'p':
                p = new Pawn(turn, col, row, boardSize);
                break;
            case 'R':
            case 'r':
                p = new Rook(turn, col, row, boardSize);
                break;
            case 'N':
            case 'n':
                p = new Knight(turn, col, row, boardSize);
                break;
            case 'B':
            case 'b':
                p = new Bishop(turn, col, row, boardSize);
                break;
            case 'Q':
            case 'q':
                p = new Queen(turn, col, row, boardSize);
                break;
            case 'K':
            case 'k':
                p = new King(turn, col, row, boardSize);
                break;
            default:
                p = null;
        }
        return p;
    }

    /**
     * Is dest empty boolean.
     *
     * @return the boolean
     */
    private boolean isDestEmpty() {
        return destPiece == null;
    }

    /**
     * Check logic boolean.
     *
     * @return the boolean
     */
    public boolean checkLogic() {
        if (isCapture) {
            if (!isDestEmpty()) {
                if (srcPiece.getColor() != destPiece.getColor()) {
                    isLegal = true;
                }
            }
        } else {
            isLegal = isDestEmpty();
        }

        return isLegal;
    }

    /**
     * Sets src piece loc.
     *
     * @param row    the row
     * @param column the column
     */
    public void setSrcPieceLoc(int row, int column) {
        this.srcPiece.setLoc(row, column);
    }
}
