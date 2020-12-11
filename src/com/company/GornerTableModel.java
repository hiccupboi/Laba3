package com.company;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class GornerTableModel extends AbstractTableModel {
    private Double[] coefficients;
    private Double from;
    private Double to;
    private Double step;
    public GornerTableModel(Double from, Double to, Double step, Double[] coefficients) {
        this.from = from;
        this.to = to;
        this.step = step;
        this.coefficients = coefficients;
    }
    public Double getFrom() {
        return from;
    }
    public Double getTo() {
        return to;
    }
    public Double getStep() {
        return step;
    }
    @Override
    public int getColumnCount() {
        return 3;
    }
    @Override
    public int getRowCount() {
        return new Double(Math.ceil((to-from)/step)).intValue()+1;
    }
    @Override
    public Object getValueAt(int row, int col) {
// Вычислить значение X как НАЧАЛО_ОТРЕЗКА + ШАГ*НОМЕР_СТРОКИ
        double x = from + step*row;
        Double result;
        if (col==0) {
            return x;
        } else {
// Если запрашивается значение 2-го столбца, то это значение многочлена
            if (col == 1) {
                result = coefficients[0];
                for (int i = 0; i < coefficients.length - 1; i++) {
                    result = result * x + coefficients[i + 1];
                }
                return result;
            } else {
// 3-й столбец
                result = coefficients[0];
                for (int i = 0; i < coefficients.length - 1; i++) {
                    result = result * x + coefficients[i + 1];
                }
                double drob = result - result.intValue();
                double E = E = Math.pow (10,-3);
                if (Math. abs(drob) < E) {
                    return true;
                } else return false;
            }
        }
    }
    @Override
    public String getColumnName(int col) {
        switch (col) {
            case 0:
// Название 1-го столбца
                return "Значение X";
            case 1:
// Название 2-го столбца
                return "Значение многочлена";
            default:
// Название 3-го столбца
                return "Точное значение?";
        }
    }
    @Override
    public Class<?> getColumnClass(int col) {
        if (col!=2) {
            return Double.class;
        } else {
            return Boolean.class;
        }
    }
}