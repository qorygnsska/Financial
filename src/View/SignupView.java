package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Controller.SignupController;
import Model.UsersModel;

public class SignupView extends JFrame {
	SignupController signupController = new SignupController();
	
	private Font font1 = new Font("나눔고딕", Font.BOLD, 20);
	private Font font2 = new Font("나눔고딕", Font.BOLD, 30);
	private Font font3 = new Font("나눔고딕", Font.BOLD, 13);
	private Color colBack = new Color(204, 255, 204);
	private Color colBack2 = new Color(255, 247, 242);

	public void newUser() {

		setTitle("회원가입");
		JPanel black = new JPanel();
		black.setBackground(colBack);
		JPanel title = new JPanel();
		JLabel Jtitle = new JLabel("회원가입");
		Jtitle.setFont(font2);
		title.setBackground(colBack);
		title.add(Jtitle);

		JPanel jp1 = new JPanel();

		jp1.setLayout(new GridLayout(4, 2));
		JPanel idPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JLabel jlid = new JLabel("아이디 : ", JLabel.CENTER);
		jlid.setFont(font1);
		idPanel.setBackground(colBack);
		idPanel.add(jlid);

		JPanel idPanel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JTextField jtid = new JTextField(10);
		idPanel2.setBackground(colBack);
		idPanel2.add(jtid);

		jp1.add(idPanel);
		jp1.add(idPanel2);

		JPanel pwdPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JLabel jlpw = new JLabel("비밀번호 : ", JLabel.CENTER);
		jlpw.setFont(font1);
		pwdPanel.setBackground(colBack);
		JPanel pwdPanel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPasswordField jtpw = new JPasswordField(10);
		pwdPanel2.setBackground(colBack);
		pwdPanel.add(jlpw);
		pwdPanel2.add(jtpw);

		jp1.add(pwdPanel);
		jp1.add(pwdPanel2);

		JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JLabel jlname = new JLabel("이름 : ", JLabel.CENTER);
		jlname.setFont(font1);
		namePanel.setBackground(colBack);
		JPanel namePanel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JTextField jtname = new JTextField(10);
		namePanel2.setBackground(colBack);
		namePanel.add(jlname);
		namePanel2.add(jtname);

		jp1.add(namePanel);
		jp1.add(namePanel2);

		JPanel juminPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JLabel jljumin = new JLabel("주민번호 : ", JLabel.CENTER);
		jljumin.setFont(font1);
		juminPanel.setBackground(colBack);
		JPanel juminPanel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JTextField jtjumin = new JTextField(5);
		juminPanel2.setBackground(colBack);
		JLabel jlumin2 = new JLabel("-");
		JPasswordField jtjumin2 = new JPasswordField(5);

		juminPanel.add(jljumin);
		juminPanel2.add(jtjumin);
		juminPanel2.add(jlumin2);
		juminPanel2.add(jtjumin2);

		jp1.add(juminPanel);
		jp1.add(juminPanel2);
		black.add(jp1);
		JPanel jp2 = new JPanel();
		jp2.setLayout(new FlowLayout());
		jp2.add(black);

		setLayout(new BorderLayout());

		JPanel jp3 = new JPanel();
		JButton back= new JButton("되돌아가기");
		JButton btuser = new JButton("회원가입");
		back.setBackground(colBack2);
		btuser.setBackground(colBack2);
		back.setFont(font3);
		btuser.setFont(font3);
		jp3.setBackground(colBack);
		jp3.add(back);
		jp3.add(btuser);
		add(title, BorderLayout.NORTH);
		add(jp2, BorderLayout.CENTER);
		add(jp3, BorderLayout.SOUTH);

		add(black);
		setBounds(0, 0, 300, 300);

		//되돌아가기 버튼 이벤트
		back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				new LoginView().user();
				dispose();
			}
		});
		
		// 회원가입 버튼 이벤트
		btuser.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				String user_id = jtid.getText();
				String user_pass = jtpw.getText();
				String name = jtname.getText();
				String jumin = jtjumin.getText() + jtjumin2.getText();
				
				// user 객체 생성
				UsersModel user = new UsersModel(user_id, user_pass, name, jumin);
				if(signupController.signup(user)) {
					JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다!", "성공", JOptionPane.PLAIN_MESSAGE);
					new LoginView().user();
					dispose();
				}else {
					JOptionPane.showMessageDialog(null, "회원가입 실패(중복 or 빈 칸)", "실패", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});

		// 화면 크기 고정하는 작업
		setResizable(false);
		// 화면 중앙에 배치할 수있도록 메서드
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}


}
