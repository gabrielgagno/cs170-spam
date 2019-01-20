import java.util.*;
import javax.swing.*;
import java.awt.*;

public class SpamFrame extends JFrame{
	private SpamPanel panelOne;
	private HamPanel panelTwo;
	private RightPanel panelThree;
	public SpamFrame(String title){
		this.setTitle(title);
		this.setLayout(new FlowLayout());
		this.setSize(600, 400);
		this.panelOne = new SpamPanel();
		this.panelTwo = new HamPanel();
		this.panelThree = new RightPanel(panelOne, panelTwo);
		this.add(panelOne);
		this.add(panelTwo);
		this.add(panelThree);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}