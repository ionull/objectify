objectify
=========

Android Object Preference Loader

[![Maven Central](https://img.shields.io/maven-central/v/bz.tsung.android/objectify.svg?style=flat)](https://repo1.maven.org/maven2/bz/tsung/android/objectify)
[![API](https://img.shields.io/badge/API-1%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=9)

* Dependence:

	```groovy
	compile 'bz.tsung.android:objectify:1.1.1'
	```
	
* Usage:
	* Save
	
		```java
		new ObjectPreferenceLoader(context, keyOfPreference, Model.class).save(model);
		```
	* Load

		```java
		Model model = new ObjectPreferenceLoader(context, keyOfPreference, Model.class).load();
		```
	* List
	
		```java
		ArrayList<Model> models = new ObjectPreferenceLoader(context, keyOfPrefence, new TypeToken<ArrayList<Model>>(){}.getType()).load();
		```
	*	Remove
	
		```java
		new ObjectPreferenceLoader(context, keyOfPreference, Model.class).remove();
		```

* Warning:
	
	**ObjectPreferenceLoader.clear(context) method will remove all preferences!**

* Tips:
	
	In case u want to register type for gson, u can pass gson to constructor:
	
	```java
	new ObjectPreferenceLoader(context, keyOfPreference, Model.class, gson);
	```
		
* Developer:
	
	Tsung Wu(@ionull) <tsung.bz@gmail.com>
