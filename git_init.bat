@echo off
git init
git add .
git commit -m "initial commit"
git branch -M main
git remote add origin https://github.com/vengateshm/AndroidPractice.git
git push -u origin main
pause