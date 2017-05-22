[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-AACustomFont-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/5707)
[![](https://jitpack.io/v/EngrAhsanAli/AACustomFont.svg)](https://jitpack.io/#EngrAhsanAli/AACustomFont)


# Table of Contents

- [AACustomFont](#section-id-4)
  - [Description](#section-id-10)
  - [Demonstration](#section-id-16)
  - [Requirements](#section-id-26)
- [Installation](#section-id-32)
  - [Maven](#section-id-37)
  - [Gradle](#section-id-63)
  - [Manual Installation](#section-id-82)
- [Getting Started](#section-id-87)
  - [Enable data binding](#section-id-90)
  - [Add font files](#section-id-104)
  - [Start embedding the fonts!](#section-id-112)
  - [Bind the views!](#section-id-132)
- [Contributions & License](#section-id-156)


<div id='section-id-4'/>

#AACustomFont

<div id='section-id-10'/>

##Description

`AACustomFont` is a lightweight custom font binder in XML directly in `TextView`, `Button`, `EditText`, `RadioButton`, `CheckBox` tags. The library is aimed to avoid custom views for custom fonts in XML and to minimize the JAVA code for setting the TypeFaces for each view.

<div id='section-id-16'/>

##Demonstration

You can use `AACustomFont` in any view that's extended from `TextView`.
For example, 
`TextView`
`Button`
`EditText`
`RadioButton`
`CheckBox` 


![](https://github.com/EngrAhsanAli/AACustomFont/blob/master/Screenshots/demo.png)


<div id='section-id-26'/>

##Requirements

- Android Studio
- Android 2.3+
- JAVA 7+
- Gradle 3.0

<div id='section-id-32'/>

# Installation

`AACustomFont` can be installed using Maven, Gradle, or manually.


<div id='section-id-37'/>

##Maven

**Step 1.** Add the JitPack repository to your build file

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```
**Step 2.** Add the dependency

```xml
<dependency>
      <groupId>com.github.EngrAhsanAli</groupId>
      <artifactId>AACustomFont</artifactId>
      <version>1.0.0</version>
</dependency>
```

<div id='section-id-63'/>

##Gradle

**Step 1.** Add the JitPack repository to your build file

```
allprojects {
    repositories {
      ...
      maven { url 'https://jitpack.io' }
    }
}
```
**Step 2.** Add the dependency

```
dependencies {
  compile 'com.github.EngrAhsanAli:AACustomFont:1.0.0'
}
```
<div id='section-id-82'/>

##Manual Installation

If you prefer not to use either of the above mentioned dependency managers, you can integrate `AACustomFont` into your project manually by adding the files contained in the java folder to your project.


<div id='section-id-87'/>

#Getting Started
----------

<div id='section-id-90'/>

##Enable data binding

You need to enable data binding in order to bind custom fonts with your views. Add the following in your *build.gradle*.
 
```java
android {
  dataBinding {
        enabled = true
    }
}
```

<div id='section-id-104'/>

##Add font files

You can add the font files in *assets/fonts* directory. `AACustomFont` will automatically grab your fonts from that directory, automatically. The font files may have *.ttf* or *.otf* extensions.

<div id='section-id-112'/>

##Start embedding the fonts!

You need to change your layout parent tag to `layout` tag and put the rest in that tag.

```xml
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools">

    <!-- Put the layouts here -->


</layout>
```

Now set your fonts like:

```xml

<TextView
    <!-- other properties -->
    app:font="@{`budidaya-italic`}" />
```
> Note that the font name will always be in lowercase.

<div id='section-id-132'/>

##Bind the views!

You need to bind the views in java:

```java
DataBindingUtil.setContentView(this, R.layout.your_layout);
```

You can set *alias* to your font name using the following method

``` 
AACustomFont.getInstance(this).setAlias("myfont", "Font-File.otf");
```

<div id='section-id-156'/>

#Contributions & License

`AACustomFont` is available under the MIT license. See the [LICENSE](./LICENSE) file for more info.

Pull requests are welcome! The best contributions will consist of substitutions or configurations for classes/methods known to block the main thread during a typical app lifecycle.

I would love to know if you are using `AACustomFont` in your app, send an email to [Engr. Ahsan Ali](mailto:hafiz.m.ahsan.ali@gmail.com)

