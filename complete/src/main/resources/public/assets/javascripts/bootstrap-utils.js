function alertMessage(msg, cssClass) {
    var html = '<div class="alert ' + cssClass + ' alert-dismissible fade show" role="alert">';
    html += '<strong>'+msg+'</strong>';
    html += '<button type="button" class="close" data-dismiss="alert" aria-label="Close">';
    html += '<span aria-hidden="true">&times;</span>';
    html += '</button>';
    html += '</div>';
    return html;
}