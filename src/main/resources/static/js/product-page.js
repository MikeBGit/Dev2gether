$(document).ready(function() {
    let comment;

    $("#edit").click(function() {
        comment =  $("#comment-box").text();
        $("#comment-box").html('<textarea name="comment" th:field="${comment.content}" rows="3" class="col-11" form="comment">' + comment + '</textarea>');
        $("#edit").replaceWith('<a id="save-edit" th:href="@{/comments/delete/{commentId}(commentId=${comment.id})}" class="btn btn-primary btn-sm col-12 mb-1">Save</a>')
        $("#delete").replaceWith('<a id="cancel" th:href="@{/comments/delete/{commentId}(commentId=${comment.id})}" class="btn btn-light btn-sm col-12">Cancel</a>')
        console.log(comment);
    });

    $("#cancel").on("click", function() {

//        $("#comment-box").html('<td id="comment-box" class="text-center align-middle" style="width:65%">' + comment + '</td>');
//        $("#edit").replaceWith('<a id="save-edit" th:href="@{/comments/delete/{commentId}(commentId=${comment.id})}" class="btn btn-primary btn-sm col-12 mb-1">Save</a>')
//        $("#delete").replaceWith('<a id="cancel" th:href="@{/comments/delete/{commentId}(commentId=${comment.id})}" class="btn btn-light btn-sm col-12">Cancel</a>')
        console.log("cancel");
    });

});