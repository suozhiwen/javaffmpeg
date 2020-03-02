import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class io {

    public static void main(String[] args) throws IOException {
        String path = "D:\\teachingPlatform\\teachingMaterialResources\\video\\123456\\12.m3u8";
        //readFileByByte(path);

//        String Str = new String("Welcome to Yiibai.com");
//
//        System.out.print("Return Value :");
//        System.out.println(Str.startsWith("Welcome"));
//
//        System.out.print("Return Value :");
//        System.out.println(Str.startsWith("Tutorials"));
//
//        System.out.print("Return Value :");
//        System.out.println(Str.startsWith("Yiibai", 11));
//
//        String str1 = "abcdefg";
//        int result1 = str1.indexOf("ab");

        List<String> info = readFileByBuffer(path);
        //readFileByByte(path);
        //clearInfoForFile(path);
        fileWriter(path, info);
        //fileRead(path);

    }

    public static List<String> readFileByBuffer(String filename) {
        BufferedReader br = null;
        try {
            List<String> infoList = new ArrayList<>();
            br = new BufferedReader(new FileReader(filename));
            String rline = null;
            while ((rline = br.readLine()) != null) {
                //读取的文件包含 “.ts” 字符 则修改信息
                if (rline.contains(".ts")) {
                    rline = "teachingMaterialResources\\video\\123456\\" + rline;
                }
                infoList.add(rline);
                System.out.println(rline);
            }
            br.close();
            return infoList;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    //清空文件内容
    public static void clearInfoForFile(String fileName) {
        File file = new File(fileName);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write("");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //  字节流读取一个文件的内容
    public void inputStream() throws IOException {
        // 定义一个字节数组存内容
        byte[] c = new byte[500];
        // 获取文件路径
        File file = new File("E://aaa.txt");
        // 创建一个字节流
        FileInputStream fileInputStream = new FileInputStream(file);
        // 读取文件内容，并把读取到内容存到数组里
        int num = fileInputStream.read(c);
        // 把数组转为字符串
        String str = new String(c);
        System.out.println("读取到的内容长度为：" + num);
        System.out.println("读取到的内容：" + str);
        // 关闭流
        fileInputStream.close();
    }

    //   字符流把内容写入文件里面
    public static void fileWriter(String path, List<String> info) throws IOException {
        File file = new File(path);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write("");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //  如果不想覆盖掉原来文件的内容，则在路径后加一个参数，
        //  该参数是否追加在原内容后面，默认为 false
        FileWriter fileWriter = new FileWriter(path, true);
        for (String s : info) {
            fileWriter.write(s);
            fileWriter.write("\r\n");
        }
        fileWriter.close();
    }


    //  字节流把内容写入文件里面
    public void fileOutputStream() throws IOException {
        // 创建一个字节数组
        byte[] a = {'a', 's', 'd', 'f', 'g'};
        // 创建一个字节输入流
        FileOutputStream outputStream = new FileOutputStream("E://aaa.txt");
        // 把内容写到文件里
        outputStream.write(a);
        // 关闭流
        outputStream.close();
        // 调用字节输出流查看内容
        inputStream();
    }

}
