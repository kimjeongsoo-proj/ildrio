/**
 * @license Copyright (c) 2003-2016, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.md or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
	config.toolbar =
		[
		[ 'NewPage','Preview' ],
		[ 'Cut','Copy','Paste','PasteText','PasteFromWord','-','Undo','Redo' ],
		[ 'Find','Replace','-','SelectAll','-','Scayt' ],
		[ 'Image','Flash','Table','HorizontalRule','SpecialChar','PageBreak'],
		[ 'Styles','Format' ],
		[ 'Bold','Italic','Strike','-','RemoveFormat' ]
		];
};
