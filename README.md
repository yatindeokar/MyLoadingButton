# MyLoadingButton

MyLoadingButton is a library that allows you to create a animated button which converts into loading, error and done state.


![myloadingbuttondemon](https://user-images.githubusercontent.com/13361899/33232256-c7ed20be-d228-11e7-8894-fbc670a47317.gif)



## Gradle Dependency

### Repository

Add this in your root build.gradle file ( not your module build.gradle file):

```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

## Dependency

Add this to your module's build.gradle file:

```
dependencies {
		compile 'com.github.yatindeokar:MyLoadingButton:v1.0.1'
	}
```

## Basic Usage

### XML

To use this MyLoadingButton in your layout simply copy and paste the xml below. This provides the default MyLoadingButton.

<code>
```xml
<com.example.myloadingbutton.MyLoadingButton
        android:id="@+id/my_loading_button"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        />
```
</code>

### MyLoadingButton Attr

```xml
       < ...
        app:mlb_labelSize="5sp"  // Set MyLoadingButton label size.
        app:mlb_label="Login Button"  // Set MyLoadingButton label text/name.
        app:mlb_backgroundColor="@color/view_color_gray"  // Set MyLoadingButton custom background color.
        app:mlb_labelColor="@color/colorAccent" // Set MyLoadingButton custom label color.
        app:mlb_loaderColor="@color/colorPrimaryDark" // Set MyLoadingButton loader color.
        app:mlb_setNormalAfterError="false" // Set MyLoadingButton to normal if its in error state, by default true.
        app:mlb_animationDuration="300" // Set MyLoadingButton animation duration.
        app:mlb_setErrorIcon="@drawable/ic_progress_cancel" // Set MyLoadingButton error icon.
        app:mlb_setDoneIcon="@drawable/ic_progress_done" // Set MyLoadingButton done icon.
        />
```

## Init from Java

### Implements to get MyLoadingButton click listener

implements MyLoadingButton.MyLoadingButtonClick

```java

MyLoadingButton myLoadingButton = findViewById(R.id.my_loading_button);
myLoadingButton.setMyButtonClickListener(this); // Set MyLoadingButton click listener

myLoadingButton.setAnimationDuration(300)
                .setButtonColor(R.color.colorAccent) // Set MyLoadingButton custom background color.
                .setButtonLabel("Label") // Set MyLoadingButton button label text.
                .setButtonLabelSize(20) // Set button label size in integer.
                .setProgressLoaderColor(Color.WHITE) // Set Color att for loader color.
                .setButtonLabelColor(R.color.white) // Set button label/text color.
                .setProgressDoneIcon(getResources().getDrawable(R.drawable.ic_progress_done)) // Set MyLoadingButton done icon drawable.
                .setProgressErrorIcon(getResources().getDrawable(R.drawable.ic_progress_cancel)) //Set MyLoadingButton error icon drawable.
                .setNormalAfterError(false); // Button animate to normal state if its in error state ,by default true.
                
```

### Change Button State

> Change button state to LOADING

```java

myLoadingButton.showLoadingButton();

``` 

> Change button state to NORMAL

```java

myLoadingButton.showNormalButton();

```

> Change button state to ERROR

```java

myLoadingButton.showErrorButton();

```

> Change button state to DONE

```java

myLoadingButton.showDoneButton();

```


## MIT License


        Copyright (c) 2017 Yatin I Deokar.

        Permission is hereby granted, free of charge, to any person obtaining a copy
        of this software and associated documentation files (the "Software"), to deal
        in the Software without restriction, including without limitation the rights
        to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
        copies of the Software, and to permit persons to whom the Software is
        furnished to do so, subject to the following conditions:

        The above copyright notice and this permission notice shall be included in all
        copies or substantial portions of the Software.

        THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
        IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
        FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
        AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
        LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
        OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
        SOFTWARE.
