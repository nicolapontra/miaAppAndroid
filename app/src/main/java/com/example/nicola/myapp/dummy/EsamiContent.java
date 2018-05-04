package com.example.nicola.myapp.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class EsamiContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<DummyEsame> ESAMI_LIST = new ArrayList<DummyEsame>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, DummyEsame> ESAMI_MAP = new HashMap<String, DummyEsame>();



    private static final int COUNT = 8;

    static {
        // Add some sample items.
        for (int i = 0; i < COUNT; i++) {
            addEsame(createDummyEsame(i));
        }
    }

    private static void addEsame(DummyEsame item) {
        ESAMI_LIST.add(item);
        ESAMI_MAP.put(item.id, item);
    }

    private static DummyEsame createDummyEsame(int position) {
        String[] esami_nomi = {"Sviluppo Mobile","Analisi","Reti di calcolatori", "PIU",
                "Fisica","Programmazione2","Programmazione1","SISAG"};

        String[] esami_dettagli = {
                "nome corso: Sviluppo Mobile SW, docente: Buono P., data superamento: ND, voto ND, CFU 9",
                "nome corso: Analisi matematica, docente: DAmbrosio, data superamento: 1 dicembre 2017, voto 26, CFU 9",
                "nome corso:Reti, docente: Novielli N., data superamento: 24 dicembre 2016, voto 30L, CFU 6",
                "nome corso:PIU, docente: De Carolis, data superamento: 24 novembre 2016, voto: 30L, CFU: 6",
        "nome corso:Fisica, docente: Fusco P., data superamento: 21 dicembre 2017, voto: 20, CFU: 6",
        "nome corso:Programmazione2, docente: Fanizzi, data superamento: 10 Gennaio 2017, voto: 27, CFU: 9",
        "nome corso:Programmazione1, docente: Di Mauro, data superamento: 08 dicembre 2016, voto: 23, CFU: 9",
        "nome corso:SISAG, docente: De Carolis, data superamento: 24 aprile 2018, voto: 20, CFU: 6"
        };

        return new DummyEsame(String.valueOf(position+1), esami_nomi[position], esami_dettagli[position]);
    }

  /*  private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }
*/
    /**
     * A dummy item representing a piece of content.
     * Classe che consente di descrivere gli elementi da visualizzare
     * in questo caso viene usato un id e un content per gli attributi mentre
     * details sarà il riferimento per il dettaglio dell'esame
     */
    public static class DummyEsame {
        public final String id;
        public final String content;
        public final String details;

        public DummyEsame(String id, String content, String details) {
            this.id = id;
            this.content = content;
            this.details = details;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
