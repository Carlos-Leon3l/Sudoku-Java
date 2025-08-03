package dio.UI.Custom.input;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import dio.model.Spaces;

import java.awt.Dimension;
import java.awt.Font;

import dio.Service.EventEnum;
import dio.Service.EventListener;


public class NumberText extends JTextField implements EventListener{
    private final Spaces spaces;

    public NumberText(final Spaces spaces){
        this.spaces = spaces;
        var dimension = new Dimension(50,50);
        this.setSize(dimension);
        this.setPreferredSize(dimension);
        this.setVisible(true);
        this.setFont(new Font("Arial", Font.PLAIN, 50));
        this.setHorizontalAlignment(CENTER);
        this.setDocument(new NumberTextLimit());
        this.setEnabled(!spaces.isFixed());
        if(this.spaces.isFixed()){
            this.setText(spaces.getActual().toString());
        }

        this.getDocument().addDocumentListener(new DocumentListener() {
            private void changeSpace() {
                if(getText().isEmpty()){
                    spaces.clearSpace();
                    return;
                } 
                spaces.setActual(Integer.parseInt(getText()));
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                changeSpace();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                changeSpace();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                spaces.setActual(Integer.parseInt(getText()));
            }});
    }

    @Override
    public void update(final EventEnum eventType) {
        if(eventType.equals(EventEnum.CLEARSPACE) && (this.isEnabled())){
            this.setText(" ");
        }
    }


}
