package twoChatTCPClient;

import java.awt.Container;

import javax.swing.JFrame;



public class Start  {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame("无聊时和自己聊天:Client");
		frame.setBounds(100, 100, 600, 700);			
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		Container container = frame.getContentPane();				
		container.setLayout(null);
		
		ChatPanel chatClientPanel = new ChatPanel();
		container.add(chatClientPanel);
		chatClientPanel.setBounds(50, 50, 500, 5000);//这里如果不设置长宽，就不会显示，因为默认长宽为0，0.
	}
}
