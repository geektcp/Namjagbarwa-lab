package com.geektcp.alpha.design.pattern.iterator;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author tanghaiyang on 2019/9/21.
 */
public class IteratorTest {

    @Test
    public void main() {
        Aggregate aggregate = new ConcreteAggregate();
        Iterator<Integer> iterator = aggregate.createIterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        Assert.assertTrue(true);
    }
}
