package com.geektcp.alpha.design.pattern.state;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author tanghaiyang on 2019/9/21.
 * 当使用设计模式时，不用考虑太多设计模式的理论，只需要考虑当前的业务情况和怎么使用最简单
 * 代码的调用方式确定之后，再想办法用设计写法满足这个写法的规则需求，同时依据设计模式考虑代码的重用性
 */
public class GumballMachineTest {

    @Test
    public void main() {
        GumballMachine gumballMachine = new GumballMachine(4);

        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();

        gumballMachine.insertQuarter();
        gumballMachine.ejectQuarter();
        gumballMachine.turnCrank();

        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();
        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();
        gumballMachine.ejectQuarter();

        gumballMachine.insertQuarter();
        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();
        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();
        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();
        Assert.assertTrue(true);
    }
}
