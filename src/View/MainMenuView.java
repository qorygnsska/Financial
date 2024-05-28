package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class MainMenuView extends JFrame {
	JPanel panMain;
	JPanel[] portList = new JPanel[2];
	JPanel[] moneyList = new JPanel[5];
	JLabel[] labelList = new JLabel[5];
	DefaultTableModel[] model = new DefaultTableModel[2];
	JTable[] tableList = new JTable[2];
	JScrollPane[] paneList = new JScrollPane[2];
	JPanel btnPan = new JPanel();
	JButton[] btnList = new JButton[2];

	public MainMenuView() {
		setTitle("재무관리");
		setLayout(new BorderLayout());
		panMain = new JPanel();
		panMain.setBorder(new LineBorder(Color.green, 8));

		add(panMain);

		panMain.setBackground(Color.white);
		panMain.setLayout(null);

		// 창크기
		screenSize();

		setIconImage(new ImageIcon("coin.png").getImage());

		importTable();
		exportTable();
		moneyPrint();
		userButton();

		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	// 테이블 몸체 클릭
	private class MyMouseListener1 extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			JScrollPane c1 = (JScrollPane) e.getSource();
			if (c1 == paneList[0]) {
				System.out.println("수입 패널 클릭");
			} else if (c1 == paneList[1]) {
				System.out.println("지출 패널 클릭");
			}

		}
	}

	// 테이블 행클릭
	private class MyMouseListener2 extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			JTable c2 = (JTable) e.getSource();
			if (c2 == tableList[0]) {
				System.out.println("수입 패널 클릭");
			} else if (c2 == tableList[1]) {
				System.out.println("지출 패널 클릭");
			}

		}
	}

	// 버튼 클릭
	private class MyButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			if (btn == btnList[0]) {
				System.out.println("로그아웃 버튼");
			} else {
				System.out.println("종료 버튼");
			}

		}

	}

	// 스크린 사이즈
	public void screenSize() {
		// 모니터 사이즈 받아오기
		Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();
		// 사이즈 설정
		setBounds(scrSize.width / 2 - 600, scrSize.height / 2 - 400, 1200, 800);
	}

	// 수입 테이블
	public void importTable() {

		String[] header = { "날짜", "금액", "유형" };
		portList[0] = new JPanel();
		portList[0].setBorder(new TitledBorder(new LineBorder(Color.green, 3), " 수입 "));
		portList[0].setBounds(30, 120, 400, 400);
		portList[0].setBackground(Color.white);
		// 내용 수정 불가
		model[0] = new DefaultTableModel(header, 0) {
			public boolean isCellEditable(int rowIndex, int mColindex) {
				return false;
			}
		};

		tableList[0] = new JTable(model[0]);
		// 테이블 컬럼 이동불가
		tableList[0].getTableHeader().setReorderingAllowed(false);
		// 컬럼 크기 조절 불가
		tableList[0].getTableHeader().setResizingAllowed(false);

		// 테이블 cell 높이 설정
		tableList[0].setRowHeight(30);

		// 셀 내용 가운데 정렬
		tableCellCenter(tableList[0]);

		// 테이블 셀 배경
		tableList[0].setBackground(Color.white);
		model[0].addRow(new Object[] { "5월7일", "100000", "급여" });

		paneList[0] = new JScrollPane(tableList[0]);

		// JScrollPane 사이즈
		paneList[0].setPreferredSize(new Dimension(350, 360));
		// 컬럼 사이즈
		tableList[0].getColumn("날짜").setPreferredWidth(60);
		tableList[0].getColumn("금액").setPreferredWidth(120);
		tableList[0].getColumn("유형").setPreferredWidth(40);

		portList[0].add(paneList[0]);
		panMain.add(portList[0]);

		paneList[0].addMouseListener(new MyMouseListener1());
		tableList[0].addMouseListener(new MyMouseListener2());
	}

	// 지출 테이블
	public void exportTable() {
		String[] header = { "날짜", "금액", "유형" };
		portList[1] = new JPanel();
		portList[1].setBorder(new TitledBorder(new LineBorder(Color.green, 3), " 지출 "));
		portList[1].setBounds(460, 120, 400, 400);
		portList[1].setBackground(Color.white);
		// 내용 수정 불가
		model[1] = new DefaultTableModel(header, 0) {
			public boolean isCellEditable(int rowIndex, int mColindex) {
				return false;
			}
		};

		tableList[1] = new JTable(model[1]);

		// 테이블 컬럼 이동불가
		tableList[1].getTableHeader().setReorderingAllowed(false);
		// 컬럼 크기 조절 불가
		tableList[1].getTableHeader().setResizingAllowed(false);

		// 테이블 cell 높이 설정
		tableList[1].setRowHeight(30);

		// 셀 내용 가운데 정렬
		tableCellCenter(tableList[1]);

		// 테이블 셀 배경
		tableList[1].setBackground(Color.white);
		model[1].addRow(new Object[] { "5월7일", "100000", "급여" });

		paneList[1] = new JScrollPane(tableList[1]);
		// JScrollPane 사이즈
		paneList[1].setPreferredSize(new Dimension(350, 360));
		// 컬럼 사이즈
		tableList[1].getColumn("날짜").setPreferredWidth(60);
		tableList[1].getColumn("금액").setPreferredWidth(120);
		tableList[1].getColumn("유형").setPreferredWidth(40);
		portList[1].add(paneList[1]);
		panMain.add(portList[1]);

		paneList[1].addMouseListener(new MyMouseListener1());
		tableList[1].addMouseListener(new MyMouseListener2());
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

	// 잔고 및 수입지출
	public void moneyPrint() {

		String[] str = { "잔고", "이 달 수입", "이 달 지출", "지난 달 수입", "지난 달 지출" };
		DecimalFormat df = new DecimalFormat("#,##0.00");
		int[] money = { 90000000, 80000, 7000000, 1000000, 5000000 };

		for (int i = 0; i < moneyList.length; i++) {
			moneyList[i] = new JPanel();
			moneyList[i].setBorder(new TitledBorder(new LineBorder(Color.green, 4), str[i]));
			moneyList[i].setBackground(Color.white);
			panMain.add(moneyList[i]);
			labelList[i] = new JLabel(df.format(money[i]) + "원");
			moneyList[i].add(labelList[i]);
			moneyList[i].setBounds(900, 120 + 90 * i, 240, 60);

		}

		moneyList[0].addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				new AmountView();
			}
		});

	}

	// 로그아웃, 종료 버튼
	public void userButton() {
		btnPan.setBounds(900, 600, 200, 50);
		panMain.add(btnPan);

		btnPan.setBackground(Color.white);

		String[] list = { "로그아웃", "종료" };

		for (int i = 0; i < btnList.length; i++) {
			btnList[i] = new JButton(list[i]);
			btnPan.add(btnList[i]);
			btnList[i].addActionListener(new MyButtonListener());
		}

		btnList[1].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			}
		});

	}

}
