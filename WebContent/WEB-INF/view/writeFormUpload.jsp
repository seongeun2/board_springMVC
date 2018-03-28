<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css"></head>
<html>
<head>
<title>게시판</title>
</head>

<p class="w3-left"  style="padding-left:30px;"></p>
<div class="w3-container">

<center><b>글쓰기</b>
<br>
<form method="post" name="writeform" enctype="multipart/form-data"  action="writeProUpload" >
<input type="hidden" name="boardid" value="${boardid}">
<input type="hidden" name="num" value="${num}">
<input type="hidden" name="ref" value="${ref}">
<input type="hidden" name="re_level" value="${re_level}">
<input type="hidden" name="re_step" value="${re_step}">
<input type="hidden" name="pageNum" value="${pageNum}">

<table class="w3-table-all"  style="width:70%;" >
   <tr>
    <td align="right" colspan="2" >
	    <a href="list.jsp"> 글목록</a> 
   </td>
   </tr>
   <tr>
    <td  width="70"   align="center">이 름</td>
    <td  width="330">
       <input type="text" size="10" maxlength="10" name="writer"></td>
  </tr>
  <tr>
    <td  width="70"   align="center" >제 목
    </td>
    <td width="330">
 	<c:if test="${num == 0}">
       <input type="text" size="40" maxlength="50" name="subject"> <!-- 새글인 경우 -->
       </c:if>
       <c:if test="${num != 0 }">
	   <input type="text" size="40" maxlength="50" name="subject" value="[답글]">	 <!-- 답글인 경우 -->
   </c:if>
   </td>
  </tr>
  <tr>
    <td  width="70"   align="center">Email</td>
    <td  width="330">
       <input type="text" size="40" maxlength="30" name="email" ></td>
  </tr>
  <tr>
    <td  width="70"   align="center" >내 용</td>
    <td  width="330" >
     <textarea name="content" rows="13" cols="40"></textarea> </td>
  </tr>
  <tr>
  	<td width="70" align="center">file</td>
  	<td width="330">
  		<input type="file" size="40" maxlength="30" name="uploadfile"> 
  	</td>
  </tr>
  
  
  
  
  
  <tr>
    <td  width="70"   align="center" >비밀번호</td>
    <td  width="330" >
     <input type="password" size="8" maxlength="12" name="passwd"> 
	 </td>
  </tr>
<tr>      
 <td colspan=2  align="center"> 
  <input type="submit" value="글쓰기" >  
  <input type="reset" value="다시작성">
  <input type="button" value="목록보기" OnClick="window.location='list.jsp'">
</td></tr></table>    
     
</form>
</center></div>  




</body>
</html>      
