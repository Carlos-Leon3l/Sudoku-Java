package dio.model;

public class Spaces {
    private Integer actual;

    private final int expected;
    private final boolean fixed;

    
    public Integer getActual() {
        return actual;
    }


    public int getExpected() {
        return expected;
    }


    public boolean isFixed() {
        return fixed;
    }

    public void setActual(Integer actual) {
        if(fixed) return;
        this.actual = actual;
    }

    public void clearSpace(){
        setActual(null);
    }

    public Spaces(final int expected, final boolean fixed) {
        this.expected = expected;
        this.fixed = fixed;
        if(fixed){
            actual = expected; // para o espaco ser preenchido e não poder ser alterado 
        }
    }

    
}
