package traitementTexte;

import java.util.ArrayList;

public class Phrase {
    private ArrayList<Syllabe> listeSyllabes;

    public Phrase(String phrase) throws Exception {
        listeSyllabes = new ArrayList<Syllabe>();
        setListeSyllabes(phrase);
    }

    public ArrayList<Syllabe> getListeSyllabes() {
        return listeSyllabes;
    }

    public void setListeSyllabes(String phrase) throws Exception {
        String[] mots = phrase.split(" ");

        for (String mot : mots) {
            wordToSyllabes(mot);
        }
    }

    /* Cherche les différentes syllabes présente dans un mot et les ajoutes à <code> listeSyllabe </code> 
    Arrête le programme si il ne trouve pas de syllabe dans un mot
    @param mot DESCRIPTION un mot du fichier.txt 
    @throw si le mot ne contient aucune syllabe de la hashmap <code> syllabeToHiragana </code>
    */
    public void wordToSyllabes(String mot) throws Exception {
        String[] lettres;
        String newSyllabe = "";
        Boolean syllabeFound = false;
        lettres = mot.split("");

        for (String lettre : lettres) {
            newSyllabe = newSyllabe.concat(lettre);

            if (Texte.alphabetVoyelle.containsKey(lettre.toLowerCase()) || newSyllabe.toLowerCase().equals("n'")) {
                listeSyllabes.add(new Syllabe(newSyllabe));
                newSyllabe = "";
                syllabeFound = true;
            }
        }

        if (!syllabeFound) {
            throw new Exception("Le mot " + mot + " n'est pas valide");
        }
    }

    @Override
    public String toString() {
        String res = "";

        for (Syllabe syllabe : listeSyllabes) {
            res = res.concat(syllabe.toString());
            res = res.concat(" ");
        }
        return res;

    }
}
