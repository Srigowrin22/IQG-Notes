<%!
	int numTimes=3;

	public String sayHello(String name)
	{
		return "Hello , " + name + " ! ";
	}
%>

<HTML>
<BODY>
		<h1> Declaration Example </h1>
		<p> The Value of Numtimes is <%= numTimes %>.</p>
		<p> Saying Hello to Everyone : <%= sayHello(" JSP ") %> ".</p>
</BODY>
</HTML>
