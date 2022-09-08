<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항작성</title>
<style>
#noticeWrite td, #noticeWrite th {
	border: 1px solid #ccc;
}
</style>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp"%>
	<script src="/summernote/summernote-lite.js"></script>
	<script src="/summernote/lang/summernote-ko-KR.js"></script>
	<link rel="stylesheet" href="/summernote/summernote-lite.css">
	<div class="page-content">
		<div class="page-title">공지사항 작성(에디터사용)</div>
		<!--첨부파일은 method="post" enctype="multipart/form-data" 이거 두가지는 반드시 들어가줘야댐  
	첨부파일이 포함 안되는건 enctype="multipart/form-data" 생략. 근데 그냥 들어갈지도 모르면 무조건 쓰기
	-->
		<form action="/noticeWrite.do" method="post"
			enctype="multipart/form-data">
			<table class="tbl" id="noticeWrite">
				<tr class="tr-1">
					<th class="td-3">제목</th>
					<td colspan="3"><input type="text" name="noticeTitle"
						class="input-form"></td>
				</tr>
				<tr class="tr-1">
					<th class="td-3">작성자</th>
					<td><%=m.getMemberId()%> <input type="hidden"
						name="noticeWriter" value="<%=m.getMemberId()%>"></td>
					<th class="td-3">첨부파일</th>
					<td><input type="file" name="upfile"></td>
				</tr>
				<tr class="tr-1">
					<td colspan="4" style="text-align: left;"><textarea
							id="noticeContent" name="noticeContent" class="input-form"></textarea>
					</td>
				</tr>
				<tr class="tr-1">
					<td colspan="4">
						<button type="submit" class="btn bc1 bs4">공지사항 작성</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<script>
		$("#noticeContent").summernote({
			height : 400,
			lang : "ko-KR",
			callbacks : {
				onImageUpload : function(files){
					uploadImage(files[0],this);
				}
			}
		});
		function uploadImage(file,editor){
			//ajax통해서 서버에 이미지를 업로드
			//업로드된 이미지의 경로를 받아오는 역할
			//<form>태그와 동일한 효과를 발생시킬 수 있는 객체
			const form = new FormData();
			form.append("file",file);
			$.ajax({
				url : "/uploadImage.do",
				type : "post",
				data : form,
				processData : false,
				contentType : false,
				success : function(data){
					$(editor).summernote("insertImage",data);
				},
				error : function(){
					
				}
			});
			//processDate : 전송하는 데이터를 문자열로 전송하게 기본값이 설정되어있음
					//		-> 파일 전송을 위해 기본값 제거하는 작업
			//contentType : enctype="application/x-www-form-urlencoded;charser=UTF-8"
			//				-> 설정되어있는 기본 enctype을 제거
		}
	</script>
	<%@include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>