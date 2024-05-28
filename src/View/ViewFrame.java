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

public class ViewFrame extends JFrame{

//	public final static int width = 1200;
//	public final static int height = 800;
//	public final static int x = 0;
//	public final static int y = 0;
	
	JPanel mainPan = new JPanel();
	public ViewFrame() {
		setLayout(null);
		setTitle("재무관리");
		
		mainPan.setBounds(0, 0,1180, 760);
		// 모니터 사이즈 받아오기
		Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();
		// 사이즈 설정
		setBounds(scrSize.width / 2 - 600, scrSize.height / 2 - 400, 1200, 800);
		
		setIconImage(new ImageIcon("coin.png").getImage());
		
		mainPan.removeAll();
		
		mainPan.add(new MainMenuView(mainPan),BorderLayout.CENTER);
		// 구성 요소 가로/세로 속성 변경하여 호출
		mainPan.revalidate();
		// 현재 재배치한 내용으로 보이기
		mainPan.repaint();
//		mainPan.setBackground(Color.blue);

		add(mainPan);
		
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	

	
	public static void main(String[] args) {
		new ViewFrame();
	}
}
