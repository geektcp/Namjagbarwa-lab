package com.geektcp.alpha.util;

/* Created by Haiyang on 2017/5/2. */

import org.apache.commons.cli.*;

public class ParseOptions {
    public static void main(String[] args) throws Exception {

        // 自定义系统变量添加方法：
        // 在idea的Run/Debug Configuration界面的VM options栏添加(不是Program arguments界面)：
        // -Dlog4j.configuration.file=./log4j.properties
        String logConfFile = System.getProperty("log4j.configuration.file");
        System.out.println("logConfFile: " + logConfFile);

        CommandLine cmd = parseOpt(args);

        if(cmd.hasOption("zookeeper")) {
            String path = cmd.getOptionValue("zookeeper");
//            getZookeeperInfo(path);
        }

        if(cmd.hasOption("offset")) {
            String partition = cmd.getOptionValue("offset");
//            getOffset(partition, false);
        }

        if(cmd.hasOption("logsize")) {
            String partition = cmd.getOptionValue("logsize");
//            getOffset(partition, true);
        }

        if(cmd.hasOption("deltaoffset")) {
            String partition = cmd.getOptionValue("deltaoffset");
//            init(partition, false);
//            getOffsetDelta(partition, false);
        }

        if(cmd.hasOption("deltalogsize")) {
            String partition = cmd.getOptionValue("deltalogsize");
//            init(partition, true);
//            getOffsetDelta(partition, true);
        }
    }


    public static CommandLine parseOpt(String[] args){
        Options options = new Options();

        Option optOffset = new Option("o", "offset", true, "get kafka offset");
        optOffset.setRequired(false);
        options.addOption(optOffset);

        Option optLogSize = new Option("l", "logsize", true, "get kafka logsize");
        optLogSize.setRequired(false);
        options.addOption(optLogSize);

        Option optDeltaOffset = new Option("d", "deltaoffset", true, "get kafka delta offset");
        optDeltaOffset.setRequired(false);
        options.addOption(optDeltaOffset);

        Option optDeltaLogsize = new Option("s", "deltalogsize", true, "get kafka delta offset");
        optDeltaLogsize.setRequired(false);
        options.addOption(optDeltaLogsize);

        Option optZookeeper = new Option("z", "zookeeper", true, "get kafka offset delta");
        optZookeeper.setRequired(false);
        options.addOption(optZookeeper);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd = null;

        if (args.length == 0) {
            String msg = "\n获取kafka偏离值，缓存大小，kafka在zk的注册信息\n" + "java -jar kafkaTools.jar --offset 0";
            formatter.printHelp(msg, options);
            System.exit(2);
        } else {
            try {
                cmd = parser.parse(options, args);
            } catch (ParseException e) {
                System.out.println(e.getMessage());
                formatter.printHelp("java -jar kafkaTools.jar --offset 0", options);

                System.exit(1);
            }
        }

        return cmd;
    }
}
