package io.corbel.lib.queries;

import com.fasterxml.jackson.databind.JsonNode;
import io.corbel.lib.queries.exception.MalformedJsonQueryException;
import io.corbel.lib.queries.exception.QueryMatchingException;
import io.corbel.lib.queries.model.Coordinates;
import io.corbel.lib.queries.model.Position;
import io.corbel.lib.queries.parser.CustomJsonParser;
import io.corbel.lib.queries.request.QueryLiteral;

/**
 * @author Alberto J. Rubio
 *
 */
public class PositionQueryLiteral extends QueryLiteral<Position> {

    public PositionQueryLiteral() { super(); }

    public PositionQueryLiteral(CustomJsonParser customJsonParser, JsonNode objectNode)
            throws MalformedJsonQueryException {
        super(customJsonParser.readValueAsObject(objectNode, Position.class));
    }

    public PositionQueryLiteral(Position position) {
        super(position);
    }

    @Override
    protected boolean near(Object object) throws QueryMatchingException {
        Coordinates coordinates = (Coordinates) object;
        return near(literal.getCoordinates().getX(), literal.getCoordinates().getY(),
                    coordinates.getX(), coordinates.getY(), literal.getMaxDistance());
    }

    private boolean near(double x1, double y1, double x2, double y2, double maxDistance) {
        double theta = y1 - y2;
        double distance = Math.sin(deg2rad(x1)) * Math.sin(deg2rad(x2)) + Math.cos(deg2rad(x1)) *
                      Math.cos(deg2rad(x2)) * Math.cos(deg2rad(theta));
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