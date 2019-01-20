import java.util.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HamPanel extends JPanel implements ActionListener{
	private JButton hamButton;
	private JTextArea textArea;
	private JScrollPane textPane;
	public HamPanel(){
		this.hamButton = new JButton("Open Ham...");
		this.setLayout(new GridLayout(2, 1));
		this.textArea = new JTextArea(10, 10);
		this.textArea = new JTextArea(10, 10);
		this.textPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.setSize(200, 400);
		this.hamButton.addActionListener(this);
		this.add(hamButton);
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
		if(e.getSource()==hamButton){
			JFileChooser fc = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
			int value = fc.showOpenDialog(this);
			 if(value == JFileChooser.APPROVE_OPTION) {
			       System.out.println("You chose to open this file: " + fc.getSelectedFile().getName());
			       SpamUtils.setFileName(fc.getSelectedFile().getName(), "ham", fc.getSelectedFile());
			 }
			 /*
			 SpamUtils.doList(fc.getSelectedFile().getName());
			 ArrayList<Word> temp = SpamUtils.getBag(fc.getSelectedFile().getName());
			 for(Word x : temp){
				 textArea.append(x.getTerm() + " - " + x.getCount() + "\n");
				 //System.out.println(x.getTerm() + " - " + x.getCount());
			 }*/
		}
	}
}