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

public class ViewFrame extends JFrame {


	public static JFrame mainFrame = new JFrame();
	MainMenuView mainMenu;
	JPanel mainPan = new JPanel();

	public ViewFrame() {

		mainFrame.setLayout(null);
		mainFrame.setTitle("재무관리");


		mainPan.setBounds(7, 0, 1170, 755);
		// 모니터 사이즈 받아오기
		Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();
		// 사이즈 설정

		mainFrame.setBounds(scrSize.width / 2 - 600, scrSize.height / 2 - 400, 1200, 800);

		mainFrame.setIconImage(new ImageIcon("coin.png").getImage());

		mainPan.removeAll();

		mainMenu = new MainMenuView(mainPan);

		mainPan.add(mainMenu, BorderLayout.CENTER);
		// 구성 요소 가로/세로 속성 변경하여 호출
		mainPan.revalidate();
		// 현재 재배치한 내용으로 보이기
		mainPan.repaint();
//		mainPan.setBackground(Color.blue);


		mainFrame.add(mainPan);

		mainFrame.setResizable(false);
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


	}

	public static void main(String[] args) {
		new ViewFrame();
	}
}
