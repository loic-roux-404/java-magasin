#!/bin/bash

echo "[ Sync production branch ]"
git add -A && git stash
git fetch origin master prod
git checkout origin/prod
git rebase origin/master
git push origin prod --force-with-lease
git checkout -
git stash pop
echo "[ Done ]"