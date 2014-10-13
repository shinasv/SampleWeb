/**
 * The Router It translates url requests into actions.
 * 
 */
define([ 'jquery', 'underscore', 'backbone',
		'views/reseller/manageEndUsersView' ], function($, _, Backbone,
		ManageEndUsersView) {

	var AppRouter = Backbone.Router.extend({
		routes : {
			// Define some URL routes
			'manageEndUsers' : 'showManageEndUsersView',
			// Default
			'*actions' : 'defaultAction'
		}
	});
	var initialize = function() {
		var app_router = new AppRouter;

		app_router.on('route:showManageEndUsersView', function() {
			// Call render on the module we loaded in via the dependency
			// array
			ManageEndUsersView.render();

		});
		Backbone.history.start();
	};
	return {
		initialize : initialize
	};
});