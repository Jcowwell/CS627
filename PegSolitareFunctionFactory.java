import java.util.LinkedHashSet;
import java.util.Set;

import aima.core.agent.Action;
import aima.core.search.framework.problem.ActionsFunction;
import aima.core.search.framework.problem.ResultFunction;
import aima.core.util.datastructure.XYLocation;

public class PegSolitareFunctionFactory {
    private static ActionsFunction _actionsFunction = null;
	private static ResultFunction _resultFunction = null;

	public static ActionsFunction getActionsFunction() {
		if (null == _actionsFunction) {
			_actionsFunction = new EPActionsFunction();
		}
		return _actionsFunction;
	}

	public static ResultFunction getResultFunction() {
		if (null == _resultFunction) {
			_resultFunction = new EPResultFunction();
		}
		return _resultFunction;
	}

	private static class EPActionsFunction implements ActionsFunction {
		public Set<Action> actions(Object state) {
			PegSolitareBoard board = (PegSolitareBoard) state;

			Set<Action> actions = new LinkedHashSet<Action>();
            // I relaized too late that I could've just iterated over the array and just get the position based off the index like a normal human being.
            for (int i = 0; i < 7; i++) {
                for (int j = 0; j < 7; j++) {
                    XYLocation loc = new XYLocation(i, j);
                    if(board.pegExistsAt(loc) ) {
                        if (board.canMovePeg(Direction.DOWN, loc)) {
                            actions.add(new PegAction("DOWN", loc));
                        }
                        if (board.canMovePeg(Direction.LEFT, loc)) {
                            actions.add(new PegAction("LEFT", loc));
                        }
                        if (board.canMovePeg(Direction.RIGHT, loc)) {
                            actions.add(new PegAction("RIGHT", loc));
                        }
                        if (board.canMovePeg(Direction.UP, loc)) {
                            actions.add(new PegAction("UP", loc));
                        }
                    }
                }
            }
            



			return actions;
		}
	}

	private static class EPResultFunction implements ResultFunction {
		public Object result(Object s, Action a) {
            PegSolitareBoard board = (PegSolitareBoard) s;
            PegAction action = (PegAction) a;

			if (Direction.UP.toString().equals(action.getName())
					&& board.canMovePeg(Direction.UP, action.getLocation())) {
				PegSolitareBoard newBoard = new PegSolitareBoard(board);
				newBoard.movePegUp(action.getLocation());
				return newBoard;
			} else if (Direction.DOWN.toString().equals(action.getName())
                    && board.canMovePeg(Direction.DOWN, action.getLocation())) {
				PegSolitareBoard newBoard = new PegSolitareBoard(board);
				newBoard.movePegDown(action.getLocation());
				return newBoard;
			} else if (Direction.LEFT.toString().equals(action.getName())
                    && board.canMovePeg(Direction.LEFT, action.getLocation())) {
				PegSolitareBoard newBoard = new PegSolitareBoard(board);
				newBoard.movePegLeft(action.getLocation());
				return newBoard;
			} else if (Direction.RIGHT.toString().equals(action.getName())
                    && board.canMovePeg(Direction.RIGHT, action.getLocation())) {
				PegSolitareBoard newBoard = new PegSolitareBoard(board);
				newBoard.movePegRight(action.getLocation());
				return newBoard;
			}

			// The Action is not understood or is a NoOp
			// the result will be the current state.
			return s;
		}
	}
}