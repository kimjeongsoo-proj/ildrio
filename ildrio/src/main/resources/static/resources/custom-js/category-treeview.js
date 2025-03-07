$(document).ready(function () {
    $('.product-display-list-wrapper ul').find('ul.nav-treeview').hide();
    $(document).on('click', '.product-display-list-wrapper ul li.parent-node a.nav-link', function(e) {
        
        if ( $(this).closest('li.parent-node').hasClass('expanded-item') ) {
            $(this).closest('li.parent-node').removeClass('expanded-item');
            $(this).closest('li.parent-node').children('ul.nav-treeview').hide('slow');
            // expandableItem.children('ul.nav-treeview').hide(400);
        }
        else {
            $(this).closest('li.parent-node').removeClass('expanded-item');
            $(this).closest('li.parent-node').addClass('expanded-item');
            $(this).closest('li.parent-node').children('ul.nav-treeview').show('slow');
            // $(this).addClass('selected');
        }
    })
    $(document).on('click', '.product-display-list-wrapper ul li.parent-node a.add-item', function() {
        // <a class="nav-link" />
        var a_link = $(this).prev();
        if (!a_link.find('i.fa-angle-right').length) {
            a_link.prepend('<i class="fas fa-angle-right"></i>')
        }
        console.log('click')
        $(this).closest('li.parent-node').children('ul.nav-treeview')
                                .append(`<li class="nav-item parent-node">
                                            <div class="main-item row align-items-center">
                                                <div class="col-7 d-flex align-items-center">
                                                    <div class="w-100">
                                                        <div class="row align-items-center">
                                                            <div class="col-8 d-flex align-items-center">
                                                                <a href="#" class="nav-link">
                                                                    <span>New Product</span>
                                                                </a>
                                                            </div>
                                                            <div class="col-4">
                                                                <button class="btn-square btn-transition btn btn-outline-focus remove-btn" style="padding-left: 10px;padding-right: 10px">
                                                                    <i class="fas fa-trash-alt"></i>
                                                                </button>
                                                            </div>
                                                        </div>
                                                    </div>	
                                                </div>
                                                <div class="col-4">
                                                    <button class="btn-square btn-transition btn btn-outline-focus up-btn" style="padding-left: 10px;padding-right: 10px">
                                                        <i class="fas fa-chevron-up"></i>
                                                    </button>
                                                    <button class="btn-square btn-transition btn btn-outline-focus down-btn" style="padding-left: 10px;padding-right: 10px">
                                                        <i class="fas fa-chevron-down"></i>
                                                    </button>
                                                </div>
                                                
                                            </div>
                                        </li>`);
        $(this).closest('li.parent-node').removeClass('expanded-item');
        $(this).closest('li.parent-node').addClass('expanded-item');
        $(this).closest('li.parent-node').children('ul.nav-treeview').show('slow');
    })
    // Have to use Jquery event-delegation
    $(document).on('click', '.product-display-list-wrapper ul li.parent-node button.remove-btn', function() {
        console.log('click');
        $(this).closest('li.nav-item').remove();
    })
    $(document).on('click', '.product-display-list-wrapper ul li.parent-node button.up-btn', function() {
        $(this).closest('li.nav-item').insertBefore($(this).closest('li.nav-item').prev());
        
    })
    $(document).on('click', '.product-display-list-wrapper ul li.parent-node button.down-btn', function() {
        $(this).closest('li.nav-item').insertAfter($(this).closest('li.nav-item').next());
    })


    

});

function fn_buildCategoryTree(option, rootNode) {
    $.each(option.treeData, function(parentIndex, node) {
        var new_li = $(`<li class="nav-item parent-node">
                            
                        </li>
                    `);
        var new_li_item = $(`<div class="main-item row align-items-center"></div>`);
        var new_li_item_div = $(`<div class="d-flex align-items-center"></div>`);
        var new_li_item_div_container = $(`<div class="w-100"></div>`);
        var new_li_item_div_container_row = $(`<div class="row align-items-center"></div>`);
        var new_li_item_div_container_row_link = $(`<div class="d-flex align-items-center"></div>`);
        var new_li_item_div_container_row_link_a = $(`<a href="#" class="nav-link">
                                <span>${node.name}</span>	
                            </a>
                        `);
        
        // pass the data object into a link 
        new_li_item_div_container_row_link_a.data("treeData", node.data)
        if (node.children) {
            new_li_item_div_container_row_link_a.prepend(`<i class="fas fa-angle-right"></i>`);
        }
        new_li_item_div_container_row_link.append(new_li_item_div_container_row_link_a);
        if (node.canAddChild) {
            new_li_item_div_container_row_link.append(`<a href="#" class="add-item">
                                            <i class="fas fa-plus-square"></i>
                                        </a>`);
        }

        if (node.canRemove) {
            new_li_item_div_container_row_link.addClass('col-8');
            var removeBtn = $(`<div class="col-4">
                                    <button class="btn-square btn-transition btn btn-outline-focus remove-btn" style="padding-left: 10px;padding-right: 10px">
                                        <i class="fas fa-trash-alt"></i>
                                    </button>
                                </div>
                            `);
            new_li_item_div_container_row.append(new_li_item_div_container_row_link);
            new_li_item_div_container_row.append(removeBtn);

        }
        else {
            new_li_item_div_container_row_link.addClass('col-12');
            new_li_item_div_container_row.append(new_li_item_div_container_row_link);
        }
        new_li_item_div_container.append(new_li_item_div_container_row);
        new_li_item_div.append(new_li_item_div_container);

        

        if (option.canMove) {
            var moveBtn = $(`<div class="col-4">
                                <button class="btn-square btn-transition btn btn-outline-focus up-btn" style="padding-left: 10px;padding-right: 10px">
                                    <i class="fas fa-chevron-up"></i>
                                </button>
                                <button class="btn-square btn-transition btn btn-outline-focus down-btn" style="padding-left: 10px;padding-right: 10px">
                                    <i class="fas fa-chevron-down"></i>
                                </button>
                            </div>
                        `);
            new_li_item_div.addClass('col-8');
            new_li_item.append(new_li_item_div);
            new_li_item.append(moveBtn);
        }
        else {
            new_li_item_div.addClass('col-12');
            new_li_item.append(new_li_item_div);
        }

        new_li.append(new_li_item);
        if (node.children && node.children.length > 0) {
            var child_ul_treeview = $(`<ul class="nav nav-treeview"></ul>`);
            var children = {
                treeData: node.children,
                canMove: option.canMove
            }
            fn_buildCategoryTree(children, child_ul_treeview);
            new_li.append(child_ul_treeview);
        }
        else {
            new_li.append(`<ul class="nav nav-treeview"></ul>`);
        }

        
        rootNode.append(new_li);
    })
}


// interface Option {
//     treeData: [{
//         name: string,
//         canRemove?: boolean;
//         canAddChild?: Boolean;
//         
//         children: [
//             {
//                 name: string,
//                 canRemove?: boolean;
//                 canAddChild?: Boolean;
//                 canMove?: boolean;
//                 children: [...]
//             }
//         ]
//     }];
//     canMove?: boolean;

// }
