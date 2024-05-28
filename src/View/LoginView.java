package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginView extends JFrame {

	public void init() {

		setTitle("재무관리");

		JPanel title = new JPanel();

		JLabel login = new JLabel("로그인 화면");

		login.setForeground(Color.BLUE);

		login.setFont(new Font("휴먼편지체", Font.BOLD, 30));

		title.add(login);

		JPanel jp1 = new JPanel();

		jp1.setLayout(new GridLayout(3, 2));

		JPanel idPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JLabel jlid = new JLabel("아이디 : ", JLabel.CENTER);
		jlid.setFont(new Font("휴먼편지체", Font.BOLD, 20));
		idPanel.add(jlid);

		JPanel idPanel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JTextField jtid = new JTextField(10);

		idPanel2.add(jtid);

		jp1.add(idPanel);
		jp1.add(idPanel2);

		JPanel pwdPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JLabel jlpw = new JLabel("비밀번호 : ", JLabel.CENTER);
		jlpw.setFont(new Font("휴먼편지체", Font.BOLD, 20));
		JPanel pwdPanel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPasswordField jtpw = new JPasswordField(10);

		pwdPanel.add(jlpw);
		pwdPanel2.add(jtpw);

		jp1.add(pwdPanel);
		jp1.add(pwdPanel2);

		JPanel loginPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton jLogin = new JButton("로그인");

		JPanel joinPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton join = new JButton("회원가입");

		loginPanel.add(jLogin);
		joinPanel.add(join);

		jp1.add(loginPanel);
		jp1.add(joinPanel);

		JPanel jp2 = new JPanel();
		jp2.setLayout(new FlowLayout());
		jp2.add(jp1);

		setLayout(new BorderLayout());

		JPanel jp3 = new JPanel();

		JLabel idpw = new JLabel("아이디 비밀번호 찾기");
		jp3.add(idpw);

		add(title, BorderLayout.NORTH);
		add(jp2, BorderLayout.CENTER);
		add(jp3, BorderLayout.SOUTH);

		setBounds(0, 0, 300, 250);

		jp3.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		// 로그인 버튼 클릭시 이벤트
		jLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
		// 회원가입 버튼 클릭시 이벤트
		join.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				new SignupView().newUser();
				dispose();

			}
		});

		setResizable(false); // 화면 크기 고정하는 작업
		// 화면 중앙에 배치할 수있도록 메서드
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}
