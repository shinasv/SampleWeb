define([ 'jquery', 'underscore', 'backbone',
		'text!../../../../reseller/manageEndUsers.html' ], function($, _,
		Backbone, manageEndUsersTemplate) {
	var ManageEndUsersView = Backbone.View.extend({
		// target item.
		el : $("#page-wrapper"),
		render : function() {
			var data = {};
			// template
			var compiledTemplate = _.template(manageEndUsersTemplate, data);
			// append the item to the view's target
			this.$el.html(compiledTemplate);
		},
		// Event Handlers
		events : {

		}
	});
	return new ManageEndUsersView;
});