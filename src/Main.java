import Tags.Image;
import Tags.Link;
import Tags.Paragraph;
import Tags.Title;

public class Main {

    private static void annulerDerniereEdition(Page page, PageHistory history) {
        PageMemento sauvegarde = history.undo();
        if (sauvegarde != null) {
            page.restore(sauvegarde);
            System.out.println("-> Restauration effectuée (titre : \"" + page.getTitle() + "\").");
        } else {
            System.out.println("-> Historique vide : aucune action à annuler.");
        }
    }

    public static void main(String[] args) {

        System.out.println("==================================================");
        System.out.println("   Démonstration CMS - Design Pattern Memento");
        System.out.println("==================================================\n");

        // Caretaker : gère la pile d'historique des états (PageMemento)
        PageHistory history = new PageHistory();

        // Originator : la page que l'on édite
        Page page = new Page("Index", "Florent Bourgeois");

        Title titrePrincipal = new Title("Bienvenue sur le CMS ENSISA");
        Paragraph intro = new Paragraph("Ceci est la version initiale de la page d'accueil.");
        page.addTag(titrePrincipal);
        page.addTag(intro);

        Site site = new Site("Mon site");
        site.addPage(page);

        System.out.println("---------- [0] État initial de la page ----------");
        System.out.println(page.toHTML());

        // --- Édition 1 : ajout d'un lien et d'une image ---
        System.out.println("\n---------- [1] Sauvegarde puis ajout d'une image et d'un lien ----------");
        history.add(page.save());

        Image logo = new Image(
                "Logo UHA",
                "https://e-formation.uha.fr/pluginfile.php/1/core_admin/logocompact/300x300/1663188003/UHA%20-%20Picto.png"
        );
        Link lien = new Link("Site de l'UHA", "https://www.uha.fr");
        page.addTag(logo);
        page.addTag(lien);
        System.out.println(page.toHTML());

        // --- Édition 2 : modification du titre de la page et mutation directe d'un Tag existant ---
        // Illustre l'intérêt de la copie profonde (Tag.copy()) dans PageMemento :
        // modifier titrePrincipal après save() n'altère pas les mementos déjà enregistrés.
        System.out.println("\n---------- [2] Sauvegarde puis modification du titre et d'un tag existant ----------");
        history.add(page.save());

        page.setTitle("Index - Version 2");
        titrePrincipal.setLvl(2);
        titrePrincipal.setText("Bienvenue sur le CMS (Titre modifié)");
        intro.setText("Texte d'introduction modifié à l'étape 2.");
        System.out.println(page.toHTML());

        // --- Édition 3 : suppression d'un tag et ajout accidentel ---
        System.out.println("\n---------- [3] Sauvegarde puis suppression d'un tag et ajout erroné ----------");
        history.add(page.save());

        page.removeTag(lien);
        page.addTag(new Paragraph("ERREUR : paragraphe temporaire à annuler !"));
        System.out.println(page.toHTML());

        // --- Annulations successives (Undo via Caretaker -> Memento -> Originator) ---
        System.out.println("\n==================================================");
        System.out.println("   Annulations successives (Undo)");
        System.out.println("==================================================");

        System.out.println("\n---------- [Undo 1] Retour avant l'édition 3 ----------");
        annulerDerniereEdition(page, history);
        System.out.println(page.toHTML());

        System.out.println("\n---------- [Undo 2] Retour avant l'édition 2 (vérifie la copie profonde) ----------");
        annulerDerniereEdition(page, history);
        System.out.println(page.toHTML());

        System.out.println("\n---------- [Undo 3] Retour à l'état initial [0] ----------");
        annulerDerniereEdition(page, history);
        System.out.println(page.toHTML());

        System.out.println("\n---------- [Undo 4] Tentative d'annulation supplémentaire (pile vide) ----------");
        annulerDerniereEdition(page, history);

        System.out.println("\n---------- Rendu final via Site.render(\"Index\") ----------");
        site.render("Index");
    }

}