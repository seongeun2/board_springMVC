<!-- deletePro -->
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>



<c:if test="${check == 1}">
<meta http-equiv = "Refresh" content="0;url=list?pageNum=${pageNum }">
</c:if>

<c:if test="${check != 1}">
<script type="text/javascript">
alert("비밀번호가 맞지 않습니다");
history.go(-1);
</script>
</c:if> 
