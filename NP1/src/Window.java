import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Window extends JFrame {
	
	public Window() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

		this.setLocation(dim.width/2-this.getSize().width/2, (int) (dim.height/2-this.getSize().height/2 * 0.3) );
		 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLayout(new BorderLayout());
		JPanel MainPanel = new JPanel();
		
		add(MainPanel,BorderLayout.PAGE_START);
		
		MainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		MainPanel.setLayout(new BoxLayout(MainPanel, BoxLayout.PAGE_AXIS));
		
		JLabel lblSearch = new JLabel("Livro:");
		JTextField TxtPesquisa = new JTextField();
		JButton btnPesquisar = new JButton("Pesquisar");
		
		JPanel pHeader = new JPanel();
		pHeader.setLayout(new FlowLayout(FlowLayout.LEADING));
		MainPanel.add(pHeader,BorderLayout.PAGE_START);
		
		pHeader.add(Box.createHorizontalGlue());
		pHeader.add(lblSearch, BorderLayout.PAGE_START);
		pHeader.add(TxtPesquisa,BorderLayout.CENTER);
		pHeader.add(btnPesquisar, BorderLayout.LINE_END);
		pack();
	}

}
