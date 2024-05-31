package View;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

// 잔고 색 변경 and 금액 컬럼 가운데 정렬
public class CustomTableCellRenderer extends DefaultTableCellRenderer {
    private int targetColumnIndex;

    public CustomTableCellRenderer(int targetColumnIndex) {
        this.targetColumnIndex = targetColumnIndex;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
        setHorizontalAlignment(SwingConstants.CENTER); // 렌더러의 가로정렬을 CENTER로
        if (column == targetColumnIndex && value instanceof String) {
        	
            String textValue = (String) value;
            if (textValue.startsWith("+")) {
                cellComponent.setForeground(java.awt.Color.red);
            } else if (textValue.startsWith("-")) {
                cellComponent.setForeground(java.awt.Color.blue);
            } else {
                cellComponent.setForeground(java.awt.Color.black);
            }
        } else {
            // 기본적으로 텍스트 색상을 검은색으로 설정
            cellComponent.setForeground(java.awt.Color.black);
        }

        return cellComponent;
    }
}
