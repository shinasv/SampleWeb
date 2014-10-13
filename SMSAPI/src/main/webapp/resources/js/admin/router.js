/**
 * The Router It translates url requests into actions.
 * 
 */
define([ 'jquery', 'underscore', 'backbone', 'views/admin/manageResellersView' ],
		function($, _, Backbone, ManageResellersView) {

			var AppRouter = Backbone.Router.extend({
				routes : {
					// Define some URL routes
					'manageResellers' : 'showManageResellersView',
					// Default
					'*actions' : 'defaultAction'
				}
			});
			var initialize = function() {
				var app_router = new AppRouter;

				app_router.on('route:showManageResellersView', function() {
					// Call render on the module we loaded in via the dependency
					// array
					ManageResellersView.render();

				});
				Backbone.history.start();
			};
			return {
				initialize : initialize
			};
		});