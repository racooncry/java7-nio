import com.aliyun.oss.OSSClient;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author: yangxw
 * @date: Created in 2018-01-23 10:25
 * @description:
 **/
public class MyFileVisitor extends SimpleFileVisitor<Path> {
    public  static int count=0;


    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exec)
            throws IOException {
        // 访问文件夹之前调用
        //System.out.println("Just visited " + dir);
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
            throws IOException {
        // 访问文件夹之后调用
        //System.out.println("About to visit " + dir);
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
            throws IOException {
        // 访问文件后调用
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long onLineTime=0;
        try {
            onLineTime =simpleDateFormat.parse("2018-01-23 15:26:00").getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long lastModifyTime= attrs.lastModifiedTime().toMillis();
        if (attrs.isRegularFile() && onLineTime>lastModifyTime) {
            count++;
            if(count>=50 && count%50==0){
                System.out.println("count:"+count);
                try {
                    System.out.println("sleeping 2 seconds .....");
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("正在上传"+file.toAbsolutePath().toString());
                String endpoint = "oss-cn-beijing.aliyuncs.com";
                String accessKeyId = "LTAIf91bRit47w1Q";
                String accessKeySecret = "NasoXjl5psg1ES3egg33fpAOd4rvd4";
                OSSClient ossClient = new OSSClient(endpoint, accessKeyId,
                        accessKeySecret);
               // ossClient.putObject("yunchengpaper", "lh/" + file.getFileName(), new FileInputStream(file.toAbsolutePath().toString()));
           // ossClient.putObject("yunchengpaper", "avatar/" + file.getFileName(), new FileInputStream(file.toAbsolutePath().toString()));
        ossClient.putObject("yunchengpaper", "baby_avatar/" + file.getFileName(), new FileInputStream(file.toAbsolutePath().toString()));
                ossClient.shutdown();
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc)
            throws IOException {
        // 文件不可访问时调用
        //System.out.println(exc.getMessage());
        return FileVisitResult.CONTINUE;
    }


    public static void main(String[] args) throws IOException {
        String winPath="E:/java/img/speed";
        String testLinuxPath="//root//shenfeng";
        String  lhPath="//mnt//httpTrans//apache-tomcat-7.0.82//webapps//shecareUser//gran//lhpaper";
        String headPath="//mnt//httpTrans//apache-tomcat-7.0.82//webapps/shecareUser//user//head";
        String babyHeadPath="//mnt//httpTrans//apache-tomcat-7.0.82//webapps/shecareUser//user//babyhead";
        Path fileDir = Paths.get(babyHeadPath);
        MyFileVisitor visitor = new MyFileVisitor();
        long a=System.currentTimeMillis();
        Files.walkFileTree(fileDir, visitor);
        System.out.println("执行耗时 : "+(System.currentTimeMillis()-a)/1000f+" 秒 ");

    }
}
