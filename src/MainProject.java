import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.io.*;
import java.util.*;

@ManagedBean
public class MainProject{

    public String answers() {

        String responseConsole;
        String responseGender;
        String responseAge;

        responseConsole = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("responseConsole");
        responseGender = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("responseGender");
        responseAge = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("responseAge");

        if (responseAge == "" || responseConsole == "" || responseGender == "") {
            responseConsole = "None";
            responseGender = "None";
            responseAge = "None";
        }

        if (responseGender.equals("Muj")) {
            if (Integer.parseInt(responseAge) <= 0 || Integer.parseInt(responseAge) > 140) {
                responseAge = "OoB";
            } else if (Integer.parseInt(responseAge) > 0 && Integer.parseInt(responseAge) <= 5) {
                responseAge = "InfantM";
            } else if (Integer.parseInt(responseAge) > 5 && Integer.parseInt(responseAge) <= 12) {
                responseAge = "ChildM";
            } else if (Integer.parseInt(responseAge) > 12 && Integer.parseInt(responseAge) <= 18) {
                responseAge = "TeenM";
            } else if (Integer.parseInt(responseAge) > 18 && Integer.parseInt(responseAge) <= 30) {
                responseAge = "YAdultM";
            } else if (Integer.parseInt(responseAge) > 30 && Integer.parseInt(responseAge) <= 70) {
                responseAge = "AdultM";
            } else if (Integer.parseInt(responseAge) > 70 && Integer.parseInt(responseAge) <= 140) {
                responseAge = "OldM";
            }
        } else if (responseGender.equals("Jena")) {
            if (Integer.parseInt(responseAge) <= 0 || Integer.parseInt(responseAge) > 140) {
                responseAge = "OoB";
            } else if (Integer.parseInt(responseAge) > 0 && Integer.parseInt(responseAge) <= 5) {
                responseAge = "InfantJ";
            } else if (Integer.parseInt(responseAge) > 5 && Integer.parseInt(responseAge) <= 12) {
                responseAge = "ChildJ";
            } else if (Integer.parseInt(responseAge) > 12 && Integer.parseInt(responseAge) <= 18) {
                responseAge = "TeenJ";
            } else if (Integer.parseInt(responseAge) > 18 && Integer.parseInt(responseAge) <= 30) {
                responseAge = "YAdultJ";
            } else if (Integer.parseInt(responseAge) > 30 && Integer.parseInt(responseAge) <= 70) {
                responseAge = "AdultJ";
            } else if (Integer.parseInt(responseAge) > 70 && Integer.parseInt(responseAge) <= 140) {
                responseAge = "OldJ";
            }
        }

        addResponse(responseConsole);
        addResponse(responseGender);
        addResponse(responseAge);

        return submit();
    }

    private void addResponse(String response) {

            try {
                File configFile = new File("c:/Users/ivan/Desktop/results.txt");
                FileInputStream inStream = new FileInputStream(configFile);
                Properties config = new Properties();
                config.load(inStream);
                int counter = Integer.parseInt(config.getProperty(response));
                counter = counter + 1;
                config.setProperty(response, Integer.toString(counter));
                FileOutputStream outStream = new FileOutputStream(configFile);
                config.store(outStream, "Results");
                inStream.close();
                config.list(System.out);
            } catch (IOException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
    }

    public String submit(){
        return "rezultati.xhtml";
    }

    public String confirm(){
        return "confirm.xhtml";
    }

}
