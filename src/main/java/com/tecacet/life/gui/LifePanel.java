package com.tecacet.life.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

import com.tecacet.life.BasicLife;
import com.tecacet.life.GenericLaws;
import com.tecacet.life.Life;

@SuppressWarnings("serial")
class ButtonPanel extends JPanel {

	public JButton addButton(String name) {
		JButton button = new JButton(name);
		add(button);
		return button;
	}
}

@SuppressWarnings("serial")
class FormField extends JPanel {
	private JLabel label;
	private JComponent component;

	public FormField(String label, JComponent comp) {
		this.label = new JLabel(label);
		component = comp;
		add(this.label);
		add(comp);
	}
}

@SuppressWarnings("serial")
public class LifePanel extends JPanel {

	private final World cellPanel;
	private final Life life;
	private final Timer timer;
	private final JComboBox<String> sizes;
	private final JComboBox<String> speeds;
	private final JTextField population;

	public LifePanel() {
		life = BasicLife.getALife();
		cellPanel = new World(life);
		sizes = new JComboBox<>(new String[] { "Small", "Medium", "Large" });
		speeds = new JComboBox<>(new String[] { "Slow", "Normal", "Fast" });
		population = new JTextField(7);
		population.setEditable(false);
		timer = new Timer(200, l -> {
			life.iterate();
			population.setText("" + life.getPopulation());
		});

		ButtonPanel buttonPanel = new ButtonPanel();
		buttonPanel.addButton("Start").addActionListener(e -> timer.start());
		buttonPanel.addButton("Stop").addActionListener(e -> {
			if (timer != null) {
				timer.stop();
			}
		});
		buttonPanel.addButton("Clear").addActionListener(e -> {
			cellPanel.clear();
			life.newGame();
		});

		JPanel controls = new JPanel();
		FormField size = new FormField("Size:", sizes);
		FormField pace = new FormField("Pace:", speeds);
		controls.add(size);
		controls.add(pace);

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(cellPanel);
		add(buttonPanel);
		add(new LawsOfNaturePanel((GenericLaws) life.getLawsOfNature()));
		add(controls);
		add(new FormField("Population", population));
	}

	private static void createAndShowGUI() {

		JFrame f = new JFrame("The Game of Life");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// JFrame.setDefaultLookAndFeelDecorated(true);
		f.getContentPane().add(new LifePanel());
		f.pack();
		f.setResizable(false);
		// f.setSize(500,500);
		f.setVisible(true);
	}

	public static void main(String[] argv) {
		javax.swing.SwingUtilities.invokeLater(() -> createAndShowGUI());
	}

}
