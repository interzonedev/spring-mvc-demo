(function($) {
	$.fn.confirm = function(settings) {
		var config = {
			message: "Are you sure you want to do this?"
		};

		if (settings) {
			$.extend(config, settings);
		}

		return this.each(function(i, element) {
			$(element).bind("click", function(evt) {
				if (!confirm(config.message)) {
					evt.preventDefault();
				}
			});
		});
	};
})(jQuery);
