package dio.UI.Custom.Painel;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import dio.UI.Custom.input.NumberText;

import static java.awt.Color.BLACK;;

public class SudokuSector extends JPanel {

    public SudokuSector(final List<NumberText> textFields){
        var dimension = new Dimension(170, 170);
        this.setSize(dimension);
        this.setPreferredSize(dimension);
        this.setBorder(new LineBorder(BLACK, 2, true));
        this.setVisible(true);

        textFields.forEach(this::add);
        
    }
    
}
