// Filename: adminmain.js

// Require.js allows us to configure shortcut alias
// Their usage will become more apparent further along in the tutorial.
require.config({
	baseUrl : "/smsapi/resources/js/",
	paths : {
		jquery : 'libs/jquery-1.9.1.min',
		underscore : 'libs/underscore-min',
		backbone : 'libs/backbone-min',
		datatables : 'libs/jquery.dataTables.min',
	},
	shim : {
		"underscore" : {
			deps : [],
			exports : "_"
		},
		"backbone" : {
			deps : [ "jquery", "underscore" ],
			exports : "Backbone"
		},
		"datatables" : {
			deps : [ "jquery" ]
		},
	}
});

require([
// Load our app module and pass it to our definition function
'reseller/app',

], function(App) {
	// The "app" dependency is passed in as "App"
	// Again, the other dependencies passed in are not "AMD" therefore don't
	// pass a parameter to this function
	App.initialize();
});