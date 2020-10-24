import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import aima.core.agent.Action;
import aima.core.search.framework.problem.Problem;

public class PegSolitareAlgorithtm {

    private boolean goalReached;

    public PegSolitareAlgorithtm(Problem problem) {
        goalReached = DepthFirstSearch(problem);
    }

    public boolean wasGoalReached() {
        return goalReached;
    }

    // Didn't implement a Node Strucutre to save the actions taken.
    private boolean DepthFirstSearch (Problem problem){
        Stack<Object> stack = new Stack<Object>();
        Set<Object> explored = new HashSet<Object>();
        stack.add(problem.getInitialState());
        while(!stack.isEmpty()) {
            Object state = stack.pop();
            if(!explored.contains(state)) {
                explored.add(state);
                if(problem.isGoalState(state)) {
                    return true;
                }
                for(Object board: getSuccessors(state, problem)) {
                    stack.add(board);
                }
            }
        }

        
        return false;
    }

    private Stack<Object> getSuccessors(Object state, Problem problem) {
        Stack<Object> boards = new Stack<Object>();
        Set<Action> actions = problem.getActionsFunction().actions(state);
        for (Action action : actions) {
            boards.add(problem.getResultFunction().result(state, action));
        }

        return boards;
    }
}