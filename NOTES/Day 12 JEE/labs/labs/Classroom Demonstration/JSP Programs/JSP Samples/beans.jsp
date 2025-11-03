<jsp:useBean id="languageBean" scope="page" class="com.sun.LanguageBean">
	<jsp:setProperty name="languageBean" property="*" />
</jsp:useBean>

<HTML>
<HEAD>
<TITLE> UseBean Action Test </TITLE>
</HEAD>

<BODY>
		<h1>UseBean Action Test Result </h1>
		<p>
			Hello, <jsp:getProperty name="languageBean" property="name" />.</p>

			<p>Your Favorite Language is 
				<jsp:getProperty name="languageBean" property="language"/>.</p>

			<p>	My Comments on Your Language : </P>

			<P> <jsp:getProperty name="languageBean" property="languageComments" />
			</p>

				
</BODY>
</HTML>
