package dio.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServicoNotificacao {
    
    private final Map<EventEnum, List<EventListener> > listener = new HashMap<>(){{
        put(EventEnum.CLEARSPACE, new ArrayList<>());
    }};

    public void subcriber(final EventEnum evenType, EventListener lstener){
        var selectedListener = listener.get(evenType);
        selectedListener.add(lstener);

    }

    public void notify(EventEnum evenType){
        listener.get(evenType).forEach(l -> l.update(evenType));
    }

}
