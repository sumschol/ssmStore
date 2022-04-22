### 搭建SSM项目的步骤

1)新建Maven工程
2)修改目录，修改pom.xml文件
3)添加SSM框架的所有依赖
4)拷贝jdbc.porperties:到resources目录下
5)新建applicationcontext_dao.xml文件，进行数据访问层的配置
6)新建applicationContext_service,xml文件，进行业务逻辑层的配置
7)新建springmvc.xml文件，配置springmvc的框架
8)新建SqlMapConfig.xml文件，进行分页插件的配置
9)使用逆向工程生成pojo和mapperl的文件
10)开发业务逻辑层，实现登录判断
11)开发控制器AdminAction,完成登录处理
12)改造页面，发送登录请求，验证登录

### solve日志：

#### ajax局部刷新问题:

传入局部标签页的html的MAV

#### thymeleaf不识别监听器:

打算在Web应用部署的时候就将typeList的数据传上去 但是有个问题：
在应用部署阶段 由于spring容器（ContextLoaderListener）也继承了ServletContextListener（也是一个监听器）
就不能保证在监听器方法前使用spring容器中的service 所以要手动获取配置文件中的bean
这样做后端确实得到了数据并进行了上传 但前端获取不到数据原因暂时未知
固选择了第二个方法：
最后改用在登陆成功时设置一个session



#### 上传图片衍生出的路径问题

使用ajax异步上传图片时，会先保存图片（transferTo方法或者FileUtils.copyInputStreamToFile方法）然后在点击提交时将图片的UUID添加到字段中，前端读取时直接读取字段中的pName(String)



#### ajax拿到的controller的MAV返回值如果只设置视图名称而缺少必要的object会走error()

w