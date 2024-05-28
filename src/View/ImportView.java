package View;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;

public class ImportView extends JFrame {
	private String day;
	private int price;
	private int imtype;
	private String memo;

	private JScrollPane sp = new JScrollPane();
	// tab
	private JTabbedPane tabPanel = new JTabbedPane();

	public ImportView() {
		screenSize();
		print();

	}

	public ImportView(String day, int price, int imtype, String memo) throws HeadlessException {
		this.day = day;
		this.price = price;
		this.imtype = imtype;
		this.memo = memo;
	}

	public void print() {
		setTitle("수입 상세 페이지");
		setBounds(50, 50, 1200, 800);
		setLayout(null);

		// main 패널 (tab 생성 포함)
		JPanel mainPanel = new JPanel();
		mainPanel.setBounds(0, 0, 1200, 700);

		// 전체 조회
		JPanel totalPanel = new JPanel();
		totalPanel.setBounds(100, 50, 1000, 600);
		// data DB 연결 필요
		String[] column = { "날짜", "금        액", "구분", "비        고" };
		String[][] rowData = { { "24/05/27", "3000000", "급여", "보너스 제외" }, { "24/05/27", "1000000", "급여", "보너스" } };

		sp = totalCheck(rowData, column);
		totalPanel.add(sp);

		// 일별 조회
		JPanel dayPanel = new JPanel();
		dayPanel.setBounds(100, 50, 1000, 600);
		// data DB 연결 필요
		String[] column1 = { "날짜", "금        액", "구분", "비        고" };
		String[][] rowData1 = { { "24/05/27", "4000000", "급여", "보너스 제외 및 알바비 포함" },
				{ "24/05/27", "2000", "이자", "은행 이자" } };
		sp = totalCheck(rowData1, column1);
		dayPanel.add(sp);

		// 월별 조회
		JPanel monthPanel = new JPanel();
		monthPanel.setBounds(100, 50, 1000, 600);
		String[] column2 = { "날짜", "금        액", "구분", "비        고" };
		String[][] rowData2 = { { "24/05/27", "5000000", "급여", "보너스 포함" }, { "24/05/27", "1000000", "기타", "길에서 주움" } };
		sp = totalCheck(rowData2, column2);
		monthPanel.add(sp);

		// tab
		tabPanel.add("전체", totalPanel);
		tabPanel.add("일별", dayPanel);
		tabPanel.add("월별", monthPanel);

		mainPanel.add(tabPanel);

		// 버튼 패널
		JPanel btnPanel = new JPanel();
		btnPanel.setBounds(950, 700, 250, 70);

		// 추가 버튼
		JButton addBtn = new JButton("추가");
		btnPanel.add(addBtn);
		addBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

		// 수정 버튼
		JButton editBtn = new JButton("수정");
		btnPanel.add(editBtn);
		editBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

		// 이전 버튼
		JButton backBtn = new JButton("이전");
		btnPanel.add(backBtn);
		backBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

		add(mainPanel);
		add(btnPanel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public JScrollPane totalCheck(String[][] rowData, String[] column) {
		JTable totalTable = new JTable(rowData, column);
		JScrollPane scrollpane = new JScrollPane(totalTable);
		scrollpane.setPreferredSize(new Dimension(1000, 550));

		// table의 data 가운데, 오른쪽 정렬하는 변수 선언
		DefaultTableCellRenderer dtcrCenter = new DefaultTableCellRenderer();
		dtcrCenter.setHorizontalAlignment(JLabel.CENTER);
		DefaultTableCellRenderer dtcrRight = new DefaultTableCellRenderer();
		dtcrRight.setHorizontalAlignment(JLabel.RIGHT);
		TableColumnModel tcm = totalTable.getColumnModel();

		// "금액"만 오른쪽 정렬, 나머지는 가운데 정렬
		totalTable.getColumn("날짜").setPreferredWidth(30);
		totalTable.getColumn("날짜").setCellRenderer(dtcrCenter);
		totalTable.getColumn("금        액").setPreferredWidth(250);
		totalTable.getColumn("금        액").setCellRenderer(dtcrRight);
		totalTable.getColumn("구분").setPreferredWidth(30);
		totalTable.getColumn("구분").setCellRenderer(dtcrCenter);
		totalTable.getColumn("비        고").setPreferredWidth(250);
		totalTable.getColumn("비        고").setCellRenderer(dtcrCenter);

		return scrollpane;
	}

	public JScrollPane dayCheck(String[][] rowData, String[] column) {
		JTable totalTable = new JTable(rowData, column);
		JScrollPane scrollpane = new JScrollPane(totalTable);
		scrollpane.setPreferredSize(new Dimension(1000, 550));

		// table의 data 가운데, 오른쪽 정렬하는 변수 선언
		DefaultTableCellRenderer dtcrCenter = new DefaultTableCellRenderer();
		dtcrCenter.setHorizontalAlignment(JLabel.CENTER);
		DefaultTableCellRenderer dtcrRight = new DefaultTableCellRenderer();
		dtcrRight.setHorizontalAlignment(JLabel.RIGHT);
		TableColumnModel tcm = totalTable.getColumnModel();

		// "금액"만 오른쪽 정렬, 나머지는 가운데 정렬
		totalTable.getColumn("날짜").setPreferredWidth(30);
		totalTable.getColumn("날짜").setCellRenderer(dtcrCenter);
		totalTable.getColumn("금        액").setPreferredWidth(250);
		totalTable.getColumn("금        액").setCellRenderer(dtcrRight);
		totalTable.getColumn("구분").setPreferredWidth(30);
		totalTable.getColumn("구분").setCellRenderer(dtcrCenter);
		totalTable.getColumn("비        고").setPreferredWidth(250);
		totalTable.getColumn("비        고").setCellRenderer(dtcrCenter);

		return scrollpane;
	}

	public JScrollPane monthCheck(String[][] rowData, String[] column) {
		JTable totalTable = new JTable(rowData, column);
		JScrollPane scrollpane = new JScrollPane(totalTable);
		scrollpane.setPreferredSize(new Dimension(1000, 550));

		// table의 data 가운데, 오른쪽 정렬하는 변수 선언
		DefaultTableCellRenderer dtcrCenter = new DefaultTableCellRenderer();
		dtcrCenter.setHorizontalAlignment(JLabel.CENTER);
		DefaultTableCellRenderer dtcrRight = new DefaultTableCellRenderer();
		dtcrRight.setHorizontalAlignment(JLabel.RIGHT);
		TableColumnModel tcm = totalTable.getColumnModel();

		// "금액"만 오른쪽 정렬, 나머지는 가운데 정렬
		totalTable.getColumn("날짜").setPreferredWidth(30);
		totalTable.getColumn("날짜").setCellRenderer(dtcrCenter);
		totalTable.getColumn("금        액").setPreferredWidth(250);
		totalTable.getColumn("금        액").setCellRenderer(dtcrRight);
		totalTable.getColumn("구분").setPreferredWidth(30);
		totalTable.getColumn("구분").setCellRenderer(dtcrCenter);
		totalTable.getColumn("비        고").setPreferredWidth(250);
		totalTable.getColumn("비        고").setCellRenderer(dtcrCenter);

		return scrollpane;
	}

	public void screenSize() {
		// 모니터 사이즈 받아오기
		Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();
		// 사이즈 설정
		setBounds(scrSize.width / 2 - 600, scrSize.height / 2 - 400, 1200, 800);
		setResizable(false);
	}

}
