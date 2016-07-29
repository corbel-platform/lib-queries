package io.corbel.lib.queries.validator;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.DoubleNode;
import io.corbel.lib.queries.exception.MalformedJsonQueryException;
import io.corbel.lib.queries.request.QueryOperator;
import org.springframework.util.Assert;

/**
 * Created by Alberto J. Rubio
 */
public class QueryLiteralValidator {

    public static void validate(JsonNode nodeField, QueryOperator operator) throws MalformedJsonQueryException {
        switch (operator) {
            case $NEAR:
                try {
                    if(nodeField.has("maxDistance")) {
                        Assert.isInstanceOf(DoubleNode.class, nodeField.get("maxDistance"));
                    }
                    Assert.notNull(nodeField.get("coordinates"));
                    Assert.isInstanceOf(DoubleNode.class, nodeField.get("coordinates").get("x"));
                    Assert.isInstanceOf(DoubleNode.class, nodeField.get("coordinates").get("y"));
                } catch(IllegalArgumentException e) {
                    throw new MalformedJsonQueryException("Wrong position format", e);
                }
        }
    }
}
