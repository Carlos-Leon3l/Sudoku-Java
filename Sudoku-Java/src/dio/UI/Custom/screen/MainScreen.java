package dio.UI.Custom.screen;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import dio.Service.EventEnum;
import dio.Service.ServicoNotificacao;
import dio.Service.TabuleiroService;
import dio.UI.Custom.Buttons.CheckGameStatusButton;
import dio.UI.Custom.Buttons.FinishGameButton;
import dio.UI.Custom.Buttons.ResetButton;
import dio.UI.Custom.Painel.MainPanel;
import dio.UI.Custom.Painel.SudokuSector;
import dio.UI.Custom.frame.MainFrame;
import dio.UI.Custom.input.NumberText;
import dio.model.Spaces;

public class MainScreen {
    private final static Dimension dimension = new Dimension(600,600);
    private final TabuleiroService tabuleiroService;
    private final ServicoNotificacao serviconotificacao;
    private JButton finishGameButton;
    private JButton CheckGameStatusButton;
    private JButton ResetGameButton;

    public MainScreen(final Map<String, String> gameConfig){
        this.tabuleiroService = new TabuleiroService(gameConfig);
        this.serviconotificacao = new ServicoNotificacao();
    }

    public void buildMainScreen(){
        JPanel mainPanel = new MainPanel(dimension);
        JFrame mainFrame = new MainFrame(dimension, mainPanel);

        for (int r = 0; r < 9; r+=3) {
            var endLinha = r + 2;
            for (int c = 0; c < 9; c+=3) {
                var endColuna = c + 2; 
                var spaces = getSpaceFromSector(tabuleiroService.getSpaces(),c, endColuna, r, endLinha);
                JPanel sector =  generateSection(spaces);
                mainPanel.add(sector);
            }
        }

        addResetButton(mainPanel);
        addShowGameStatusButton(mainPanel);
        addFinishGameButton(mainPanel);
        

        mainFrame.revalidate();
        mainFrame.repaint();
    }

    private List<Spaces> getSpaceFromSector(List<List<Spaces>> spaces, final int initCol, final int endCol, final int initRow, final int endRow){
        List<Spaces> spaceSector = new ArrayList<>();
        for (int r = initRow; r <= endRow; r++) {
            for (int c = initCol; c <= endCol; c++) {
                spaceSector.add(spaces.get(c).get(r));
            }
            
        }
        return spaceSector;
    }

    private JPanel generateSection(final List<Spaces> spaces){
        List<NumberText> fields = new ArrayList<>(spaces.stream().map(NumberText::new).toList());
        fields.forEach(t -> serviconotificacao.subcriber(EventEnum.CLEARSPACE, t));
        return new SudokuSector(fields);
    }

    private void addFinishGameButton(JPanel mainPanel) {
        finishGameButton = new FinishGameButton(e -> {
            if(tabuleiroService.jogoFinalizado()){
                JOptionPane.showMessageDialog(null, "Parbens, voce concluiu o Sudoku", "Fim de Jogo", 0);
                ResetGameButton.setEnabled(false);
                CheckGameStatusButton.setEnabled(false);
                finishGameButton.setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(null, "Seu jogo tem algum erro, tente novamente", "Fim de Jogo", 0);
            }
        });
        mainPanel.add(finishGameButton);

    }

    private void addShowGameStatusButton(JPanel mainPanel) {
        CheckGameStatusButton = new CheckGameStatusButton(e -> {
            var hasErrors = tabuleiroService.temErro();
            var gameStatus= tabuleiroService.getStatus();
            String message = switch(gameStatus){
                case COMPLETO -> "O jogo esta completo ";
                case INCOMPLETO -> "O jogo esta incompleto";
                case NAOINICIADO -> "O jogo não foi iniciado";
                };
                message += hasErrors ? " e contem erros " : " nao contem erros";
                JOptionPane.showMessageDialog(null, message);
        });
        //mainPanel.add(finishGameButton);
        mainPanel.add(CheckGameStatusButton);
    }

    private void addResetButton(JPanel mainPanel) {
        JButton ResetGameButton = new ResetButton(e -> {
            var dialogResult = JOptionPane.showConfirmDialog(null, "Desseja realmente reiniciar o jogo", "Limpar o Jogo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if(dialogResult == 0){
                tabuleiroService.resetandoJogo();
                serviconotificacao.notify(EventEnum.CLEARSPACE);
            }
        });
        mainPanel.add(ResetGameButton);
    }
}
