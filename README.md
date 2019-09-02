# SWAGGER 不安全配置Demo

## Description

在生产环境打开Swagger时，存在安全问题，导致API泄漏。

## Detection

 - codesec

   特征：@EnableSwagger2。需要考虑业务是否配置了在生产环境关闭。
   
 - 黑盒
   扫描路径：sw/swagger-ui.html，swagger/swagger-ui.html，/swagger-ui.html等,(可能有变化，见配置)
 
 ## 参考
 demo fork from [https://github.com/moxingwang/swagger](https://github.com/moxingwang/swagger)