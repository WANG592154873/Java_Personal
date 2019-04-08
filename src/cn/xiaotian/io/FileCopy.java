package cn.xiaotian.io;

import java.io.*;

/***
 *
 *一个文件复制工具，网络上下载的FC格式的rom都在文件里面，统一复制出来手动太麻烦，不完善，但是基本功能可以用
 */
public class FileCopy {
    static String destPath = "E:/roms";

    public static void main(String[] args) {
        File src = new File("E:/roms");

        listDir(src);
    }
    public static void listDir(File src){
        for(File sub:src.listFiles()){
            if(sub.isDirectory()){
                listDir(new File(src,sub.getName()));
            }
            if(sub.isFile()){
                try {
                    fileCopy(sub,destPath,sub.getName());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void fileCopy(File src,String destPath,String filename) throws IOException {
        File dest = new File(destPath);
            InputStream is = new FileInputStream(src);
            OutputStream os = new FileOutputStream(new File(dest,filename));

            byte[] flush = new byte[1024];
            int len = 0;
            while(-1!=(len=is.read(flush))){
                os.write(flush,0,len);
            }
            os.flush();
            os.close();
            is.close();
    }
}
