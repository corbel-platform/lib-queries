package io.corbel.lib.queries.request;

public enum QueryOperator {

    //Primitive operators
    $EQ(false, false),
    $GT(false, false),
    $GTE(false, false),
    $LT(false, false),
    $LTE(false, false),
    $NE(false, false),
    $LIKE(false, false),
    $EXISTS(false, false),
    $SIZE(false, false),

    //Array operators
    $IN(true, false),
    $NIN(true, false),
    $ALL(true, false),
    $ELEM_MATCH(true, false),

    //Object operators
    $NEAR(false,true);

    boolean arrayOperator;
    boolean objectOperator;

    private QueryOperator(boolean arrayOperator, boolean objectOperator) {
        this.arrayOperator = arrayOperator;
        this.objectOperator = objectOperator;
    }

    public boolean isArrayOperator() {
        return arrayOperator;
    }
    public boolean isObjectOperator() {
        return objectOperator;
    }

}
