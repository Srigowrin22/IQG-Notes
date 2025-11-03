<HTML><BODY>

<H1>If Tag Example</H1>
<%@ taglib uri="/WEB-INF/coop.tld" prefix="coop" %>

<coop:if>
	<coop:condition>true</coop:condition>
	<coop:then>Condition was true</coop:then>
	<coop:else>Condition was false</coop:else>
</coop:if>

<P>
	<coop:if>
		<coop:condition><%= request.isSecure() %></coop:condition>
		<coop:then>Request is using SSL (https)</coop:then>
		<coop:else>Request is not using SSL</coop:else>
	</coop:if>

<P>
Some coin tosses:<BR>
	<coop:repeat reps="10">
		<coop:if>
<coop:condition> <%= new java.util.Random( ).nextInt( ) > 15 %> </coop:condition>
			<coop:then><B>Heads</B><BR></coop:then>
			<coop:else><B>Tails</B><BR></coop:else>
		</coop:if>
	</coop:repeat>

</BODY></HTML>