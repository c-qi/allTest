package org.zhire.suanfa.practise;

import org.junit.Test;

/**
 * 输入:
 * [[0,1,0,0],
 * [1,1,1,0],
 * [0,1,0,0],
 * [1,1,0,0]]
 * <p>
 * 输出: 16
 *
 * @Date 2020/10/30 10:57
 * @Author by chenqi
 */
public class Jianzhi463 {
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public int islandPerimeter(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (grid[i][j] == 1) {
                    int cnt = 0;
                    for (int k = 0; k < 4; ++k) {
                        int tx = i + dx[k];
                        int ty = j + dy[k];
                        if (tx < 0 || tx >= n || ty < 0 || ty >= m || grid[tx][ty] == 0) {
                            cnt += 1;
                        }
                    }
                    ans += cnt;
                }
            }
        }
        return ans;

    }

    /**
     * 每次检索到陆地，判断一下 上下左右 有没有边，求和。
     *
     * @param grid
     * @return
     */
    public int islandPerimeter2(int[][] grid) {
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    // up
                    if (i == 0 || grid[i - 1][j] == 0) {
                        res++;
                    }
                    // down 注意length和右边的length取值不一样
                    if (i == grid.length - 1 || grid[i + 1][j] == 0) {
                        res++;
                    }
                    // left
                    if (j == 0 || grid[i][j - 1] == 0) {
                        res++;
                    }
                    // right
                    if (j == grid[0].length - 1 || grid[i][j + 1] == 0) {
                        res++;
                    }
                }
            }
        }
        return res;
    }

    @Test
    public void t() {
        int[][] ints = {
                {0, 1},

        };
        System.out.println(islandPerimeter(ints));
        System.out.println(islandPerimeter2(ints));
    }
}
