
public class ReceiptServlet extends HttpServlet {
public void doPost(HttpServletRequest request,
HttpServletResponse response)
throws ServletException, IOException {
...
scart = (ShoppingCart)
session.getAttribute("examples.bookstore.cart");
...
// Clear out shopping cart by invalidating the session
session.invalidate();
// set content type header before accessing the Writer
response.setContentType("text/html");
out = response.getWriter();
...
}