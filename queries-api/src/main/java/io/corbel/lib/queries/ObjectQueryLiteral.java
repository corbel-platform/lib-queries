package io.corbel.lib.queries;

import com.fasterxml.jackson.databind.JsonNode;
import io.corbel.lib.queries.request.QueryLiteral;

/**
 * @author Alberto J. Rubio
 *
 */
public class ObjectQueryLiteral extends QueryLiteral<JsonNode> {

    public ObjectQueryLiteral() { super(); }

    public ObjectQueryLiteral(JsonNode objectNode) { super(objectNode); }

    @Override
    public String toString() {
        return getLiteral().toString();
    }
}
