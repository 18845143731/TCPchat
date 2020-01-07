package twoChatTCPClient;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class ChatPanel extends JPanel implements Runnable{
	 JLabel chatView;
	 JTextField textF;
	 JButton btSend;	
	 OutputStream outputStreamClient;
	 Socket cliSocket ;
	 InputStream inputStreamClient;
	ChatPanel() {		
		this.setBackground(Color.white);
		this.setVisible(true);	
		this.setLayout(null);		
		init();
	}
	
	 public void init() {	
		 layoutInit();
		 netInit();
		 new Thread(this,"ThreadClient").start();;//开启子线程，套接字的在子线程的实现。
	 }
	 
	 public void layoutInit() {
		 btSend = new JButton("发送");
		 btSend.addActionListener(new ActionListener() {//按钮点击事件			
			@Override
			public void actionPerformed(ActionEvent var1) {
				String sendData = textF.getText();
				try {
					outputStreamClient.write(sendData.getBytes());//将sendData写出
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}//想server传输数据		
			
			}
			
		});
		 textF = new JTextField();
		 chatView = new JLabel();
		 this.add(btSend);
		 this.add(textF);
		 this.add(chatView);
		 btSend.setBounds(400, 400, 60, 30);
		 textF.setBounds(20,400,350,30);
		 chatView.setBounds(20, 10, 450, 370);
		 chatView.setOpaque(true);//设置颜色时，必须先把它设置成不透明的
		 chatView.setBackground(Color.lightGray);
		 chatView.setVerticalAlignment(SwingConstants.TOP);;
		

	 }

	 public void netInit() {
		 try {		  
			 InetAddress serverIP = InetAddress.getByName("127.0.0.1");//得到服务器的IP，这里是本机
			 int port = 9999;	
			 cliSocket =new Socket(serverIP,port);			
			 outputStreamClient = cliSocket.getOutputStream();	
			 inputStreamClient = cliSocket.getInputStream();	
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 }
		@Override
		public void run() {
			
			try {
				while(true) {									
					byte byteData[] = new byte[1024];
					inputStreamClient.read(byteData);
					chatView.setText(new String(byteData));					
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
}
