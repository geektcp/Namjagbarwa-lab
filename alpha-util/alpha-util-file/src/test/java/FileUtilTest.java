

import alpha.common.base.util.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author tanghaiyang on 2019/9/3.
 */
public class FileUtilTest {

    @Test
    public void listAllFilesTest() {
        File file = new File("F:\\tmp");
        FileUtils.listAllFiles(file);
        Assert.assertTrue(true);
    }

    @Test
    public void readFile() throws Exception {
        String resourceDir = "data/edge/part-000";
        String path = this.getClass().getResource("/" + resourceDir).getPath();
        BufferedReader br = new BufferedReader(new FileReader(new File(path)));
        String line = "";
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        br.close();
        Assert.assertTrue(true);
    }

    @Test
    public void readFileCharset() throws Exception {
        String resourceDir = "data/edge/part-000";
        String charset = "GBK";
        String path = this.getClass().getResource("/" + resourceDir).getPath();
        File file = new File(path);
        FileInputStream fis = new FileInputStream(file);
        BufferedReader br = new BufferedReader(new InputStreamReader(fis, charset));

        String line = "";
        while ((line = br.readLine()) != null) {
            String[] arr = line.split("\\|@\\|");
//            log.info("arr[4]: " + arr[4]);
            break;
        }
        Assert.assertTrue(true);
    }

    @Test
    public void writeFile() throws Exception {
        String resourceDir = "data/edge/part-000";
        String path = this.getClass().getResource("/" + resourceDir).getPath();
        BufferedWriter bw = new BufferedWriter(new FileWriter(new File(path), true));
        String line = "this is the content!";
        bw.write(line);
        bw.flush();
        bw.close();
        Assert.assertTrue(true);
    }

    @Test
    public void readFileByte() throws Exception {
        String srcFile = "/share/down/apache-jmeter-5.2.1.zip";
        File file = new File(srcFile);
        Long totalSpace = file.length() / 1024 / 1024;
        FileInputStream fis = new FileInputStream(file);
        byte[] bytes = new byte[10000];
        int size = fis.read(bytes, 0, 2000);
        Assert.assertTrue(true);
    }

    /**
     * 分批循环读取文件到byte[]使用了缓存
     * public abstract int read(ByteBuffer dst) throws IOException;
     */
    @Test
    public void writeFileByteByChannel() {
        try {
            String srcFilePath = "/share/down/jdk-9+181_linux-x64_ri.zip";
            String dstFilePath = "/share/down/file/jdk-9+181_linux-x64_ri.zip";
            File srcFile = new File(srcFilePath);
            FileInputStream srcFis = new FileInputStream(srcFile);
            FileChannel srcFileChannel = srcFis.getChannel();
            File dstFile = new File(dstFilePath);
            FileOutputStream dstFos = new FileOutputStream(dstFile);
            FileChannel dstFileChannel = dstFos.getChannel();
            int len = 2000;
            int size = 0;
            ByteBuffer buffer = ByteBuffer.allocateDirect(len);
            while (true) {
                size = srcFileChannel.read(buffer);
                if (size == -1) {
                    break;
                }
                buffer.flip();
                dstFileChannel.write(buffer);
                buffer.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertTrue(true);
    }

    /**
     * 只能一步到位读取文件
     * public int read(byte b[], int off, int len)
     * 不能进行循环读取，没有用到缓存
     */
    @Test
    public void writeFileByte() {
        try {
            String srcFilePath = "/share/down/jdk-9+181_linux-x64_ri.zip";
            String dstFilePath = "/share/down/file/jdk-9+181_linux-x64_ri-2.zip";
            File srcFile = new File(srcFilePath);
            FileInputStream srcFis = new FileInputStream(srcFile);
            File dstFile = new File(dstFilePath);
            FileOutputStream dstFos = new FileOutputStream(dstFile);
            int len = 4000;
            int fileSize = (int)srcFile.length();
            byte[] bytes = new byte[fileSize];
            int size = srcFis.read(bytes, 0, fileSize);
            dstFos.write(bytes,0, fileSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertTrue(true);
    }
}
