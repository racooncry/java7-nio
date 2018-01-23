import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    /**
     * 对文件/文件夹的基本操作
     *
     * 有一点值得要注意的是getName和getPath、getAbsolutePath的区别
     * getName输出的只是文件名，不包括文件路径
     * （java里面）getPath、getAbsolutePath输出的是文件路径加文件名
     * */
    public static void main(String[] args) throws IOException {

        String string="/abc/info/";
        if(string.startsWith("/abc/user/")){
            System.out.println("123");
        }else{
            System.out.println("456");
        }



        //创建一个集合来接收获得的文件
        List<File> list = new ArrayList<File>();

        //定义一个路径，这里是拿D盘做例子
        String path="E:/java/img";

        //调用工具类的静态方法，返回的是一个集合对象
        list = FileUtils.getAllFiles(path);

        // 获取D盘下的所有的文件（包括文件和文件夹）
        for (File file : list) {
            System.out.println(file.getPath());
        }

        // 获取D盘下的所有图片呢》？图片格式：jpg、png、jif，假设找这三种格式的
//        for (File file3 : list) {
//            String picturePath = file3.getPath();
//            if (picturePath.endsWith(".jpg") || picturePath.endsWith(".png")
//                    || picturePath.endsWith(".jif")) {
//                System.out.println(picturePath);
//            }
//        }
    }
}
