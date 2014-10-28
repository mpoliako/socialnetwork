/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socialnetwork.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tiles.web.util.TilesDispatchServlet;

import socialnetwork.model.command.ICommand;
import socialnetwork.utils.Message;

@WebServlet(name = "Controller", urlPatterns = {"/Controller"})
public class Controller extends TilesDispatchServlet {
	
	private static final String RETURN_PAGE = "returnPage";

    /**
	 * 
	 */
	private static final long serialVersionUID = -5028481789116446869L;
	ControllerHelper controllerHelper = ControllerHelper.getInstance();

    public Controller() {
        super();
    }

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response){
        String page = null;
        try {
            ICommand command = controllerHelper.getCommand(request);
            page = command.execute(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
            request.setAttribute("messageError", Message.getInstance().getProperty(Message.SERVLET_EXECPTION));

        } catch (IOException e) {
            e.printStackTrace();
            request.setAttribute("messageError", Message.getInstance().getProperty(Message.IO_EXCEPTION));

        }
        
        if(page.endsWith(".tiles")) {
        	request.setAttribute(RETURN_PAGE, page);
        	super.doGet(request, response);
        } else {
        	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            try {
				dispatcher.forward(request, response);
			} catch (ServletException | IOException e) {
				throw new RuntimeException(e);
			}       
        }       
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
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
    }
    
    @Override
    protected String getDefinitionName(HttpServletRequest request) {
    	
    	String path = (String) request.getAttribute(RETURN_PAGE);
    	int start = path.startsWith("/") ? 1 : 0;
    	int end = path.indexOf(".tiles");
    	   
    	return path.substring(start, end);  	
    }
}
