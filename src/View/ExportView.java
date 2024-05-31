package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import Controller.DatePickerController;

import Controller.ExportController;

import DatePickerEx.Dateformet;
import Model.ExportModel;
import Model.UsersModel;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class ExportView extends JPanel {
	int num = 0;
	String[] datelist = new String[2];
	DatePickerController dpc = new DatePickerController();

	JPanel panMain;
	private String day;
	private int price;
	private int extype;
	private String memo;

	private JScrollPane sp = new JScrollPane();
	public JTabbedPane tabPanel = new JTabbedPane();
	private JPanel mainPanel, btnPanel, checkPanel, totalPanel, dayPanel, monthPanel, selectPanel, btnsPanel,
			updatePanel, datePanelL, datePan, amountPanelL, amountPanelR, typePanelL, typePanelR, memoPanelL,
			memoPanelR;
	DefaultTableModel[] exportModel = new DefaultTableModel[4];
	ExportController ec = new ExportController();

	public static String date;

	public ExportView() {
	}

	public ExportView(JPanel panel) {
		System.out.println("뷰 생성");
		panMain = panel;
		System.out.println("프레임 생성");
		print();

	}

	public ExportView(String day, int price, int extype, String memo) throws HeadlessException {
		this.day = day;
		this.price = price;
		this.extype = extype;
		this.memo = memo;
	}

	public void print() {
		System.out.println("프레임 생성 시작");
		Rectangle rect = panMain.getBounds();
		setPreferredSize(rect.getSize());
		// panMain.setBounds(7, 0, 1170, 765);
		setLayout(null);

		// 메인 패널
		mainPanel = new JPanel();
		// mainPanel.setBorder(new LineBorder(Color.green, 8));
		mainPanel.setBackground(Color.white);
		mainPanel.setBounds(0, 0, 1200, 800);
		mainPanel.setLayout(null);

		// 메뉴 패널(메인으로 돌아가기 버튼)
		btnPanel = new JPanel();
		btnPanel.setBackground(Color.white);
		JButton menuBtn = new JButton("메인으로");
		menuBtn.addActionListener(new ActionListener() {

			// 버튼 클릭 시 메인으로 돌아가는 이벤트
			@Override
			public void actionPerformed(ActionEvent e) {
				panMain.removeAll();
				panMain.add(new MainMenuView(panMain));
				panMain.revalidate();
				panMain.repaint();
			}
		});

		btnPanel.add(menuBtn);
		btnPanel.setBounds(1050, 20, 100, 50);
		mainPanel.add(btnPanel);

		// 조회 패널(전체, 일별, 월별 tab패널)
		checkPanel = new JPanel();
		checkPanel.setBounds(0, 0, 800, 600);
		checkPanel.setBackground(Color.white);

		// 탭 패널 추가

		tabPanel.add("전체", totalPanel());
		tabPanel.add("일별", dayPanel());
		tabPanel.add("월별", monthPanel());
		tabPanel.add("기간", selectPanel());
		// tabPanel.setSelectedIndex(1);
		checkPanel.add(tabPanel);
		checkPanel.setBounds(40, 50, 750, 600);
		mainPanel.add(checkPanel);

		// 수정 패널(추가, 수정, 삭제)
		updatePanel = new JPanel(new GridLayout(4, 2));
		updatePanel.setBounds(650, 250, 500, 300);
		updatePanel.setBackground(Color.white);

		// 날짜 패널(왼쪽)
		datePanelL = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // 오른쪽부터 정렬
		JLabel dateLabel = new JLabel("날    짜 : ", 10);
		datePanelL.add(dateLabel);
		datePanelL.setBackground(Color.white);
		updatePanel.add(datePanelL);

		// 날짜 패널(오른쪽)

		datePan = jDatePickerEx();
		datePan.setLayout(new FlowLayout(FlowLayout.LEFT));
		datePan.setBackground(Color.white);
		updatePanel.add(datePan);

		// 금액 패널(왼쪽)
		amountPanelL = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JLabel amountLabel = new JLabel("금    액 : ", 10);
		amountPanelL.add(amountLabel);
		amountPanelL.setBackground(Color.white);
		updatePanel.add(amountPanelL);

		// 금액 패널(오른쪽)
		amountPanelR = new JPanel(new FlowLayout(FlowLayout.LEFT)); // 왼쪽부터 정렬
		JTextField amountField = new JTextField(20);
		amountPanelR.add(amountField);
		amountPanelR.setBackground(Color.white);
		updatePanel.add(amountPanelR);

		// 유형 패널(왼쪽)
		typePanelL = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JLabel typeLabel = new JLabel("유    형 : ", 10);
		typePanelL.add(typeLabel);
		typePanelL.setBackground(Color.white);
		updatePanel.add(typePanelL);

		// 유형 패널(오른쪽)
		typePanelR = new JPanel(new FlowLayout(FlowLayout.LEFT)); // 왼쪽부터 정렬
		String[] exportType = { "교통비", "식비", "관리비", "쇼핑", "기타" };
		JComboBox typeBox = new JComboBox(exportType);
		typePanelR.add(typeBox);
		typePanelR.setBackground(Color.white);
		updatePanel.add(typePanelR);

		// 비고 패널(왼쪽)
		memoPanelL = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JLabel memoLabel = new JLabel("비    고 : ", 10);
		memoPanelL.add(memoLabel);
		memoPanelL.setBackground(Color.white);
		updatePanel.add(memoPanelL);

		// 비고 패널(오른쪽)
		memoPanelR = new JPanel(new FlowLayout(FlowLayout.LEFT)); // 왼쪽부터 정렬
		JTextField memoField = new JTextField(20);
		memoPanelR.add(memoField);
		memoPanelR.setBackground(Color.white);
		updatePanel.add(memoPanelR);

		// 버튼 패널(추가, 수정, 삭제)
		btnsPanel = new JPanel();
		btnsPanel.setBounds(870, 600, 250, 100);
		btnsPanel.setBackground(Color.white);

		// 추가 버튼
		JButton addBtn = new JButton("추가");
		btnsPanel.add(addBtn);
		addBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String dateText = date;
				int amount = Integer.parseInt(amountField.getText());
				String type = typeBox.getSelectedItem().toString();
				int type_id = 1;

				// 콤보박스에서 type을 받으면 type_id로 저장하는 반복문
				for (int i = 0; i < typeBox.getItemCount(); i++) {
					if (type.equals(typeBox.getItemAt(i))) {
						type_id = i + 1;
					}
				}

				String memo = memoField.getText();
				System.out.println(UsersModel.user.getId() + " " + dateText + " " + amount + " " + type + " " + memo);

				ExportModel exportModel = new ExportModel(UsersModel.user.getId(), dateText, amount, type_id, memo);
				if (ec.add(exportModel)) {
					JOptionPane.showMessageDialog(null, "지출 내역에 기입되었습니다!", "성공", JOptionPane.PLAIN_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "지출 내역에 기입되지 않았습니다!", "실패", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		// 수정 버튼
		JButton updateBtn = new JButton("수정");
		btnsPanel.add(updateBtn);
		updateBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

		// 삭제 버튼
		JButton deleteBtn = new JButton("삭제");
		btnsPanel.add(deleteBtn);
		deleteBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

		mainPanel.add(btnsPanel);
		mainPanel.add(updatePanel);

		add(mainPanel);
		setVisible(true);
	}

	public JScrollPane totalCheck() {
		System.out.println("테이블");
		String[] header = { "날짜", "금        액", "구분", "비        고" };
		exportModel[0] = ec.getExport(header);

		JTable totalTable = new JTable(exportModel[0]);
		totalTable.getTableHeader().setReorderingAllowed(false);
		totalTable.getTableHeader().setResizingAllowed(false);
		totalTable.setRowHeight(20);
		JScrollPane scrollpane = new JScrollPane(totalTable);
		scrollpane.setPreferredSize(new Dimension(700, 550));

		// table의 data 가운데, 오른쪽 정렬하는 변수 선언
		DefaultTableCellRenderer dtcrCenter = new DefaultTableCellRenderer();
		dtcrCenter.setHorizontalAlignment(JLabel.CENTER);
		DefaultTableCellRenderer dtcrRight = new DefaultTableCellRenderer();
		dtcrRight.setHorizontalAlignment(JLabel.RIGHT);
		TableColumnModel tcm = totalTable.getColumnModel();

		// "금액"만 오른쪽 정렬, 나머지는 가운데 정렬
		totalTable.getColumn("날짜").setPreferredWidth(50);
		totalTable.getColumn("날짜").setCellRenderer(dtcrCenter);
		totalTable.getColumn("금        액").setPreferredWidth(230);
		totalTable.getColumn("금        액").setCellRenderer(dtcrRight);
		totalTable.getColumn("구분").setPreferredWidth(30);
		totalTable.getColumn("구분").setCellRenderer(dtcrCenter);
		totalTable.getColumn("비        고").setPreferredWidth(250);
		totalTable.getColumn("비        고").setCellRenderer(dtcrCenter);

		return scrollpane;
	}

	public JScrollPane dayCheck() {
		String[] header = { "날짜", "금        액", "구분", "비        고" };
		exportModel[0] = ec.getExport(header);

		JTable totalTable = new JTable(exportModel[0]);
		totalTable.getTableHeader().setReorderingAllowed(false);
		totalTable.getTableHeader().setResizingAllowed(false);
		totalTable.setRowHeight(20);
		JScrollPane scrollpane = new JScrollPane(totalTable);
		scrollpane.setPreferredSize(new Dimension(700, 550));

		// table의 data 가운데, 오른쪽 정렬하는 변수 선언
		DefaultTableCellRenderer dtcrCenter = new DefaultTableCellRenderer();
		dtcrCenter.setHorizontalAlignment(JLabel.CENTER);
		DefaultTableCellRenderer dtcrRight = new DefaultTableCellRenderer();
		dtcrRight.setHorizontalAlignment(JLabel.RIGHT);
		TableColumnModel tcm = totalTable.getColumnModel();

		// "금액"만 오른쪽 정렬, 나머지는 가운데 정렬
		totalTable.getColumn("날짜").setPreferredWidth(50);
		totalTable.getColumn("날짜").setCellRenderer(dtcrCenter);
		totalTable.getColumn("금        액").setPreferredWidth(230);
		totalTable.getColumn("금        액").setCellRenderer(dtcrRight);
		totalTable.getColumn("구분").setPreferredWidth(30);
		totalTable.getColumn("구분").setCellRenderer(dtcrCenter);
		totalTable.getColumn("비        고").setPreferredWidth(250);
		totalTable.getColumn("비        고").setCellRenderer(dtcrCenter);

		return scrollpane;
	}

	public JScrollPane monthCheck() {
		String[] header = { "날짜", "금        액", "구분", "비        고" };
		exportModel[0] = ec.getExport(header);

		JTable totalTable = new JTable(exportModel[0]);
		totalTable.getTableHeader().setReorderingAllowed(false);
		totalTable.getTableHeader().setResizingAllowed(false);
		totalTable.setRowHeight(20);
		JScrollPane scrollpane = new JScrollPane(totalTable);
		scrollpane.setPreferredSize(new Dimension(700, 550));

		// table의 data 가운데, 오른쪽 정렬하는 변수 선언
		DefaultTableCellRenderer dtcrCenter = new DefaultTableCellRenderer();
		dtcrCenter.setHorizontalAlignment(JLabel.CENTER);
		DefaultTableCellRenderer dtcrRight = new DefaultTableCellRenderer();
		dtcrRight.setHorizontalAlignment(JLabel.RIGHT);
		TableColumnModel tcm = totalTable.getColumnModel();

		// "금액"만 오른쪽 정렬, 나머지는 가운데 정렬
		totalTable.getColumn("날짜").setPreferredWidth(50);
		totalTable.getColumn("날짜").setCellRenderer(dtcrCenter);
		totalTable.getColumn("금        액").setPreferredWidth(230);
		totalTable.getColumn("금        액").setCellRenderer(dtcrRight);
		totalTable.getColumn("구분").setPreferredWidth(30);
		totalTable.getColumn("구분").setCellRenderer(dtcrCenter);
		totalTable.getColumn("비        고").setPreferredWidth(250);
		totalTable.getColumn("비        고").setCellRenderer(dtcrCenter);

		return scrollpane;
	}
	
	private JScrollPane selectCheck() {
		String[] header = { "날짜", "금        액", "구분", "비        고" };
		exportModel[0] = ec.getExport(header);

		JTable totalTable = new JTable(exportModel[0]);
		totalTable.getTableHeader().setReorderingAllowed(false);
		totalTable.getTableHeader().setResizingAllowed(false);
		totalTable.setRowHeight(20);
		JScrollPane scrollpane = new JScrollPane(totalTable);
		scrollpane.setPreferredSize(new Dimension(700, 550));

		// table의 data 가운데, 오른쪽 정렬하는 변수 선언
		DefaultTableCellRenderer dtcrCenter = new DefaultTableCellRenderer();
		dtcrCenter.setHorizontalAlignment(JLabel.CENTER);
		DefaultTableCellRenderer dtcrRight = new DefaultTableCellRenderer();
		dtcrRight.setHorizontalAlignment(JLabel.RIGHT);
		TableColumnModel tcm = totalTable.getColumnModel();

		// "금액"만 오른쪽 정렬, 나머지는 가운데 정렬
		totalTable.getColumn("날짜").setPreferredWidth(50);
		totalTable.getColumn("날짜").setCellRenderer(dtcrCenter);
		totalTable.getColumn("금        액").setPreferredWidth(230);
		totalTable.getColumn("금        액").setCellRenderer(dtcrRight);
		totalTable.getColumn("구분").setPreferredWidth(30);
		totalTable.getColumn("구분").setCellRenderer(dtcrCenter);
		totalTable.getColumn("비        고").setPreferredWidth(250);
		totalTable.getColumn("비        고").setCellRenderer(dtcrCenter);

		return scrollpane;
	}

	// 전체 조회 패널
	public JPanel totalPanel() {
		totalPanel = new JPanel();
		totalPanel.setBounds(0, 0, 800, 600);
		totalPanel.setBackground(Color.white);
		totalPanel.add(totalCheck());
		return totalPanel;
	}

	// 일별 조회 패널
	public JPanel dayPanel() {
		dayPanel = new JPanel();
		dayPanel.setBounds(100, 50, 1000, 600);
		dayPanel.setBackground(Color.white);
		dayPanel.add(dayCheck());
		return dayPanel;
	}

	// 월별 조회 패널
	public JPanel monthPanel() {
		monthPanel = new JPanel();
		monthPanel.setBounds(100, 50, 1000, 600);
		monthPanel.setBackground(Color.white);
		monthPanel.add(monthCheck());
		return monthPanel;
	}

	// 기간 조회 패널
	private Component selectPanel() {
		selectPanel = new JPanel();
		selectPanel.setBounds(100, 50, 1000, 600);
		selectPanel.setBackground(Color.white);
		selectPanel.add(selectCheck());
		return null;
	}

	//캘린더
	public JPanel jDatePickerEx() {
		JPanel j1 = new JPanel();
		// 현재 날짜를 가져옴
		LocalDate now = LocalDate.now();
		int year = now.getYear();// 년도 저장
		int month = now.getMonthValue();// 월 저장
		int day = now.getDayOfMonth();// 일 저장

		// SqlDateModel: 날짜 선택기는 선택한 날짜를 java.sql.Date 형식의 개체로 반환합니다
		// 날짜를 저장하고 관리하기 위해 UtilDateModel 객체를 생성합니다.
		UtilDateModel model = new UtilDateModel();
		// 초기 날짜 설정 Month는 +1반환 해서 결과 월이 나옴 5월을 표기하고 싶으면 4로넣어야함
		// 텍스트 필드에 표시된 날짜의 기본 형식은 사용자의 요구에 맞지 않을 수 있습니다. 이 경우
		// javax.swing.JFormattedTextField.AbstractFormatter 클래스를 확장
		model.setDate(year, month - 1, day);// 현재날짜를 표시
		model.setSelected(true); // 텍스트 필드에 보이기
		// 날짜를 선택할 수 있는 패널(JDatePanelImpl)을 생성하고, 앞서 생성한 model을 인자로 전달하여 연결합니다.
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		// 실제로 사용자에게 보여지는 날짜 선택 위젯(JDatePickerImpl)을 생성하고, 앞서 생성한 datePanel을 인자로 전달합니다.
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new Dateformet());
		j1.add(datePicker);

		SimpleDateFormat dateFormat = new SimpleDateFormat("yy/MM/dd");
		date = dateFormat.format(model.getValue());

		// 날짜가 변경될 때마다 호출되는 listener 추가
		model.addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {

				if ("value".equals(evt.getPropertyName()) && "value" != null) {
					date = dateFormat.format(model.getValue());

					datelist[num] = date;
					tabPanel.setSelectedIndex(1);

					if (datelist[0] != null) {

						if (dpc.search(datelist)) {
							System.out.println("검색 성공");
							datelist[0] = null;
						} else {
							JOptionPane.showMessageDialog(null, "선택날짜에 내용이 없습니다.", "실패", JOptionPane.ERROR_MESSAGE);
							System.out.println("검색 실패");
							datelist[0] = null;
						}

					}

				}

			}
		});
		return j1;

	}

}
