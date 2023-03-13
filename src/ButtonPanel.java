import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import javax.swing.*;

/**
 * Class ButtonPanel:  holds buttons to pick the current shape and to clear the shapes.
 * @author Barb Ericson
 */
public class ButtonPanel extends JPanel {
  private String[] shapeNames; // array of shapes that can be drawn
  private JButton clearButton;  // clear button
  private ShapeIComponent shapeComponent;
  private JTextField startAngleField;
  private JTextField arcAngleField;
  private JLabel startAngleLabel;
  private JLabel arcAngleLabel;
  private JComponent[] arcFields = new JComponent[4];
  
  /** Constructor
   * @param shapeComponent   the shape component
   */
  public ButtonPanel (ShapeIComponent shapeComponent) {
    this.shapeComponent = shapeComponent;
    shapeNames = new String[]{Shape.RECTANGLE, Shape.OVAL, Shape.ARC, Shape.LINE}; // modify this if you add more shapes
    
    // initialize the panel using the init() private helper method
    init();
  }
  
  /** Private helper method to initialize the panel */
  private void init() {
    JButton currButton = null;
    
    for (String name: shapeNames) {
      currButton = new JButton(name);
      add(currButton); // add is an INHERITED METHOD from a class in JPanel's inheritance hierarchy!
      currButton.addActionListener(e -> {
        if (shapeComponent != null) {
          shapeComponent.setShape(e.getActionCommand());

          for (JComponent field : arcFields) {
            if (name.equals("Arc")) {
              field.setVisible(true);
            } else {
              field.setVisible(false);
            }
          }
        }
      });
    }
    
    // create the clear button
    clearButton = new JButton("Clear");
    add(clearButton); // add is an INHERITED METHOD from a class in JPanel's inheritance hierarchy!
    clearButton.addActionListener(e -> {
      if (shapeComponent != null) {
        shapeComponent.clearShapes();
      }
    });

    startAngleField = new JTextField(Integer.toString(Arc.getStartAngle()), 3);
    arcAngleField = new JTextField(Integer.toString(Arc.getArcAngle()), 3);
    startAngleLabel = new JLabel("Start Angle: ");
    arcAngleLabel = new JLabel("Arc Angle: ");
    arcFields[0] = startAngleLabel;
    arcFields[1] = startAngleField;
    arcFields[2] = arcAngleLabel;
    arcFields[3] = arcAngleField;
    for (JComponent field : arcFields) {
      add(field);
      field.setVisible(false);
    }
    startAngleField.addActionListener(e -> {
      String text = startAngleField.getText();
      if (isNumeric(text)) {
        Arc.setStartAngle(Integer.parseInt(text));
      }
    });
    arcAngleField.addActionListener(e -> {
      String text = arcAngleField.getText();
      if (isNumeric(text)) {
        Arc.setArcAngle(Integer.parseInt(text));
      }
    });
  }

  private boolean isNumeric(String s) {
    for (int i = 0; i < s.length(); i++) {
      if (!Character.isDigit(s.charAt(i))) {
        return false;
      }
    }
    return true;
  }
}
