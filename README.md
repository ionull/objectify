objectify
=========

Android Object Preference Loader

* Dependence:

	```groovy
	compile 'bz.tsung.android:objectify:1.0.0'
	```
	
* Usage:
	* Save
	
		```java
		new ObjectPreferenceLoader<Model>(context, keyOfPreference, Model.class).save(model);
		```
	* Load

		```java
		Model model = new ObjectPreferenceLoader<Model>(context, keyOfPreference, Model.class).load();
		```
	* List
	
		```java
		ArrayList<Model> models = new ObjectPreferenceLoader<ArrayList<Model>>(context, keyOfPrefence, new TypeToken<ArrayList<Model>>(){}.getType()).load();
		```
	*	Remove:
	
		```java
		new ObjectPreferenceLoader<Model>(context, keyOfPreference, Model.class).remove();
		```

* Warning:
	
	**ObjectPreferenceLoader.clear(context) method will remove all preferences!**
		
* Developer:
	
	Tsung Wu(@ionull) <tsung.bz@gmail.com>