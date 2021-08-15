# 模块设计

# 思路


# 用法


# 依赖
     <dependency>
            <groupId>org.antlr</groupId>
            <artifactId>antlr4-runtime</artifactId>
            <version>4.5</version>
        </dependency>


# 插件
ANTLR v4 grammars plugins


Demo.g4内容：
```
grammar Demo;

//parser
prog:stat
;
stat:expr|NEWLINE
;

expr:multExpr(('+'|'-')multExpr)*
;
multExpr:atom(('*'|'/')atom)*
;
atom:'('expr')'
    |INT
    |ID
;

//lexer
ID:('a'..'z'|'A'..'Z')+;
INT:'0'..'9'+;
NEWLINE:'\r'?'\n';
WS:(' '|'\t'|'\n'|'\r')+{skip();};
```

安装完插件，配置好依赖后，新建Demo.g4文件后，右键点击该文件，选择 Generate ANTLR Recognizer
默认会在当前模块的根目录下gen文件夹下生成几个java文件：
Demo.tokens
DemoBaseListener.java
DemoLexer.java
DemoLexer.tokens
DemoListener.java
DemoParser.java


