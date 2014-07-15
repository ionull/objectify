objectify
=========

Android Object Preference Loader

* Dependence:
	>compile 'bz.tsung.android:objectify:1.0.0'
	
* Usage:
	1. Save
		>new ObjectPreferenceLoader<Model>(context, keyOfPreference, Model.class).save(model);
	2. Load
		>Model model = new ObjectPreferenceLoader<Model>(context, keyOrPreference, Model.class).load();
	3. List
		>ArrayList<Model> models = new ObjectPreferenceLoader<ArrayList<Model>>(context, keyOfPrefence, new TypeToken<ArrayList<Model>>(){}.getType()).load();
		
* Developer:
	Tsung Wu(@ionull) <tsung.bz@gmail.com>