
public class CatalogServlet extends HttpServlet {
public void doGet (HttpServletRequest request,
HttpServletResponse response) throws ServletException,
IOException {
// Get the user's session and shopping cart
HttpSession session = request.getSession(true);
ShoppingCart cart = (ShoppingCart)session.getAttribute(
"examples.bookstore.cart");
// If the user has no cart, create a new one
if (cart == null) {
cart = new ShoppingCart();
session.setAttribute("examples.bookstore.cart", cart);
}
//If the user wants to add a book, add it and print the result
String bookToAdd = request.getParameter("Add");
if(bookToAdd != null) {
BookDetails book = database.getBookDetails(bookToAdd);
cart.add(bookToAdd, book);
out.println("<p><h3>" + ...);
}
}