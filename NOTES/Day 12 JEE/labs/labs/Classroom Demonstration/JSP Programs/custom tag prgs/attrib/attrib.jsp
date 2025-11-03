<HTML>

<BODY bgcolor=pink>
<H1>Example program for Tags with attributes</H1>

<%@ taglib uri="/WEB-INF/Attrib.tld" prefix="attr" %>

<UL>
	<LI>20-digit: <attr:val length="20" />
	<LI>40-digit: <attr:val length="40" />
	<LI>80-digit: <attr:val length="80" />
	<LI>default-digit: <attr:val />
</UL>

</BODY>
</HTML>