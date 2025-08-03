package dio;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import dio.util.TemplateTabuleiro;
import dio.model.Spaces;
import dio.model.Tabuleiro;

public class Sudoku {

    private static Scanner scanner = new Scanner(System.in);
    
    private static Tabuleiro tabuleiro;
    private static int BOARD_LIMIT = 9;

    public static void main(String[] args) throws Exception {
        final var positions = Stream.of(args).collect(Collectors.toMap(k -> k.split(";")[0], v -> v.split(";")[1]));

        var option = -1;
        while (true){
            System.out.println("Selecione uma das opções a seguir");
            System.out.println("1 - Iniciar um novo Jogo");
            System.out.println("2 - Colocar um novo número");
            System.out.println("3 - Remover um número");
            System.out.println("4 - Visualizar jogo atual");
            System.out.println("5 - Verificar status do jogo");
            System.out.println("6 - limpar jogo");
            System.out.println("7 - Finalizar jogo");
            System.out.println("8 - Sair");

            option = scanner.nextInt();

            switch (option){
                case 1 -> startGame(positions);
                case 2 -> inputNumber();
                case 3 -> removeNumber();
                case 4 -> showCurrentGame();
                case 5 -> showGameStatus();
                case 6 -> clearGame();
                case 7 -> finishGame();
                case 8 -> System.exit(0);
                default -> System.out.println("Opção inválida, selecione uma das opções do menu");
            }
        }
    }

    private static void startGame(final Map<String, String> positions) {
        if(nonNull(tabuleiro)){
            System.out.println("Jogo ja foi iniciado");
            return;
        } 

        List<List<Spaces>> spaces = new ArrayList<>();

        for (int i = 0; i < BOARD_LIMIT; i++) {
            spaces.add(new ArrayList<>());
            for (int j = 0; j < BOARD_LIMIT; j++) {
                var positionsConfig = positions.get("%s,%s".formatted(i, j));
                var expected = Integer.parseInt(positionsConfig.split(",")[0]);
                var fixed = Boolean.parseBoolean(positionsConfig.split(",")[1]);
                var currentSpace = new Spaces(expected, fixed);
                spaces.get(i).add(currentSpace);

            }
        }
        tabuleiro = new Tabuleiro(spaces);
        System.out.println("O jogo vai começar");
    }

    private static void inputNumber() {
        if(isNull(tabuleiro)){
            System.out.println("Jogo ainda não iniciado");
            return;
        } 

        System.out.println("Informe a coluna que deseja inserir");
        var coluna = runUntilGetValidNumber(0, 8);
        System.out.println("Informe a linha que deseja inserir");
        var linha = runUntilGetValidNumber(0, 8);

        System.err.printf("Informe o numero que vai entrar na posicao %s %s \n", coluna,linha);
        var value = runUntilGetValidNumber(1, 9);
        if(!tabuleiro.trocarValor(coluna, linha, value)){
            System.out.printf("A posicao %s %s tem um valor fixo ", coluna, linha);
        }
    }

    private static void removeNumber() {
        if(isNull(tabuleiro)){
            System.out.println("Jogo ainda não iniciado");
            return;
        } 

        System.out.println("Informe a coluna que deseja inserir");
        var coluna = runUntilGetValidNumber(0, 8);
        System.out.println("Informe a linha que deseja inserir");
        var linha = runUntilGetValidNumber(0, 8);

        if(!tabuleiro.limparValor(coluna, linha)){
            System.out.printf("A posicao %s %s tem um valor fixo ", coluna, linha);
        }
    }

    private static void showCurrentGame() {
        if(isNull(tabuleiro)){
            System.out.println("Jogo ainda não iniciado");
            return;
        }

        var args = new Object[81];
        var argsPos = 0;
        for (int i = 0; i < BOARD_LIMIT; i++) {
            for (var col : tabuleiro.getSpaces()) {
                args[argsPos ++] = " " + ((isNull(col.get(i).getActual())) ? " " : col.get(i).getActual());
            }
        }

        System.out.println("Seu jogo esta assim ");

        System.out.printf(TemplateTabuleiro.BOARDTEMPLATE + "\n", args);
    }

    private static void showGameStatus() {
        if(isNull(tabuleiro)){
            System.out.println("Jogo ainda não iniciado");
            return;
        }

        System.out.printf("o jogo atualmente se encontra no status %s", tabuleiro.statusDoJogo().getLabel());
        if(tabuleiro.temAlgumErro()){
            System.out.println("O jogo contem erros ");
        } else {
            System.out.println("O jogo nao contem erros ");
        }
    }    

    private static void clearGame() {
        if(isNull(tabuleiro)){
            System.out.println("Jogo ainda não iniciado");
            return;
        }

        System.out.println("Tem certeza que quer resetar o progresso ?");
        var confirmar = scanner.next();
        while (!confirmar.equalsIgnoreCase("sim") || !confirmar.equalsIgnoreCase("não")) {
            System.out.println("Informe 'sim' ou 'nâo'");
            confirmar = scanner.next();
            if (confirmar.equalsIgnoreCase("sim")) {
                tabuleiro.resetarJogo();
                break;
            }
             
        }

        
    }

    private static void finishGame() {
        if(isNull(tabuleiro)){
            System.out.println("Jogo ainda não iniciado");
            return;
        }
        if(tabuleiro.jogoFinalizado()){
            System.out.println("Parabens, você concluiu o sudoku ");
            showCurrentGame();
            tabuleiro = null;
        }
        else if(tabuleiro.temAlgumErro()){
            System.out.println("Seu jogo contem erros, verifique o tabuleiro");
        } else {
            System.out.println("você ainda precisa preencher algum espaço");
        }
    }
    private static int runUntilGetValidNumber(final int min, final int max){
        var current = scanner.nextInt();
        while (current < min  || current > max ) {
            System.out.printf("Informe um numero entre %s e %s", min, max );
            current = scanner.nextInt();

        }
        return current;
    }
}
