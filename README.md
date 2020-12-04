# java-automobil

1. Git

```sh
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

# Ou sinon quand on veut repartir la bonne version en écrasant tout :
git fetch --all
git reset --hard origin/master

# Quand on est en conflit une fois résolu via un IDE
git add -A
git rebase --continue
```
