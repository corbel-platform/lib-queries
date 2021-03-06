package io.corbel.lib.queries.request;

import static org.fest.assertions.api.Assertions.assertThat;

import org.junit.Test;

import io.corbel.lib.queries.BooleanQueryLiteral;
import io.corbel.lib.queries.QueryNodeImpl;
import io.corbel.lib.queries.StringQueryLiteral;

public class ResourceQueryTest {

    private static final String FIELD1 = "field1";
    private static final String FIELD2 = "field2";
    private static final String VALUE1 = "value1";
    private static final boolean VALUE2 = true;

    @Test
    public void toStringTest() {
        ResourceQuery resourceQuery = new ResourceQuery();
        StringQueryLiteral value1 = new StringQueryLiteral();
        value1.setLiteral(VALUE1);
        BooleanQueryLiteral value2 = new BooleanQueryLiteral();
        value2.setLiteral(VALUE2);

        resourceQuery.addQueryNode(new QueryNodeImpl(QueryOperator.$EQ, FIELD1, value1));
        assertThat(resourceQuery.toString()).isEqualTo("[{\"$eq\":{\"" + FIELD1 + "\":\"" + VALUE1 + "\"}}]");

        ResourceQuery resourceQuery2 = new ResourceQuery();
        resourceQuery2.addQueryNode(new QueryNodeImpl(QueryOperator.$GT, FIELD2, value2));
        resourceQuery2.addQueryNode(new QueryNodeImpl(QueryOperator.$EQ, FIELD1, value1));
        assertThat(resourceQuery2.toString())
                .isEqualTo("[{\"$gt\":{\"" + FIELD2 + "\":" + VALUE2 + "}},{\"$eq\":{\"" + FIELD1 + "\":\"" + VALUE1 + "\"}}]");

    }

    @Test
    public void getFiltersTest() {
        ResourceQuery resourceQuery = new ResourceQuery();
        StringQueryLiteral value1 = new StringQueryLiteral();
        value1.setLiteral(VALUE1);
        BooleanQueryLiteral value2 = new BooleanQueryLiteral();
        value2.setLiteral(VALUE2);

        resourceQuery.addQueryNode(new QueryNodeImpl(QueryOperator.$EQ, FIELD1, value1));
        resourceQuery.addQueryNode(new QueryNodeImpl(QueryOperator.$GT, FIELD2, value2));
        assertThat(resourceQuery.getFilters()).containsExactly(FIELD1, FIELD2);
    }
}
