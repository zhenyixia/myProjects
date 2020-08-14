## 一、下载安装node.js

下载地址：http://nodejs.cn/

选择.msi文件，直接点击 安装。可行选择安装c盘或d盘，根据情况。

重新打开cmd窗口，执行node -v 命令，出现：

C:\Users\Administrator>node -v
v14.6.0

说明安装成功



## 二、修改npm镜像

由于在国内使用npm是非常慢的，所以在这里我们推荐使用淘宝npm镜像，使用

**淘宝的cnpm命令管理工具可以代替默认的npm管理工具**：

*npm install -g cnpm --registry=https://registry.npm.taobao.org*

该安装cnpm命令,不会改变npm的源

C:\Users\Administrator>npm install -g cnpm --registry=https://registry.npm.taobao.org
npm WARN deprecated request@2.88.2: request has been deprecated, see https://github.com/request/request/issues/3142
C:\Users\Administrator\AppData\Roaming\npm\cnpm -> C:\Users\Administrator\AppData\Roaming\npm\node_modules\cnpm\bin\cnpm

+cnpm@6.1.1
added 684 packages from 964 contributors in 23.157s

如上面表示设置成功

***npm相关介绍可详见附注***

## 三、安装vue脚手架

淘宝镜像安装成功之后，我们就可以全局使用vue-cli脚手架，

输入命令：cnpm install --global vue-cli  回车；验证是否安装成功，

在命令输入vue，出来vue的信息，及说明安装成功；

![image-20200726115256372](D:\all-dev-learning\github-repository\local-markdown-source\搭建vue环境001.jpg)

## 四、设置缓存及npm地址

执行命令：

npm config set prefix "D:\server\nodejs\node_global"  // 默认下载路径
npm config set cache "D:\server\nodejs\node_cache"  //默认缓存路径

查看npm配置：

npm config list / ls //查看基本配置

npm config list / ls -l //查看所有配置



## 五、搭建一个vue项目

项目一般不建在C盘

所以到D盘创建目录与工程：

```
D:\all-dev-learning\github-repository>vue init webpack web001-vue-project
```

其中web001-vue-project是文件夹

上面命令回车后，一直回车，默认选yes直到出现是否安装vue-router 仍然选择yes

再后面根据情况选择，如果出现一些组件或模板安装失败，也不要紧，先进入web001-vue-project目录

执行命令 cnpm install 出现如下，说明安装成功

![image-20200726143314419](D:\all-dev-learning\github-repository\local-markdown-source\搭建vue环境003.jpg)

在命令行输入：npm run dev 回车运行项目

## 六、去除eslint校验

由于初始化项目时选择了eslint的校验，这是一种强校验，对语法要求严格。导致工程启动不了，需要去除eslint校验：

如下：

在项目中找到build----->webpack.base.conf.js文件

注释掉如下代码即可：

```js
const createLintingRule = () => ({
  // test: /\.(js|vue)$/,
  // loader: 'eslint-loader',
  // enforce: 'pre',
  // include: [resolve('src'), resolve('test')],
  // options: {
  //   formatter: require('eslint-friendly-formatter'),
  //   emitWarning: !config.dev.showEslintErrorsInOverlay
  // }
})

```

然后运行npm run dev 即可正常启动项目

```bash
D:\all-dev-learning\github-repository\web001-vue-project> npm run dev

> y@1.0.0 dev D:\all-dev-learning\github-repository\web001-vue-project
> webpack-dev-server --inline --progress --config build/webpack.dev.conf.js

 13% building modules 33/37 modules 4 active ...project\src\components\HelloWorld.vue{ parser: "babylon" } is deprecated; we now treat it as { parser: "babel" }.
 95% emitting

 DONE  Compiled successfully in 3597ms                                                                                                          下午2:51:53
 I  Your application is running here: http://localhost:8082
```



## 七、vue gitignore 设置

如下为常用忽略：

```
//.gitignore
 
.DS_Store
node_modules
/dist
 
# local env files
.env.local
.env.*.local
 
# Log files
npm-debug.log*
yarn-debug.log*
yarn-error.log*
 
# Editor directories and files
.idea
.vscode
*.suo
*.ntvs*
*.njsproj
*.sln
*.sw?
```



## 八、vue-cli4.0创建工程

以上是vue-cli2.0的脚手架搭建方式，现在已经升级到到4.0了 ,项目搭建方式也就更简单便捷了， 输入命令： 
npm install -g @vue/cli ，下载脚手架工具，下载成功之后 输入命令：vue create <Project Name> 文件夹名称，

然后就是选择配置，第一个default是默认配置，第二个是手动配置，手动配置就要看自己的项目需要，配置需要的文件（不过一般我为了方便，都会选择默认配置，哈哈），然后就等着创建文件夹；

文件创建成功之后，直接输入命令： npm run serve,就可以运行项目啦！







## 附注：

### 1，npm是什么？

npm,全称【node package management】，是nodejs内置的软件包管理器。毫无疑问，**npm是用来管理软件包的**。

它是世界上最大的软件注册表，每星期大约有**30亿**次的下载量，包含超过**600000**个包(包)(即，代码模块)。来自各大洲的开源软件开发者使用NPM互相分享和借鉴.包的结构使您能够轻松跟踪依赖项和版本。

**npm由三大独立部分组成：**

1. 网站：开发者查找包（package）、设置参数以及管理 npm 使用体验的主要途径，网址为：[https://www.npmjs.com/](https://links.jianshu.com/go?to=https%3A%2F%2Fwww.npmjs.com%2F)

2. 注册表：是一个巨大的数据库，保存了每个包的基本信息。

3. 命令行工具：开发者与npm包打交道的工具。

**npm的使用方法**

要使用npm这个软件包管理工具，最常见的方法就是在电脑上安装nodejs。

因为nodejs软件内置了npm，所以windows系统在安装nodejs后，打开cmd即可使用npm下载资源 。

安装nodejs后，打开npm官网：[https://www.npmjs.com/](https://www.npmjs.com)，就可以在输入框中查找你所需要的软件包信息。

![image-20200726122324989](D:\all-dev-learning\github-repository\local-markdown-source\搭建vue环境002.jpg)      

复制安装命令，到cmd即可安装使用，跟maven相似，很方便

**修改缓存与仓库**

上面命令安装后，会安装默认仓库，一般在C盘，由于依赖模板很大就像maven仓库一样。所以要修改下缓存与仓库：

npm config set prefix "D:\server\nodejs\node_global"
npm config set cache "D:\server\nodejs\node_cache"

### 2，关于vue-cli，即vue脚手架

vue-cli就是Vue的脚手架工具，和我们工地看到的脚手架真是那么一回事，它帮助我们搭建基本的开发环境，好比架子搭建好了，利用它可以搞定目录结构，本地调试，单元测试，热加载及代码部署等。



### 3，webpack是什么？

>Webpack 是一个开源的前端打包工具。Webpack 提供了前端开发缺乏的模块化开发方式，将各种静态资源视为模块，并从它生成优化过的代码。要使用Webpack 前须先安装Node.js。

Webpack 可以从终端、或是更改 webpack.config.js 来设置各项功能。

webpack 的主要目标是将 JavaScript 文件打包在一起，打包后的文件用于在浏览器中使用，但它也能够胜任转换(transform)、打包(bundle)或包裹(package)任何资源(resource or asset)。

在模块化编程中，开发者将程序分解成离散功能块(discrete chunks of functionality)，并称之为模块。



安装webpack-4.x

需要安装 webpack-cli 依赖 ,所以使用这条命令 npm install webpack webpack-cli -g

npm install webpack -g，安装完成之后输入 `webpack -v`，如果出现相应的版本号，则说明安装成功。

### 4，webpack-v 命令问题？

>C:\Users\Administrator>webpack
>'webpack' 不是内部或外部命令，也不是可运行的程序
>或批处理文件。

尝试在环境变量中增加：NODE_PATH,用户与系统变量的Path中增加node_global的目录，但是不管用。

暂无解决办法，但不影响工程，暂不管

### 5，vsCode常用插件

1. 主题： **[Material Theme](https://marketplace.visualstudio.com/items?itemName=Equinusocio.vsc-material-theme)**

对应的格式设置：

```js
{
    "editor.multiCursorModifier": "ctrlCmd",
    "editor.formatOnPaste": false,
    "workbench.activityBar.visible": false,
    "workbench.iconTheme": "eq-material-theme-icons-darker",
    "workbench.colorCustomizations": {},
    "materialTheme.cache.workbench.settings": {
        "themeColours": "Darker",
        "accentPrevious": "Acid Lime"
    },
    "workbench.colorTheme": "Material Theme Darker",
    "material-icon-theme.angular.iconsEnabled": true,
    "material-icon-theme.folders.icons": "specific",
    "editor.lineHeight": 24,
    "editor.fontLigatures": true,
    "editor.fontFamily": "FiraCode-Medium"
}
```

2. 代码格式美化

   **[Beautify](https://marketplace.visualstudio.com/items?itemName=HookyQR.beautify)** ：格式化的时候，给出格式化文本选项

3. 代码规范

   [ESLint](https://marketplace.visualstudio.com/items?itemName=dbaeumer.vscode-eslint) ：检查 `js` 语法规范，你可以使用不同的规范，如 [airbnb](https://www.npmjs.com/package/eslint-config-airbnb) 、[standard](https://github.com/standard/eslint-config-standard) 、[google](https://github.com/google/eslint-config-google)。

4. 设置同步

   [Settings Sync](https://marketplace.visualstudio.com/items?itemName=Shan.code-settings-sync) ：重点介绍下这个插件，如果你有两台电脑（比如，家里和公司）都使用 VS Code ，可是在公司或家里对 VS Code 安装了插件或者修改了配置，回到家或公司又要重新弄一次，这个插件就能解决问题，同步多台电脑设置。



### 6，vsCode常用快捷键

删除当前行： ctrl + shift + k

**代码格式化**：shift + alt +f

**全局查找文件：**ctrl + shift + f

**显示相关插件的命令(如：git log)**：ctrl + shift + p



### 7，vsCode修改字体

直接在setting.json中设置如下：

```js
// 控制字体系列。
  "editor.fontFamily": "Consolas, 'Courier New', monospace",

  // 启用字体连字
  "editor.fontLigatures": false,

  // 以像素为单位控制字号。
  "editor.fontSize": 14,

  // 控制字体粗细。
  "editor.fontWeight": "normal",
```





### 8，npm常用命令

卸载某个模板： npm uninstall  module-name

安装某个模块： npm i/install module-name

设置模块源仓库： npm set registry https://registry.npm.taobao.org/





