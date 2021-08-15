package com.geektcp.alpha.antlr.rule;

import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;
import org.junit.Test;

/**
 * Created by HaiyangHome on 2019/3/24.
 *
 * 规则引擎
 */
public class RuleUtils {

    @Test
    public void RuleTest() throws Exception{
        ExpressRunner runner = new ExpressRunner();
        DefaultContext<String, Object> context = new DefaultContext<String, Object>();
        context.put("a",1);
        context.put("b",2);
        context.put("c",3);
        String express = "a+b*c";
        Object r = runner.execute(express, context, null, true, true);
        System.out.println(r);
    }


    /**
     * 对于计算表示表达，阿里的这个规则引擎，实际上确实是把它转换成一棵二叉树，计算结果其实就是遍历这棵二叉树
     * @throws Exception
     */
    @Test
    public void arithmeticTest()throws Exception{
        String express = "10 * 10 +  3 * (1 + 2) + 5 * 2";
        ExpressRunner runner = new ExpressRunner(false,true);
        Object r = runner.execute(express,null, null, true,true);
        System.out.println("表达式计算：" + express + " = " + r);
    }
}
