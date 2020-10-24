import java.util.ArrayList;
import java.util.List;
import java.util.Arrays; 

import aima.core.agent.Action;
import aima.core.agent.impl.DynamicAction;
import aima.core.util.datastructure.XYLocation;

/*
    Based on EightPuzzleBoard implementation
*/
public class PegSolitareBoard {
    
    public static Action LEFT = new DynamicAction("Left");
	public static Action RIGHT = new DynamicAction("Right");
	public static Action UP = new DynamicAction("Up");
    public static Action DOWN = new DynamicAction("Down");
    private int state[];

    //
	// PUBLIC METHODS
	//

	public PegSolitareBoard() {
		state = new int[] {0, 0, 1, 1, 1, 0, 0,
                           0, 0, 1, 1, 1, 0, 0,
                           1, 1, 1, 1, 1, 1, 1,
                           1, 1, 1, 2, 1, 1, 1,
                           1, 1, 1, 1, 1, 1, 1,
                           0, 0, 1, 1, 1, 0, 0,
                           0, 0, 1, 1, 1, 0, 0};
	}

	public PegSolitareBoard(int[] state) {
		this.state = new int[state.length];
		System.arraycopy(state, 0, this.state, 0, state.length);
    }
    
    public PegSolitareBoard(PegSolitareBoard copyBoard) {
		this(copyBoard.getState());
    }
    
    public int[] getState() {
		return state;
	}

	public int getValueAt(XYLocation loc) {
		return getValueAt(loc.getXCoOrdinate(), loc.getYCoOrdinate());
	}

	public XYLocation getLocationOf(int val) {
		int absPos = getPositionOf(val);
		return new XYLocation(getXCoOrdinateCoord(absPos), getYCoOrdinateCoord(absPos));
    }
    
    public void movePegRight(XYLocation loc) {
		// Get the value at the input location
		// Set the value of the new location to the value of the input location
		// Set the value of the input location to 2
		// Set the value Directional adjecent location to 2
		int peg = getValueAt(loc);
		int x = loc.getXCoOrdinate();
		int y = loc.getYCoOrdinate();

		int x2 = loc.right().getXCoOrdinate();
		int y2 = loc.right().getYCoOrdinate();

		int x3 = loc.right().right().getXCoOrdinate();
		int y3 = loc.right().right().getYCoOrdinate();

		if (x3 < 7) {
			setValue(x3, y3, peg);
			setValue(x2, y2, 2);
			setValue(x, y, 2);

		}
	}

	public void movePegLeft(XYLocation loc) {
		int peg = getValueAt(loc);
		int x = loc.getXCoOrdinate();
		int y = loc.getYCoOrdinate();

		int x2 = loc.left().getXCoOrdinate();
		int y2 = loc.left().getYCoOrdinate();

		int x3 = loc.left().left().getXCoOrdinate();
		int y3 = loc.left().left().getYCoOrdinate();

		if (x3 >= 0) {
			setValue(x3, y3, peg);
			setValue(x2, y2, 2);
			setValue(x, y, 2);

		}
	}

	public void movePegDown(XYLocation loc) {
		int peg = getValueAt(loc);
		int x = loc.getXCoOrdinate();
		int y = loc.getYCoOrdinate();

		int x2 = loc.down().getXCoOrdinate();
		int y2 = loc.down().getYCoOrdinate();

		int x3 = loc.down().down().getXCoOrdinate();
		int y3 = loc.down().down().getYCoOrdinate();

		if (y3 < 7) {
			setValue(x3, y3, peg);
			setValue(x2, y2, 2);
			setValue(x, y, 2);

		}
	}

	public void movePegUp(XYLocation loc) {
		int peg = getValueAt(loc);
		int x = loc.getXCoOrdinate();
		int y = loc.getYCoOrdinate();

		int x2 = loc.up().getXCoOrdinate();
		int y2 = loc.up().getYCoOrdinate();

		int x3 = loc.up().up().getXCoOrdinate();
		int y3 = loc.up().up().getYCoOrdinate();

		if (y3 >= 0) {
			setValue(x3, y3, peg);
			setValue(x2, y2, 2);
			setValue(x, y, 2);

		}
    }
	
	public void moveUp(int x1, int y1, int x2, int y2) {

	}

	public boolean pegExistsAt(int x, int y) {
		return getValueAt(x, y) == 1;
	}

	public boolean pegExistsAt(XYLocation loc) {
		return  getValueAt(loc) == 1;
	}

	public boolean isOutOfBounds(int x, int y) {
		return  getValueAt(x, y) == 0;
	}

	public boolean isOutOfBounds(XYLocation loc) {
		return  getValueAt(loc) == 0;
	}

	public boolean isEmptySpace(int x, int y) {
		return  getValueAt(x, y) == 2;
	}

	public boolean isEmptySpace(XYLocation loc) {
		return  getValueAt(loc) == 0;
	}

	public boolean canMovePeg(Direction action, XYLocation loc) {
		boolean result = true;
		switch(action) {
		case DOWN:
			result = (getValueAt(loc.down()) == 1 && getValueAt(loc.down().down()) == 2);
			break;
		case LEFT:
			result = (getValueAt(loc.left()) == 1 && getValueAt(loc.left().left()) == 2);
			break;
		case RIGHT:
			result = (getValueAt(loc.right()) == 1 && getValueAt(loc.right().right()) == 2);
			break;
		case UP:
			result = (getValueAt(loc.up()) == 1 && getValueAt(loc.up().up()) == 2);
			break;
		default:
			break;
		}
		return result;
	}

    public List<XYLocation> getPositions() {
		ArrayList<XYLocation> result = new ArrayList<>(9);
		for (int i = 0; i < 49; i++) {
			int absPos = getPositionOf(i);
			result.add(new XYLocation(getXCoOrdinateCoord(absPos), getYCoOrdinateCoord(absPos)));
		}
		return result;
	}

	public void setBoard(List<XYLocation> locs) {
		int count = 0;
		for (XYLocation loc : locs)
			setValue(loc.getXCoOrdinate(), loc.getYCoOrdinate(), count++);
	}

	// public boolean canMovePeg(Action action) {
	// 	boolean result = true;
	// 	int absPos = getPositionOf(0);
	// 	if (action.equals(LEFT))
	// 		result = (getXCoOrdinateCoord(absPos) != 0);
	// 	else if (action.equals(RIGHT))
	// 		result = (getXCoOrdinateCoord(absPos) != 2);
	// 	else if (action.equals(UP))
	// 		result = (getYCoOrdinateCoord(absPos) != 0);
	// 	else if (action.equals(DOWN))
	// 		result = (getYCoOrdinateCoord(absPos) != 2);
	// 	return result;
	// }

	public int getSize() {
		return state.length;
	}

	@Override
	public boolean equals(Object o) {
		if (o != null && getClass() == o.getClass()) {
			PegSolitareBoard aBoard = (PegSolitareBoard) o;
			if (!Arrays.equals(state, aBoard.state)) {
				return false;
			}
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int result = 17;
		for (int i = 0; i < 32; i++) {
			int position = this.getPositionOf(i);
			result = 37 * result + position;
		}
		return result;
	}

	@Override
	public String toString() {
		return state[0] + " " + state[1] + " " + state[2] + " " + state[3] + " " + state[4] + " " + state[5] + " " + state[6] + "\n" + 
               state[7] + " " + state[8] + " " + state[9] + " " + state[10] + " " + state[11] + " " + state[12] + " " + state[13] + "\n" +
               state[14] + " " + state[15] + " " + state[16] + " " + state[17] + " " + state[18] + " " + state[19] + " " + state[20] + "\n" +
               state[21] + " " + state[22] + " " + state[23] + " " + state[24] + " " + state[25] + " " + state[26] + " " + state[27] + "\n" +
               state[28] + " " + state[29] + " " + state[30] + " " + state[31] + " " + state[32] + " " + state[33] + " " + state[34] + "\n" +
               state[35] + " " + state[36] + " " + state[37] + " " + state[38] + " " + state[39] + " " + state[40] + " " + state[41] + "\n" +
               state[42] + " " + state[43] + " " + state[44] + " " + state[45] + " " + state[46] + " " + state[47] + " " + state[48] + "\n";
	}

	//
	// PRIVATE METHODS
	//

	/**
	 * Note: The graphic representation maps x values on row numbers (x-axis in
	 * vertical direction).
	 */
	private int getXCoOrdinateCoord(int absPos) {
		return absPos % 7;
	}

	/**
	 * Note: The graphic representation maps y values on column numbers (y-axis
	 * in horizontal direction).
	 */
	private int getYCoOrdinateCoord(int absPos) {
		return absPos / 7;
	}

	private int getAbsPosition(int x, int y) {
		return x + 7 * y;
	}

	private int getValueAt(int x, int y) {
		// refactor this use either case or a div/mod soln
		if(x < 0 || x > 6 || y < 0 || y > 6) {
			return 0;
		}
		try {
			return state[getAbsPosition(x, y)];
		} catch(Exception e) {
			return 0;
		} 
	}

	private int getPositionOf(int val) {
		for (int i = 0; i < 49; i++)
			if (state[i] == val)
				return i;
		return -1;
	}

	private void setValue(int x, int y, int val) {
		int absPos = getAbsPosition(x, y);
		state[absPos] = val;
	}


}