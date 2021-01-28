#!/bin/bash

echo "[ Sync production branch ]"
git add -A && git stash
git checkout master
git pull origin master --rebase
git push --force-with-lease
git rebase --onto prod
git checkout -
git stash pop
echo "[ Done ]"