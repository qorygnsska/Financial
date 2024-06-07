package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.io.IOException;
import java.awt.image.BufferedImage;


public class MyPanel extends JPanel implements ActionListener {
	// final 기호를 사용해 상수로 활용, 이미지의 크기 및 초기 위치
	private final int width = 900;
	private final int height = 130;
	private final int start_x = 0;
	private final int start_y = -4;
	// 이미지 파일을 담기 위한 BufferedImage 객체 변수를 생성
	private BufferedImage image;
	// 타이머 객체 생성
	private Timer timer;
	private int x, y;

	
	public MyPanel() {
	
		setBackground(new Color(255, 255, 255));
		setPreferredSize(new Dimension(width, height));
		// 이미지 파일 객체 생성
		File input = new File("image/수정.png");
		try {
			// input에 가져온 이미지 파일을 버퍼 이미지에 저장
			image = ImageIO.read(input);
		} catch (IOException e) {
			e.printStackTrace();
		} // 이미지 읽기
		x = start_x;
		y = start_y;

		// 타이머 생성자 
		timer = new Timer(1, this);
		timer.start();
	}

	@Override
	// 그래픽을 출력하는 메서드
	public void paintComponent(Graphics g) {
		// 그래픽 객체를 paintComponent 메서드로 이동시킴
		super.paintComponent(g);

		// 이미지를 화면에 그리기
		g.drawImage(image, x, y, this);
	}

	// 이미지 동작 메서드
	@Override
	public void actionPerformed(ActionEvent e) {
		// 이벤트 발생 시 좌표를 해당 픽셀 만큼 이동
		x += 1;

		// 이미지 객체가 범위를 벗어난 초기 위치로 이동
		if (x > width) {
			x = start_x;
			y = start_y;
		}

		// 동작이 완료되면 repaint 메서드로 이미지를 갱신
		repaint();
	}
}
