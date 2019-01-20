import java.util.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SpamPanel extends JPanel implements ActionListener{
	private JButton spamButton;
	private JTextArea textArea;
	private JScrollPane textPane;
	public SpamPanel(){
		this.spamButton = new JButton("Open Spam...");
		this.setLayout(new GridLayout(2, 1));
		this.setTextArea(new JTextArea(10, 10));
		this.textPane = new JScrollPane(getTextArea(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.setSize(200, 400);
		this.spamButton.addActionListener(this);
		this.add(spamButton);
		this.add(textPane);
		this.setVisible(true);
	}
	
	public JTextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}

	public void actionPerformed(ActionEvent e){
		//Object x = e.getSource();
		if(e.getSource()==spamButton){
			JFileChooser fc = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
			int value = fc.showOpenDialog(this);
			 if(value == JFileChooser.APPROVE_OPTION) {
			       System.out.println("You chose to open this file: " + fc.getSelectedFile().getName());
			       SpamUtils.setFileName(fc.getSelectedFile().getName(), "spam", fc.getSelectedFile());
			 }
			 
		}
	}
}
