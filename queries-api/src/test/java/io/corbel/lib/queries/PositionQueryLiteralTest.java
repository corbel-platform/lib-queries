package io.corbel.lib.queries;

import io.corbel.lib.queries.exception.QueryMatchingException;
import io.corbel.lib.queries.model.Coordinates;
import io.corbel.lib.queries.model.Position;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class PositionQueryLiteralTest {

    private static final PositionQueryLiteral QUERY_LITERAL = new PositionQueryLiteral(new Position(new Coordinates(45.60, 67.80), 50.5));

    @Test
    public void toStringTest() {
        assertThat(QUERY_LITERAL.toString()).isEqualTo("{coordinates{latitude: 45.6, longitude: 67.8}, maxDistance: 50.5}");
    }

    @Test
    public void nearTest() throws QueryMatchingException {
        Coordinates coordinates = new Coordinates(45.6, 67.8);
        assertThat(QUERY_LITERAL.near(coordinates)).isTrue();

        coordinates = new Coordinates(67.6, 40.8);
        assertThat(QUERY_LITERAL.near(coordinates)).isFalse();
    }

}
