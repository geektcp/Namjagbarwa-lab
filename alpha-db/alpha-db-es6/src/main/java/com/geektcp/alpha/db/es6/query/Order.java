package com.geektcp.alpha.db.es6.query;

/**
 * Created by nagle on 2018/1/4.
 */
public enum Order {
    /**
     * Ascending order.
     */
    ASC {
        @Override
        public String toString() {
            return "asc";
        }
    },
    /**
     * Descending order.
     */
    DESC {
        @Override
        public String toString() {
            return "desc";
        }
    }
}
