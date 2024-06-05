package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import Controller.AmountController;
import Model.AmountModel;
import Model.UsersModel;

public class AmountView extends JPanel {

	JPanel panMain;
	private Font font = new Font("나눔고딕", Font.BOLD, 30);
	private Font font2 = new Font("함초롱돋움", Font.PLAIN, 15);
	private Font font3 = new Font("나눔고딕", Font.BOLD, 15);
	
	private Color colBack = new Color(255, 255, 255);
	private Color colBtn = new Color(240, 248, 255);
	private Color colLine = new Color(225, 235, 255);

	AmountController amountController = new AmountController();

	public AmountView(JPanel panel) {
		setLayout(null);

		panMain = panel; // MainMenuView의 패널 가져옴
		Rectangle rect = panel.getBounds(); // 패널의 정보 저장
		setPreferredSize(rect.getSize()); // 패널의 사이즈 지정

		setBackground(colBack); // 프레임 배경색 설정

		JPanel mainPan = new JPanel();
		mainPan.setBounds(100, 30, 1000, 600);
		mainPan.setBackground(colBack);
		mainPan.setBorder(new LineBorder(colLine, 7, true)); // 메인 패널 테두리 색

		// 타이틀 패널 생성!
		JPanel titlePan = new JPanel();
		titlePan.setBackground(colBack); // 타이틀 배경색
		//titlePan.setBorder(new LineBorder(new Color(225, 235, 255), 3, true)); // 타이틀 패널 테두리 색

		// 타이틀 라벨 생성!
		JLabel titleLabel = new JLabel("상세 거래내역 (최근 1년까지 조회)");
		titleLabel.setFont(font);

		// 패널에 타이틀 라벨 추가
		titlePan.add(titleLabel);

		// 테이블 담당 패널 생성!
		JPanel tablePan = new JPanel();
		tablePan.setBorder(new LineBorder(colLine, 4, true)); // 테이블 패널 테두리 색

		// 테이블의 열
		Object[] tableHeader = { "날짜", "금액", "구분", "비고", "잔액" };
		Object[][] data1 = amountController.selecet();

		// 데이터 모델 생성
		DefaultTableModel model = new DefaultTableModel(data1, tableHeader) {
			public boolean isCellEditable(int rowIndex, int mColindex) {
				return false;
			}
		};

		// 테이블 생성
		JTable amountTable = new JTable(model);

		amountTable.setRowHeight(30); // 행의 높이 설정

		amountTable.setFont(font2);
		// 테이블 컬럼 이동불가
		amountTable.getTableHeader().setReorderingAllowed(false);
		// 컬럼 크기 조절 불가
		amountTable.getTableHeader().setResizingAllowed(false);
		// 테이블 내용 가운데 정렬
		tableCellCenter(amountTable);
		// 테이블 셀 글자색 설정
		tableCellColor(amountTable);

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
		//btnPan.setBorder(new LineBorder(Color.green, 4));

		// 버튼 생성
		JButton mainBtn = new JButton("메인으로");
		mainBtn.setFont(font3);
		//mainBtn.setBorder(BorderFactory.createLineBorder(Color.black)); // 버튼의 테두리 색
		mainBtn.setBackground(colBtn); // 버튼 배경색

		mainBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 메인으로 이동
				panMain.removeAll(); // 현재 패널 내용 지움
				panMain.add(new MainMenuView(panMain)); // 현재 패널에 메인메뉴 패널 추가
				panMain.revalidate(); // 패널의 가로세로 다시 지정
				panMain.repaint(); // 패널 그리기

			}
		});

		btnPan.add(mainBtn);

		mainPan.add(titlePan);
		mainPan.add(tablePan);

		add(mainPan);
		add(btnPan); // 메인에 버튼 추가

		setVisible(true);
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

	// 금액 색상 바꾸기
	public void tableCellColor(JTable t) {
		int targetColumnIndex = 1;
		TableColumnModel tcm = t.getColumnModel();

		TableColumn column = tcm.getColumn(targetColumnIndex);
		column.setCellRenderer(new CustomTableCellRenderer(targetColumnIndex));

	}
}
