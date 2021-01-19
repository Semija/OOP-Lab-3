 import java.util.*;
 
public class AStarState
{

    private final Map2D map;

    public AStarState(Map2D map) {
        if (map == null)
            throw new NullPointerException("map cannot be null");

        this.map = map;
    }

    public Map2D getMap()
    {
        return map;
    }



	private final HashMap<Location, Waypoint> openVertex = new HashMap<>();
	private final HashMap<Location, Waypoint> closeVertex = new HashMap<>();

    public int numOpenWaypoints()
    {
        return openVertex.size();
    }

    public Waypoint getMinOpenWaypoint() {
        if (openVertex.isEmpty()) return null;

        float minCost = 3.4e+38f;
        Waypoint minCostObject = null;

        ArrayList<Waypoint> values = new ArrayList<>(openVertex.values());
        for (Waypoint element : values) {
            if (element.getTotalCost() < minCost) {
                minCost = element.getTotalCost();
                minCostObject = element;
            }
        }
        return minCostObject;
    }

    public boolean addOpenWaypoint(Waypoint newWP) {
        ArrayList<Location> locations = new ArrayList<Location>(openVertex.keySet());

        Location newLoc = newWP.getLocation();

        for (Location index : locations) {
            if (newLoc.equals(index)) {
               Waypoint oldWP = openVertex.get(index);
                double oldCost = oldWP.getPreviousCost();
                double newCost = newWP.getPreviousCost();

                if (newCost < oldCost) {
                    openVertex.put(newLoc, newWP);
                    return true;
                }
                return false;
            }
        }

        openVertex.put(newLoc, newWP);
        return true;
    }

    public boolean isLocationClosed(Location loc)
    {
        return openVertex.containsKey(loc);
    }

    public void closeWaypoint(Location loc) {
        Waypoint wp = openVertex.get(loc);
        openVertex.remove(loc);
        closeVertex.put(loc, wp);
    }

}