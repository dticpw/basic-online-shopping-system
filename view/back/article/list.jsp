<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- 引入分页标签 -->
<%@ taglib prefix="zx" uri="/pager/tag"%>
<!-- 引入自定义标签 -->   
 <!DOCTYPE html>
<html lang="zh_CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>商品首页</title>
    <!-- Bootstrap core CSS -->
    <%-- c:url 标签的作用：
    	1.自动在URL的前面加上context path
    	2.如果客户端禁用了Cookie，自动使用URL重写技术，把jsessionid放到url的分号后面
    	/taobao/resources/bootstrap/css/bootstrap.css;jsessionid=xxxxx
     --%>
    <link href="<c:url value="/resources/bootstrap/css/bootstrap.css"/>" rel="stylesheet" />
    <link href="${ctx}/resources/css/taobao.css" rel="stylesheet" />
    <!-- 引入分页标签样式 -->
    <link href="${ctx}/resources/css/pager.css" rel="stylesheet" />
    
    <script type="text/javascript" src="${pageContext.request.contextPath }/resources/jquery/jquery.js"></script>

<script type="text/javascript">
          
           $(function(){
        	   
        	     //为一级商品类型绑定 onchange事件
        	     $("#firstType").change(function(){
        	    	   
        	    	    //获取用户选中的  一级商品类型
        	    	    var val = this.value;
        	    	    
						if(val != "0"){	// 一级商品类型为空了
        	    	    	$.ajax({
        	    	    		type : "post",
        	    	    		url : "${ctx}/ajaxLoadSeTypes.do",
        	    	    		data : "code="+val,	// 把当前的一级类型带过去
        	    	    		dataType : "json",	// 那么后台返回的一定要是json格式的字符串
        	    	    		success : function(arr){	// 接受返回的数组
        	    	    			$("#secondType").empty().append("<option value='0'>请选择二级商品类型</option>")
        	    	    			$.each(arr,function(){
        	    	    				$("#secondType").append("<option value="+this.code+">"+this.name+"</option>")
        	    	    			})
        	    	    			
        	    	    		},error :function(){
        	    	    			alert("网路加载异常");
        	    	    		}
        	    	    		
        	    	    		
        	    	    	})
        	    	    }
        	    	    

        	     })

           })
      
      </script>
      
</head>

<body>

<!-- 横幅导航条开始 -->
	<jsp:include page="/WEB-INF/view/front/nav/nav_back.jsp"></jsp:include>
	
<!-- 横幅导航条结束 -->

<!--  横幅下方的主体开始 -->
<div class="container">

    <div class="row row-offcanvas row-offcanvas-right">

        <!-- 侧边导航开始 -->
        <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar" role="navigation">
            <div class="list-group">
                <c:forEach items="${requestScope.types }" var="type">
                    <a href="<c:url value="/mindex.do?typeCode=${type.code }"/>" class="list-group-item <c:if test="${type.code eq param.typeCode or top.code eq fn:substring(param.typeCode, 0, 4) }">active</c:if>">${type.name }</a>
                </c:forEach>
            </div>
        </div>
        <!--  侧边导航结束 -->
        <!-- 内容主体开始 -->
        <div class="col-xs-12 col-sm-9">
            <p class="pull-right visible-xs">
                <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">显示导航条</button>
            </p>


            <div class="alert alert-info" role="alert">
                <div>
                	<!-- tomcat8以上的版本已经解决了get请求的乱码 -->
                	<!-- 现在这个表单是post请求 直接搜索keyword会乱码 于是加一个编码过滤器 -->
                    <form action="<c:url value="/mindex.do"/>" method="get">
                        <table class="table-condensed">
                            <tbody>
                            <tr>
                           
                                <td>
                                <!-- 如果当前选择了商品的类型，仅在该类型下面进行搜索 -->
                                <input type="hidden" name="typeCode" value="${typeCode}" />
                                <div class="input-group">
                                    <input type="text" name="keyword" value="${keyword}"
                                           class="form-control" placeholder="搜索商品的标题" />
                                    <div class="input-group-btn">
                                        <button class="btn" type="submit">
                                            <span class="glyphicon glyphicon-search"></span>
                                        </button>
                                    </div>
                                </div>
                                </td>
                                <td>
                                	<!-- 点击按钮到data-target中去  元素id为exampleModal的是最下面写的弹出框元素 -->
                                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal" data-whatever="添加商品">添加商品</button>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </form>
                </div>
            </div>
            

            <!-- 展示商品数据 -->
            <div class="row">
                <c:forEach items="${articles }" var="a">
                    <div class="col-xs-6 col-lg-4">
            	<span class="thumbnail">
                    <a class="label label-danger" href="<c:url value="/article_manager/removeArticle.action?id=${a.id}"/>">下架</a>
                    <a class="label label-success" href="<c:url value="/article_manager/showUpdate.action?id=${a.id }"/>">修改</a>
				    <a href="<c:url value="/article_manager/preArticle.action?id=${a.id }"/>">
 				      <img style="width:200px;height:200px" src="<c:url value="resources/image/article/${a.image }"/>" alt="...">

		              <p style="height: 20px; overflow: hidden;">${a.title }</p>
				    </a>
		              <p>
		              	<span class="price">${a.price }</span>
		              	<c:if test="${not empty a.discount }">
                            <span class="discountPrice">惊爆价: <fmt:formatNumber value="${a.price * a.discount  }" pattern="0.00"></fmt:formatNumber>(${a.discount }折)</span>
                        </c:if>
		              </p>
              </span>
                    </div>
                </c:forEach>
            </div>
            
            <!-- 分页开始 -->
            
            <%-- <zx:bpager pageIndex="1" pageSize="10" pageStyle="black" totalNum="999" submitUrl="index.action?pageIndex={0}"></zx:bpager> --%>
            <!-- submitUrl="${ctx}/mindex.do?pageIndex={0}&typeCode=${typeCode}&keyword=${keyword}" -->
            <div class="row">
				<nav>
					<ul class="pagination">
						<div>
							<a href="javascript:goPage(1)">首页</a> <a
								href='javascript:goPage(1)'>上一页</a> <a
								href='javascript:goPage(2)'>下一页</a> <a
								href='javascript:goPage(7)'>尾页</a> &nbsp; <span>第&nbsp;
								1&nbsp;页/共&nbsp; 7页 ，共 52 条数据 </span>
						</div>
					</ul>
			</div>
			<!-- 分页结束 -->
		<!-- 自定义分页标签 
		     pageIndex:当前页码
		     pageSize：每页显示的记录数
		     recordCount：总记录数
		     submitUrl：提交地址
		     stylecss：分页样式
		-->
		  <%-- <zx:bpager
		     pageIndex="${pageModel.pageIndex}" 
		     pageSize="${pageModel.pageSize}" 
		     totalNum="${pageModel.totalNum}" 
		     submitUrl="${pageContext.request.contextPath}/article_manager/getAll.action?pageIndex={0}&title=${title}&typeCode=${typeCode}" 
		     pageStyle="yellow"></zx:bpager> --%>
		 </div>
		 
    </div>

    <hr>

    <footer>
        <p>&copy; 版权所有，欢迎借鉴</p>
    </footer>
</div>

<!-- 弹出框 -->
<div class="modal fade" id="exampleModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="exampleModalLabel">添加物品</h4>
            </div>
            <div class="modal-body ">
                <div align="center">
                    <span style="color:red;"></span>
                    <form name="articleform" class="form-horizontal" action="<c:url value="/article_manager/addArticle.action"/>" enctype="multipart/form-data" method="post">
                       <div class="form-group">
                            <label class="col-sm-3 control-label">一级商品类型：</label>
                            <div class="col-sm-4">
                                <select class="form-control" id="firstType">
                                    <option value="0">请选择一级商品类型</option>
                                    <c:forEach items="${types}" var="type">
                                        <option value="${type.code}">${type.name}</option>
                                    </c:forEach>
                                </select> 
                            </div>
                        </div>
                        

                         <div class="form-group">
                            <label class="col-sm-3 control-label">二级商品类型：</label>
                            <div class="col-sm-4">
                                <select class="form-control" required="required" name="code" id="secondType">
                                    <option value="0">请选择二级商品类型</option>
                                    
                                </select> 
                            </div>
                            <span id="typeMessage"></span>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">标题：</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" name="title" size="50">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">供应商：</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" name="supplier" size="50">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">地区：</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" name="locality" size="50">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">价格：</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" name="price" size="50">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">库存数量：</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" name="storage" size="50">
                            </div>
                        </div>

                       <!--  <div class="form-group">
                            <label class="col-sm-3 control-label">上架日期：</label>
                            <div class="col-sm-4">
                                <input type=text class="form-control" name="putawayDate" id="putawayDate" size="50">
                            </div>
                        </div> -->
                        <div class="form-group">
                            <label class="col-sm-3 control-label">物品封面：</label>
                            <div class="col-sm-4">
                                <input type="file" class="form-control" name="file" size="40" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">书面描述：</label>
                            <div class="col-sm-6">
                                <textarea rows="5" cols="60" class="form-control" name="description"></textarea>
                            </div>
                        </div>
                        <table>
                            <tr>
                                <td><input type="submit" class="btn btn-success btn-sm" value="提交" /></td>
                                <td>&nbsp;&nbsp;<input type="button" class="btn btn-danger btn-sm" data-dismiss="modal" value="取消" /></td>
                            </tr>
                        </table>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>


<!--  横幅下方的主体结束 -->
<!-- 一般来讲，css必须在head里面引入，因为页面加载完成，就需要显示正确的样式 -->
<!-- js一般在页面最后面加载，等页面布局都完成以后，再来处理js文件！ -->
<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="${pageContext.request.contextPath }/resources/jquery/jquery.js"></script>
<script src="${pageContext.request.contextPath }/resources/bootstrap/js/bootstrap.js"></script>
<script src="${pageContext.request.contextPath }/resources/js/taobao.js"></script>
<script type="text/javascript">
    $(function(){
       /*  $("#secondType").val("${secondType}");
        $("#addTypeCode").val("${typeCode}"); */
    })

</script>
</body>
</html>