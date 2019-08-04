package com.tecacet.life.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.tecacet.life.GenericLaws;

@SuppressWarnings("serial")
public class LawsOfNaturePanel extends JPanel {

	class IndexedButton extends JRadioButton {
		int index;

		public IndexedButton(int index) {
			this.index = index;
		}
	}

	class RadioRow extends JPanel implements ActionListener {

		boolean[] law;

		RadioRow(int number, boolean[] law) {
			this.law = law;
			buttons = new IndexedButton[number];
			for (int i = 0; i < buttons.length; i++) {
				addButton(i);
			}
		}

		void addButton(int i) {
			buttons[i] = new IndexedButton(i);
			buttons[i].addActionListener(this);
			if (law[i]) {
				buttons[i].setSelected(true);
			}
			add(buttons[i]);
		}

		public void actionPerformed(ActionEvent event) {
			IndexedButton button = (IndexedButton) event.getSource();
			law[button.index] = button.isSelected();
		}

	}

	IndexedButton[] buttons;
	GenericLaws laws;

	public LawsOfNaturePanel(GenericLaws laws) {
		this.laws = laws;
		JPanel birthLaw = new JPanel();
		birthLaw.add(new JLabel("Birth law:"));
		birthLaw.add(new RadioRow(8, laws.birthLaw));
		JPanel survivalLaw = new JPanel();
		survivalLaw.add(new JLabel("Survival law:"));
		survivalLaw.add(new RadioRow(8, laws.survivalLaw));
		add(birthLaw);
		add(survivalLaw);
	}

}
