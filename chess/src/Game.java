import PiecePack.Bishop;
import PiecePack.King;
import PiecePack.Knight;
import PiecePack.Pawn;
import PiecePack.Piece;
import PiecePack.Queen;
import PiecePack.Rook;

/**
 * The type Game.
 */
public class Game {

    /**
     * The Board.
     */
    private Board board;
    /**
     * The Pgns.
     */
    private String[] pgns;
    /**
     * The Move.
     */
    private Move move;

    /**
     * Instantiates a new Game.
     *
     * @param fen  the fen
     * @param pgns the pgns
     * @param size the size
     */
    public Game(String fen, String[] pgns, int size) {
        this.board = new Board(fen, size);
        this.pgns = pgns;
    }

    /**
     * Print board.
     */
    public void printBoard() {
        this.board.printBoard();
    }

    /**
     * Play move boolean.
     *
     * @param pgn  the pgn
     * @param turn the turn
     * @return the boolean
     */
    private boolean playMove(String pgn, boolean turn) {
        this.move = new Move(pgn, turn, this.board.getSize());
        move.setDestPiece(this.board.getPiece(move.getDestRow(), move.getDestColumn()));
        if (move.checkLogic()) {
            findSrc(false);
        }
        if (move.isLegal()) {
            makeMove();
        }
        System.out.println("\n" + pgn + " " + getColor(turn));
        if (move.isLegal()) {
            this.board.printBoard();
        } else {
            System.out.println("Illegal");
            return false;
        }
        return true;
    }

    /**
     * Make move.
     */
    private void makeMove() {
        int row = move.getSrcPiece().getRow();
        int column = move.getSrcPiece().getColumn();
        if (move.isPromotion()) {
            move.setSrcPiece(move.getPromotionPiece());
            move.setSrcPieceLoc(row, column);
        }
        board.setPiece(move.getSrcPiece(), move.getDestRow(), move.getDestColumn());
        board.setPiece(null, row, column);
    }

    /**
     * Find src boolean.
     *
     * @param check the check
     * @return the boolean
     */
    private boolean findSrc(boolean check) {
        int srcRow = move.getSrcRow();
        int srcColumn = move.getSrcColumn();

        if (srcRow > -1 && srcColumn > -1) {
            Piece piece = board.getPiece(srcRow, srcColumn);
            if (checkLoc(piece, check)) {
                return true;
            }
        } else if (srcRow > -1) {
            for (int col = 0; col < board.getSize(); col++) {
                Piece piece = board.getPiece(srcRow, col);
                if (checkLoc(piece, check)) {
                    return true;
                }
            }
        } else if (srcColumn > -1) {
            for (int row = 0; row < board.getSize(); row++) {
                Piece piece = board.getPiece(row, srcColumn);
                if (checkLoc(piece, check)) {
                    return true;
                }
            }
        }
        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                Piece piece = board.getPiece(row, col);
                if (piece == null) {
                    continue;
                }
                if (checkLoc(piece, check)) {
                    return true;
                }
            }
        }
        move.setLegal(false);
        return false;
    }

    /**
     * Check loc boolean.
     *
     * @param piece the piece
     * @param check the check
     * @return the boolean
     */
    private boolean checkLoc(Piece piece, boolean check) {
        if (move.getSrcPiece().equals(piece)) {
            if (piece.isPawn()) {
                if (piece.getColor()) {
                    if (piece.getRow() - move.getDestRow() < 1) {
                        return false;
                    }
                } else {
                    if (piece.getRow() - move.getDestRow() > 0) {
                        return false;
                    }
                }
                int deltaRow = piece.getColor() ? -1 : 1;
                boolean isFree = (board.getPiece(piece.getRow() + deltaRow, piece.getColumn()) == null);
                boolean isSecFree = piece.getRow() + 2 * deltaRow < 0 || piece.getRow() + 2 * deltaRow > 7
                                    || (board.getPiece(piece.getRow() + 2 * deltaRow, piece.getColumn()) == null);
                if (((Pawn) piece).canMove(move.getDestRow(), move.getDestColumn(), move.isCapture(),
                        isFree, isSecFree)) {
                    if (check || isCheck(piece.getRow(), piece.getColumn())) {
                        move.setSrcPiece(piece);
                        return true;
                    }
                }
            } else if (piece.canMove(move.getDestRow(), move.getDestColumn(), move.isCapture())) {
                if (isFree(piece, move.getDestRow(), move.getDestColumn())) {
                    if (check || isCheck(piece.getRow(), piece.getColumn())) {
                        move.setSrcPiece(piece);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Is free boolean.
     *
     * @param piece  the piece
     * @param row    the row
     * @param column the column
     * @return the boolean
     */
    private boolean isFree(Piece piece, int row, int column) {
        if (piece.isKnight() || piece.isKing()) {
            return true;
        }

        int deltaRow = Integer.compare(0, piece.getRow() - row);
        int deltaCol = Integer.compare(0, piece.getColumn() - column);
        int cRow = piece.getRow() + deltaRow;
        int cCol = piece.getColumn() + deltaCol;
        while (cRow != row || cCol != column) {
            if (board.getPiece(cRow, cCol) != null) {
                return false;
            }
            cRow += deltaRow;
            cCol += deltaCol;
        }
        return true;
    }

    /**
     * Is check boolean.
     *
     * @param row    the row
     * @param column the column
     * @return the boolean
     */
    private boolean isCheck(int row, int column) {
        Board saveBoard = new Board(board);
        Move saveMove = new Move(move);
        move.setSrcPieceLoc(row, column);
        makeMove();

        King whiteKing = (King) board.findKing(true);
        King blackKing = (King) board.findKing(false);
        boolean whiteThreat = isKingInThreat(whiteKing);
        boolean blackThreat = isKingInThreat(blackKing);

        if (saveMove.getTurn()) {
            if (checkCheck(saveBoard, saveMove, whiteThreat, blackThreat)) {
                return false;
            }
        } else {
            if (checkCheck(saveBoard, saveMove, blackThreat, whiteThreat)) {
                return false;
            }
        }
        move = saveMove;
        this.board = saveBoard;
        return true;
    }

    /**
     * Check check boolean.
     *
     * @param saveBoard   the save board
     * @param saveMove    the save move
     * @param whiteThreat the white threat
     * @param blackThreat the black threat
     * @return the boolean
     */
    private boolean checkCheck(Board saveBoard, Move saveMove, boolean whiteThreat, boolean blackThreat) {
        if (whiteThreat) {
            move = saveMove;
            this.board = saveBoard;
            return true;
        }
        if (blackThreat != move.isCheck()) {
            move = saveMove;
            this.board = saveBoard;
            return true;
        }
        return false;
    }

    /**
     * Is king in threat boolean.
     *
     * @param king the king
     * @return the boolean
     */
    private boolean isKingInThreat(King king) {
        Pawn genPawn = new Pawn(!king.getColor(), -1, -1, board.getSize());
        Bishop genBishop = new Bishop(!king.getColor(), -1, -1, board.getSize());
        King genKing = new King(!king.getColor(), -1, -1, board.getSize());
        Knight genKnight = new Knight(!king.getColor(), -1, -1, board.getSize());
        Queen genQueen = new Queen(!king.getColor(), -1, -1, board.getSize());
        Rook genRook = new Rook(!king.getColor(), -1, -1, board.getSize());
        Piece[] pieces = {genPawn, genBishop, genKing, genKnight, genQueen, genRook};
        move.setDestPiece(king);
        move.setDestRow(king.getRow());
        move.setDestColumn(king.getColumn());
        move.setSrcRow(-1);
        move.setSrcColumn(-1);
        move.setTurn(!king.getColor());
        for (Piece p : pieces) {
            move.setSrcPiece(p);
            if (findSrc(true)) {
                return true;
            }
        }
        return false;
    }


    /**
     * Play.
     */
    public void play() {
        boolean turn = true;
        for (String pgn : this.pgns) {
            if (playMove(pgn, turn)) {
                turn = !turn;
            } else {
                return;
            }
        }
    }

    /**
     * Gets color.
     *
     * @param turn the turn
     * @return the color
     */
    private String getColor(boolean turn) {
        return turn ? "White" : "Black";
    }
}
