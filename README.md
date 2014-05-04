MiniRefreshActionItem
=====================

NOTE: This is a work in progress is not finished yet!

Just another action bar item that implements this common pattern (in a mini way!).

This library just only includes two use cases (and nothing more):

- A refresh icon that when the user clicks the component, it shows an indeterminate ProgressBar.
- A refresh icon that when the user clicks the component, it starts to rotate itself.

Sample image:

<a href="http://imgur.com/lsxx89M"><img src="http://i.imgur.com/lsxx89M.gif" title="Hosted by imgur.com" /></a>

If you want, you can see it live in the sample application (Not yet uploaded!):

<a href="https://play.google.com/store/apps/details?id=com.aldoborrero.minirefreshactionitem.sample">
  <img alt="Android app on Google Play"
       src="https://developer.android.com/images/brand/en_app_rgb_wo_45.png" />
</a>

Why did you bother to create another library when we have RefreshActionItem made by Manuel Peinado?
===================================================================================================

The main reason on why I did it is because I just wanted to learn how to release a library to Maven Central with Gradle.
Nothing more.

If you don't need any of the features that RefreshActionItem provides, like Pie or Badge indicator types, then go ahead and
use mine, the API is almost the same and the only differences are in how the source code is structured inside.

Including it in your project
----------------------------

```gradle
dependencies {
  compile 'com.aldoborrero:minirefreshactionitem:1.0.+'
}
```

NOTE: Not yet uploaded!

Usage
-----

To be finished!

Customization
-------------

To be finished!

Credits
-------

* Big thanks to Manuel Peinado for creating [RefreshActionItem][1] in which this simple library has ripped some parts of
its source code.

Developed By
------------

Aldo Borrero González - <aldo@aldoborrero.com>

<a href="https://plus.google.com/+AldoBorrero">
  <img alt="Follow me on Google+"
       src="https://raw.github.com/ViewsForAndroid/MiniRefreshActionItem/master/art/google_plus_icon_logo.png" />
</a>

License
-------

    Copyright 2014 Aldo Borrero

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.


 [1]: https://github.com/ManuelPeinado/RefreshActionItem-Native