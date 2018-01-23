import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: yangxw
 * @date: Created in 2018-01-23 10:16
 * @description:
 **/
public class FileUtils {
    //获取文件夹下所有的文件
    public static List<File> getAllFiles(String dir){

        //创建一个集合存放遍历到的File
        List<File> files=new ArrayList<File>();

        File file=new File(dir);
        //文件夹必须存在                并且要是文件夹
        if (file.exists()&&file.isDirectory()) {
            //重点！    这里要本身一直遍历
            longErgodic(file,files);//把遍历得到的东西存放在files里面
        }
        return files;
    }

    //重点理解，这是一个递归方法，会不断来回调用本身，但是所有获得的数据都会存放在集合files里面
    private static void longErgodic(File file, List<File> files) {

        //.listFiles()方法的使用
        //把文件夹的所有文件（包括文件和文件名）都放在一个文件类的数组里面
        File[] fillArr=file.listFiles();

        //如果是一个空的文件夹
        if (fillArr==null) {
            //后面的不执行，直接返回
            return;
        }

        //如果文件夹有内容,遍历里面的所有文件（包括文件夹和文件），都添加到集合里面
        for (File file2 : fillArr) {

            //如果只是想要里面的文件或者文件夹或者某些固定格式的文件可以判断下再添加
            files.add(file2);

            //添加到集合后，在来判断是否是文件夹，再遍历里面的所有文件
            //方法的递归
            longErgodic(file2, files);
        }
    }
}
