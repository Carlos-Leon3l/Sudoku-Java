package dio.UI.Custom.Buttons;

import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ResetButton extends JButton{

     public ResetButton(final ActionListener actionListener){
        this.setText("Resetar Jogo");
        this.addActionListener(actionListener);
    
    }
}
