package traitementTexte;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.FileSystems;
import java.nio.file.Path;

/* 
<code> this </code> est une classe qui va analyser le fichier .txt en recupérant chaques phrases (ligne) de se fichier
<code> this </code> va aussi permettre d'écrire le résultat de la traduction dans un fichier html ayant le même nom que le .txt
*/
public class Texte {
    private ArrayList<Phrase> listePhrases;
    protected static HashMap<String, String> alphabetVoyelle;
    protected static HashMap<String, Integer> syllabeToHiragana;


    public Texte(String chemin) throws Exception{
        listePhrases = new ArrayList<Phrase>();
        setAlphabetVoyelle();
        setSyllabeToHiragana();
        lectureFichier(chemin);
        ecritureFichier(chemin);

    }

    //Ouvre le fichier spécifié en argument et vas stocker les phrases dans la liste <code> listePhrase </code>
    public void lectureFichier(String chemin) throws Exception{
        Path path = FileSystems.getDefault().getPath(chemin); 
        Scanner in = new Scanner(path);
        String phrase;
        while (in.hasNext()) {
            phrase = in.nextLine().trim().replaceAll("(\\s)+", "$1");
            listePhrases.add(new Phrase(phrase));
        }
        in.close();

    }

    /* Créé et écrit les Hiraganas et Katagana dans le fichier avec le même nom que celui passer en argument mais avec une extension HTML
    @param chemin DESCIPTION correspond au chemin relatif au fichier principal.java du fichier .txt
    */
    public void ecritureFichier(String chemin) throws FileNotFoundException{
        String[] parts = chemin.split("\\.");
        String newFile = parts[0] + ".html";

        File file = new File(newFile);
        PrintWriter out1 = new PrintWriter(file);

        Boolean fin = false;
        int cpt = 0;
        int cptFin = 0;

        out1.print("<!DOCTYPE html>\n");
        out1.print("<html>\n");
        out1.print("   <head>\n");
        out1.print("       <meta http-equiv='Content-Type' content='text/html; charset=utf-8'/>\n");
        out1.print("       <title>TP 2</title>\n"); 
        out1.print("   </head>\n");
        out1.print("   <body>\n");
        out1.println("      <hr>");
        out1.println("          <table>");

        while(!fin){
            out1.println("              <tr>");

            for(int i = listePhrases.size()-1; i>=0; i--){
                
                if(listePhrases.get(i).getListeSyllabes().size() > cpt){
                    out1.print("                    <td>\n");
                    out1.print("                        " + listePhrases.get(i).getListeSyllabes().get(cpt).toString() + "\n");
                    out1.print("                    </td>\n");
                }else{
                    out1.print("                    <td>\n");
                    out1.print("                    </td>\n");
                    cptFin ++;
                }
            }
            cpt ++;
            fin = cptFin == listePhrases.size();
            cptFin = 0;

            out1.println("              </tr>");
        }
        out1.println("          </table>");
        out1.println("      </hr>");
        out1.print("   </body>\n");
        out1.print("</html>\n");

        out1.close();
    }

    public void setAlphabetVoyelle(){
        alphabetVoyelle = new HashMap<>();
        alphabetVoyelle.put("a", "voyelle");
        alphabetVoyelle.put("e", "voyelle");
        alphabetVoyelle.put("i", "voyelle");
        alphabetVoyelle.put("o", "voyelle");
        alphabetVoyelle.put("u", "voyelle");
        alphabetVoyelle.put("n'", "voyelle");
    }

    public void setSyllabeToHiragana(){
        syllabeToHiragana = new HashMap<>();
        syllabeToHiragana.put("a" , 12354);
        syllabeToHiragana.put("ka" , 12363);
        syllabeToHiragana.put("sa" , 12373);
        syllabeToHiragana.put("ta" , 12383);
        syllabeToHiragana.put("na" , 12394);
        syllabeToHiragana.put("ha" , 12399);
        syllabeToHiragana.put("ma" , 12414);
        syllabeToHiragana.put("ya" , 12420);
        syllabeToHiragana.put("ra" , 12425);
        syllabeToHiragana.put("wa" , 12431);
        syllabeToHiragana.put("ga" , 12364);
        syllabeToHiragana.put("za" , 12374);
        syllabeToHiragana.put("da" , 12384);
        syllabeToHiragana.put("ba" , 12400);
        syllabeToHiragana.put("pa" , 12401);
        syllabeToHiragana.put("i" , 12356);
        syllabeToHiragana.put("ki" , 12365);
        syllabeToHiragana.put("shi" , 12375);
        syllabeToHiragana.put("chi" , 12385);
        syllabeToHiragana.put("ni" , 12395);
        syllabeToHiragana.put("hi" , 12402);
        syllabeToHiragana.put("mi" , 12415);
        syllabeToHiragana.put("ri" , 12426);
        syllabeToHiragana.put("wi" , 12432);
        syllabeToHiragana.put("gi" , 12366);
        syllabeToHiragana.put("ji" , 12376);
        syllabeToHiragana.put("dji" , 12386);
        syllabeToHiragana.put("bi" , 12403);
        syllabeToHiragana.put("pi" , 12404);
        syllabeToHiragana.put("u" , 12358);
        syllabeToHiragana.put("ku" , 12367);
        syllabeToHiragana.put("su" , 12377);
        syllabeToHiragana.put("tsu" , 12388);
        syllabeToHiragana.put("nu" , 12396);
        syllabeToHiragana.put("fu" , 12405);
        syllabeToHiragana.put("mu" , 12416);
        syllabeToHiragana.put("yu" , 12422);
        syllabeToHiragana.put("ru" , 12427);
        syllabeToHiragana.put("gu" , 12368);
        syllabeToHiragana.put("zu" , 12378);
        syllabeToHiragana.put("dzu" , 12389);
        syllabeToHiragana.put("bu" , 12406);
        syllabeToHiragana.put("pu" , 12407);
        syllabeToHiragana.put("e" , 12360);
        syllabeToHiragana.put("ke" , 12369);
        syllabeToHiragana.put("se" , 12379);
        syllabeToHiragana.put("te" , 12390);
        syllabeToHiragana.put("ne" , 12397);
        syllabeToHiragana.put("he" , 12408);
        syllabeToHiragana.put("me" , 12417);
        syllabeToHiragana.put("re" , 12428);
        syllabeToHiragana.put("we" , 12433);
        syllabeToHiragana.put("ge" , 12370);
        syllabeToHiragana.put("ze" , 12380);
        syllabeToHiragana.put("de" , 12391);
        syllabeToHiragana.put("be" , 12409);
        syllabeToHiragana.put("pe" , 12410);
        syllabeToHiragana.put("o" , 12362);
        syllabeToHiragana.put("ko" , 12371);
        syllabeToHiragana.put("so" , 12381);
        syllabeToHiragana.put("to" , 12392);
        syllabeToHiragana.put("no" , 12398);
        syllabeToHiragana.put("ho" , 12411);
        syllabeToHiragana.put("mo" , 12418);
        syllabeToHiragana.put("yo" , 12424);
        syllabeToHiragana.put("ro" , 12429);
        syllabeToHiragana.put("wo" , 12434);
        syllabeToHiragana.put("go" , 12372);
        syllabeToHiragana.put("zo" , 12382);
        syllabeToHiragana.put("do" , 12393);
        syllabeToHiragana.put("bo" , 12412);
        syllabeToHiragana.put("po" , 12413);
        syllabeToHiragana.put("ya" , 12419);
        syllabeToHiragana.put("yu" , 12421);
        syllabeToHiragana.put("yo" , 12423);
        syllabeToHiragana.put("n'" , 12435);
    }
}
