objectify
=========

KMP Object Preference Loader

[![Maven Central](https://img.shields.io/maven-central/v/bz.tsung.kmp/objectify.svg?style=flat)](https://repo1.maven.org/maven2/bz/tsung/kmp/objectify)
[![API](https://img.shields.io/badge/API-19%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=19)

* What's new in 3.1
    + Objectify now works both on Android and JVM project.

* What's new in 3.0
	+ Replace (migrate) shared preferences with datastore preferences.

* Dependence:

	```groovy
	compile 'bz.tsung.kmp:objectify:3.1.2'
	```
	
* Usage:
    * Init

      ```kotlin
      // for android
      AndroidObjectify.init(
          context = context, 
          keysToMigrate = setOf(key1, key2), 
          migrateAll = false, 
          storeName = "awesome_app")
        
      // for jvm
      JvmObjectify.init(
          storePath = "/my/path/to/settings",
          storeName = "awesome_app")
      ```
    * Save
	
        ```kotlin
        ObjectPreferenceLoader(keyOfPreference, Model::class.java).set(model)
        ```
    * Load

        ```kotlin
        val model: Model = ObjectPreferenceLoader(keyOfPreference, Model::class.java).get()
        ```
    * List
	
        ```kotlin
        val models: ArrayList<Model> = ObjectPreferenceLoader(keyOfPreference, object : TypeToken<ArrayList<Model>>() {}.type).get()
        ```
    * Remove
	
        ```kotlin
        IntPreferenceLoader(keyOfPreference).remove()
        ```

* Warning:
	
	**PreferenceLoader.clear() method will remove all preferences!**

* Tips:
	
	In case u want to register type for gson, u can pass gson to constructor:
	
	```kotlin
	ObjectPreferenceLoader(keyOfPreference, Model::class.java, gson)
	```
		
* Developer:
	
	Tsung Wu (@ionull) <tsung.bz@gmail.com>
