import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Web extends SearchDao {
    public static void main(String[] args) throws IOException, SQLException {
        int choose;
        String input = null;
        Handler t = null;

        ArrayList<EarthQuake> eql = null;
        while (eql == null) {
            System.out.println
                    (
                            "输入查询方式：\n" +
                                    "1.地区查询\n" +
                                    "2.国家查询\n" +
                                    "3.日期查询\n" +
                                    "4.经纬度查询"
                    );
            choose = Integer.parseInt(reader.readLine());
            switch (choose) {
                case 1: {
                    System.out.println("输入地区：");
                    input = reader.readLine();
                    eql = byRegion(input);
                    break;
                }
                case 2: {
                    System.out.println("输入国家：");
                    input = reader.readLine();
                    eql = byCountry(input);
                    break;
                }
                case 3: {
                    System.out.println("输入时间(使用\'-\'用于间隔)：");
                    input = reader.readLine();
                    var numString = input.split("-");
                    eql = byDate(Integer.parseInt(numString[0]), Integer.parseInt(numString[1]), Integer.parseInt(numString[2]));
                    break;
                }
                case 4: {
                    System.out.println("输入经纬度(使用逗号用于间隔)：");
                    input = reader.readLine();
                    var numString = input.split(",");
                    eql = byCoordinate(Double.parseDouble(numString[0]), Double.parseDouble(numString[1]));
                    break;
                }
                default: {
                    System.out.println("请输入合适的数字!");
                    break;
                }
            }
        }
        System.out.println("结果请在8080端口查看。");
        ServerSocket ss = new ServerSocket(8080);
        System.out.println("server is running...");
        for (; ; )
        {
            Socket sock = ss.accept();
            System.out.println("connected from " + sock.getRemoteSocketAddress());
            t = new Handler(sock);
            t.setEql(eql);
            t.start();
        }
    }
}

class Handler extends Thread {
    Socket sock;
    ArrayList<EarthQuake> eql;

    public Handler(Socket sock) {
        this.sock = sock;
    }

    public void setEql(ArrayList<EarthQuake> eql)
    {
        this.eql = eql;
    }
    public void run() {
        try (InputStream input = this.sock.getInputStream()) {
            try (OutputStream output = this.sock.getOutputStream()) {
                handle(input, output);
            }
        } catch (Exception e) {
            try {
                this.sock.close();
            } catch (IOException ioe) {
                System.out.println("client disconnected.");
            }
        }
    }

    private void handle(InputStream input, OutputStream output) throws IOException,SQLException {
        System.out.println("Process new http request...");
        var reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
        var writer = new BufferedWriter(new OutputStreamWriter(output, StandardCharsets.UTF_8));
        // 读取http请求
        boolean requestOk = false;
        String first = reader.readLine();
        if (first.startsWith("GET / HTTP/1.")) {
            requestOk = true;
        }

        for (; ; ) {
            String header = reader.readLine();
            if (header.isEmpty())
                break;
            System.out.println(header);
        }
        System.out.println(requestOk ? "Response OK" : "Response Error");
        if (!requestOk) {
            writer.write("HTTP/1.0 404 Not Found\r\n");
            writer.write("Content-Length: 0\r\n");
            writer.write("\r\n");
            writer.flush();
        } else {
            var eql = new ArrayList<EarthQuake>(this.eql);
            Iterator<EarthQuake> eqi = eql.iterator();
            StringBuilder eqls = new StringBuilder();
            eqls.append("OT            LATITUDE          LONGITUDE            DEPTH            MAGNITUDE            REGION").append("<br>");
            while (eqi.hasNext()) {
                eqls.append(eqi.next().toString()).append("<br>");
            }
            String data = "<html><body><h5>" + eqls + "</h5></body></html>";
            int length = data.getBytes(StandardCharsets.UTF_8).length;
            writer.write("HTTP/1.0 200 OK\r\n");
            writer.write("Connection: close\r\n");
            writer.write("Content-Type: text/html\r\n");
            writer.write("Content-Length: " + length + "\r\n");
            writer.write("\r\n"); // 空行标识Header和Body的分隔
            writer.write(data);
            //writer.flush();
        }
    }
}
