<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="jp.co.aforce.beans.Login"%>
<%@ page import="jp.co.aforce.beans.Product"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<%
Login user = (Login) session.getAttribute("user");
if (user == null) {
	response.sendRedirect(request.getContextPath() + "/views/login-in.jsp");
	return;
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>トップページ</title>
<link rel="stylesheet" href="<c:url value='/css/userMenu.css' />">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body>

	<header>
		<h1>TIEMPO SHOP</h1>
		<ul>
			<li><a
				href="<%=request.getContextPath()%>/views/userAccount.jsp">アカウント</a></li>
			<li><a
				href="${pageContext.request.contextPath}/views/CartServlet">カート</a></li>
			<li><a href="<c:url value='/views/Logout' />">ログアウト</a></li>
		</ul>
	</header>

	<!-- スライドショー -->
<section class="main-visual-slideshow">
  <div class="slide-track">
    <div class="slide">
      <img src="${pageContext.request.contextPath}/img/prismpack-tiempo-mainpc-min.jpg" alt="スライド1">
      <div class="overlay">
        <h2>勝利を決めるボールタッチ</h2>
        <p>柔軟性に優れたFlyTouch Plusがスムーズな動きを可能にし、ゲームの流れを形成する。<br>
        ティエンポが生み出すプレーはアートのよう。<br>
        限られたプレーヤーだけがそのサイエンスを知る。<br>
        2025年春夏 NEWカラー登場。ティエンポシリーズの最新モデルが新たなパフォーマンスを約束します。</p>
      </div>
    </div>
    <div class="slide">
      <img src="${pageContext.request.contextPath}/img/5_000000060220.jpg" alt="スライド2">
      <div class="overlay">
        <h2>素足感覚のしなやかさと攻撃力を両立</h2>
        <p>新しいティエンポは、ピンクの閃光と共にフィールドを駆け抜ける。<br>
        極上のフィット感と軽さが、素足のようなボールタッチを実現。<br>
        エレガントでいて攻撃的。ティエンポの進化がここにある。</p>
      </div>
    </div>
    <div class="slide">
      <img src="${pageContext.request.contextPath}/img/9b6e5197.jpg" alt="スライド3">
      <div class="overlay">
        <h2>静けさの中に宿る知性と力</h2>
        <p>ピッチに響く一歩一歩が、意図されたプレーの連続。<br>
        シャープなカットと精密なボールコントロールを可能にする、洗練されたメタリックグレーの一足。<br>
        知性を備えたプレーメーカーのための「ティエンポ」限定モデル。</p>
      </div>
    </div>
    <div class="slide">
      <img src="${pageContext.request.contextPath}/img/NI1757BU42912_1_m.jpg" alt="スライド4">
      <div class="overlay">
        <h2>伝統と革新が融合する至高のタッチ</h2>
        <p>クラシカルな佇まいに秘められた最新テクノロジー。<br>
        洗練されたシルバーが放つ気品と、鋭いブラックのアクセントが示す攻撃性。<br>
        ピッチを支配する者だけが知る、究極の一足がここに誕生。<br>
        ティエンポ・レジェンド9、真の実力者のために。
        </p>
        
      </div>
    </div>
  </div>
</section>








	<main>
		<section class="products">
			<h2>
				<c:choose>
					<c:when test="${not empty keyword}">
						「${keyword}」の検索結果
					</c:when>
					<%-- <c:otherwise>
						商品一覧
					</c:otherwise>--%>
				</c:choose>
			</h2>

			<!-- 検索フォーム -->
			<form action="<c:url value='/SearchServlet' />" method="get"
				class="search-form">
				<input type="text" name="keyword" placeholder="商品名で検索"
					value="${keyword}">
				<button type="submit">検索</button>
			</form>

			<!-- 並び替えフォーム -->
			<form action="${pageContext.request.contextPath}/UserMenuServlet"
				method="get" class="sort-form">
				<label for="sort">並び替え：</label> <select name="sort" id="sort">
					<option value="">選択してください</option>
					<option value="price_asc"
						${param.sort == 'price_asc' ? 'selected' : ''}>価格が安い順</option>
					<option value="price_desc"
						${param.sort == 'price_desc' ? 'selected' : ''}>価格が高い順</option>
					<option value="release_new"
						${param.sort == 'release_new' ? 'selected' : ''}>新着順（発売日）</option>
					<option value="release_old"
						${param.sort == 'release_old' ? 'selected' : ''}>古い順（発売日）</option>
				</select>
				<button type="submit">適用</button>
			</form>

			<!-- 商品リスト -->
			<ul class="product-list">
				<c:forEach var="product" items="${products}">
					<li class="product-item">
						<form
							action="${pageContext.request.contextPath}/views/CartServlet"
							method="post">
							<input type="hidden" name="productId" value="${product.id}" />

							<!-- 画像 -->
							<img src="<c:url value='/img/${product.image}' />"
								alt="${product.name}の画像" />

							<!-- 商品名と価格 -->
							<p class="product-name">${product.name}</p>
							<p class="product-price">¥<fmt:formatNumber value="${product.price}" type="number" groupingUsed="true" /></p>


							<!-- サイズ選択をここに配置 -->
							<label>サイズ： <select name="size" required>
									<option value="">選択してください</option>
									<option value="25.0">25.0</option>
									<option value="25.5">25.5</option>
									<option value="26.0">26.0</option>
									<option value="26.5">26.5</option>
									<option value="27.0">27.0</option>
							</select>
							</label>

							<!-- 数量選択 -->
							<label>数量： <select name="quantity" required>
									<option value="1">1</option>
									<option value="2">2</option>
									<option value="3">3</option>
									<option value="4">4</option>
									<option value="5">5</option>
							</select>
							</label>

							<!-- カートに追加 -->
							<button type="submit" onclick="alert('カートに追加されました。')">
								<i class="fa-solid fa-cart-shopping"></i>カートに追加
							</button>
						</form>
					</li>
				</c:forEach>
			</ul>
		</section>
	</main>
<script src="${pageContext.request.contextPath}/js/userMenu.js"></script>
</body>
</html>
