
   CKEDITOR.plugins.registered['save'] = {
        init : function(editor) {
            var command = editor
                    .addCommand(
                            'save',
                            {
                                modes : {
                                    wysiwyg : 1,
                                    source : 1
                                },
                                exec : function(editor) {
                                    fn_ckeditorSave(); // Add here custom function for the save button
                                }
                            });
            editor.ui.addButton('Save', {
                label : 'Save',
                command : 'save'
            });
            
        }
    }
   
   CKEDITOR.editorConfig = function( config ) {
		// Define changes to default configuration here. For example:
		// config.language = 'fr';
		// config.uiColor = '#AADC6E';
		
		config.extraPlugins = 'youtube';
		config.toolbarGroups: [{
	          "name": "styles",
    	      "groups": ["styles"]
    	    },
            {
  	          "name": "basicstyles",
  	          "groups": ["basicstyles"]
  	        },
  	        {
  	          "name": "links",
  	          "groups": ["links"]
  	        },
  	        {
  	          "name": "paragraph",
  	          "groups": ["list", "blocks"]
  	        },
  	        {
  	          "name": "document",
  	          "groups": ["mode"]
  	        },
  	        {
  	          "name": "insert",
  	          "groups": ["insert"]
  	        },
  	        {
  	          "name": "tools",
  	          "groups": ["tools"]
  	        }
  	      ];
  	      config.removeButtons = 'NewPage,Cut,Copy,Templates,Print,Paste,PasteText,PasteFromWord,Find,SelectAll,Scayt,Form,Checkbox,Radio,TextField,Textarea,Select,Button,ImageButton,HiddenField,CopyFormatting,RemoveFormat,Language,Anchor,ShowBlocks,About,Outdent,Indent,CreateDiv,Blockquote,BidiLtr,BidiRtl,Flash,Smiley,Iframe,Superscript,Subscript,SpecialChar,PageBreak,HorizontalRule';
  	    	
	};
   

