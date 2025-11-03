<jsp:useBean id="lbRef" scope="page" class="training.iqgateway.LanguageBean">
	<jsp:setProperty name="lbRef" property="*" />
</jsp:useBean>

<HTML>
<HEAD>
<TITLE> UseBean Action Test </TITLE>
</HEAD>

<BODY>
		<h1>UseBean Action Test Result </h1>
		<p>
			Hello, <jsp:getProperty name="lbRef" property="name" />.</p>

			<p>Your Favorite Language is 
				<jsp:getProperty name="lbRef" property="language"/>.</p>

			<p>	My Comments on Your Language : </P>

			<P> <jsp:getProperty name="lbRef" property="languageComments" />
			</p>

				
</BODY>
</HTML>
