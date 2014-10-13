define([ 'jquery', 'underscore', 'backbone',
		'text!../../../../admin/manageResellers.html' ], function($, _,
		Backbone, manageResellersTemplate) {
	var ManageResellersView = Backbone.View.extend({
		// target item.
		el : $("#page-wrapper"),
		render : function() {
			var data = {};
			// template
			var compiledTemplate = _.template(manageResellersTemplate, data);
			// append the item to the view's target
			this.$el.html(compiledTemplate);
		},
		// Event Handlers
		events : {

		}
	});
	return new ManageResellersView;
});