$(document).ready(function () {

    function selectNav(obj) {
        $(obj)
            .parents('ul:first')
                .find('a')
                    .removeClass('selected')
                .end()
            .end()
            .addClass('selected');
    }
        
   console.log($('#scrollContainer').find('.panel').attr("id"));
   console.log($('#slider .navigation').find('a[href*=".'+$('#scrollContainer').find('.panel').attr("id")+'."]').attr("href"));
   
   var panelId = $('#scrollContainer').find('.panel').attr("id");
   var a = $('#slider .navigation').find('a[href*="'+panelId+'"]')
   selectNav(a);


});