package repository;

import java.util.HashMap;

public class repoUser {
    private HashMap<String, String> parole;

    public repoUser() {
        parole = new HashMap<>();
        parole.put("1234", "parola1");
        parole.put("5632", "parola2");
        parole.put("orto", "ORTOPEDIE");
        parole.put("ger", "GERIATRIE");
    }

    public boolean verificaParola(String id, String parola) {
        if (parole.containsKey(id)) {
            String parolaStocata = parole.get(id);
            return parolaStocata.equals(parola);
        }
        return false;
    }
}
