
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;

/**
 * 利用mp4转m3u8解决
 */
public class Mp4TransitionM3u8 {

    private final static String ffmpegExePath = "D:\\ffmpeg\\bin\\ffmpeg.exe";
    private final static String inputFilePath = "G:\\ffmpeg\\123456.mp4";
    private final static String path = "G:\\ffmpeg\\";


    @Value("${ffmpeg:ffmpeg}")
    private String ffmpeg;

    public static void main(String[] args) {
        test();
    }

    public static void test() {
        List<String> command = new ArrayList<String>();

        command.add(ffmpegExePath);
        command.add("-i");
        command.add(inputFilePath);
        command.add("-profile:v");
        command.add("baseline");
        command.add("-level");
        command.add("3.0");
        command.add("-s");
        command.add("640x360");
        command.add("-start_number");
        command.add("0");
        command.add("-hls_time");
        command.add("100");
        command.add("-hls_list_size");
        command.add("0");
        command.add("-f");
        command.add("hls");
        command.add("G:\\ffmpeg\\123456.m3u8");

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

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
