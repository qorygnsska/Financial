package View;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import Controller.LoginController;
import Model.UsersModel;

public class UseridpwFindView extends JFrame {
	LoginController loginController = new LoginController();

	private JPanel jpmain;
	private JButton idbtn;
	private JLabel jlid;
	private JTextField jtjumin;
	private JPasswordField jpjumin;
	private JLabel jl1;
	private JLabel jp;
	private JTextField jtp;
	private JPanel jp1;
	private JButton backbtn;
	private static Font font1 = new Font("나눔고딕", Font.BOLD, 15);
	private static Font font2 = new Font("나눔고딕", Font.BOLD, 16);
	private static Font font3 = new Font("나눔고딕", Font.BOLD, 14);
	



	//아이디 찾기 메소드
	public void idfind() {
		setIconImage(new ImageIcon("image/coin.png").getImage());
		jpmain = new JPanel();
		setTitle("아이디");
		setBounds(0, 0, 300, 150);
		jpmain.setBorder(new TitledBorder(new LineBorder(new Color(225, 235, 255), 4),"아이디 찾기",TitledBorder.LEFT, TitledBorder.TOP, font3));
		jpmain.setBackground(Color.white);
		
		
		jlid = new JLabel("주민번호");
		jlid.setFont(font1);
		jl1 = new JLabel("-");
		jtjumin = new JTextField(6);
		jpjumin = new JPasswordField(7);
		jpmain.add(jlid);
		jpmain.add(jtjumin);
		jpmain.add(jl1);
		jpmain.add(jpjumin);
		idbtn = new JButton("검색");
		idbtn.setFont(font2);
		idbtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		backbtn = new JButton("돌아가기");
		backbtn.setFont(font2);
		backbtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jpmain.add(backbtn);
		jpmain.add(idbtn);

		jpmain.setBounds(10, 10, 300, 150);
		add(jpmain);
		//로그인화면으로 되돌아가기
		backbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new LoginView().user();
				dispose();

			}
		});
		//검색버튼
		idbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String jumin = jtjumin.getText() + jpjumin.getText();
				//숫자로만 입력가능
				if(jumin.matches("[0-9]+")) {				
				if (loginController.idfind(jumin) != null) {
					String id = String.valueOf(loginController.idfind(jumin));
					JOptionPane.showMessageDialog(null, "아이디 : " + id, "성공", JOptionPane.PLAIN_MESSAGE);
					new LoginView().user();
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "입력하신 정보가 없습니다.", "실패", JOptionPane.ERROR_MESSAGE);
				}
				
				}else {
					JOptionPane.showMessageDialog(null, "숫자를 입력하세요.", "실패", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});

	jtjumin.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
			//입력된 텍스트
				String jumintext = jtjumin.getText();
				//글자 수 제한
				int maxtext=6;		
						
				 if(jumintext.length()>=maxtext) {
					//입력된 텍스트가 6자리를 넘어가면 입력을 무시
					e.consume();
				}
			
				
			}	
			@Override
			public void keyReleased(KeyEvent e) {}			
			@Override
			public void keyPressed(KeyEvent e) {}
		});
	
	jpjumin.addKeyListener(new KeyListener() {
		
		@Override
		public void keyTyped(KeyEvent e) {
		//입력된 텍스트
			String jumintext = jpjumin.getText();
			//글자 수 제한
			int maxtext=7;
			if(jumintext.length()>=maxtext) {
				//입력된 텍스트가 7자리를 넘어가면 입력을 무시
				e.consume();
			}				
		}	
		@Override
		public void keyReleased(KeyEvent e) {}			
		@Override
		public void keyPressed(KeyEvent e) {}
	});
		
		
		
		
		setResizable(false); // 화면 크기 고정하는 작업
		// 화면 중앙에 배치할 수있도록 메서드
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

	}
	//비밀번호 찾기 메소드
	public void pwfind() {
		setIconImage(new ImageIcon("image/coin.png").getImage());
		jp1 = new JPanel();
		jpmain = new JPanel();
		setTitle("비밀번호");
		setBounds(0, 0, 250, 180);

		jpmain.setBorder(new TitledBorder(new LineBorder(new Color(225, 235, 255), 4), "비밀번호 찾기",TitledBorder.LEFT, TitledBorder.TOP, font3));
		jpmain.setBackground(Color.white);
		jp = new JLabel("아이디", JLabel.CENTER);
		jp.setFont(font1);
		JPanel jp1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		jp1.setBackground(Color.white);
		jtp = new JTextField(10);
		jp1.add(jtp);

		JPanel jp2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		jp2.setBackground(Color.white);
		jlid = new JLabel("주민번호", JLabel.CENTER);
		jlid.setFont(font1);
		jl1 = new JLabel("-");
		jtjumin = new JTextField(5);
		jpjumin = new JPasswordField(5);
		jpmain.add(jp);
		jpmain.add(jp1);
		jpmain.add(jlid);
		jp2.add(jtjumin);
		jp2.add(jl1);
		jp2.add(jpjumin);
		jpmain.add(jp2);
		idbtn = new JButton("검색");
		idbtn.setFont(font2);
		idbtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		backbtn = new JButton("돌아가기");
		backbtn.setFont(font2);
		backbtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jpmain.add(backbtn);
		jpmain.add(idbtn);

		jpmain.setBounds(10, 10, 250, 180);
		add(jpmain);
		//로그인화면으로 되돌아가기
		backbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new LoginView().user();
				dispose();

			}
		});
		//비밀번호 검색 
		idbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String uid = jtp.getText();
				String jumin = jtjumin.getText() + jpjumin.getText();
				//숫자로만 입력가능
				if(jumin.matches("[0-9]+")) {	
				if (loginController.pwfind(jumin, uid) != null) {
					String pw = String.valueOf(loginController.pwfind(jumin, uid));
					JOptionPane.showMessageDialog(null, "비밀번호 : " + pw, "성공", JOptionPane.PLAIN_MESSAGE);
					new LoginView().user();
					dispose();
					
				} else {
					JOptionPane.showMessageDialog(null, "입력하신 정보가 없습니다.", "실패", JOptionPane.ERROR_MESSAGE);
				}
				}else {
					JOptionPane.showMessageDialog(null, "숫자를 입력하세요", "실패", JOptionPane.ERROR_MESSAGE);
					
				}
			}
		});

		
	jtjumin.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
			//입력된 텍스트
				String jumintext = jtjumin.getText();
				//글자 수 제한
				int maxtext=6;
				if(jumintext.length()>=maxtext) {
					//입력된 텍스트가 6자리를 넘어가면 입력을 무시
					e.consume();
				}				
			}	
			@Override
			public void keyReleased(KeyEvent e) {}			
			@Override
			public void keyPressed(KeyEvent e) {}
		});
	
	jpjumin.addKeyListener(new KeyListener() {
		
		@Override
		public void keyTyped(KeyEvent e) {
		//입력된 텍스트
			String jumintext = jpjumin.getText();
			//글자 수 제한
			int maxtext=7;
			if(jumintext.length()>=maxtext) {
				//입력된 텍스트가 7자리를 넘어가면 입력을 무시
				e.consume();
			}				
		}	
		@Override
		public void keyReleased(KeyEvent e) {}			
		@Override
		public void keyPressed(KeyEvent e) {}
	});
		
		
		
		 setResizable(false); // 화면 크기 고정하는 작업
		// 화면 중앙에 배치할 수있도록 메서드
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

	}
	
	

}
