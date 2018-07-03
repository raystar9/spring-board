function deleteConfirm(){
	var c = confirm('정말로 삭제하시겠습니까?');
	if(c == true) {
		document.getElementById("delete-form").submit();
	} 
}
function onDownloadClicked(){
	$.ajax({
		url: "/mvc/board/post/download",
		data: {
			postno: "${post.postNo}"
		},
		method: "GET"
	});
}