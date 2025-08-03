package dio.model;

public enum GameStatusEnum {

    NAOINICIADO("Não iniciado"),
    INCOMPLETO("Incompleto"),
    COMPLETO("Completo");

    private String label;
    

    public String getLabel() {
        return label;
    }

    GameStatusEnum(final String label){
        this.label = label;
    }
}
