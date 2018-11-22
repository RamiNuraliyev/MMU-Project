package com.hit.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Observable;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class CacheUnitView extends Observable implements View{

	JPanel container=new JPanel(new BorderLayout());
    JPanel request;
	JPanel panel2 = new JPanel();
	JTextArea l= new JTextArea();
	Font f2 =new Font("Arial", Font.BOLD,13);
	Font f =new Font("Segoe Script", Font.BOLD, 20);
	JTextArea cacheText= new JTextArea();
	int reqnum,dmnum,swapnum=0;
	JLabel label5 = new JLabel("Total Number Of DataModels swaps:"+swapnum);
	JLabel label3 = new JLabel("Total Number Of Request:"+reqnum);
	JLabel label4 = new JLabel("Total Number Of DataModels:"+dmnum);
	public CacheUnitView() {
		cacheText.setText(" ");
		
		cacheText.setFont(f);
		l.setFont(f2);
	}

	protected void createAndShowGUI() {
		JButton b1,b2;
		//Create and set up the window.
		JFrame frame = new JFrame("CacheUnitUI");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel=new JPanel(new FlowLayout());
		panel2.setLayout(new BoxLayout(panel2,BoxLayout.PAGE_AXIS));
		request=new JPanel();
		request.setLayout(new BoxLayout(request,BoxLayout.PAGE_AXIS));

		//add label
		JLabel label = new JLabel("capacity: 3");
		JLabel label2 = new JLabel("algorithm: LRU ");
		panel2.add(label);
		panel2.add(label2);
		panel2.add(label3);
		panel2.add(label4);
		panel2.add(label5);

		//Add buttons
		b1=new JButton("Load a Request");
		b2=new JButton("Show statistics");
		panel.add(b1);
		panel.add(b2);
		l.setBackground(new Color(255,228,181,200));
		cacheText.setBackground(new Color(200,240,230,140));
		request.add(new JLabel("Cache"));
		request.add(cacheText);
		request.add(new JLabel("Request"));
		cacheText.setText("Cache content");
		cacheText.setEditable(false);
		panel2.setVisible(false);
		container.add(panel, BorderLayout.NORTH);
		container.add(panel2, BorderLayout.EAST);
		container.add(request, BorderLayout.WEST);

		//Display the window.
		frame.add(container);
		b1.addActionListener(new Load());
		b2.addActionListener(new Statistics());
		frame.setSize(800,400);
		//frame.pack();
		frame.setVisible(true);	

	}
	public void start() {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});

	}
	public <T>void updateUIData(T t) {

		if(t!=null)
		if(((String)t).length()>3)
		{
			cacheText.removeAll();
			cacheText.setText((String) t);
		}
		//update swaps
		else
			swapnum=Integer.parseInt((String)t);
		dmnum=cacheText.getLineCount()-1;
		label5.setText("Total Number Of DataModels swaps:"+swapnum);
		label4.setText("Total Number Of DataModels:"+dmnum);
	}

public class Load implements ActionListener{
	@Override
	public void actionPerformed(ActionEvent arg0) {
		JFileChooser fc=new JFileChooser();
		fc.showOpenDialog(null);
		try {
			BufferedReader br=new BufferedReader(new FileReader(fc.getSelectedFile()));
			l.read(br, null);
			request.add(l);
			reqnum++;
			label3.setText("Total Number Of Request:"+reqnum);
			l.setEditable(false);
			//send to controller by notify!!!!!
			String req=(l.getText());
			setChanged();
			notifyObservers(req);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
public class Statistics implements ActionListener{
	public Statistics() {

	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		panel2.setVisible(true);
		label5.setText("Total Number Of DataModels swaps:"+swapnum);

	}

}

}
