import lombok.extern.slf4j.Slf4j;

/**
 * @author tanghaiyang on 2019/4/24.
 */
@Slf4j
public class Floyd {
    private int[][] length = null;  // 任意两点之间路径长度
    private int[][][] path = null;  // 任意两点之间的路径

    public Floyd(int[][] G) {
        int MAX = 100;
        int row = G.length; // 图G的行数
        int[][] spot = new int[row][row];   // 定义任意两点之间经过的点
        int[] onePath = new int[row];   // 记录一条路径
        length = new int[row][row];
        path = new int[row][row][];
        for (int i = 0; i < row; i++)   // 处理图两点之间的路径
            for (int j = 0; j < row; j++) {
                if (G[i][j] == 0) G[i][j] = MAX;    // 没有路径的两个点之间的路径为默认最大
                if (i == j) G[i][j] = 0;    // 本身的路径长度为0
            }
        for (int i = 0; i < row; i++)   // 初始化为任意两点之间没有路径
            for (int j = 0; j < row; j++)
                spot[i][j] = -1;
        for (int i = 0; i < row; i++)   // 假设任意两点之间的没有路径
            onePath[i] = -1;
        for (int v = 0; v < row; ++v)
            for (int w = 0; w < row; ++w)
                length[v][w] = G[v][w];

        for (int u = 0; u < row; ++u)
            for (int v = 0; v < row; ++v)
                for (int w = 0; w < row; ++w)
                    if (length[v][w] > length[v][u] + length[u][w]) {
                        length[v][w] = length[v][u] + length[u][w]; // 如果存在更短路径则取更短路径
                        spot[v][w] = u; // 把经过的点加入
                    }
        for (int i = 0; i < row; i++) { // 求出所有的路径
            int[] point = new int[1];
            for (int j = 0; j < row; j++) {
                point[0] = 0;
                onePath[point[0]++] = i;
                outputPath(spot, i, j, onePath, point);
                path[i][j] = new int[point[0]];
                for (int s = 0; s < point[0]; s++)
                    path[i][j][s] = onePath[s];
            }
        }
    }

    private void outputPath(int[][] spot, int i, int j, int[] onePath, int[] point) {// 输出i// 到j// 的路径的实际代码，point[]记录一条路径的长度
        if (i == j) return;
        if (spot[i][j] == -1)
            onePath[point[0]++] = j;
            // System.out.print(" "+j+" ");
        else {
            outputPath(spot, i, spot[i][j], onePath, point);
            outputPath(spot, spot[i][j], j, onePath, point);
        }
    }

    public static void main(String[] args) {
        int data[][] = {
                {10, 27, 44, 17, 11, 27, 42, 10, 10, 10, 20, 25, 21, 21, 18, 27, 10},// x1
                {27, 10, 31, 27, 49, 10, 10, 10, 10, 10, 10, 10, 52, 21, 41, 10, 10},// 1
                {44, 31, 10, 19, 10, 27, 32, 10, 10, 10, 47, 10, 10, 10, 32, 10, 10},// 2
                {17, 27, 19, 10, 14, 10, 10, 10, 10, 10, 30, 10, 10, 10, 31, 10, 10},// 3
                {11, 49, 10, 14, 10, 13, 20, 10, 10, 28, 15, 10, 10, 10, 15, 25, 30},// 4
                {27, 10, 27, 10, 13, 10, 19, 21, 10, 26, 26, 10, 10, 10, 28, 29, 10},// 5
                {42, 10, 32, 10, 20, 19, 10, 13, 10, 32, 10, 10, 10, 10, 10, 33, 10},// 6
                {10, 10, 10, 10, 10, 21, 13, 10, 19, 10, 10, 10, 10, 10, 10, 10, 10},// 7
                {10, 10, 10, 10, 10, 10, 10, 19, 10, 11, 20, 10, 10, 10, 10, 33, 21},// 8
                {10, 10, 10, 10, 28, 26, 32, 10, 11, 10, 10, 20, 10, 10, 29, 14, 13},// 9
                {20, 10, 47, 30, 15, 26, 10, 10, 20, 10, 10, 18, 10, 10, 14, 19, 20},// 10
                {25, 10, 10, 10, 10, 10, 10, 10, 10, 20, 18, 10, 23, 10, 10, 14, 10},// 11
                {21, 52, 10, 10, 10, 10, 10, 10, 10, 10, 10, 23, 10, 27, 22, 10, 10},// 12
                {21, 21, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 27, 10, 10, 10, 10},// 13
                {18, 41, 32, 31, 15, 28, 10, 10, 10, 29, 14, 10, 22, 10, 10, 11, 10},// 14
                {27, 10, 10, 10, 25, 29, 33, 10, 33, 14, 19, 14, 10, 10, 11, 10, 19},// 15
                {10, 10, 10, 10, 30, 10, 10, 10, 21, 13, 20, 10, 10, 10, 10, 19, 10} // 16
        };
        for (int i = 0; i < data.length; i++)
            for (int j = i; j < data.length; j++)
                if (data[i][j] != data[j][i]) return;

        Floyd test = new Floyd(data);
        for (int i = 0; i < data.length; i++)
            for (int j = i; j < data[i].length; j++) {
                log.info("||||||||||");
                log.info("From " + i + " to " + j + " path is: ");
                for (int k = 0; k < test.path[i][j].length; k++)
                    log.info(test.path[i][j][k] + " ");
                log.info("path:{}",test.path);
                log.info("==============");
                log.info("From " + i + " to " + j + " length :" + test.length[i][j]);
            }
    }
}