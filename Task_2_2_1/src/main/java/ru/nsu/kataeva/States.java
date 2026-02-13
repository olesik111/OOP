package ru.nsu.kataeva;

/**
 * State of an order.
 */
public enum States {
    CREATED,
    BAKING,
    READY,
    IN_WAREHOUSE,
    DELIVERING,
    DELIVERED
}