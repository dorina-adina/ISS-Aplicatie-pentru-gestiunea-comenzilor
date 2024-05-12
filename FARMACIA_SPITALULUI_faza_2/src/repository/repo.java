package repository;

import domain.*;

import java.util.ArrayList;
import java.util.List;

public class repo {
    private List<Sectie> sectii = new ArrayList<>();
//    private List<Comanda> comenzi = new ArrayList<>();
    private List<Produs> produse = new ArrayList<>();
    private List<Farmacist> farmacisti = new ArrayList<>();
    private List<Comanda> comandaProduse = new ArrayList<>();

//    private HashMap<Integer, String> farmacisti;
//    private HashMap<String, String> sectii;

//    public repo() {
//        farmacisti = new HashMap<>();
//        farmacisti.put(1234, "Popa Madalina");
//        farmacisti.put(5632, "Poptean Tudor");
//
//        sectii = new HashMap<>();
//        sectii.put("orto", "Ortopedie");
//        sectii.put("ger", "Geriatrie");
//    }
//
//    public boolean autentificareF(int id, String parola) {
//        return farmacisti.containsKey(id) && farmacisti.get(id).equals(parola);
//    }
//
//    public boolean autentificareS(String id, String parola) {
//        return sectii.containsKey(id) && sectii.get(id).equals(parola);
//    }

    public void adaugaSectie(Sectie sectie) throws DuplicateException{
        try {
            for (Sectie entityi : sectii) {
                if (entityi.getId_sectie() == sectie.getId_sectie()) {
                    throw new DuplicateException("Exista deja o sectie cu ID-ul respectiv!");
                }
            }
        }
        catch (DuplicateException e){
            System.out.println(e);
            return;
        }
        sectii.add(sectie);
    }

    public void adaugaProdus(Produs produs) throws DuplicateException{
        try {
            for (Produs entityi : produse) {
                if (entityi.getId_produs() == produs.getId_produs()) {
                    throw new DuplicateException("Exista deja un produs cu ID-ul respectiv!");
                }
            }
        }
        catch (DuplicateException e){
            System.out.println(e);
            return;
        }
        produse.add(produs);
    }

    public void adaugaFarmacist(Farmacist farmacist) throws DuplicateException{
        try {
            for (Farmacist entityi : farmacisti) {
                if (entityi.getId_farmacist() == farmacist.getId_farmacist()) {
                    throw new DuplicateException("Exista deja un farmacist cu ID-ul respectiv!");
                }
            }
        }
        catch (DuplicateException e){
            System.out.println(e);
            return;
        }
        farmacisti.add(farmacist);
    }


//    public void adaugaComanda(Comanda comanda) throws DuplicateException{
//        try {
//            for (Comanda entityi : comenzi) {
//                if (entityi.getId_comanda() == comanda.getId_comanda()) {
//                    throw new DuplicateException("Exista deja o comanda cu ID-ul respectiv");
//                }
//            }
//        }
//        catch (DuplicateException e){
//            System.out.println(e);
//            return;
//        }
//        comenzi.add(comanda);
//    }

    public void adaugaComandaProdus(Comanda comandaProdus) throws DuplicateException {
        try {
            for (Comanda entityi : comandaProduse) {
                if (entityi.getId_comanda() == comandaProdus.getId_comanda()) {
                    throw new DuplicateException("Exista deja o comanda cu ID-ul respectiv!");
                }
            }
        }
        catch (DuplicateException e){
            System.out.println(e);
            return;
        }
        try {
            int ok = 1;
            for(int i = 0; i < comandaProduse.size(); i++)
            {
                if((findProdus(comandaProdus.getProduse().get(i).getId_produs()) == null)){
                    ok = 0;
                    break;
                }
            }
            if(ok == 0)
                throw new RepositoryException("Produsul cu ID -ul dat nu exista!");
        } catch (RepositoryException e){
            System.out.println(e);
            return;
        }
        comandaProduse.add(comandaProdus);
    }


    public void updateComandaProdus(Comanda entityNew) throws RepositoryException {
        Comanda entityToUpdate = null;
        for (int i = 0; i < comandaProduse.size(); i++) {
            for(int j = 0; j < comandaProduse.get(i).getProduse().size(); j++) {
                if (comandaProduse.get(i).getProduse().get(j).getId_produs() == entityNew.getProduse().get(j).getId_produs()) {
                    entityToUpdate = entityNew;
                    remove(comandaProduse.get(i).getId_comanda(), comandaProduse.get(i).getProduse().get(j).getId_produs());
                    comandaProduse.add(entityToUpdate);
                    break;
                }
            }
        }
        try{
            if (entityToUpdate == null) {
                throw new RepositoryException("Comanda/Produsul cu ID-ul specificat nu exista!");
            }
        }
        catch(RepositoryException e){
            System.out.println(e);
            return;
        }
    }

    public void remove(int id_c, int id_p) throws RepositoryException {
        Comanda entityToRemove = null;
        for (int i = 0; i < comandaProduse.size(); i++) {
            for (int j = 0; j < comandaProduse.get(i).getProduse().size(); j++) {

                if (comandaProduse.get(i).getProduse().get(j).getId_produs() == id_p && comandaProduse.get(j).getId_comanda() == id_c) {
                    entityToRemove = comandaProduse.get(i);
                    comandaProduse.remove(entityToRemove);
                    break;
                }
            }
            try {
                if (entityToRemove == null) {
                    throw new RepositoryException("Comanda/Produsul cu ID-ul specificat nu exista!");
                }
            } catch (RepositoryException e) {
                System.out.println(e);
                return;
            }
        }
    }

    public List<Comanda> getComenziPeSectie(String sectie) {
        List<Comanda> comenziSectie = new ArrayList<>();
        for (Comanda comanda : comandaProduse) {
            if (comanda.getSectie().equals(sectie)) {
                comenziSectie.add(comanda);
            }
        }
        return comenziSectie;
    }

    public List<Comanda> getComenzi(){
        return comandaProduse;
    }

//    public List<Sectie> getSectii(){
//        return sectii;
//    }
//
//    public List<Farmacist> getFarmacisti(){
//        return farmacisti;
//    }

    public List<Produs> getProduse(){
        return produse;
    }

    public Comanda findComanda(int id){
        try{
            for (Comanda comanda : comandaProduse) {
                if (comanda.getId_comanda() == id) {
                    return comanda;
                }
            }
            throw new RepositoryException("Comanda cu ID-ul specificat nu exista!");}
        catch (RepositoryException e){
            System.out.println(e);
        }
        return null;
    }

    public Produs findProdus(int nume){
        try{
            for (Produs produs : produse) {
                if (produs.getId_produs() == nume) {
                    return (Produs) produs;
                }
            }
            throw new RepositoryException("Produsul cu ID-ul specificat nu exista!");}
        catch (RepositoryException e){
            System.out.println(e);
        }
        return null;
    }

}
