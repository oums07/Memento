package Tags;

public class Title extends Tag {

    private int lvl = 1;

    public Title() {
        text = "Titre par défaut";
    }

    public Title(String text) {
        this.text = text;
    }


    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public String toHTML(){
        return  "<h"+ this.lvl +">" + text + "</h"+ this.lvl +">";
    }


    @Override
    public String toString() {
        return "Title{" +
                "text='" + text + '\'' +
                ", lvl=" + lvl +
                '}';
    }
    @Override
    public Tag copy() {
        Title copie = new Title(this.text);
        copie.setLvl(this.lvl);
        return copie;
    }

}
