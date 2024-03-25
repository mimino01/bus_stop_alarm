import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
	public static void main(String[] args) {
		
		try {
			ServerSocket serverSocket = new ServerSocket(8000);
			Socket socket = serverSocket.accept();
			
			System.out.println("서버on");
			
			InputStream in = socket.getInputStream();
			OutputStream out = socket.getOutputStream();
			
			byte[] inputData = new byte[100];
			int length = in.read(inputData);
			String inputMessage = new String(inputData, 0, length);
			
			System.out.println("클라이언트에서 받은 데이터 : " + inputMessage);
			
			String outputMessage = "나다 서버";
			out.write(outputMessage.getBytes());
			out.flush();
			
			socket.close();
			serverSocket.close();
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}