
package app.controllers;

import app.models.DatosContacto;
import app.models.ValidacionException;
import java.util.Map;
import org.javalite.activeweb.AppController;
import org.javalite.activeweb.annotations.POST;
import org.javalite.common.JsonHelper;

/**
 *
 * @author universidad
 */
public class ContactoController extends AppController{
    
    public void index(){
        
    }
    
    
    @POST
    public void procesarDatos(){
        try {
            
            DatosContacto contacto = new DatosContacto();
            Map<String, String> parametros = params1st();
            contacto.validar(parametros);
            contacto.rellenar(parametros);
            respond(JsonHelper.toJsonString(contacto)).contentType("application/json").status(400);  
            
            
        } catch (ValidacionException e) {
            
            view("error", e);
            return;
        } 
        
    }
}
