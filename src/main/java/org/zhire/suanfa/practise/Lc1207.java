package org.zhire.suanfa.practise;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 独一无二的出现次数
 *
 * 给你一个整数数组 arr，请你帮忙统计数组中每个数的出现次数。
 * 如果每个数的出现次数都是独一无二的，就返回 true；否则返回 false。
 * 示例 1：
 * 输入：arr = [1,2,2,1,1,3]
 * 输出：true
 * 解释：在该数组中，1 出现了 3 次，2 出现了 2 次，3 只出现了 1 次。没有两个数的出现次数相同。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-number-of-occurrences
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Date 2020/10/28 14:14
 * @Author by chenqi
 */
public class Lc1207 {

    @Test
    public void t() {
        int[] arr = {1, 2, 2, 1, 1, 3, 3};
        System.out.println(uniqueOccurrences(arr));
    }

    public boolean uniqueOccurrences(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            int flag = 1;
            if (map.containsKey(arr[i])) {
                flag += map.get(arr[i]);
            }
            map.put(arr[i], flag);
        }
        map.forEach((k, v) -> set.add(v));
        return set.size() == map.size();
    }

}
