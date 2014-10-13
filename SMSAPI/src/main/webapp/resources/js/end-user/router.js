/**
 * The Router It translates url requests into actions.
 * 
 */
define([ 'jquery', 'underscore', 'backbone', 'views/reseller/sampleView' ],
		function($, _, Backbone, SampleView) {

			var AppRouter = Backbone.Router.extend({
				routes : {
					// Define some URL routes
					'sampleView' : 'showSampleView',
					// Default
					'*actions' : 'defaultAction'
				}
			});
			var initialize = function() {
				var app_router = new AppRouter;

				app_router.on('route:showSampleView', function() {
					// Call render on the module we loaded in via the dependency
					// array
					SampleView.render();

				});
				Backbone.history.start();
			};
			return {
				initialize : initialize
			};
		});