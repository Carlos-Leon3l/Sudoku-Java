package dio.UI.Custom.Buttons;

import javax.swing.JButton;
import java.awt.event.ActionListener;;

public class CheckGameStatusButton extends JButton{
    
    public CheckGameStatusButton(final ActionListener actionListener){
        this.setText("Verificar Jogo");
        this.addActionListener(actionListener);
        

        
    }
}
