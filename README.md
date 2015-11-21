# shoppingGMS
JDBC上手设计项目 
Email:SuperHooKM@gmail.com

概要需求：
一、编写目的
         此需求规格说明书对《商超购物管理系统》项目做了全面细致的用户需求分析，明确所要开发的软件应具有的功能、性能与界面，使开发人员能清楚地明白需求，并在此基础上进一步提出概要设计说明书和完成后续设计与开发工作。
       本说明书定义商超购物管理系统的详细需求，明确了其功能内容、开发途径，是整个软件开发的依据，它对以后阶段的工作起指导作用。本文也是项目完成后系统验收的依据。
二、项目背景
       商超购物管理系统具有商品管理、前台收银、商品库存等功能，本项目使用java作为开发语言，java控制台显示界面，oracle数据库存储数据，可以有效地锻炼和加强学生运用java、oracle数据库及基本sql编程开发的能力。
本项目适用于有java、基本sql基础的学生进行实战训练。
三、设计内容
    本项目的目标是完成商超购物管理系统，主要包括：具有商品管理、前台收银、商品库存等功能。
    1）商品维护
      （1）商品维护菜单的显示
      商品维护菜单的显示，输入数字进入相应操作界面，输入0返回主菜单
     （2）商品添加
      输入商品价格应为大于0的实数，商品数量应为大于0的整数 ，否则显示输入错误。输入y继续进行添加操作，输入n返回商品维护菜单。
     （3）商品的更改
      输入要更改的商品名，选择要更改的项目，整个过程是循环可重复的，如商品添加。
     （4）商品的删除
     （5）商品列表显示
          显示商品的名称、价格、数量、备注等
     （6）商品查询 
        按照子菜单选择 “1、按照商品数量升序查询”、 “2、按商品价格升序查询”、“3、输入关键字查询商品”等查询方式。
    2）前台收银
     (1)前台登陆 
     选择登录系统，输入用户名和密码，校验正确进入系统；三次登陆机会，三次输入都错误退出程序 。
    （2）商品录入，录入名称，录入数量，计算出价格和汇总价格； 
      商品收银，输入钱，显示找零，确认后商品收银成功，商品库存数量减。
    3）商品管理
     （1）列出当日卖出商品列表。
    （2）售货员管理，售货员账号的增删改，参考商品的增删改。
