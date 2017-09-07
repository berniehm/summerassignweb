/**
 * This class is the Home of the web app and renders the home page
 * created by Bernadete Murphy
 * @07/08/17
 */
package controllers;

import play.Logger;
import play.mvc.Controller;

public class Home extends Controller
{
    public static void index() {
        Logger.info("Rendering about");
        render ("../views/home.html");
    }
}