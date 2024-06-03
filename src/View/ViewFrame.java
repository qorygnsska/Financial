package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class ViewFrame{

	
	public static JFrame mainFrame = new JFrame();
	public static MainMenuView mainMenu;
	public static JPanel mainFan = new JPanel();

	public ViewFrame() {

		mainFrame.setTitle("재무관리");

		//mainFan.setLayout(null);
		mainFan.setBounds(0, 0, 1200, 800);
		// 모니터 사이즈 받아오기
		Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();
		// 사이즈 설정

		mainFrame.setBounds(scrSize.width / 2 - 600, scrSize.height / 2 - 400, 1200, 800);

		mainFrame.setIconImage(new ImageIcon("coin.png").getImage());

		
		mainFan.removeAll();

		mainMenu = new MainMenuView(mainFan);

		mainFan.add(mainMenu, BorderLayout.CENTER);
		// 구성 요소 가로/세로 속성 변경하여 호출
		mainFan.revalidate();
		// 현재 재배치한 내용으로 보이기
		mainFan.repaint();
//		mainPan.setBackground(Color.blue);


		mainFrame.add(mainFan);

//		mainFrame.setResizable(false);
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


	}

}
