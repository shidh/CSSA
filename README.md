CSSA
====

The website for Chinese Students & Scholars Association in Muenchen 

 - User注册/登录
 - User前端交互页面 （用户浏览内容，报名活动，用户间SNS交互）
 - Admin后台管理页面（发布活动，报名管理，邮件通知）
 - MultiMedia（image，video，map）支持
 - MultiLang（CN，EN，DE）支持



For Devs：


I. System Requirements
  - Play Framework version 1.2.7: http://www.playframework.com/download
  - Java 7

II. Installation
  1. Follow the Play installation guide: http://www.playframework.com/documentation/1.2.7/install
  2. Clone this cssa repo to local
  3. If you use Eclipse: run 'play eclipsify cssa' and import the local git repo

III. Relevant Docs
  - Main Doc page: http://www.playframework.com/documentation/1.2.7/home
  - Java API: http://www.playframework.com/documentation/api/1.2.7/index.html
  - JPA: http://www.playframework.com/documentation/1.2.7/jpa


一些建模的想法：
 - Admin能增删改查Post
 - User能看Post，能报名，能评论
 - Post包含Postcontent和Comments
 - Postcontent包含Image，Map，Video
 - 可以报名的Post是Activity
 - Activity有confirmedList和WaitingList
  
 
 TODO：
 - 【前/后台】报名confirm list, waiting list, Event和User的多对多映射  --Allen
 - 【后台】新建Post图片，地址（google）获取（存数据库）
 - 【后台】User管理
 -   Wiki
 -   
