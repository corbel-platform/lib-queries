package io.corbel.lib.queries.model;

/**
 * @author Alberto J. Rubio
 *
 */
public class Position {

    private Coordinates coordinates;
    private Double maxDistance;

    public Position(){}

    public Position(Coordinates coordinates) {
        this(coordinates, null);
    }

    public Position(Coordinates coordinates, Double maxDistance) {
        this.coordinates = coordinates;
        this.maxDistance = maxDistance;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Double getMaxDistance() {
        return maxDistance;
    }

    public void setMaxDistance(Double maxDistance) {
        this.maxDistance = maxDistance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (coordinates != null ? !coordinates.equals(position.coordinates) : position.coordinates != null)
            return false;
        return maxDistance != null ? maxDistance.equals(position.maxDistance) : position.maxDistance == null;

    }

    @Override
    public int hashCode() {
        int result = coordinates != null ? coordinates.hashCode() : 0;
        result = 31 * result + (maxDistance != null ? maxDistance.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "{" + coordinates + ", maxDistance: " + maxDistance + "}";
    }
}
