package traitementTexte;

public class Syllabe {

    private String syllabe;
    private Boolean majuscule;
    private String unicodeSyllabe;

    public Syllabe(String syllabe) throws Exception{
        
        // Vérifi si le premier char de syllabe est une majuscule 
        this.majuscule = Character.isUpperCase(syllabe.charAt(0));

        this.syllabe = syllabe.toLowerCase();
        setUnicodeSyllabe();
        
    }

    public String getUnicodeSyllabe(){
        return unicodeSyllabe;
    }

    /* Vérifie si une syllabe contiens les charactères Ya Yo ou Yu en plus d'une consonne
    @param syllabe DESCRIPTION une syllabe d'un mot du fichier .txt
    @return DESCRIPTION retoune true si une syllabe contient ya yo ou yu en plus d'une autre consonne
    */
    public boolean containsYaYoYu(String syllabe) {
        String x = syllabe.substring(1).toLowerCase();
        return x.equals("ya") || x.equals("yo") || x.equals("yu");
    }

    /* gere le cas particulier des syllabes contenant ya yo yu en plus d'une autre consonne et donne une valeur à <code> unicodeSyllabe </code>
    @param syllabe DESCRIPTION sylllabe d'un mot du fichier .txt contenant ya yo ou yu en plus d'une autre consonne
    */
    public void gereYaYoYu(String syllabe) throws Exception {
        String firstPart;
        String secondPart;
        Integer codeUnicode1;
        Integer codeUnicode2;


        firstPart = syllabe.substring(0, 1) + "i";
        secondPart = syllabe.substring(1).toLowerCase();

        if(majuscule){
            codeUnicode1 = (Texte.syllabeToHiragana.get(firstPart) + 96);
            codeUnicode2 = (Texte.syllabeToHiragana.get(secondPart) + 96);
            
            this.unicodeSyllabe = "&#"+codeUnicode1+";"+"&#"+codeUnicode2+";";
        }
        else{
            

            codeUnicode1 = (Texte.syllabeToHiragana.get(firstPart));
            codeUnicode2 = (Texte.syllabeToHiragana.get(secondPart));
            
            this.unicodeSyllabe = "&#"+codeUnicode1+";"+"&#"+codeUnicode2+";";
        }
    }

    /*
    @throws lorsque <code> this.syllabe </code> n est pas une syllabe référencé dans <code> syllabeToHiragana </code>
    */
    public void setUnicodeSyllabe() throws Exception{

        if(containsYaYoYu(this.syllabe)){
            gereYaYoYu(syllabe);
        } 
        else if(Texte.syllabeToHiragana.containsKey(syllabe)){
            Integer codeUnicode;
            if(majuscule){
                codeUnicode = (Texte.syllabeToHiragana.get(syllabe) + 96);
                this.unicodeSyllabe = "&#"+codeUnicode.toString()+";";
            } else{
                codeUnicode = (Texte.syllabeToHiragana.get(syllabe));
                this.unicodeSyllabe = "&#"+codeUnicode+";";
            }
        } else{ 
            throw new Exception("la syllabe "+syllabe+" n'est pas connue");
        } 
    }
    
    @Override
    public String toString(){
        return unicodeSyllabe;
    }

}
