package twoChatTCPServer;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class ChatPanel extends JPanel implements Runnable{
	JLabel chatView;
	OutputStream outputStreamServer;
	Socket acceptSocket;
	InputStream inputStreamServer;
	ServerSocket serSocket;
	ChatPanel() {		
		this.setBackground(Color.white);
		this.setVisible(true);	
		this.setLayout(null);
		init();
	}
	
	 public void init() {		 
		 layoutInit();
		 netInit();
		 new Thread(this,"ThreadServer").start();//开启子线程，套接字接受数据的在子线程里实现。
	 }
	 
	 //布局初始化，及按钮的响应事件
	 public void layoutInit() {
		 JButton btSend = new JButton("发送");	
		 JTextField textF = new JTextField();
		 chatView = new JLabel();
		 this.add(btSend);
		 this.add(textF);
		 this.add(chatView);
		 btSend.addActionListener(new ActionListener() {//按钮点击事件发送消息			
				@Override
				public void actionPerformed(ActionEvent var1) {
					String sendData = textF.getText();
					try {
						outputStreamServer.write(sendData.getBytes());//将sendData通过流写出
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}//想server传输数据					
				}
			});
		 btSend.setBounds(400, 400, 60, 30);
		 textF.setBounds(20,400,350,30);
		 chatView.setBounds(20, 10, 450, 370);
		 chatView.setOpaque(true);//设置颜色时，必须先把它设置成不透明的
		 chatView.setBackground(Color.lightGray);
		 chatView.setVerticalAlignment(SwingConstants.TOP);
	 }
	 
	 public void netInit() {
		 try {		  
			 serSocket = new  ServerSocket(9999);
			 acceptSocket = serSocket.accept();
			 inputStreamServer = acceptSocket.getInputStream();
			 outputStreamServer = acceptSocket.getOutputStream();
			} catch (Exception e) {
				e.printStackTrace();
			}
	 }
	 
	 //接受客户端数据的子线程
	@Override
	public void run() {	
		try {
			while(true) {
				byte byteData[] = new byte[1024];
				inputStreamServer.read(byteData);
				chatView.setText(new String(byteData));
				System.out.println(new String(byteData));
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
