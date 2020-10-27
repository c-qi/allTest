package org.zhire.suanfa.practise;

import org.junit.Test;

/**
 * 矩阵中的路径
 * 输入：board =
 * [["A","B","C","E"],
 * ["S","F","C","S"],
 * ["A","D","E","E"]],
 * word = "ABCCED"
 * 输出：true
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ju-zhen-zhong-de-lu-jing-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Date 2020/10/27 14:30
 * @Author by chenqi
 */
public class Jianzhi12 {
    @Test
    public void test() {
        char[][] chars = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        // System.out.println(Arrays.deepToString(chars));
        String word = "ABCCEDASFC";
        //exist(chars, word);
        System.out.println(exist2(chars, word));

    }

    public boolean exist(char[][] board, String word) {
        char[] array = word.toCharArray();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                //System.out.println(board[i][j]);
                if (board[i][j] == array[0]) {
                    System.out.println(i + " " + j);
                }
            }
        }
        return false;
    }

    public boolean exist2(char[][] board, String word) {
        char[] words = word.toCharArray();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(board, words, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 深度优先搜索（DFS）
     *
     * @param board
     * @param word
     * @param i
     * @param j
     * @param k
     * @return
     */
    boolean dfs(char[][] board, char[] word, int i, int j, int k) {
        if (i >= board.length || i < 0 || j >= board[0].length || j < 0 || board[i][j] != word[k]) {
            return false;
        }
        if (k == word.length - 1) {
            return true;
        }
        char tmp = board[i][j];
        board[i][j] = '/';
        boolean res = dfs(board, word, i + 1, j, k + 1) || dfs(board, word, i - 1, j, k + 1) ||
                dfs(board, word, i, j + 1, k + 1) || dfs(board, word, i, j - 1, k + 1);
        board[i][j] = tmp;
        return res;
    }

}
