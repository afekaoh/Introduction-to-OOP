import PiecePack.Bishop;
import PiecePack.King;
import PiecePack.Knight;
import PiecePack.Pawn;
import PiecePack.Piece;
import PiecePack.Queen;
import PiecePack.Rook;


/**
 * The type Board.
 */
public class Board {

    /**
     * The Size.
     */
    private int size;
    /**
     * The Pieces.
     */
    private Piece[][] pieces;

    /**
     * Instantiates a new Board.
     *
     * @param fen  the fen
     * @param size the size
     */
    public Board(String fen, int size) {
        this.size = size;
        createBoard(fen);
    }

    /**
     * Instantiates a new Board.
     *
     * @param board the board
     */
    public Board(Board board) {
        this.size = board.size;
        this.pieces = new Piece[this.size][this.size];
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                Piece piece = null, copyPiece = board.getPiece(row, column);
                if (copyPiece != null) {
                    if (Pawn.class.equals(copyPiece.getClass())) {
                        piece = new Pawn(copyPiece);
                    } else if (Rook.class.equals(copyPiece.getClass())) {
                        piece = new Rook(copyPiece);
                    } else if (Knight.class.equals(copyPiece.getClass())) {
                        piece = new Knight(copyPiece);
                    } else if (Bishop.class.equals(copyPiece.getClass())) {
                        piece = new Bishop(copyPiece);
                    } else if (Queen.class.equals(copyPiece.getClass())) {
                        piece = new Queen(copyPiece);
                    } else if (King.class.equals(copyPiece.getClass())) {
                        piece = new King(copyPiece);
                    }
                }
                pieces[row][column] = piece;
            }
        }
    }

    /**
     * Gets size.
     *
     * @return the size
     */
    public int getSize() {
        return size;
    }

    /**
     * Create board.
     *
     * @param fen the fen
     */
    private void createBoard(String fen) {
        this.pieces = new Piece[this.size][this.size];
        String[] rows = fen.split("/");
        int row = 0;
        for (String s : rows) {
            int col = 0;
            for (int j = 0; j < s.length(); j++) {
                char ch = s.charAt(j);
                if (Character.isDigit(ch)) {
                    int num = Character.getNumericValue(ch);
                    for (int k = 0; k < num; k++) {
                        pieces[row][col] = null;
                        col++;
                    }
                } else {
                    switch (ch) {
                        case 'p':
                            pieces[row][col] = new Pawn(false, col, row, size);
                            break;
                        case 'P':
                            pieces[row][col] = new Pawn(true, col, row, size);
                            break;
                        case 'r':
                            pieces[row][col] = new Rook(false, col, row, size);
                            break;
                        case 'R':
                            pieces[row][col] = new Rook(true, col, row, size);
                            break;
                        case 'n':
                            pieces[row][col] = new Knight(false, col, row, size);
                            break;
                        case 'N':
                            pieces[row][col] = new Knight(true, col, row, size);
                            break;
                        case 'b':
                            pieces[row][col] = new Bishop(false, col, row, size);
                            break;
                        case 'B':
                            pieces[row][col] = new Bishop(true, col, row, size);
                            break;
                        case 'q':
                            pieces[row][col] = new Queen(false, col, row, size);
                            break;
                        case 'Q':
                            pieces[row][col] = new Queen(true, col, row, size);
                            break;
                        case 'k':
                            pieces[row][col] = new King(false, col, row, size);
                            break;
                        case 'K':
                            pieces[row][col] = new King(true, col, row, size);
                            break;
                        default:
                            pieces[row][col] = null;
                    }
                    col++;
                }
            }
            row++;
        }
    }

    /**
     * Print columns.
     */
    private void printColumns() {
        char column = 'A';
        System.out.print("* |");
        for (int i = 0; i < this.size; i++) {
            if (i != 0) {
                System.out.print(" ");
            }
            System.out.print(column);
            column++;
        }
        System.out.println("| *");
    }

    /**
     * Print spacers.
     */
    private void printSpacers() {
        System.out.print("* -");
        for (int i = 0; i < this.size; i++) {
            System.out.print("--");
        }
        System.out.println(" *");
    }

    /**
     * Print row.
     *
     * @param p    the p
     * @param rowI the row i
     */
    private void printRow(Piece[] p, int rowI) {
        System.out.print(rowI + " ");
        for (int j = 0; j < size; j++) {
            System.out.print(p[j] != null ? "|" + p[j] : "| ");
        }
        System.out.print("| " + rowI);
    }

    /**
     * Print board.
     */
    public void printBoard() {
        printColumns();
        printSpacers();
        int index = size;
        for (Piece[] row : this.pieces) {
            printRow(row, index--);
            System.out.println();
        }
        printSpacers();
        printColumns();
    }

    /**
     * Get piece piece.
     *
     * @param row    the row
     * @param column the column
     * @return the piece
     */
    public Piece getPiece(int row, int column) {
        return pieces[row][column];
    }

    /**
     * Sets piece.
     *
     * @param piece  the piece
     * @param row    the row
     * @param column the column
     */
    public void setPiece(Piece piece, int row, int column) {
        pieces[row][column] = piece;
        if (piece != null) {
            pieces[row][column].setLoc(row, column);
        }
    }

    /**
     * Find king piece.
     *
     * @param color the color
     * @return the piece
     */
    public Piece findKing(boolean color) {
        for (Piece[] row : pieces) {
            for (Piece piece : row) {
                if (piece == null) {
                    continue;
                }
                if (piece.isKing() && piece.getColor() == color) {
                    return piece;
                }
            }
        }
        return null;
    }
}
