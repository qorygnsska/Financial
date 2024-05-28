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
	

	public void newUser() {

		setTitle("회원가입");
		JPanel back = new JPanel();
		back.setBackground(Color.CYAN);
		JPanel title = new JPanel();
		JLabel Jtitle = new JLabel("회원가입");
		Jtitle.setFont(new Font("궁서체", Font.BOLD, 30));
		title.setBackground(Color.CYAN);
		title.add(Jtitle);

		JPanel jp1 = new JPanel();

		jp1.setLayout(new GridLayout(4, 2));
		JPanel idPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JLabel jlid = new JLabel("아이디 : ", JLabel.CENTER);
		jlid.setFont(new Font("휴먼편지체", Font.BOLD, 20));
		idPanel.setBackground(Color.CYAN);
		idPanel.add(jlid);

		JPanel idPanel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JTextField jtid = new JTextField(10);
		idPanel2.setBackground(Color.CYAN);
		idPanel2.add(jtid);

		jp1.add(idPanel);
		jp1.add(idPanel2);

		JPanel pwdPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JLabel jlpw = new JLabel("비밀번호 : ", JLabel.CENTER);
		jlpw.setFont(new Font("휴먼편지체", Font.BOLD, 20));
		pwdPanel.setBackground(Color.CYAN);
		JPanel pwdPanel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPasswordField jtpw = new JPasswordField(10);
		pwdPanel2.setBackground(Color.CYAN);
		pwdPanel.add(jlpw);
		pwdPanel2.add(jtpw);

		jp1.add(pwdPanel);
		jp1.add(pwdPanel2);

		JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JLabel jlname = new JLabel("이름 : ", JLabel.CENTER);
		jlname.setFont(new Font("휴먼편지체", Font.BOLD, 20));
		namePanel.setBackground(Color.CYAN);
		JPanel namePanel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JTextField jtname = new JTextField(10);
		namePanel2.setBackground(Color.CYAN);
		namePanel.add(jlname);
		namePanel2.add(jtname);

		jp1.add(namePanel);
		jp1.add(namePanel2);

		JPanel juminPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JLabel jljumin = new JLabel("주민번호 : ", JLabel.CENTER);
		jljumin.setFont(new Font("휴먼편지체", Font.BOLD, 20));
		juminPanel.setBackground(Color.CYAN);
		JPanel juminPanel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JTextField jtjumin = new JTextField(5);
		juminPanel2.setBackground(Color.CYAN);
		JLabel jlumin2 = new JLabel("-");
		JPasswordField jtjumin2 = new JPasswordField(5);

		juminPanel.add(jljumin);
		juminPanel2.add(jtjumin);
		juminPanel2.add(jlumin2);
		juminPanel2.add(jtjumin2);

		jp1.add(juminPanel);
		jp1.add(juminPanel2);
		back.add(jp1);
		JPanel jp2 = new JPanel();
		jp2.setLayout(new FlowLayout());
		jp2.add(back);

		setLayout(new BorderLayout());

		JPanel jp3 = new JPanel();

		JButton btuser = new JButton("회원가입");
		jp3.setBackground(Color.CYAN);
		jp3.add(btuser);
		add(title, BorderLayout.NORTH);
		add(jp2, BorderLayout.CENTER);
		add(jp3, BorderLayout.SOUTH);

		add(back);
		setBounds(0, 0, 300, 300);

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
