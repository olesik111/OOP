package ru.nsu.kataeva;

/**
 * State of an order.
 */
public enum OrderState {
    CREATED {
        @Override
        public String toString() {
            return "Order Created";
        }
    },
    BAKING {
        @Override
        public String toString() {
            return "Baking in progress";
        }
    },
    READY {
        @Override
        public String toString() {
            return "Pizza is Ready";
        }
    },
    IN_WAREHOUSE {
        @Override
        public String toString() {
            return "Waiting in warehouse";
        }
    },
    DELIVERING {
        @Override
        public String toString() {
            return "Courier is on the way";
        }
    },
    DELIVERED {
        @Override
        public String toString() {
            return "Successfully Delivered";
        }
    }
}