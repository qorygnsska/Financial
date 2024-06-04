package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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

public class LoginView extends JFrame {
	private static Font font1 = new Font("나눔고딕", Font.BOLD, 19);
	private static Font font2 = new Font("나눔고딕", Font.BOLD, 30);
	private static Font font3 = new Font("나눔고딕", Font.BOLD, 13);
	private Color colBack = new Color(235, 255, 235);
	private Color colBack2 = new Color(255, 247, 242);
	private ImageIcon img = new ImageIcon("search.png");
	LoginController loginController = new LoginController();

	UsersModel usersModel = new UsersModel();

	public void user() {

		setTitle("재무관리");
		JPanel back = new JPanel();
		back.setBackground(colBack);
		back.setBorder(new LineBorder(Color.GREEN, 3));

		setIconImage(new ImageIcon("coin.png").getImage());

		JPanel title = new JPanel();

		JLabel login = new JLabel("로그인 화면");

		login.setForeground(Color.black);

		login.setFont(font2);
		title.setBackground(colBack);
		title.add(login);

		JPanel jp1 = new JPanel();

		jp1.setLayout(new GridLayout(3, 2));

		JPanel idPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JLabel jlid = new JLabel("아이디 : ", JLabel.CENTER);
		jlid.setFont(font1);
		idPanel.setBackground(colBack);

		JPanel idPanel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JTextField jtid = new JTextField(10);
		idPanel2.setBackground(colBack);

		idPanel.add(jlid);
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

		JPanel loginPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton jLogin = new JButton("로그인");
		jLogin.setBackground(colBack2);
		jLogin.setFont(font3);
		loginPanel.setBackground(colBack);

		JPanel joinPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton join = new JButton("회원가입");
		join.setBackground(colBack2);
		join.setFont(font3);
		joinPanel.setBackground(colBack);

		loginPanel.add(jLogin);
		joinPanel.add(join);

		jp1.add(loginPanel);
		jp1.add(joinPanel);

		JPanel jp2 = new JPanel();
		jp2.setBackground(colBack);
		jp2.setLayout(new FlowLayout());
		jp2.add(jp1);

		JPanel jp3 = new JPanel();
		jp3.setBackground(colBack);
		JLabel idsearch = new JLabel("아이디 찾기 /", img, JLabel.CENTER);
		JLabel pwsearch = new JLabel("비밀번호 찾기");
		idsearch.setFont(font3);
		pwsearch.setFont(font3);
		jp3.add(idsearch);
		jp3.add(pwsearch);

		add(title, BorderLayout.NORTH);
		add(jp2, BorderLayout.CENTER);
		add(jp3, BorderLayout.SOUTH);

		setBounds(0, 0, 300, 250);

		idsearch.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// 라벨 밖에서 커서 원래대로
				idsearch.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mouseEntered(MouseEvent e) {

				// 라벨에서 포인터 변경
				idsearch.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				UseridpwFindView uidpwfind = new UseridpwFindView();
				uidpwfind.idfind();
				dispose();
			}

		});

		pwsearch.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// 라벨 밖에서 커서 원래대로
				pwsearch.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// 라벨에서 포인터 변경
				pwsearch.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				UseridpwFindView uidpwfind = new UseridpwFindView();
				uidpwfind.pwfind();
				dispose();
			}
		});

		// 로그인 버튼 클릭시 이벤트
		jLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String id = jtid.getText();
				String pass = jtpw.getText();

				// 컨트롤러에 전송
				if (loginController.login(id, pass)) {
					usersModel.save(id, pass);
					System.out.println(UsersModel.user.getName() + "님 로그인");
					new ViewFrame();
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "아이디 또는 비밀번호가 다릅니다!", "로그인 실패", JOptionPane.ERROR_MESSAGE);
				}

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

	public static void main(String[] args) {
		new LoginView().user();
	}
}