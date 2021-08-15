package com.geektcp.alpha;

import com.alibaba.fastjson.JSON;
import com.geektcp.alpha.antlr.MathLexer;
import com.geektcp.alpha.antlr.MathParser;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

/**
 * @author tanghaiyang on 2019/6/1.
 */
@Slf4j
public class Main {

    @Test
    public void test(){
        CharStream input = CharStreams.fromString("12*2+12\r\n");
        MathLexer lexer=new MathLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        MathParser parser = new MathParser(tokens);
        ParseTree tree = parser.prog(); // parse

        log.info("tree:\n{}", JSON.toJSONString(tree,true));
    }
}
