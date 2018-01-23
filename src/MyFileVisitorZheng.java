import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: yangxw
 * @date: Created in 2018-01-23 11:07
 * @description:
 **/
public class MyFileVisitorZheng extends SimpleFileVisitor<Path> {
    private List<String> list_file =new ArrayList<String>();
    public static int count=0;

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
        if (attrs.isRegularFile()) {
            list_file.add(file.getFileName().toString());//插入一个List<String>有别的用。
            System.out.println(file.toAbsolutePath().toString());
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

    public List<String> getList() {
        return list_file;
    }

    public static void main(String[] args) throws IOException {
        Path fileDir = Paths.get("E:/java/img/speed");
        MyFileVisitorZheng visitor = new MyFileVisitorZheng();
        long a=System.currentTimeMillis();
        Files.walkFileTree(fileDir, visitor);
        //放到一个List<String>是有别的用处，跟测试无关
        List<String> list = visitor.getList();
//        for(String string:list){
//            System.out.println(string);
//        }
        System.out.println("执行耗时 : "+(System.currentTimeMillis()-a)/1000f+" 秒 ");

    }
}
