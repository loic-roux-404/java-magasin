# java-automobil

1. Git

Merge vs rebase :

![Rebase vs merge](http://cdn.differencebetween.net/wp-content/uploads/2018/11/Difference-Between-Git-Rebase-and-Merge-.png)

Staging area &rarr; repo local &rarr; remote

![Remote / local](https://support.nesi.org.nz/hc/article_attachments/360004194235/Git_Diagram.svg)

### Commandes

```sh
# Bases
git status # Consulter l'état de la staging area
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
