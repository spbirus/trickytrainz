# Software Engineering
1186 Software Engineering

## Git/Github use
It is a good idea before you start working on something to make sure your master is up to date using `git pull`.  Make sure you are on the master for that.  You can then switch to another branch or create a new one. Here is a link on how to use git bash https://dont-be-afraid-to-commit.readthedocs.io/en/latest/git/commandlinegit.html but below it is described as well.

To checkout a branch using git bash type `git checkout <branch name>`

To create a new branch type `git checkout -b <branch name>`

To update your branch with master
 1) `git checkout master`
 2) `git pull`
 3) `git checkout <branch name>`
 4) `git commit -m "<message>"`
 5) `git push`
 6) `git merge origin master` or you can just go on Github and do it manually

Committing
 ~ add all files `git add --all`
 ~ add specific files `git add <filename>`
 ~ commit with message `git commit -m "<message>"`
 ~ push to Github `git push <branch name>`
