import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RightPanel extends JPanel implements ActionListener{
	private JButton generateButton;
	private JButton detectButton;
	private JTextField textField;
	private JLabel spamLabel;
	private SpamPanel spam;
	private HamPanel ham;
	private JLabel dictionaryLength;
	public RightPanel(SpamPanel spam, HamPanel ham){
		this.generateButton = new JButton("Generate Bag of Words");
		this.detectButton = new JButton("Detect");
		this.textField = new JTextField(9);
		this.spam = spam;
		this.ham = ham;
		this.spamLabel = new JLabel("SPAM?");
		this.dictionaryLength = new JLabel("DICTIONARY LENGTH: ");
		this.setLayout(new GridLayout(5, 1));
		this.setSize(200, 400);
		this.add(dictionaryLength);
		this.add(generateButton);
		this.add(textField);
		this.add(detectButton);
		this.add(spamLabel);
		this.generateButton.addActionListener(this);
		this.detectButton.addActionListener(this);
		this.setVisible(true);
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==generateButton){
			SpamUtils.doList(SpamUtils.getSpamFile());
			SpamUtils.doList(SpamUtils.getHamFile());
			ArrayList<Word> temp = SpamUtils.getBag(SpamUtils.getSpamFile());
			 for(Word x : temp){
				 spam.getTextArea().append(x.getTerm() + " - " + x.getCount() + "\n");
				 //System.out.println(x.getTerm() + " - " + x.getCount());
			 }
			 temp = SpamUtils.getBag(SpamUtils.getHamFile());
			 for(Word x : temp){
				 ham.getTextArea().append(x.getTerm() + " - " + x.getCount() + "\n");
				 //System.out.println(x.getTerm() + " - " + x.getCount());
			 }
			 dictionaryLength.setText(SpamUtils.countDictionary() + "");
			 //SpamUtils.makeDictionary();
		}
		else if(e.getSource()==detectButton){
			//spamLabel.setText(SpamUtils.detect(textField.getText()) + "");
			float label = SpamUtils.detect(textField.getText());
			if(label<0.5){
				spamLabel.setText("HAM");
			}
			else{
				spamLabel.setText("SPAM");
			}
		}
		
	}
}
