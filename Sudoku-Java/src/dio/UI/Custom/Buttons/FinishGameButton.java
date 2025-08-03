package dio.UI.Custom.Buttons;

import java.awt.event.ActionListener;

import javax.swing.JButton;

public class FinishGameButton extends JButton{

    public FinishGameButton(final ActionListener actionListener){
        this.setText("Finalizar jogo");
        this.addActionListener(actionListener);
    
    }
}
