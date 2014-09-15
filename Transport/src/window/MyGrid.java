package window;

import java.awt.GridBagConstraints;
import java.awt.Insets;

public class MyGrid extends GridBagConstraints {
 
	public MyGrid(int x, int y) {
		// TODO Auto-generated constructor stub
	    this.fill = GridBagConstraints.HORIZONTAL;
	    this.insets = new Insets(10,10,10,10);
	    this.gridx = x;
	    this.gridy = y;
	}
}
