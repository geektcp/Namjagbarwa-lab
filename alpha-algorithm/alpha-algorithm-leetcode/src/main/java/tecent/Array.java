package tecent;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

/**
 * @author tanghaiyang on 2019/2/18.
 */
@Slf4j
public class Array {
    public static void main(String[] args) {
        int[] arr = {1,2,23,19,39,11,8};

        int[] ret = twoSum(arr,10);


    }


    /*
    * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
      你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
    * */
    private static int[] twoSum(int[] nums, int target) {
        int[] result = null;
        ArrayList<Integer> mid = new ArrayList<>();
        for(int num: nums){
//            log.info("num: {}", num);
            if(num<=target) {
                mid.add(num);
            }
        }

        for(Integer num: mid){
            log.info("num: {}", num);
        }

        return null;
    }
}
