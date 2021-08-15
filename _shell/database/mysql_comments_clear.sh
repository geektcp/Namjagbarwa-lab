#!/bin/sh
# 清理数据库中的垃圾评论

# 清除需要审核通过的大于1000字节的评论
#mysql -uw0rdpress -ppassw0rd#2015 -vvv -e "delete from w0rdpress.thy_comments WHERE comment_approved ='0' and length(comment_content)>1000 ;"

# 清除已经审核的大于1000字节的评论
#mysql -uw0rdpress -ppassw0rd#2015 -vvv -e "delete from w0rdpress.thy_comments WHERE comment_approved ='1' and length(comment_content)>1000 ;"

echo "垃圾评论中的长度大约1000字节的评论"
#mysql -uw0rdpress -ppassw0rd#2015 -vvv -e "delete from w0rdpress.thy_comments WHERE comment_approved ='spam' and length(comment_content) > 750 ;"

#mysql -uw0rdpress -ppassw0rd#2015 -vvv -e "show databases;"

echo "清除垃圾评论"
#mysql -uw0rdpress -ppassw0rd#2015 -vvv -e "delete FROM w0rdpress.thy_comments WHERE comment_approved = 'spam';"

echo "清除回收站评论"
#mysql -uw0rdpress -ppassw0rd#2015 -vvv -e "delete FROM w0rdpress.thy_comments WHERE comment_approved = 'trash';" 

echo "!!!!!!慎用!!!!!!清除所有待审核的评论"
mysql -uw0rdpress -ppassw0rd#2015 -vvv -e "delete FROM w0rdpress.thy_comments WHERE comment_approved = '0';" 


echo "测试数据库"
#mysql -uw0rdpress -ppassw0rd#2015 -e "show databases;"

echo "分离待审评论中所有带有链接的评论"
#mysql -uw0rdpress -ppassw0rd#2015 -vvv -e "update w0rdpress.thy_comments set comment_approved ='spam' WHERE comment_approved ='0' and comment_content regexp '(http://)+';"


echo "在带有链接的评论中，继续分离出带有正常关键字的链接"
#mysql -uw0rdpress -ppassw0rd#2015 -vvv -e "update w0rdpress.thy_comments set comment_approved ='spam'   WHERE comment_approved ='0' and comment_content regexp '(my blog|webpage|web-site|My site|homepage|website|my site)+'; "
#mysql -uw0rdpress -ppassw0rd#2015 -vvv -e "update w0rdpress.thy_comments set comment_approved ='spam'   WHERE comment_approved ='0' and comment_content regexp '(blog|webpage|web-site|homepage|website|site)+'; "




echo "在带有链接的评论中，分离出小于500字节的评论"
#mysql -uw0rdpress -ppassw0rd#2015 -vvv -e "update w0rdpress.thy_comments set comment_approved ='spam' WHERE comment_approved ='trash' and length(comment_content) < 500 ;"


echo "审批通过所有垃圾评论中的条目"
#mysql -uw0rdpress -ppassw0rd#2015 -vvv -e "update w0rdpress.thy_comments set comment_approved ='1' WHERE comment_approved ='spam';"



