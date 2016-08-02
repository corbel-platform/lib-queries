package io.corbel.lib.queries;

import io.corbel.lib.queries.exception.QueryMatchingException;
import io.corbel.lib.queries.model.Coordinates;
import io.corbel.lib.queries.model.Position;
import io.corbel.lib.queries.request.QueryLiteral;

/**
 * @author Alberto J. Rubio
 *
 */
public class PositionQueryLiteral extends QueryLiteral<Position> {

    public PositionQueryLiteral() { super(); }

    public PositionQueryLiteral(Position position) {
        super(position);
    }

    @Override
    protected boolean near(Object object) throws QueryMatchingException {
        Coordinates coordinates = (Coordinates) object;
        return near(literal.getCoordinates().getLatitude(), literal.getCoordinates().getLongitude(),
                    coordinates.getLatitude(), coordinates.getLongitude(), literal.getMaxDistance());
    }

    private boolean near(double latitude1, double longitude1, double latitude2, double longitude2, double maxDistance) {
        double theta = longitude1 - longitude2;
        double distance = Math.sin(deg2rad(latitude1)) * Math.sin(deg2rad(latitude2)) + Math.cos(deg2rad(latitude1)) *
                      Math.cos(deg2rad(latitude2)) * Math.cos(deg2rad(theta));
        //Convert distance to meters
        distance = Math.acos(distance);
        distance = rad2deg(distance);
        distance = distance * 60 * 1.1515;
        distance = distance * 1609.344;
        //Compare distance & max distance
        return maxDistance >= distance;
    }

    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }

    @Override
    public String toString() {
        return getLiteral().toString();
    }

}