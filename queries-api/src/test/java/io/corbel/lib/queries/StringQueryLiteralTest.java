package io.corbel.lib.queries;

import static org.fest.assertions.api.Assertions.assertThat;

import org.junit.Test;

import io.corbel.lib.queries.exception.QueryMatchingException;
import io.corbel.lib.queries.request.QueryLiteral;
import io.corbel.lib.queries.request.QueryOperator;

public class StringQueryLiteralTest {

    @Test
    public void eqTest() throws QueryMatchingException {
        QueryLiteral<String> literal = new StringQueryLiteral("something");
        assertThat(literal.operate(QueryOperator.$EQ, "something")).isTrue();

        assertThat(literal.operate(QueryOperator.$EQ, "other")).isFalse();
    }

    @Test
    public void likeTest() throws QueryMatchingException {
        QueryLiteral<String> literal = new StringQueryLiteral(".*something.*");
        assertThat(literal.operate(QueryOperator.$LIKE, "some something some")).isTrue();

        assertThat(literal.operate(QueryOperator.$LIKE, "some SomEthing some")).isTrue();

        assertThat(literal.operate(QueryOperator.$LIKE, "other")).isFalse();
    }

    @Test(expected = QueryMatchingException.class)
    public void notAllowedOperatorTest() throws QueryMatchingException {
        QueryLiteral<String> literal = new StringQueryLiteral("something");
        literal.operate(QueryOperator.$IN, "something");
    }
}
