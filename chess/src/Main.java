/**
 * The type Main.
 */
public class Main {

    /**
     * The Pgn 81.
     */
    private static String[] pgn81 = {
            "Qxd8+", "Kxd8",
            "Rxh8", "Bb4+",
            "Bd2", "Bxd2+",
            "Kxd2", "Rxa1",
            "Rxg8+", "Kd7",
            "Bb5+", "Nc6",
            "Bxc6+", "Kd6",
            "Rxc8", "Rxb1",
            "Nf3", "Ra1",
            "Bb5", "Rb1",
            "Be2", "Rb4",
            "Ng5", "Rf4",
            "Ke3", "Rb4",
            "Ne4+", "Ke7",
            "Rc7+", "Kf8",
            "Bc4", "Rb2",
            "Ng5", "Rb1",
            "Rf7+", "Ke8",
            "Nh7", "Re1+",
            "Kd4", "Ra1",
            "Nf6+", "Kd8",
            "Rd7+", "Kc8",
            "Kc5", "Rb1",
            "Be6", "Rg1",
            "Rd1+", "Kb7",
            "Rxg1", "Kb8",
            "Kb6", "Ka8",
            "Rg8#"
    };

    /**
     * The Pgn 82.
     */
    private static String[] pgn82 = {
            "e4", "Nc6",
            "Nf3", "h5",
            "c4", "g6",
            "d4", "e6",
            "a3", "a6",
            "b4", "d6",
            "Nc3", "Nf6",
            "e5", "dxe5",
            "Nxe5", "h4",
            "Nxc6", "bxc6",
            "Bg5", "Rb8",
            "Qf3", "Be7",
            "d5", "Nxd5",
            "Nxd5", "Bxg5",
            "Ne3", "Qd4",
            "Qxc6+", "Bd7",
            "Nc2", "Qc3+",
            "Ke2", "Qxc2+",
            "Kf3", "Bxc6+",
            "Kg4", "Qe4+",
            "f4", "Qf5#"
    };

    /**
     * The Pgn 83.
     */
    private static String[] pgn83 = {
            "Qe2+", "Ne7",
            "Rxa8", "Rxh1",
            "Bg5", "Nd7",
            "Qg2", "Rh8",
            "Bb5", "Rh6",
            "Nf3", "Bg7",
            "Bxh6", "Bb2",
            "Nbd2", "Bc1",
            "Bg5", "Qb6",
            "Bxe7", "Bxd2+",
            "Kxd2", "Qh6+",
            "Qg5", "Qa6",
            "Bxa6", "Nc5",
            "Rxc8+", "Kf7",
            "Qf6#"
    };

    /**
     * The Pgn 84.
     */
    private static String[] pgn84 = {
            "h5", "Rxh5",
            "gxh5", "Rxb4",
            "cxb4", "Rxb4",
            "axb4", "Rxc4",
            "dxc4", "Rxc4",
            "bxc4", "Rxa4",
            "b5", "Rxa2+",
            "bxa2", "Rxa2+",
            "Kxa2", "Rxf4",
            "gxf4", "Rxf4",
            "exf4", "Rxd4",
            "d3", "Rexe4",
            "fxe4", "Rxg2",
            "hxg2", "Rxg2",
            "fxg2", "Rexe4",
            "dxe4", "Rxe4",
            "f5", "Rxd1",
            "f6", "Rxe2",
            "f4", "Rxc2",
            "f7", "Rdxc1",
            "f8=Q+", "Kh7",
            "Qf5+", "Kh6",
            "Qxc2", "Rxc2",
            "g4", "Rxc4",
            "f5", "Ra4+",
            "Kb3", "Ra5",
            "b6", "Rb5+",
            "Ka4", "Rxb6",
            "e2", "Rxb2",
            "e4", "Re2",
            "Kb5", "Rxe4",
            "Kc5", "Re5+",
            "Kd4", "Kg5",
            "Kd3", "Kh4",
            "Kd4", "Kg5",
            "Kc4", "Kf4",
            "h6", "Re7",
            "f6", "Rh7",
            "f7", "Rxf7",
            "Kd5", "Rf5+",
            "gxf5", "Kxf5",
            "h7", "Kg6",
            "h8=Q", "Kf7",
            "h4", "Ke7",
            "h5", "Kf7",
            "h6", "Kg6",
            "Qg7+", "Kf5",
            "h7", "Kf4",
            "h8=R", "Kf3",
            "Rf8+", "Ke3",
            "h4", "Ke2",
            "g2", "Kd2",
            "h5", "Kc2",
            "h6", "Kb1",
            "h7", "Ka2",
            "h8=B", "Ka3",
            "Rf4", "Ka2",
            "g4", "Kb1",
            "g5", "Ka2",
            "g6", "Ka3",
            "Qe5", "Ka2",
            "g7", "Kb1",
            "g8=N", "Kc1",
            "Rf8", "Kb1",
            "Kd6", "Ka2",
            "Ke7", "Kb1",
            "Ke8", "Ka2",
            "Qb2#"
    };

    /**
     * The Pgn 85.
     */
    private static String[] pgn85 = {
            "h8=Q", "a1=Q",
            "Qxa1", "a2",
            "h7", "a3",
            "h8=Q", "a4",
            "Qxa8+", "Kc5",
            "h6", "a5",
            "h7", "a6",
            "h8=Q", "Kb4",
            "Qag7", "a1=Q",
            "h5", "Qxg7",
            "Qxg7", "a2",
            "h6", "a1=Q",
            "h7", "a3",
            "h8=Q", "a4",
            "h4", "a2",
            "h5", "a3",
            "h6", "a5",
            "h4", "a4",
            "h5", "Qxg7",
            "hxg7", "a1=Q",
            "h6", "Qxh1+",
            "Ke2", "Qxa8",
            "Qxa8", "a2",
            "Qh1", "Kb3",
            "h7", "a3",
            "h8=Q", "a1=Q",
            "g8=Q+", "Kc2",
            "Qd1+", "Qxd1+",
            "Ke3", "a2",
            "Qg6+", "Kc1",
            "Qc8+", "Kb2",
            "Qgc2+", "Qxc2",
            "Qb8+", "Kc1",
            "Kd4", "a1=Q+",
            "Kd5", "Qa5+",
            "Qb5", "Qc5+",
            "Kxc5", "Kc2",
            "Kc6", "Kc3",
            "Kc5", "Qxb5+",
            "Kxb5", "Kb3"
    };

    /**
     * The Pgn 86.
     */
    private static String[] pgn86 = {
            "Bb2+", "Ka4",
            "Bc2+", "Kb4",
            "Bhg7", "Kc5",
            "Ba3+", "Kb5",
            "Bcb3", "Kc6",
            "Bgd5+", "Kb5",
            "Bgf8", "Ka5",
            "Bab4+", "Kb5",
            "Bdc4+", "Kc6",
            "Ba4+", "Kc7",
            "Bfd6+", "Kb6",
            "Bcb5", "Kb7",
            "Ba5", "Ka7",
            "Bc6", "Ka6",
            "Bdc7", "Ka7",
            "Bab6+", "Ka6",
            "Bab5#"
    };

    /**
     * The Pgn 87.
     */
    private static String[] pgn87 = {
            "Nc2+", "Ka4",
            "Nb2+", "Kb5",
            "Nfe3", "Kc5",
            "Nf3", "Kb5",
            "Ng3", "Kc5",
            "Ncd3+", "Kb5",
            "Na3+", "Kc6",
            "Nbc4", "Kc7",
            "Nb4", "Kb7",
            "Nfe5", "Ka7",
            "Ned5", "Kb7",
            "Nf5", "Ka7",
            "Nb5+", "Kb7",
            "Na5+", "Kb8",
            "Nfd6", "Ka8",
            "Nb6+", "Kb8",
            "Nec6#"
    };

    /**
     * The Pgn 88.
     */
    private static String[] pgn88 = {
            "Qb7", "Ka4",
            "Qga7#"
    };

    /**
     * The Pgn 89.
     */
    private static String[] pgn89 = {
            "Rg3+", "Ka4",
            "Rh4+", "Ka5",
            "Rg5+", "Ka6",
            "Rh6+", "Ka7",
            "Rg7+", "Ka8",
            "Rh8#"
    };


    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        final int SIZE = 8;
        String fen89 = "8/8/8/8/8/k7/8/K5RR";
        String fen88 = "8/8/8/8/8/k7/8/K5QQ";
        String fen87 = "8/8/8/8/8/k7/8/K1NNNNNN";
        String fen81 = "rnbqkbnr/8/8/8/8/8/8/RNBQKBNR";
        String fen82 = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR";
        String fen83 = "rnbqkbnr/8/8/8/8/8/8/RNBQKBNR";
        String fen84 = "rrrrrrrk/rrrrrrrr/8/8/PPPPPPPP/PPPPPPPP/PPPPPPPP/KPPPPPPP";
        String fen85 = "p7/p6P/p1k4P/p6P/p6P/p4K1P/p6P/7P";
        String fen86 = "6B1/6BB/7B/8/8/k7/8/K7";
        String[] fens = {fen81, fen82, fen83, fen84, fen85, fen86, fen87, fen88, fen89};
        String[][] pgns = {pgn81, pgn82, pgn83, pgn84, pgn85, pgn86, pgn87, pgn88, pgn89};
        for (int k = 0; k < 9; k++) {
//        int k = 4;
            System.out.println("game number: " + k);
            String fen = fens[k];
            String[] pgn = pgns[k];

            Game game = new Game(fen, pgn, SIZE);

            game.printBoard();

            game.play();
        }
    }
}
