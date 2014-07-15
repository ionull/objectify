objectify
=========

Android Object Preference Loader

* Dependence:

	```gradle
	compile 'bz.tsung.android:objectify:1.0.0'
	```
	
* Usage:
	1. Save
	
		```java
		new ObjectPreferenceLoader<Model>(context, keyOfPreference, Model.class).save(model);
		```
	2. Load

		```java
		Model model = new ObjectPreferenceLoader<Model>(context, keyOfPreference, Model.class).load();
		```
	3. List
	
		```java
		ArrayList<Model> models = new ObjectPreferenceLoader<ArrayList<Model>>(context, keyOfPrefence, new TypeToken<ArrayList<Model>>(){}.getType()).load();
		```
		
* Developer:
	Tsung Wu(@ionull) <tsung.bz@gmail.com>