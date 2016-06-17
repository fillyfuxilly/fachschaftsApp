package fachschaftwirtschaft.fachschaftapp;



import org.ksoap2.HeaderProperty;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.MarshalDate;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Vector;

import webService.Appointment;


/**
 * @author Matthias Heinen
 */
public class ErstiHelferClient {

    /**
     *  Feature-Toggle, da der Web Service nicht verfügbar ist.
     */
    private static final boolean WEBSERVICEISAVAILABLE = false;
    private static final String NAMESPACE = "http://erstihelfer.erstihelfer.de/";
    private static final String URL = "http://10.0.2.2:8080/erstihelfer/ErstiHelferOnlineIntegration";

    /**
     * Speichert einen neuen Termin beim Web Service
     * @param appointment Ein fertiger Termin wird der Methode übergeben
     * @return String, der per Toast ausgegeben wird, um den Nutzer über Erfolg oder Misserfolg zu informieren
     */
    public static String createAppointment(Appointment appointment) {

        if(WEBSERVICEISAVAILABLE) {
            String METHOD_NAME = "createAppointment";
            SoapObject response = null;
            String back = "Serverfehler";

            try {
                response = executeSoapAction(METHOD_NAME, appointment.getTitle(), appointment.getLocation(), appointment.getDate().getTime(),
                        appointment.getDescription(), appointment.getGroupNr());


            } catch (SoapFault e) {
                e.printStackTrace();
            }
            try {
                back = response.getPrimitivePropertyAsString("text");

            } catch (NullPointerException e) {
                e.printStackTrace();
            }
            return back;
        } else {

            return "Erfolgreich erstellt";
        }
    }

    /**
     * Holt einen Termine Vector vom Web Service, basierend auf der eingegeben Gruppennummer
     * @param groupNr Gruppennummer der gewünschten Termine
     * @return Gibt ein Array aus Terminen zurück
     */
    public static Appointment[] getAppointments(int count, int groupNr) {

        if(WEBSERVICEISAVAILABLE) {
            String METHOD_NAME = "getAppointments";
            Vector<SoapObject> response = null;
            Appointment[] appointments  = new Appointment[5];

            /*try {
                 response = executeSoapActionVector(METHOD_NAME, count, groupNr);

                appointments = new Appointment[response.size()];

                for (int i = 0; i < response.size(); i++) {
                    Date date = new Date();

                    String description = response.get(i).getPrimitivePropertyAsString("description");
                    String location = response.get(i).getPrimitivePropertyAsString("location");
                    String title = response.get(i).getPrimitivePropertyAsString("title");

                    try {
                        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
                        date = df.parse(response.get(i).getPrimitivePropertyAsString("startTime"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    GregorianCalendar gc = new GregorianCalendar();
                    gc.setTime(date);
                    int group = Integer.parseInt(response.get(i).getPrimitivePropertyAsString("groupNr"));

                    appointments[i] = new Appointment(title, location, gc, description, group);
                }*/

                appointments[0] = new Appointment("Gastvortrag Dr.Acula", "Raum D227", null, "Zur Mittagsstunde gibt es einen spannenden Vortrag von Herrn Dr.Acula zu den ökonomischen Auswirkungen der Blutspende", 1);
                appointments[1] = new Appointment("Aaseerallye", "Torminbrücke", null, "Wie ihr zur Torminbrücke kommt, könnt ihr in den Infos nachlesen", 1);
                appointments[2] = new Appointment("Kulturprogramm", "Überwasserkirche", null, "Wir treffen uns vor der Überwasserkirche. Es geht dann weiter in kleinen Gruppen", 1);
                appointments[3] = new Appointment("Frühstück", "Raum A004", null, "Kaffee und Brötchen zum Aufwachen und warm werden", 1);
                appointments[4] = new Appointment("Rundgang Bib und Erstitaschen", "Vor Raum A004", null, "Eure Gruppenleiter zeigen euch kurz ein paar wichtige Ecken des FH-Gebäudes und die Bib", 1);

            /* } catch (SoapFault e) {
                e.printStackTrace();
                appointments = null;
            } */
            return appointments;
        } else {

            Appointment[] appointments = new Appointment[5];

            /*  Hier musst du erst 5 appointment Objekte erstellen, diese dann zum Array hinzufügen
                und dann kannst du mit, wenn du die Methode in der AppointmentActivity aufrust
            */

            return appointments;

        }
    }

    /**
     * Registriert einen neuen Nutzer beim Web Service
     * @param userName Name des Nutzers
     * @param groupNr Gruppennummer des Nutzers
     * @return String, der per Toast ausgegeben wird, um den Nutzer über Erfolg oder Misserfolg zu informieren
     */
    public static String registerNewUser(String userName, int groupNr) {
        if(WEBSERVICEISAVAILABLE) {
            String METHOD_NAME = "registerNewUser";
            SoapObject response = null;
            String back = "Serverfehler";

            try {
                response = executeSoapAction(METHOD_NAME, userName, groupNr);

            } catch (SoapFault e) {
                e.printStackTrace();
            }
            try {
                back = response.getPrimitivePropertyAsString("text");

            } catch (NullPointerException e) {
                e.printStackTrace();
            }
            return back;
        } else {

            return "Erfolgreich registriert";
        }
    }

    public static String changeGroup(String userName, int groupNr) {

        if(WEBSERVICEISAVAILABLE) {
            String METHOD_NAME = "changeGroup";
            SoapObject response = null;
            String back = "Serverfehler";

            try {
                response = executeSoapAction(METHOD_NAME, userName, groupNr);

            } catch (SoapFault e) {
                e.printStackTrace();
            }
            try {
                back = response.getPrimitivePropertyAsString("text");

            } catch (NullPointerException e) {
                e.printStackTrace();
            }
            return back;
        } else {

            return "Gruppe erfolgreich gewechselt";
        }
    }

    public static boolean isAdmin(String userName) {

        if(WEBSERVICEISAVAILABLE) {
            String METHOD_NAME = "changeGroup";
            SoapPrimitive response = null;

            try {
                response = executeSoapActionPrimitive(METHOD_NAME, userName);

            } catch (SoapFault e) {
                e.printStackTrace();
            }
            try {
                return Boolean.parseBoolean(response.toString());

            } catch (NullPointerException e) {
                e.printStackTrace();
            }
            return false;
        } else {

            return true;
        }
    }



    /**
     * Mit dieser Methode kann man Methoden des Web Service mit komplexen Rückgabeparametern nutzen.
     * @param methodName
     * @return SoapObject
     */
    private static SoapObject executeSoapAction(String methodName, Object... args) throws SoapFault {

        Object result = null;

	    /* Create a org.ksoap2.serialization.SoapObject object to build a SOAP request. Specify the namespace of the SOAP object and method
	     * name to be invoked in the SoapObject constructor.
	     */
        SoapObject request = new SoapObject(NAMESPACE, methodName);

	    /* The array of arguments is copied into properties of the SOAP request using the addProperty method. */
        for (int i=0; i<args.length; i++) {
            request.addProperty("arg" + i, args[i]);
        }

	    /* Next create a SOAP envelop. Use the SoapSerializationEnvelope class, which extends the SoapEnvelop class, with support for SOAP
	     * Serialization format, which represents the structure of a SOAP serialized message. The main advantage of SOAP serialization is portability.
	     * The constant SoapEnvelope.VER11 indicates SOAP Version 1.1, which is default for a JAX-WS webservice endpoint under JBoss.
	     */
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

	    /* Assign the SoapObject request object to the envelop as the outbound message for the SOAP method call. */
        envelope.setOutputSoapObject(request);

	    /* Create a org.ksoap2.transport.HttpTransportSE object that represents a J2SE based HttpTransport layer. HttpTransportSE extends
	     * the org.ksoap2.transport.Transport class, which encapsulates the serialization and deserialization of SOAP messages.
	     */
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

        try {
	        /* Make the soap call using the SOAP_ACTION and the soap envelop. */
            List<HeaderProperty> reqHeaders = null;

            new MarshalDate().register(envelope);
            @SuppressWarnings({"unused", "unchecked"})
            List<HeaderProperty> respHeaders = androidHttpTransport.call("", envelope , reqHeaders);

	        /* Get the web service response using the getResponse method of the SoapSerializationEnvelope object.
	         * The result has to be cast to SoapPrimitive, the class used to encapsulate primitive types, or to SoapObject.
	         */
            result = envelope.getResponse();

            if (result instanceof SoapFault) {
                throw (SoapFault) result;
            }
        }
        catch (SoapFault e) {
            e.printStackTrace();
            throw e;
        }
        catch (Exception e) {
            e.printStackTrace();
        }


        return (SoapObject) result;
    }


    /**
     * Mit dieser Methode kann man Methoden des Web Service mit primitiven Rückgabeparametern nutzen.
     * @param methodName
     * @return SoapPrimitive
     */
    private static SoapPrimitive executeSoapActionPrimitive(String methodName, Object... args) throws SoapFault {

        Object result = null;

	    /* Create a org.ksoap2.serialization.SoapObject object to build a SOAP request. Specify the namespace of the SOAP object and method
	     * name to be invoked in the SoapObject constructor.
	     */
        SoapObject request = new SoapObject(NAMESPACE, methodName);

	    /* The array of arguments is copied into properties of the SOAP request using the addProperty method. */
        for (int i=0; i<args.length; i++) {
            request.addProperty("arg" + i, args[i]);
        }

	    /* Next create a SOAP envelop. Use the SoapSerializationEnvelope class, which extends the SoapEnvelop class, with support for SOAP
	     * Serialization format, which represents the structure of a SOAP serialized message. The main advantage of SOAP serialization is portability.
	     * The constant SoapEnvelope.VER11 indicates SOAP Version 1.1, which is default for a JAX-WS webservice endpoint under JBoss.
	     */
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

	    /* Assign the SoapObject request object to the envelop as the outbound message for the SOAP method call. */
        envelope.setOutputSoapObject(request);

	    /* Create a org.ksoap2.transport.HttpTransportSE object that represents a J2SE based HttpTransport layer. HttpTransportSE extends
	     * the org.ksoap2.transport.Transport class, which encapsulates the serialization and deserialization of SOAP messages.
	     */
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

        try {
	        /* Make the soap call using the SOAP_ACTION and the soap envelop. */
            List<HeaderProperty> reqHeaders = null;

            new MarshalDate().register(envelope);
            @SuppressWarnings({"unused", "unchecked"})
            List<HeaderProperty> respHeaders = androidHttpTransport.call("", envelope , reqHeaders);

	        /* Get the web service response using the getResponse method of the SoapSerializationEnvelope object.
	         * The result has to be cast to SoapPrimitive, the class used to encapsulate primitive types, or to SoapObject.
	         */
            result = envelope.getResponse();

            if (result instanceof SoapFault) {
                throw (SoapFault) result;
            }
        }
        catch (SoapFault e) {
            e.printStackTrace();
            throw e;
        }
        catch (Exception e) {
            e.printStackTrace();
        }


        return (SoapPrimitive) result;
    }

    /**
     * Mit dieser Methode kann man Methoden des Web Service mit Vector Rückgabeparametern nutzen.
     * @param methodName
     * @return Vector<SoapObject>
     */
    private static Vector<SoapObject> executeSoapActionVector(String methodName, Object... args) throws SoapFault {

        Object result = null;

	    /* Create a org.ksoap2.serialization.SoapObject object to build a SOAP request. Specify the namespace of the SOAP object and method
	     * name to be invoked in the SoapObject constructor.
	     */
        SoapObject request = new SoapObject(NAMESPACE, methodName);

	    /* The array of arguments is copied into properties of the SOAP request using the addProperty method. */
        for (int i=0; i<args.length; i++) {
            request.addProperty("arg" + i, args[i]);
        }

	    /* Next create a SOAP envelop. Use the SoapSerializationEnvelope class, which extends the SoapEnvelop class, with support for SOAP
	     * Serialization format, which represents the structure of a SOAP serialized message. The main advantage of SOAP serialization is portability.
	     * The constant SoapEnvelope.VER11 indicates SOAP Version 1.1, which is default for a JAX-WS webservice endpoint under JBoss.
	     */
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

	    /* Assign the SoapObject request object to the envelop as the outbound message for the SOAP method call. */
        envelope.setOutputSoapObject(request);

	    /* Create a org.ksoap2.transport.HttpTransportSE object that represents a J2SE based HttpTransport layer. HttpTransportSE extends
	     * the org.ksoap2.transport.Transport class, which encapsulates the serialization and deserialization of SOAP messages.
	     */
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

        try {
	        /* Make the soap call using the SOAP_ACTION and the soap envelop. */
            List<HeaderProperty> reqHeaders = null;

            new MarshalDate().register(envelope);

            @SuppressWarnings({"unused", "unchecked"})
            List<HeaderProperty> respHeaders = androidHttpTransport.call("", envelope, reqHeaders);

	        /* Get the web service response using the getResponse method of the SoapSerializationEnvelope object.
	         * The result has to be cast to SoapPrimitive, the class used to encapsulate primitive types, or to SoapObject.
	         */
            result = envelope.getResponse();

            if (result instanceof SoapFault) {
                throw (SoapFault) result;
            }
        }
        catch (SoapFault e) {
            e.printStackTrace();
            throw e;
        }
        catch (Exception e) {
            e.printStackTrace();
        }


        return (Vector<SoapObject>) result;
    }

}


