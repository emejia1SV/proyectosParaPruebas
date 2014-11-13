
/**
 * ServicioWebSDACallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package sv.avantia.depurador.agregadores.servicio.web;

    /**
     *  ServicioWebSDACallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class ServicioWebSDACallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public ServicioWebSDACallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public ServicioWebSDACallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for ingresoListaNegra method
            * override this method for handling normal response from ingresoListaNegra operation
            */
           public void receiveResultingresoListaNegra(
                    sv.avantia.depurador.agregadores.servicio.web.ServicioWebSDAStub.IngresoListaNegraResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from ingresoListaNegra operation
           */
            public void receiveErroringresoListaNegra(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for depuracionMasiva method
            * override this method for handling normal response from depuracionMasiva operation
            */
           public void receiveResultdepuracionMasiva(
                    sv.avantia.depurador.agregadores.servicio.web.ServicioWebSDAStub.DepuracionMasivaResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from depuracionMasiva operation
           */
            public void receiveErrordepuracionMasiva(java.lang.Exception e) {
            }
                


    }
    