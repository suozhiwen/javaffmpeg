import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FfmpegUtil {

    /**
     * ffmpeg 启动路径
     */
    private final static String ffmpeg = "D:\\ffmpeg\\bin\\ffmpeg.exe";

    /**
     * 解析后文件存放路径
     */
//    @Value("${memoryAddress:memoryAddress}")
    private static String memoryAddress = "G:\\ffmpeg";


    public static void main(String[] args) {
        //shear();
        String inputFilePath = "G:\\ffmpeg\\123456.mp4";
        mp4TransitionM3u8(inputFilePath, "100");
    }

    /**
     * 新建文件夹
     *
     * @param path
     * @return
     */
    public static void addfolder(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    public static void mp4TransitionM3u8(String inputFilePath, String durationTime) {
        List<String> command = new ArrayList<String>();
        String[] path = inputFilePath.split("\\\\");
        String pathFileName = path[path.length - 1];
        //截取文件名称
        String fileName = pathFileName.substring(0, pathFileName.indexOf("."));
        addfolder(memoryAddress + "\\" + fileName);
        command.add(ffmpeg);
        command.add("-i");
        command.add(inputFilePath);
        command.add("-profile:v");
        command.add("baseline");
        command.add("-level");
        command.add("3.0");
        command.add("-s");
        command.add("1280*720");
        command.add("-start_number");
        command.add("0");
        command.add("-hls_time");
        command.add(durationTime);
        command.add("-hls_list_size");
        command.add("0");
        command.add("-f");
        command.add("hls");
        command.add(memoryAddress + "\\" + fileName + "\\" + fileName + ".m3u8");
        System.out.println(command);
        ProcessBuilder builder = new ProcessBuilder();
        builder.command(command);
        //正常信息和错误信息合并输出
        builder.redirectErrorStream(true);
        process(builder);
    }

    private static void process(ProcessBuilder builder) {
        try {
            //开始执行命令
            Process process = builder.start();
            //如果你想获取到执行完后的信息，那么下面的代码也是需要的
            StringBuffer sbf = new StringBuffer();
            String line = null;
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while ((line = br.readLine()) != null) {
                sbf.append(line);
                sbf.append(" ");
            }
            String resultInfo = sbf.toString();
            System.out.println(resultInfo);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}