package game2048;

import java.util.Formatter;
import java.util.Observable;


/** The state of a game of 2048.
 *  @author Anna
 */
public class Model extends Observable {
    /** Current contents of the board. */
    private Board board;
    /** Current score. */
    private int score;
    /** Maximum score so far.  Updated when game ends. */
    private int maxScore;
    /** True iff game is ended. */
    private boolean gameOver;

    /* Coordinate System: column C, row R of the board (where row 0,
     * column 0 is the lower-left corner of the board) will correspond
     * to board.tile(c, r).  Be careful! It works like (x, y) coordinates.
     */

    /** Largest piece value. */
    public static final int MAX_PIECE = 2048;

    /** A new 2048 game on a board of size SIZE with no pieces
     *  and score 0. */
    public Model(int size) {
        board = new Board(size);
        score = maxScore = 0;
        gameOver = false;
    }

    /** A new 2048 game where RAWVALUES contain the values of the tiles
     * (0 if null). VALUES is indexed by (row, col) with (0, 0) corresponding
     * to the bottom-left corner. Used for testing purposes. */
    public Model(int[][] rawValues, int score, int maxScore, boolean gameOver) {
        int size = rawValues.length;
        board = new Board(rawValues, score);
        this.score = score;
        this.maxScore = maxScore;
        this.gameOver = gameOver;
    }

    /** Return the current Tile at (COL, ROW), where 0 <= ROW < size(),
     *  0 <= COL < size(). Returns null if there is no tile there.
     *  Used for testing. Should be deprecated and removed.
     *  */
    public Tile tile(int col, int row) {
        return board.tile(col, row);
    }

    /** Return the number of squares on one side of the board.
     *  Used for testing. Should be deprecated and removed. */
    public int size() {
        return board.size();
    }

    /** Return true iff the game is over (there are no moves, or
     *  there is a tile with value 2048 on the board). */
    public boolean gameOver() {
        checkGameOver();
        if (gameOver) {
            maxScore = Math.max(score, maxScore);
        }
        return gameOver;
    }

    /** Return the current score. */
    public int score() {
        return score;
    }

    /** Return the current maximum game score (updated at end of game). */
    public int maxScore() {
        return maxScore;
    }

    /** Clear the board to empty and reset the score. */
    public void clear() {
        score = 0;
        gameOver = false;
        board.clear();
        setChanged();
    }

    /** Add TILE to the board. There must be no Tile currently at the
     *  same position. */
    public void addTile(Tile tile) {
        board.addTile(tile);
        checkGameOver();
        setChanged();
    }

    /** Tilt the board toward SIDE. Return true if this changes the board.
     *
     * 1. If two Tile objects are adjacent in the direction of motion and have
     *    the same value, they are merged into one Tile of twice the original
     *    value and that new value is added to the score instance variable
     * 2. A tile that is the result of a merge will not merge again on that
     *    tilt. So each move, every tile will only ever be part of at most one
     *    merge (perhaps zero).
     * 3. When three adjacent tiles in the direction of motion have the same
     *    value, then the leading two tiles in the direction of motion merge,
     *    and the trailing tile does not.
     * */
    public boolean tilt(Side side) {
        boolean changed;
        changed = false;

        // TODO: Modify this.board (and perhaps this.score) to account
        // for the tilt to the Side SIDE. If the board changed, set the
        // changed local variable to true.

        // Check if there are any valid moves on the board, move tiles and set changed to true.
        // Otherwise, do nothing.

        board.setViewingPerspective(side);
        // From WEST to EAST, from NORTH to SOUTH, iterate every position, check if a tile can move it here.
        int size = board.size();
        for (int col = 0; col < size; col++) {
            for (int targetRow = size - 1; targetRow >= 0; targetRow--) {
                // Get a tile at current column from top to down except the TARGETROW.
                for (int row = targetRow - 1; row >= 0; row--) {
                    Tile tile = board.tile(col, row);

                    if (tile != null) {
                        changed = true;
                        // Move the tile to the highest row where it can reach.
                        // By meaning "the highest row where it can reach" refers to
                        // 1. the TARGETROW has no tile
                        // 2. the TARGETROW has a tile which can be merged
                        // 3. the TARGETROW has a tile but can't merge
                        Tile targetTile = board.tile(col, targetRow);
                        if (targetTile == null) {
                            // For situation 1: the TARGETROW has no tile
                            board.move(col, targetRow, tile);
                        } else if (targetTile.value() == tile.value()){
                            // For situation 2: the TARGETROW has a tile which can be merged
                            // Move the tile to merge, then break to next TARGETTILE
                            board.move(col, targetRow, tile);
                            score += tile.value() * 2;
                            break;
                        } else {
                            // For situation 3: the TARGETROW has a tile but can't merge
                            // It should move the tile to TARGETROW-1 row, then break to next TARGETTILE
                            board.move(col, targetRow - 1, tile);
                            break;
                        }
                    }
                }
            }
        }
        board.setViewingPerspective(Side.NORTH);

        checkGameOver();
        if (changed) {
            setChanged();
        }
        return changed;
    }

    /** Checks if the game is over and sets the gameOver variable
     *  appropriately.
     */
    private void checkGameOver() {
        gameOver = checkGameOver(board);
    }

    /** Determine whether game is over. */
    private static boolean checkGameOver(Board b) {
        return maxTileExists(b) || !atLeastOneMoveExists(b);
    }

    /** Returns true if at least one space on the Board is empty.
     *  Empty spaces are stored as null.
     * */
    public static boolean emptySpaceExists(Board board) {
        // TODO: Fill in this function.
        for (Tile tile : board) {
            if (tile == null) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if any tile is equal to the maximum valid value.
     * Maximum valid value is given by MAX_PIECE. Note that
     * given a Tile object t, we get its value with t.value().
     */
    public static boolean maxTileExists(Board board) {
        // TODO: Fill in this function.
        for (Tile tile : board) {
            if (tile != null && tile.value() == MAX_PIECE) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if there are any valid moves on the board.
     * There are two ways that there can be valid moves:
     * 1. There is at least one empty space on the board.
     * 2. There are two adjacent tiles with the same value.
     */
    public static boolean atLeastOneMoveExists(Board board) {
        // TODO: Fill in this function.
        return emptySpaceExists(board) || twoAdjacentSameTilesExist(board);
    }

    /**
     * Returns true if there are two adjacent tiles with the same value,
     * otherwise return false.
     */
    private static boolean twoAdjacentSameTilesExist(Board board) {
        Side[] directions = {Side.NORTH, Side.SOUTH, Side.WEST, Side.EAST};
        for (Tile tile : board) {
            if (tile != null) {
                for (Side direction : directions) {
                    Tile adjacentTile = getAdjacentTile(board, tile, direction);
                    if (adjacentTile != null && tile.value() == adjacentTile.value()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Search a tile which is adjacent to the giving tile in specific direction
     * Return the tile if it exists, otherwise return null
     */
    private static Tile getAdjacentTile(Board board, Tile tile, Side direction) {
        int col, row;
        int boardSize = board.size();
        switch (direction) {
            case NORTH:
                col = tile.col();
                row = tile.row() + 1;
                break;
            case SOUTH:
                col = tile.col();
                row = tile.row() - 1;
                break;
            case WEST:
                col = tile.col() - 1;
                row = tile.row();
                break;
            case EAST:
                col = tile.col() + 1;
                row = tile.row();
                break;
            default:
                return null;
        }

        Tile adjacentTile = null;
        if (col >= 0 && col < boardSize && row >= 0 && row < boardSize) {
            adjacentTile = board.tile(col, row);
        }
        return adjacentTile;
    }


    @Override
    /** Returns the model as a string, used for debugging. */
    public String toString() {
        Formatter out = new Formatter();
        out.format("%n[%n");
        for (int row = size() - 1; row >= 0; row -= 1) {
            for (int col = 0; col < size(); col += 1) {
                if (tile(col, row) == null) {
                    out.format("|    ");
                } else {
                    out.format("|%4d", tile(col, row).value());
                }
            }
            out.format("|%n");
        }
        String over = gameOver() ? "over" : "not over";
        out.format("] %d (max: %d) (game is %s) %n", score(), maxScore(), over);
        return out.toString();
    }

    @Override
    /** Returns whether two models are equal. */
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        } else if (getClass() != o.getClass()) {
            return false;
        } else {
            return toString().equals(o.toString());
        }
    }

    @Override
    /** Returns hash code of Model’s string. */
    public int hashCode() {
        return toString().hashCode();
    }
}
