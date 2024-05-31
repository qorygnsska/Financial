package View;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

// 잔고 색 테이블 클래스
public class CustomTableCellRenderer extends DefaultTableCellRenderer {
    private int targetColumnIndex;

    public CustomTableCellRenderer(int targetColumnIndex) {
        this.targetColumnIndex = targetColumnIndex;
    }

    @Override
    public java.awt.Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        java.awt.Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

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
