package com.tecacet.life.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.UIManager;

import com.tecacet.life.Cell;
import com.tecacet.life.CellListener;
import com.tecacet.life.Coordinate;
import com.tecacet.life.Life;

@SuppressWarnings("serial")
public class World extends JPanel implements CellListener {

    static {
        UIManager.put("ToggleButton.select", Color.green);
    }

    class CoordinateView extends JToggleButton {

        private Coordinate coord;

        public CoordinateView(int x, int y) {
            super();

            coord = new Coordinate(x, y);

            addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (isSelected()) {
                        Cell cell = life.addCell(coord);
                        cell.addCellListener(listener);
                    } else {
                        life.removeCell(coord);
                    }
                }

            });
        }

        public void die() {
            setSelected(false);

        }
    }

    private CoordinateView[][] grid;
    private int size = 50;
    private Life life;
    private CellListener listener;
    private Logger log = Logger.getLogger(this.getClass().getName());

    public World(Life f) {

        life = f;
        listener = this;
        grid = new CoordinateView[size][size];
        setLayout(new GridLayout(size, size));
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = new CoordinateView(i, j);
                // TODO set button size relative to grid size
                int buttonSize = 500 / size;
                grid[i][j].setPreferredSize(new Dimension(buttonSize, buttonSize));
                add(grid[i][j]);
            }
        }
    }

    void clear() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j].die();
            }
        }
    }

    @Override
    public synchronized void died(Coordinate coord) {
        log.fine("Received death event at " + coord.getX() + "," + coord.getY());
        if (coord.getX() >= 0 && coord.getX() < size && coord.getY() >= 0 && coord.getY() < size) {
            grid[coord.getX()][coord.getY()].die();
        }
    }

    @Override
    public void born(Cell cell) {
        cell.addCellListener(this);
        Coordinate coord = cell.getCoordinate();
        log.fine(("Received cell born event"));
        if (coord.getX() >= 0 && coord.getX() < size && coord.getY() >= 0 && coord.getY() < size) {
            grid[coord.getX()][coord.getY()].setSelected(true);
        }
    }

    @Override
    public void done() {
        // TODO Auto-generated method stub

    }
}
