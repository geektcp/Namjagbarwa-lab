package com.geektcp.alpha.design.pattern.observer;

import com.geektcp.alpha.design.pattern.observer.example.Event;
import com.geektcp.alpha.design.pattern.observer.example.Person;
import com.geektcp.alpha.design.pattern.observer.example.PersonListener;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author tanghaiyang on 2020/5/25 13:41.
 */
public class PersonTest {

    @Test
    public void listenerTest() {
        Person p = new Person();
        p.registerListener(new PersonListener() {

            public void doEat(Event e) {
                Person p = e.getSource();
                System.out.println(p + "吃个死");
            }

            public void doRun(Event e) {
                // TODO Auto-generated method stub
            }
        });
        p.eat();
        Assert.assertTrue(true);
    }

}
