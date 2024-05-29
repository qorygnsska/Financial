package DatePickerEx;

import java.awt.BorderLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Controller.DatePickerController;
import View.ExportView;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class JDatePickerEx extends JPanel {
	int num = 0;
	String[] datelist = new String[2];
	DatePickerController dpc = new DatePickerController();

	public JDatePickerEx() {
	}

	public JPanel datePanel() {
		JPanel j1 = new JPanel();
		// 현재 날짜를 가져옴
		LocalDate now = LocalDate.now();
		int year = now.getYear();// 년도 저장
		int month = now.getMonthValue();// 월 저장
		int day = now.getDayOfMonth();// 일 저장
//		setLayout(new BorderLayout());

//		JPanel j1 = new JPanel();

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
//		jp1.add(datePicker);

//		add(j1,BorderLayout.CENTER);
//		j1.setBounds(650, 300, 400, 400);

		// 날짜가 변경될 때마다 호출되는 listener 추가
		model.addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if ("value".equals(evt.getPropertyName()) && "value" != null) {
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					String date = dateFormat.format(model.getValue());

					datelist[num] = date;

			

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



 class ImportJDatePickerEx extends JPanel {
	int num = 0;
	String[] datelist = new String[2];
	DatePickerController dpc = new DatePickerController();

	public ImportJDatePickerEx() {
	}

	public JPanel datePanel() {
		JPanel j1 = new JPanel();

		LocalDate now = LocalDate.now();
		int year = now.getYear();// 년도 저장
		int month = now.getMonthValue();// 월 저장
		int day = now.getDayOfMonth();// 일 저장

		UtilDateModel model = new UtilDateModel();
	
		model.setDate(year, month - 1, day);// 현재날짜를 표시
		model.setSelected(true); // 텍스트 필드에 보이기
		
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
	
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new Dateformet());

		j1.add(datePicker);

		model.addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if ("value".equals(evt.getPropertyName()) && "value" != null) {
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					String date = dateFormat.format(model.getValue());

					datelist[num] = date;				

					if (datelist[0] != null) {

						if (dpc.importsearch(datelist)) {
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







