# java-automobile

[Schémas Logiciel](https://miro.com/welcomeonboard/PE5sjBC3W7MUSrV7R4fpbLQdyP0hT7scbKkO8q64iCDPbfiT27uTNgcswV27chpu)

### 1. Swing

- [Layouts](http://zetcode.com/javaswing/swinglayoutmanagement/)

### 2. Modules internes

**Le form builder :**

Pour créer rapidement un formulaire simple on peut utiliser le module `View.SwingModules.FormBuilder;`

Le builder est compatible avec tous les composants Swing tels que :
- `JComboBox` (Pour les select)
- `JButton`
- `JTextField`
- `JLabel`
- ...

L'objectif est d'instancier un des composants swing dans notre classe et de les ajouter dans le builder au fur et à mesure.
Le builder est composé au fur et à mesure pour inscrire des champs dans un layout grid bag à une colomne.
> Il est prévu plus tard de pouvoir choisir d'autres possibilté de layout avec plusieurs colomnes

On commence ainsi un builder de cette façon :

> Attention : il est conseiller de définir les composants swing en variables de classe
> pour pouvoir ensuite récupérer leurs valeurs dans le controleur.

```java
FormBuilder builder = new FormBuilder(false); // Ce boolean en param désactive la création automatique de labels
// On peut chainer les configurations
builder.add('NomLabel', new JLabel("Nom"))
       .add('Nom', new JTextField(20))
       .add('JButton', new JButton('button'))
       .disableListButton() // Désactive le boutons pour aller sur la vue liste d'une entité
       .disableSubmitButton() // Pas de submission de ce formulaire
       .disableBackButton() // Pas de retour en arrière;

// Ensuite pour récupérer le panel à attacher à la window
builder.getPanel();
```

### 3. Git

Merge vs rebase :

![Rebase vs merge](http://cdn.differencebetween.net/wp-content/uploads/2018/11/Difference-Between-Git-Rebase-and-Merge-.png)

Staging area &rarr; repo local &rarr; remote

![Remote / local](https://support.nesi.org.nz/hc/article_attachments/360004194235/Git_Diagram.svg)

### Commandes

```sh
# Bases
git status # Consulter l'état de la staging area
git diff # Consulter les modifs, ajouter --staged si les fichiers ont étés git add 
git add -A # Ajouter tous les fichiers modifiés et nouveaux dans le prochaint commit
git commit -m "" # Placer tous les fichiers ready (en vert) dans un commit
git remote -v # Voir les remotes distantes liés à notre remote locale
git log -10 # historique des commit (-10 = en voir que 10)

git fetch origin master
git checkout origin/master
git branch ma-branche
git checkout ma-branche
# Ou sinon tout d'un coup
git fetch origin master:ma-branche && git checkout ma-branche

# Pour mettre à jour :
(être bien checkout sur ma branche)
git fetch origin master
git rebase origin/master
# Equivalent à :
git pull  origin master --rebase

# Si vous travailler sur la même branche que quelqu'un
git pull --rebase # (récupère automatiquement et rebase la branche courrante)

# Quand on veut repartir de la bonne version en écrasant tout :
git fetch --all
git reset --hard origin/master

# Quand on est en conflit, une fois résolu via un IDE :
git add -A
git rebase --continue

# Commandes avancées

# reset seulement le README à la version sur origin/master
git checkout origin/master -- README.md
# Modifier l'historique jusqu'au 2 derniers commits
git rebase -i HEAD~2
# Ajouter un commit à son historique courante
git cherry-pick <hash-commit>

```
