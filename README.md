## TSS接口文档
> @author Yuchen Chiang  
> @date 2018-10-20 
 
> 学生管理接口，见src/main/java/com/silver/tss/web/StudentController  
> 统计接口，见src/main/java/com/silver/tss/web/StatisticsController  
> 题目管理接口，见src/main/java/com/silver/tss/web/TopicsController   
> EXCEL导入导出接口，见src/main/java/com/silver/tss/web/ExcelController

## 配置排坑
> Tomcat8 采用apt安装的方式安装  
> 修改/var/lib/tomcat8/conf/server.xml配置中的端口为80  
> 因为操作系统是Ubuntu16.04，默认关闭1023以下端口  
> 因此需要将/etc/default/tomcat8中的AUTHBIND=yes才能开启80端口访问

## BUG排坑
> Mybatis Generator会默认对text类型特殊处理，在生成mapper文件时会生成有相同id的单元，造成无法初始化bean的bug。虽然建表sql定义的字段是varchar但是由于尚未探明的原因，会被mybatis generator理解为text特殊处理。解决方案是删除多余代码。