package Board;

public class Maze {
    private String[][] _board;
    private String[][] _paths;

    Maze(String[][] board, String[][] paths) {
        _board = board;
        _paths = paths;
    }

    public String[][] get_board() {
        return _board;
    }

    String[][] get_paths() {
        return _paths;
    }
}
