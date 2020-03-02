import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FFmpegTest {
    private final static String ffmpegExePath = "D:\\ffmpeg\\bin\\ffmpeg.exe";
    private final static String inputFilePath = "G:\\ffmpeg\\Java异常体系.mp4";
    private final static String outputFilePath = "G:\\ffmpeg\\littleStarJavaMp4.wmv";
    private final static String logPng = "G:\\ffmpeg\\2354823-c6039673e01df723.webp";

    private final static String path = "G:\\ffmpeg\\";
    private final static String format = ".mp4";

    public static void main(String[] args) {
        //shear();
        aa();
    }


    /**
     * 视频剪切
     */
    public static void shear(){
        int time = 651;

        int subsection = (int) Math.ceil(651 / 100);

        int aaa = time/100 == 0 ? subsection : subsection+1 ;
        System.out.println(subsection);
        System.out.println(aaa);
        for (int i = 0; i < aaa; i++) {
            System.out.println(i);
            if (i == 0) {
                b("0", "100", path + i + format);
            }
            if (i > 0) {
                System.out.println(i * 100);
                System.out.println((i + 1) * 100);
                b(i * 100 + "",  "100", path + i + format);
            }
        }
    }

    /**
     * @param begin     开始时间
     * @param end       结束时间
     * @param videoName 新视频名称
     */
    public static void b(String begin, String end, String videoName) {
        List<String> command = new ArrayList<String>();
        //截取视频
        command.add(ffmpegExePath);
        command.add("-i");
        command.add(inputFilePath);
        command.add("-ss");
        command.add(begin);
        command.add("-c");
        command.add("copy");
        command.add("-t");
        command.add(end);
        command.add(videoName);

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
            System.out.print(resultInfo);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void aa() {
        List<String> command = new ArrayList<String>();
        //添加水印
//        command.add(ffmpegExePath);
//        command.add("-i");
//        command.add(inputFilePath);
//        command.add("-i");
//        command.add(logPng);
//        command.add("-filter_complex");
//        command.add("overlay");
//        command.add("G:\\ffmpeg\\login.mp4");


        //获取视频信息   Duration时长
//        command.add(ffmpegExePath);
//        command.add("-i");
//        command.add(inputFilePath);

        //截取视频
//        command.add(ffmpegExePath);
//        command.add("-i");
//        command.add(inputFilePath);
//        command.add("-ss");
//        command.add("30");
//        command.add("-c");
//        command.add("copy");
//        command.add("-t");
//        command.add("500");
//        command.add("G:\\ffmpeg\\jieqump11114.mp4");

//        System.out.println(command);

//        command.add("-i");
//        command.add(inputFilePath);
//        command.add("-vcodec");
//        command.add("wmv1");
//        command.add(outputFilePath);


        command.add(ffmpegExePath);
        command.add("-i");
        command.add(inputFilePath);
        command.add("-profile:v baseline -level 3.0 -s 640x360 -start_number ");
        command.add("0 -hls_time 10 -hls_list_size 0 -f");
        command.add("hls");
        command.add("G:\\ffmpeg\\11111.m3u8");



        ProcessBuilder builder = new ProcessBuilder();
        builder.command(command);
        //正常信息和错误信息合并输出
        builder.redirectErrorStream(true);
        process(builder);
    }
}
