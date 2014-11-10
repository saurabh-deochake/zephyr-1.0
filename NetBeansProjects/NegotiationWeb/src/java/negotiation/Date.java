/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package negotiation;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.Calendar;

/**
 *
 * @author Saurabh
 */
@ManagedBean
@RequestScoped
public class Date {
public String getDate(){
    return Calendar.getInstance().getTime().toString();
}
}
