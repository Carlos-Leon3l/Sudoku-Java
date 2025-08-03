package dio.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dio.model.Tabuleiro;
import dio.model.GameStatusEnum;
import dio.model.Spaces;

public class TabuleiroService {
    private static final int BOARD_LIMIT = 9;
    private static Tabuleiro tabuleiro;

    public TabuleiroService(Map<String,String> gameConfig){
        tabuleiro = new Tabuleiro(initTabuleiro(gameConfig));
    }

    public List<List<Spaces>> getSpaces(){
        return tabuleiro.getSpaces();
    }

    public boolean temErro(){
        return tabuleiro.temAlgumErro();
    }

    public GameStatusEnum getStatus(){
        return tabuleiro.statusDoJogo();
    }

    public boolean jogoFinalizado(){
        return tabuleiro.jogoFinalizado();
    }

    public void resetandoJogo(){
        tabuleiro.resetarJogo();
    }

    private List<List<Spaces>> initTabuleiro(final Map<String,String> gameConfig) {
        List<List<Spaces>> spaces = new ArrayList<>();

        for (int i = 0; i < BOARD_LIMIT; i++) {
            spaces.add(new ArrayList<>());
            for (int j = 0; j < BOARD_LIMIT; j++) {
                var positionsConfig = gameConfig.get("%s,%s".formatted(i, j));
                var expected = Integer.parseInt(positionsConfig.split(",")[0]);
                var fixed = Boolean.parseBoolean(positionsConfig.split(",")[1]);
                var currentSpace = new Spaces(expected, fixed);
                spaces.get(i).add(currentSpace);

            }
        }
        tabuleiro = new Tabuleiro(spaces);
        return spaces;
    }
}
