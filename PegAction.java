
import aima.core.agent.impl.DynamicAction;
import aima.core.util.datastructure.XYLocation;

/**
 * Pegs can be removed, and moved. For movements, a vertical direction
 * is assumed. Therefore, only the end point needs to be specified.
 * 
 * @author Jevon Cowell
 */
public class PegAction extends DynamicAction {
    public static final String REMOVE_PEG = "removePegAt";
    public static final String MOVE_PEG = "movePegTo";
    public static final String UP = "UP";
    public static final String DOWN = "DOWN";
    public static final String LEFT = "LEFT";
    public static final String RIGHT = "RIGHT";

	public static final String ATTRIBUTE_PEG_LOC = "location";

	/**
	 * Creates a queen action. Supported values of type are {@link #PLACE_PEG}
	 * , {@link #REMOVE_PEG}, or {@link #MOVE_PEG}.
	 */
	public PegAction(String type, XYLocation loc) {
		super(type);
		setAttribute(ATTRIBUTE_PEG_LOC, loc);
	}

	public XYLocation getLocation() {
		return (XYLocation) getAttribute(ATTRIBUTE_PEG_LOC);
	}

	public int getX() {
		return getLocation().getXCoOrdinate();
	}

	public int getY() {
		return getLocation().getYCoOrdinate();
	}
}
