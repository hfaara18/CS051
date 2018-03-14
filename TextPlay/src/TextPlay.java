import objectdraw.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * A program that creates a text with varied
 *  properties upon mouse click
 * 
 * @author Hussein Faara, CS051J
 *
 */
public class TextPlay extends WindowController 
		implements ActionListener, ChangeListener {
	// drop-down menus for font and color
	private JComboBox<String> fontMenu;
	private JComboBox<String> colorMenu;

	// slider for font size
	private JSlider fontSizeSlider;

	// button to remove text
	private JButton removeText;

	// a field to take player's text input
	private JTextField textField

	;
	private Text displayText;

	// font size label and its initial value
	private JLabel fontSizeLabel = new JLabel
			("Current Font Size : " + 14, JLabel.CENTER);
	private int fontSizeValue = 14;

	public void begin() {
		// new panel with 4 rows and a column
		JPanel userPanel = new JPanel(new GridLayout(4, 1));

		textField = new JTextField("Enter text to be displayed here");

		// remove text button and listener
		removeText = new JButton("Remove last text");
		removeText.addActionListener(this);

		// font drop-down menu and listener
		fontMenu = new JComboBox<String>();
		fontMenu.addItem("Courier");
		fontMenu.addItem("Sand");
		fontMenu.addItem("Zapfino");
		fontMenu.addItem("Times");
		fontMenu.addActionListener(this);

		// color drop-down menu and adds listener
		colorMenu = new JComboBox<String>();
		colorMenu.addItem("Black");
		colorMenu.addItem("Blue");
		colorMenu.addItem("Green");
		colorMenu.addItem("Red");
		colorMenu.addActionListener(this);

		// font-size slider and adds listener
		int minFontSize = 10;
		int maxFontSize = 48;
		int defaultFontSize = 14;
		fontSizeSlider = new JSlider(JSlider.HORIZONTAL,
				minFontSize, maxFontSize, defaultFontSize);
		fontSizeSlider.addChangeListener(this);

		// initializing text
		displayText = new Text("", 0, 0, canvas);

		// adding panels to a single row
		JPanel stylePanel = new JPanel(new GridLayout(1, 3));
		stylePanel.add(removeText);
		stylePanel.add(fontMenu);
		stylePanel.add(colorMenu);

		// adds program components to the south of the window
		userPanel.add(textField);
		userPanel.add(stylePanel);
		userPanel.add(fontSizeSlider);
		userPanel.add(fontSizeLabel);
		add(userPanel, BorderLayout.SOUTH);
		validate();

	}

	// runs upon the occurrence of an event
	public void actionPerformed(ActionEvent event) {
		if (displayText != null)
			if (event.getSource() == removeText) {
				removeText();
			} else if (event.getSource() == colorMenu) {
				setColorFromMenu();
			} else if (event.getSource() == fontMenu) {
				setFontFromMenu();
			}

	}

	// informs sliders about user changes to them
	public void stateChanged(ChangeEvent event) {
		fontSizeValue = fontSizeSlider.getValue();
		displayText.setFontSize(fontSizeSlider.getValue());

		fontSizeLabel.setText("Current Font Size : "
										+ fontSizeValue);
	}

	// Displays text when player clicks on canvas
	public void onMouseClick(Location point) {
		Object userText = textField.getText();
		displayText = new Text(userText, point.getX(),
				point.getY(), canvas);
		displayText.setFontSize(fontSizeValue);
		setColorFromMenu();
		setFontFromMenu();
	}

	// Sets the text to the player's selected font
	public void setFontFromMenu() {
		Object selectedFont = fontMenu.getSelectedItem();
		if (selectedFont.equals("Courier")) {
			displayText.setFont("COURIER");
		} else if (selectedFont.equals("Sand")) {
			displayText.setFont("SAND");
		} else if (selectedFont.equals("Zapfino")) {
			displayText.setFont("ZAPFINO");
		} else if (selectedFont.equals("Times")) {
			displayText.setFont("TIMES");
		}

	}

	// set the text color to the players selected color
	public void setColorFromMenu() {
		Object selectedColor = colorMenu.getSelectedItem();
		if (selectedColor.equals("Black")) {
			displayText.setColor(Color.BLACK);
		} else if (selectedColor.equals("Blue")) {
			displayText.setColor(Color.BLUE);
		} else if (selectedColor.equals("Green")) {
			displayText.setColor(Color.GREEN);
		} else if (selectedColor.equals("Red")) {
			displayText.setColor(Color.RED);
		}
	}

	// Removes last text from canvas
	public void removeText() {
		if (displayText != null) {
			displayText.removeFromCanvas();
			displayText = null;
		}
	}

}
