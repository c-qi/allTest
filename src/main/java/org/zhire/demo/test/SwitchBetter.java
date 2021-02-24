package org.zhire.demo.test;

/**
 * switch优化
 *
 * @Date 2021/2/24 15:59
 * @Author by chenqi
 */
public class SwitchBetter {

    public static void main(String[] args) {
        System.out.println(getResult(1, 99, "-"));
        System.out.println(getResult2(1, 1, AddOperate.class));
    }

    public static int getResult(int numberA, int numberB, String operate) {
        int result = 0;
        switch (operate) {
            case "+":
                result = new AddOperate().compute(numberA, numberB);
                break;
            case "-":
                result = new DelOperate().compute(numberA, numberB);
                break;
            default:
                System.out.println("error");
        }
        return result;
    }

    private static <T extends Operate> int getResult2(int numberA, int numberB, Class<T> clz) {
        int result = 0;
        try {
            return clz.newInstance().compute(numberA, numberB);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return result;
        }
    }

    static abstract class Operate {
        Operate() {
        }

        abstract int compute(int numberA, int numberB);
    }

    static class AddOperate extends Operate {
        AddOperate() {
        }

        @Override
        public int compute(int numberA, int numberB) {
            return numberA + numberB;
        }
    }

    static class DelOperate extends Operate {
        DelOperate() {
        }

        @Override
        public int compute(int numberA, int numberB) {
            return numberA - numberB;
        }
    }
}
