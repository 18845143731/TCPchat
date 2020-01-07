package twoChatTCPServer;

import java.awt.Container;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;



public class Start {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame("无聊时和自己聊天:server");
		frame.setBounds(100, 100, 600, 700);			
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		Container container = frame.getContentPane();				
		container.setLayout(null);
		
		ChatPanel chatSeverPanel = new ChatPanel();
		container.add(chatSeverPanel);
		chatSeverPanel.setBounds(50, 50, 500, 500);//这里如果不设置长宽，就不会显示，因为默认长宽为0，0.
	}
}
