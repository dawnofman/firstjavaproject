//package editor.mcm.fhooe.at;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

//import DrawingPanel;

public class View implements DataObserver {

	Frame mFrame = null;
	DrawingPanel mPanel = null;
	Panel mButtonPanel = null;
	
	Panel mChoicePanel = null;
	TextField mTextField = null;
	CheckboxGroup mCBGroup = null;
	Button[] mActionButtons = null;
	Controller mController = null;

	public View(Controller _c) {
		mController = _c;
		mFrame = new Frame("Editor");
		mFrame.setSize(600, 425);
		mFrame.addWindowListener(mController);
		mPanel = new DrawingPanel();
		mPanel.setSize(600, 400);
		mPanel.setBackground(Color.white);
		mPanel.addComponentListener(mController);
		mPanel.addMouseListener(mController);
		mFrame.add(mPanel, BorderLayout.CENTER);
		mChoicePanel = new Panel();
		mFrame.add(mChoicePanel, BorderLayout.NORTH);
		mButtonPanel = new Panel();
		mTextField = new TextField(15);
//		mFrame.add(mButtonPanel, BorderLayout.SOUTH);
		setupButtons();
		mFrame.setVisible(true);
	}

	private void setupButtons() {
		mCBGroup = new CheckboxGroup();
		Checkbox rectCB = new Checkbox("Rectangle", mCBGroup, true);
		Checkbox commCB = new Checkbox("Comment", mCBGroup, true);
		Checkbox lineCB = new Checkbox("Line", mCBGroup, true);
		Checkbox textCB = new Checkbox("Text", mCBGroup, true);

		mCBGroup.setSelectedCheckbox(rectCB);
		mChoicePanel.add(rectCB);
		mChoicePanel.add(commCB);
		mChoicePanel.add(lineCB);
		mChoicePanel.add(textCB);
		mChoicePanel.add(mTextField);
		mTextField.addKeyListener(mController);
		
		rectCB.addItemListener(mController);
		commCB.addItemListener(mController);
		lineCB.addItemListener(mController);
		textCB.addItemListener(mController);
		mController.drawMode = "Rectangle";
		
		mActionButtons = new Button[10];
		mActionButtons[0] = new Button("Load");
		mActionButtons[1] = new Button("ZTF");
		mActionButtons[2] = new Button("+");
		mActionButtons[3] = new Button("-");
		mActionButtons[4] = new Button("U/N");
		mActionButtons[5] = new Button("D/S");
		mActionButtons[6] = new Button("L/W");
		mActionButtons[7] = new Button("R/E");
		mActionButtons[8] = new Button("Rot L");
		mActionButtons[9] = new Button("Rot R");
		for (int i = 0; i < mActionButtons.length; i++) {
			mActionButtons[i].addActionListener(mController);
			mActionButtons[i].setActionCommand(mActionButtons[i].getLabel());
			mButtonPanel.add(mActionButtons[i]);
		}
	}

	@Override
	public void update(BufferedImage _data) {
		mPanel.addFigure(_data);
	}
}