/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.models;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import org.javalite.common.Collections;

public class DatosContacto implements Serializable {

    public DatosContacto() {

    }

    private String nombre;
    private Integer edad;
    private String email;
    private List<String> motivos;
    private String comentarios;
    private List<String> errores = new LinkedList<>();

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getMotivos() {
        return motivos;
    }

    public void setMotivos(List<String> motivos) {
        this.motivos = motivos;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public List<String> getErrores() {
        return errores;
    }

    public void setErrores(List<String> errores) {
        this.errores = errores;
    }

    public static List<Map> getListaMotivos() {
        List res = Collections.list(
                Collections.map("id", 1, "descripcion", "Consulta"),
                Collections.map("id", 2, "descripcion", "Reclamo"),
                Collections.map("id", 3, "descripcion", "Agradecimiento")
        );
        return res;
    }

    public DatosContacto rellenar(Map<String, String> parametros) {
        setNombre(parametros.get("nombre"));
        setEmail(parametros.get("email"));
        setEdad(Integer.parseInt(parametros.get("edad")));
        //setMotivos(parametros.get("motivos"));
        setComentarios(parametros.get("comentario"));
        return this;
    }

    public void validar(Map<String, String> parametros) throws ValidacionException {

        String patternEmail = "^[a-zA-Z0-9.!#$%&’+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)$";
        String patternNum = "[\\d]+";

        if (parametros.get("nombre").isEmpty()) {
            errores.add("El campo nombre esta vacio");
        } else if (parametros.get("nombre").length() > 15) {
            errores.add("El campo nombre excede los 15 caracteres");
        }

        if (parametros.get("email").isEmpty()) {
            errores.add("El campo Email esta vacio");
        } else if (!Pattern.matches(patternEmail, parametros.get("email"))) {
            errores.add("El campo Email no es valido");
        }

        if (parametros.get("edad").isEmpty()) {
            errores.add("El campo edad esta vacio");
        } else if (!Pattern.matches(patternNum, parametros.get("edad"))) {
            errores.add("El campo edad no es numero");
        } else if (Integer.parseInt(parametros.get("edad")) > 100) {
            errores.add("Ingrese una edad menor que 100");
        }

//        if (parametros.get("motivos").isEmpty()) {
//            errores.add("Ingrese motivos validos");
//        }

        if (parametros.get("comentario").isEmpty()) {
            errores.add("Ingrese comentarios validos");
        } else if (parametros.get("comentario").length() > 250) {
            errores.add("Ingrese comentarios menos que 250 caracteres");
        }

        if (!errores.isEmpty()) {
            throw new ValidacionException("Errores");
        }
    }
}
