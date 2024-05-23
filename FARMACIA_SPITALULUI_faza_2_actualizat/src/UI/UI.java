package UI;

import domain.*;
import repository.DuplicateException;
import repository.RepositoryException;
import repository.repo;
import repository.repoUser;

import java.util.*;

public class UI {

    private repo repo = new repo();
    private repoUser userRepository = new repoUser();


    public void login() throws Exception {
        boolean aut = false, aut1 = false;
        String str;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bun venit!");
        while(!aut1) {
            System.out.println("Alegeti - sectie sau farmacist: ");
            str = scanner.nextLine();

            if (Objects.equals(str, "sectie")) {
                System.out.println("Pentru a continua, autentificati-va.");
                aut1 = true;
                while (!aut) {
                    System.out.print("Dati ID: ");
                    String id = scanner.nextLine();

                    System.out.print("Dati Parola: ");
                    String parola = scanner.nextLine();

                    if (userRepository.verificaParola(id, parola)) {
                        System.out.println("Autentificare reusita!");
                        aut = true;
                        meniu();
                    } else {
                        System.out.println("Autentificare esuata! ID sau parola incorecte. Reincercati!");
                    }
                }
            } else {
                if (Objects.equals(str, "farmacist")) {
                    System.out.println("Pentru a continua, autentificati-va.");
                    aut1 = true;
                    while (!aut) {
                        System.out.print("Dati ID: ");
                        String id = scanner.nextLine();

                        System.out.print("Dati Parola: ");
                        String parola = scanner.nextLine();

                        if (userRepository.verificaParola(id, parola)) {
                            System.out.println("Autentificare reusita!");
                            aut = true;
                            meniu1();
                        } else {
                            System.out.println("Autentificare esuata! ID sau parola incorecte. Reincercati!");
                        }
                    }
                } else System.out.println("Trebuie sa alegeti intre cele doua: sectie/farmacist");
            }
        }
    }

    public void adaugam() throws Exception {
//        Sectie ortopedie = new Sectie("orto", "Ortopedie");
//        Sectie geriatrie = new Sectie("ger", "Geriatrie");
//        repo.adaugaSectie(ortopedie); repo.adaugaSectie(geriatrie);
//
//        Farmacist farmacist1 = new Farmacist(1234, "Popa Madalina");
//        Farmacist farmacist2 = new Farmacist(5632, "Poptean Tudor");
//        repo.adaugaFarmacist(farmacist1); repo.adaugaFarmacist(farmacist2);

        Produs medicament1 = new Produs(13423, "Paracetamol", "prospect_paracetamol", 10.00, 40);
        Produs medicament2 = new Produs(45678, "Aspenter", "prospect_aspenter", 20.00, 5);
        Produs medicament3 = new Produs(10009, "Sideral", "prospect_sideral", 90.00, 10);
        repo.adaugaProdus(medicament1); repo.adaugaProdus(medicament2); repo.adaugaProdus(medicament3);

        ArrayList<Produs> produse = new ArrayList<>();
        produse.add(medicament1); produse.add(medicament2);

        ArrayList<Integer> cantitati = new ArrayList<>();
        cantitati.add(3); cantitati.add(4);

        Comanda comanda1 = new Comanda(1, "ortopedie", "2024.03.12", produse, cantitati, "in asteptare");

//        comanda1.adaugaProdus(medicament1, 3);
//        comanda1.adaugaProdus(medicament2, 4);
        repo.adaugaComandaProdus(comanda1);


//        repo.adaugaComandaProdus(new ComandaProdus(comanda1, medicament1, 3));
//        repo.adaugaComandaProdus(new ComandaProdus(2, 45678, 7));
//        repo.adaugaComandaProdus(new ComandaProdus(2, 10009, 5));

    }

    public void UIadaugareComanda() throws Exception {
        int id_comanda, id_produs, cantitate, nr;
        String sectie;
        String data, status, str;
        String[] dataSplit;
        int a, l, z;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Dati datele comenzii: ");

        try {
            System.out.print("ID comanda: ");
            str = scanner.next();
            id_comanda = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            System.out.println("ID-ul trebuie sa fie un numar intreg!");
            return;
        }
        scanner.nextLine();

        System.out.print("Sectia: ");
        sectie = scanner.next();

        System.out.print("Data(aaaa-ll-zz) comenzii: ");
        data = scanner.next();
//        dataSplit = data.split(".");
//
//        try {
//            a = Integer.parseInt(dataSplit[0]);
//        }catch (NumberFormatException e){
//            System.out.println("Anul trebuie sa fie un numar intreg");
//            return;
//        }
//        try{
//            if(a != 2024)
//                throw new Exception("Anul sa fie 2024");
//        } catch (Exception e) {
//            System.out.println("Anul sa fie 2024");
//            return;
//        }
//
//        try {
//            l = Integer.parseInt(dataSplit[1]);
//        }catch (NumberFormatException e){
//            System.out.println("Luna trebuie sa fie un numar intreg");
//            return;
//        }
//        try{
//            if(l < 1 || l > 12)
//                throw new Exception("Atentie!");
//        } catch (Exception e) {
//            System.out.println("Atentie!");
//            return;
//        }
//        try {
//            z = Integer.parseInt(dataSplit[2]);
//        }catch (NumberFormatException e){
//            System.out.println("Ziua trebuie sa fie un numar intreg");
//            return;
//        }
//        try{
//            if((l == 1 || l == 3 || l == 5 || l == 7 || l == 8 || l == 10 || l == 12) && (z < 1 || z > 31))
//                throw new Exception("Atentie!");
//            else if((l == 4 || l == 6 || l == 9 || l == 11) && (z < 1 || z > 30))
//                throw new Exception("Atentie!");
//            else if((l == 2) && (z < 1 || z > 28))
//                throw new Exception("Atentie!");
//        } catch (Exception e) {
//            System.out.println("Atentie!");
//            return;
//        }

        status = "in asteptare";

        Comanda comanda = null;
//        comanda = new Comanda(id_comanda, sectie, data, produse, cantitati, status);

        try {
            System.out.println("Dati numarul de produse pe care doriti sa le adaugati: ");
            str = scanner.next();
            nr = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            System.out.println("Numarul trebuie sa fie un numar intreg!");
            return;
        }
        int k = 0;
        while(k < nr) {

            try {
                System.out.print("ID produs: ");
                str = scanner.next();
                id_produs = Integer.parseInt(str);
            } catch (NumberFormatException e) {
                System.out.println("ID-ul trebuie sa fie un numar intreg!");
                return;
            }

            try {
                if (repo.findProdus(id_produs) == null)
                    throw new RepositoryException("Produsul cu id-ul specificat nu exista!");
            } catch (RepositoryException e) {
                System.out.println(e.getMessage());
                return;
            }
            scanner.nextLine();

            try {
                System.out.print("Cantitate: ");
                str = scanner.next();
                cantitate = Integer.parseInt(str);
            } catch (NumberFormatException e) {
                System.out.println("Cantitatea trebuie sa fie un numar intreg!");
                return;
            }
            scanner.nextLine();

            comanda.adaugaProdus(repo.findProdus(id_produs), cantitate);
            k++;
        }

        repo.adaugaComandaProdus(comanda);
    }


//    public void UIadaugareComandaProdus() throws RepositoryException {
//        int cantitate;
//        Comanda comanda; Produs produs;
//        int id_comanda, id_produs;
//        String str, nume;
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.println("Adaugati la comanda: ");
//
//        try {
//            System.out.print("ID comanda: ");
//            str = scanner.next();
//            id_comanda = Integer.parseInt(str);
//        }catch (NumberFormatException e){
//            System.out.println("ID-ul trebuie sa fie un numar intreg");
//            return;
//        }
//
//        try{
//            if(repo.findComanda(id_comanda) == null)
//                throw new RepositoryException("Comanda cu id-ul specificat nu exista");
//        }catch (RepositoryException e){
//            System.out.println();
//            return;
//        } scanner.nextLine();
//
//        try {
//            System.out.print("ID produs: ");
//            str = scanner.next();
//            id_produs = Integer.parseInt(str);
//        }catch (NumberFormatException e){
//            System.out.println("ID-ul trebuie sa fie un numar intreg");
//            return;
//        }
//
//        try{
//            if(repo.findProdus(id_produs) == null)
//                throw new RepositoryException("Produsul cu id-ul specificat nu exista");
//        }catch (RepositoryException e){
//            System.out.println();
//            return;
//        } scanner.nextLine();
//
//        try {
//            System.out.print("Cantitate: ");
//            str = scanner.next();
//            cantitate = Integer.parseInt(str);
//        }catch (NumberFormatException e){
//            System.out.println("Cantitatea trebuie sa fie un numar intreg");
//            return;
//        }
//        scanner.nextLine();
//
//
//        ComandaProdus comandaProdus = null;
//        comandaProdus = new ComandaProdus(repo.findComanda(id_comanda), repo.findProdus(id_produs), cantitate);
//        repo.adaugaComandaProdus(comandaProdus);
//    }

    public void UIupdateComanda() throws RepositoryException {
        int id_comanda, id_produs, cantitateNoua;
        String str;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Dati id_ul comenzii si al produsului de modificat: ");

        try {
            System.out.print("ID comanda: ");
            str = scanner.next();
            id_comanda = Integer.parseInt(str);
        }catch (NumberFormatException e){
            System.out.println("ID-ul trebuie sa fie un numar intreg!");
            return;
        }

        try{
            if(repo.findComanda(id_comanda) == null)
                throw new RepositoryException("Comanda cu id-ul specificat nu exista!");
        }catch (RepositoryException e){
            System.out.println();
            return;
        } scanner.nextLine();

        try {
            System.out.print("ID produs: ");
            str = scanner.next();
            id_produs = Integer.parseInt(str);
        }catch (NumberFormatException e){
            System.out.println("ID-ul trebuie sa fie un numar intreg!");
            return;
        }

        try{
            if(repo.findProdus(id_produs) == null)
                throw new RepositoryException("Produsul cu id-ul specificat nu exista!");
        }catch (RepositoryException e){
            System.out.println();
            return;
        } scanner.nextLine();

        try {
            System.out.print("Dati noua cantitate de medicament: ");
            str = scanner.next();
            cantitateNoua = Integer.parseInt(str);
        }catch (NumberFormatException e){
            System.out.println("Cantitatea trebuie sa fie un numar intreg!");
            return;
        }
        Comanda comanda = repo.findComanda(id_comanda);
        ArrayList<Produs> produseComanda = comanda.getProduse();
        ArrayList<Integer> cantitatiComanda = comanda.getCantitati();

        for (int i = 0; i < produseComanda.size(); i++) {
            if (produseComanda.get(i).getId_produs() == id_produs) {
                cantitatiComanda.set(i, cantitateNoua);
            }
        }

        repo.updateComandaProdus(comanda);
    }

    public void UIgetAllComenzi(){
        Collection comenzi;
        comenzi = repo.getComenzi();
        for(Object comanda : comenzi){
            System.out.println(comanda);
        }
    }

    public void UIgetComenziPerSectie(){
        String str; Collection comenzi;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Dati sectia pentru care doriti sa vedeti comenzile: ");
        str = scanner.next();
        comenzi = repo.getComenziPeSectie(str);
        for(Object comanda : comenzi){
            System.out.println(comanda);
        }
    }

//    public void UIgetSectii(){
//        Collection sectii;
//        sectii = repo.getSectii();
//        for(Object comanda : sectii){
//            System.out.println(comanda);
//        }
//    }

    public void UIgetProduse(){
        Collection produse;
        produse = repo.getProduse();
        for(Object comanda : produse){
            System.out.println(comanda);
        }
    }

//    public void UIgetFarmacisti(){
//        Collection farmacisti;
//        farmacisti = repo.getSectii();
//        for(Object comanda : farmacisti){
//            System.out.println(comanda);
//        }
//    }

    public void meniu() throws Exception {
        adaugam();
        Scanner scanner = new Scanner(System.in);
        int choice; String str;
        do {
            try{
                System.out.println("Va rugam sa alegeti o optiune: ");

                System.out.println("1. Adaugati comanda.");
                System.out.println("2. Actualizati comanda.");
                System.out.println("3. Afisati comenzile pe sectie.");
                System.out.println("4. Afisati medicamentele.");
                System.out.println("0. Iesire");

                System.out.print("Optiune: ");

                str = scanner.next();
                choice = Integer.parseInt(str);}
            catch (NumberFormatException e){
                System.out.println("Optiunea trebuie sa fie un numar intreg!");
                return;
            }
            scanner.nextLine();

            switch (choice) {
                case 1:
                    UIadaugareComanda();
                    break;
                case 2:
                    UIupdateComanda();
                    break;
                case 3:
                    UIgetComenziPerSectie();
                    break;
                case 4:
                    UIgetProduse();
                    break;

                case 0:
                    System.out.println("Sfarsit!");
                    break;
                default:
                    System.out.println("Optiune invalida. Va rugam sa incercati din nou!");
            }

        } while (choice != 0);

        scanner.close();
    }

    public void meniu1() throws Exception {
        adaugam();
        Scanner scanner = new Scanner(System.in);
        int choice; String str;
        do {
            try{
                System.out.println("Va rugam sa alegeti o optiune: ");

                System.out.println("1. Afisati comenzile");
                System.out.println("2. Afisati comenzile pe sectie");
                System.out.println("3. Afisati medicamentele");
                System.out.println("0. Iesire");

                System.out.print("Optiune: ");

                str = scanner.next();
                choice = Integer.parseInt(str);}
            catch (NumberFormatException e){
                System.out.println("Optiunea trebuie sa fie un numar intreg!");
                return;
            }
            scanner.nextLine();

            switch (choice) {
                case 1:
                    UIgetAllComenzi();
                    break;
                case 2:
                    UIgetComenziPerSectie();
                    break;
                case 3:
                    UIgetProduse();
                    break;

                case 0:
                    System.out.println("Sfarsit!");
                    break;
                default:
                    System.out.println("Optiune invalida. Va rugam sa incercati din nou!");
            }

        } while (choice != 0);

        scanner.close();
    }
}
