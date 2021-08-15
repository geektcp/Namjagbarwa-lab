# master
deny push to branch master

# dev
allow push to branch dev

# branch protect
> 分支保护有下面5个选项：
#### 1. Require pull request reviews before merging

#### 2. Require branches to be up to date before merging
##### 2.1 Codacy/PR Quality ReviewRequired
```
扫描整个工程的代码，类似sonar，但是很慢，5分钟左右
```
##### 2.2 Page Build
##### 2.3 github/pages
 
#### 3.Require signed commits

#### 4.Require linear history
```
要求主线分支
```

#### 5.Include administrators
```
勾选5和1，或者勾选5和2.1可以限制任何人直接提交代码到master
单独勾选3也可以保护分支
单独勾选4或者单独勾选5不能保护分支，还是可以直接提交到master
```

# best resolve
```
勾选1和5，必须有其他用户review一次，而且必须是Approve类型的review
注意：分支保护限制下，提交者只能操作Comment类型的review，这个review会算进攻限度，但跟分支保护无关
```