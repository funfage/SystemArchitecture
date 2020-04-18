1. 首先创建备份的目录

   ![](imgs\Snipaste_2020-04-15_15-10-32.png)

2. 进入该目录,右键选择Git Bash Here

   ![](imgs\Snipaste_2020-04-15_15-12-39.png)

3. 输入命令: git --bare init 回车

   ![](imgs\Snipaste_2020-04-15_15-14-01.png)

   *有关git init和git --bare init的区别参考以下链接*
   [git init 和git –bare init 的具体区别？](http://blog.haohtml.com/archives/12265)

4. 进入需要备份的目录右键Git Bash Here

   ![](imgs\Snipaste_2020-04-15_15-21-46.png)

5. 输入命令: git remote add backup <url> 回车, 即可创建一个远程分支

   ![](imgs\Snipaste_2020-04-15_15-28-24.png)

6. 接着输入命令: git push backup master 回车, 即可将master分支推送到远程分支backup, 完成备份

   ![](imgs\Snipaste_2020-04-15_15-31-23.png)