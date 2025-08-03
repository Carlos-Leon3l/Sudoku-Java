package dio.model;

import java.util.Collection;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class Tabuleiro {
    private final List<List<Spaces>> spaces;    

    public Tabuleiro(final List<List<Spaces>> spaces){
        this.spaces= spaces;
    }

    public List<List<Spaces>> getSpaces(){
        return spaces;
    }

    public GameStatusEnum statusDoJogo(){
        if(spaces.stream().flatMap(Collection::stream).noneMatch(s -> !s.isFixed() && nonNull(s.getActual()))){
            return GameStatusEnum.NAOINICIADO;
        }
        return spaces.stream().flatMap(Collection::stream).anyMatch(s -> isNull(s.getActual())) ? GameStatusEnum.INCOMPLETO : GameStatusEnum.COMPLETO;
    }
    

    public boolean temAlgumErro(){
        if(statusDoJogo() == GameStatusEnum.NAOINICIADO){
            return false;
        }

        return spaces.stream().flatMap(Collection::stream)
                              .anyMatch(s -> nonNull(s.getActual()) && !s.getActual().equals(s.getExpected()));
    }

    public boolean trocarValor(final int coluna , final int linha, final int valor ){
        var space = spaces.get(coluna).get(linha);
        if(space.isFixed()){
            return false;
        }
        space.setActual(valor);

        return true;
    }

    public boolean limparValor(final int coluna, final int linha){
        var space = spaces.get(coluna).get(linha);
        if(space.isFixed()){
            return false;
        }
        space.clearSpace();
        return true;

    }

    public void resetarJogo(){
        spaces.forEach(c -> c.forEach(Spaces::clearSpace));
    }

    public boolean jogoFinalizado(){
        return !temAlgumErro() && statusDoJogo().equals(GameStatusEnum.COMPLETO);

    }

    
}
