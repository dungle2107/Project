/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Brand;
import model.Category;
import model.Product;

/**
 *
 * @author Hoa
 */
@WebServlet(name = "Sort3Servlet", urlPatterns = {"/sort3"})
public class Sort3Servlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Sort3Servlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Sort3Servlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAO d = new DAO();
        List<Product> list = d.getAll();
        List<Product> list1 = new ArrayList();
        String id_raw = request.getParameter("id");
        int id = 0;
        try {
            id = Integer.parseInt(id_raw);
        } catch (NumberFormatException e) {
        }
        switch (id) {
            case 1:
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getPrice() > 500000 && list.get(i).getPrice() < 1500000) {
                        list1.add(list.get(i));
                    }
                }
                break;
            case 2:
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getPrice() > 1500000 && list.get(i).getPrice() < 3000000) {
                        list1.add(list.get(i));
                    }
                }
                break;
            case 3:
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getPrice() > 3000000 && list.get(i).getPrice() < 150000000) {
                        list1.add(list.get(i));
                    }
                }
                break;
            case 4:
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getPrice() > 600000) {
                        list1.add(list.get(i));
                    }
                }
        }

        request.setAttribute("data", list1);
        List<Category> listCate = d.getAllCate();
        request.setAttribute("cate", listCate);
        List<Brand> listBrand = d.getAllBrand();
        request.setAttribute("brand", listBrand);
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
