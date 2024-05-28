package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class AmountView extends JFrame {

	Font font = new Font("함초롱돋움", Font.BOLD, 30);
	Font font2 = new Font("함초롱돋움", Font.PLAIN, 15);
	Font font3 = new Font("함초롱돋움", Font.BOLD, 15);

	public AmountView() {
		setLayout(null);
		setTitle("잔고 상세 페이지");
		getContentPane().setBackground(new Color(255, 255, 255)); // 프레임 배경색 설정
		screenSize(); // 프레임 크기 설정

		JPanel mainPan = new JPanel();
		mainPan.setBounds(100, 30, 1000, 600);
		mainPan.setBackground(Color.white);
		mainPan.setBorder(new LineBorder(Color.green, 4));

		// 타이틀 패널 생성!
		JPanel titlePan = new JPanel();
		titlePan.setBackground(new Color(235, 232, 255)); // 타이틀 배경색
		titlePan.setBorder(new LineBorder(Color.green, 2));

		// 타이틀 라벨 생성!
		JLabel titleLabel = new JLabel("상세 거래내역 (최근 1년까지 조회)");
		titleLabel.setFont(font);

		// 패널에 타이틀 라벨 추가
		titlePan.add(titleLabel);

		// 테이블 담당 패널 생성!
		JPanel tablePan = new JPanel();
		tablePan.setBorder(new LineBorder(Color.green, 2));

		// 테이블의 열
		Object[] tableHeader = { "날짜", "금액", "구분", "잔액" };

		// 테이블 데이터 생성
		Object[][] data = { { "2024-05-27", "-10000", "까까 사먹음", "90000" }, { "2024-05-26", "+5000", "당근", "95000" },
				{ "2024-05-27", "-10000", "까까 사먹음", "90000" }, { "2024-05-26", "+5000", "당근", "95000" },
				{ "2024-05-27", "-10000", "까까 사먹음", "90000" }, { "2024-05-26", "+5000", "당근", "95000" },
				{ "2024-05-27", "-10000", "까까 사먹음", "90000" }, { "2024-05-26", "+5000", "당근", "95000" },
				{ "2024-05-27", "-10000", "까까 사먹음", "90000" }, { "2024-05-26", "+5000", "당근", "95000" },
				{ "2024-05-27", "-10000", "까까 사먹음", "90000" }, { "2024-05-26", "+5000", "당근", "95000" },
				{ "2024-05-27", "-10000", "까까 사먹음", "90000" }, { "2024-05-26", "+5000", "당근", "95000" },
				{ "2024-05-27", "-10000", "까까 사먹음", "90000" }, { "2024-05-26", "+5000", "당근", "95000" },
				{ "2024-05-27", "-10000", "까까 사먹음", "90000" }, { "2024-05-26", "+5000", "당근", "95000" } };

		// 데이터 모델 생성
		DefaultTableModel model = new DefaultTableModel(data, tableHeader) {
			public boolean isCellEditable(int rowIndex, int mColindex) {
				return false;
			}
		};

		// 테이블 생성
		JTable amountTable = new JTable(model);

		amountTable.setBackground(Color.PINK); // 셀 배경색 변경
		amountTable.setGridColor(Color.WHITE); // 셀 테두리색 변경
		amountTable.setForeground(Color.WHITE); // 셀 글자색 변경
		amountTable.setRowHeight(30); // 행의 높이 설정

		amountTable.setFont(font2);
		// 테이블 컬럼 이동불가
		amountTable.getTableHeader().setReorderingAllowed(false);
		// 컬럼 크기 조절 불가
		amountTable.getTableHeader().setResizingAllowed(false);
		// 테이블 내용 가운데 정렬
		tableCellCenter(amountTable);

		// 스크롤 생성
		JScrollPane js = new JScrollPane();

		// 테이블의 크기 설정
		js.setPreferredSize(new Dimension(800, 500));

		// 스크롤과 테이블 연결
		js.setViewportView(amountTable);

		tablePan.add(js);

		// 버튼 관리 패널 생성
		JPanel btnPan = new JPanel(new GridLayout());
		btnPan.setBounds(870, 650, 150, 50);
		btnPan.setBorder(new LineBorder(Color.green, 4));

		// 버튼 생성
		JButton mainBtn = new JButton("메인으로");
		mainBtn.setFont(font3);
		mainBtn.setBorder(BorderFactory.createLineBorder(Color.black)); // 버튼의 테두리 색
		mainBtn.setBackground(Color.white);

		mainBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//new MainMenuView();
				dispose();

			}
		});

		btnPan.add(mainBtn);

		mainPan.add(titlePan);
		mainPan.add(tablePan);

		add(mainPan);
		add(btnPan); // 메인에 버튼 추가

		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	// 스크린 사이즈
	public void screenSize() {
		// 모니터 사이즈 받아오기
		Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();
		// 사이즈 설정
		setBounds(scrSize.width / 2 - 600, scrSize.height / 2 - 400, 1200, 800);
		setResizable(false);
	}

	// 테이블 내용 가운데 정렬하기
	public void tableCellCenter(JTable t) {
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer(); // 디폴트테이블셀렌더러를 생성
		dtcr.setHorizontalAlignment(SwingConstants.CENTER); // 렌더러의 가로정렬을 CENTER로

		TableColumnModel tcm = t.getColumnModel(); // 정렬할 테이블의 컬럼모델을 가져옴

		// 전체 열에 지정
		for (int i = 0; i < tcm.getColumnCount(); i++) {

			// 컬럼모델에서 컬럼의 갯수만큼 컬럼을 가져와 for문을 이용하여
			// 각각의 셀렌더러를 아까 생성한 dtcr에 set해줌
			tcm.getColumn(i).setCellRenderer(dtcr);

		}

	}

}
