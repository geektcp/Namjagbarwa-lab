package com.geektcp.alpha.design.pattern.singleton;

/**
 * @author tanghaiyang on 2019/9/21.
 */
public enum  EnumSingleton {
    INSTANCE;

    private String objName;


    public String getObjName() {
        return objName;
    }


    public void setObjName(String objName) {
        this.objName = objName;
    }


    public static void main(String[] args) {
        // 单例测试
        EnumSingleton firstSingleton = EnumSingleton.INSTANCE;
        firstSingleton.setObjName("firstName");
        System.out.println(firstSingleton.getObjName());
        EnumSingleton secondSingleton = EnumSingleton.INSTANCE;
        secondSingleton.setObjName("secondName");
        System.out.println(firstSingleton.getObjName());
        System.out.println(secondSingleton.getObjName());

        // 反射获取实例测试
        try {
            EnumSingleton[] enumConstants = EnumSingleton.class.getEnumConstants();
            for (EnumSingleton enumConstant : enumConstants) {
                System.out.println(enumConstant.getObjName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
